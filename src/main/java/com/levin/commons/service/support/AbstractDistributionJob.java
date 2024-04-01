package com.levin.commons.service.support;


import com.levin.commons.utils.RedissonLockUtils;
import com.levin.commons.utils.StatHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import static com.levin.commons.utils.CpuUtils.sleepIfCpuLoadOverThreshold;


@Schema(title = "分布式定时任务")
@Slf4j
public abstract class AbstractDistributionJob<T> {

    private final AtomicBoolean running = new AtomicBoolean(false);

    @Resource
    @Setter
    RedissonClient redissonClient;

    /**
     * 是否单机模式，默认为分布式模式
     */
    @Getter
    @Setter
    private boolean standalone = false;

    @Getter
    @Setter
    private boolean pause = false;


    private final AtomicLong counter = new AtomicLong(0);

    /**
     * 统计辅助
     */
    protected final StatHelper statHelper = new StatHelper();


    @PostConstruct
    public void init() {
        log.info("分布式定时任务[ {} ]初始化完成.", getName());
    }

    /**
     * 执行任务
     *
     * @param timeoutMs 单次任务执行超时时间
     * @param runOnce   是否只执行一次
     * @param batchSize 数据批量大小
     */
    protected void doTask(int timeoutMs, boolean runOnce, int batchSize) {

        if (pause) {
            return;
        }
        //本地不可重入
        if (running.compareAndSet(false, true)) {
            try {
                if (standalone) {
                    batchProcess(timeoutMs, runOnce, batchSize);
                } else {
                    tryLockAndDoTask(getJobLockKey(), () -> batchProcess(timeoutMs, runOnce, batchSize));
                }
            } finally {
                running.set(false);
            }
        }

    }

    /**
     * 尝试锁定并执行任务
     *
     * @param lockKey 如果参数值为空，则认为不需要锁定
     * @param tasks   任务
     * @return
     */
    protected boolean tryLockAndDoTask(String lockKey, Runnable... tasks) {

        Assert.notEmpty(tasks, "not tasks to do");

        if (StringUtils.hasText(lockKey)) {
            return RedissonLockUtils.tryLockAndDoTask(redissonClient.getLock(lockKey), tasks);
        } else {
            for (Runnable task : tasks) {
                task.run();
            }
            return true;
        }
    }

    /**
     * @return
     */
    protected String getName() {
        return getClass().getName() + ":" + getJobLockKey();
    }

    /**
     * 获取执行器
     * <p>
     * 如果返回 null 则同步执行
     *
     * @return
     */
    protected Executor getExecutor() {
        return null;
    }

    /**
     * 获取任务同步锁，如果返回null表示不锁
     *
     * @return
     */
    protected String getJobLockKey() {
        return getClass().getName();
    }

    /**
     * 获取记录同步锁，如果返回null表示不锁
     *
     * @param record
     * @return
     */
    protected String getDataLockKey(T record) {
        return null;
    }

    /**
     * 批量获取数据
     *
     * @param batchNo   批次号，从1开始
     * @param batchSize 批大小
     * @return
     */
    abstract protected List<T> getBatchData(long startTime, int batchNo, int batchSize);

    /**
     * 单条数据处理发生错误时，是否终止任务执行
     *
     * @return
     */
    protected boolean isTerminateOnException() {
        return false;
    }

    /**
     * 处理单条数据
     * 返回 false 则终止任务执行
     *
     * @param data
     * @return
     */
    abstract protected boolean processData(T data);

    /**
     * 是否统计速率
     *
     * @return
     */
    protected boolean isStatRatio() {
        return true;
    }


    /**
     * 每条记录处理完成后的睡眠时间
     * 用于防止过快处理
     * <p>
     * 如果返回小余0的数，则不睡眠
     *
     * @return
     */
    protected long getSleepByPerRecord() {
        return 1;
    }

    /**
     * 定时任务处理时间,超过最大CPU占用率时，将会被主动降低处理速度
     *
     * @return
     */
    protected int getMaxCpuRatio() {
        return 85;
    }

    /**
     * 没批次处理完成后的睡眠时间
     * <p>
     * 防止资源过度使用
     *
     * @return
     */
    protected long getSleepByPerBatch() {
        return 1;
    }

    /**
     * 获取单条数据的描述信息
     *
     * @param data
     * @return
     */
    protected String getDataDesc(T data) {
        return data.toString();
    }

    /**
     * 按指定的批大小执行任务
     *
     * @param timeoutMs 处理超时时间
     * @param isRunOnce
     * @param batchSize
     */
    protected void batchProcess(long timeoutMs, boolean isRunOnce, int batchSize) {

        log.info("[ {} ] 开始第[ {} ]次执行批任务...", getName(), counter.incrementAndGet());

        final long startTime = System.currentTimeMillis();

        List<T> dataList = null; //Collections.emptyList();

        int batchNo = 0;

        do {

            dataList = getBatchData(startTime, ++batchNo, batchSize);

            if (dataList == null) {
                break;
            }

            final AtomicBoolean isStop = new AtomicBoolean(false);

            for (T data : dataList) {

                if (isStop.get()) {
                    log.error("[ {} ] 第[ {} ]次批任务终止执行", getName(), counter.get());
                    return;
                }

                if (isStatRatio()) {
                    statHelper.onAlarm(30, 15, 0.5, (growthRatio, ratio) -> {
                        log.info("[ {} ] 第[ {} ]次批任务执行 速率: {}, 同比上一个采样周期变化率: {}", getName(), counter.get(), ratio, growthRatio);
                    });
                }

                //使用执行器
                Optional.ofNullable(getExecutor())
                        .orElse(Runnable::run)
                        .execute(() ->
                                        //尝试锁定记录，并且处理单条记录
                                {
                                    boolean hasLock = tryLockAndDoTask(getDataLockKey(data), () -> {
                                        try {
                                            //执行
                                            isStop.set(!processData(data));

                                            if (isStop.get()) {
                                                log.info("[ {} ] 第[ {} ] 单条数据<<<{}>>> 后续主动终止任务执行。", getName(), counter.get(), getDataDesc(data));
                                            }
                                        } catch (Exception e) {
                                            isStop.set(isTerminateOnException());
                                            log.error(getName() + "处理单条数据<<<" + getDataDesc(data) + ">>>时发生异常" + e.getMessage(), e);
                                        }

                                        sleepIfCpuLoadOverThreshold(getMaxCpuRatio(), getSleepByPerRecord());
                                    });

                                    if (!hasLock
                                            && log.isDebugEnabled()) {
                                        log.debug("[ {} ] 第[ {} ] 单条数据<<<{}>>>无法获取锁，忽略处理。", getName(), counter.get(), getDataDesc(data));
                                    }
                                }
                        );

                try {
                    //防止过快处理，占满CPU
                    long sleep = getSleepByPerRecord();

                    if (sleep > 0) {
                        Thread.sleep(sleep);
                    }
                } catch (InterruptedException e) {
                    //如果被中断，退出循环
                    break;
                }

                //如果已经超时，退出循环
                if ((System.currentTimeMillis() - startTime) > timeoutMs) {
                    log.warn("*** [ {} ] 第[ {} ]次批任务执行超时，退出执行", getName(), counter.get());
                    return;
                }
            }

            try {
                //防止过快处理，占满CPU
                long sleep = getSleepByPerBatch();

                if (sleep > 0) {
                    Thread.sleep(sleep);
                }
            } catch (InterruptedException e) {
                //如果被中断，退出循环
                break;
            }


        } while (!isRunOnce && dataList.size() >= 1);

        log.info("[ {} ] 第[ {} ]次批任务执行完成", getName(), counter.get());
    }

}
