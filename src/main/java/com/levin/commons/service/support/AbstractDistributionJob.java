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


@Schema(description = "分布式定时任务")
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
        log.info("分布式定时任务[ {} ]初始化完成.", getClass().getName());
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
     * @param pageIndex
     * @param pageSize
     * @return
     */
    abstract protected List<T> getBatchData(int pageIndex, int pageSize);

    /**
     * 处理单条数据
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
     * @param timeoutMs
     * @param isRunOnce
     * @param batchSize
     */
    protected void batchProcess(long timeoutMs, boolean isRunOnce, int batchSize) {

        log.info("[ {} ] 开始第[ {} ]次执行批任务...", getJobLockKey(), counter.incrementAndGet());

        final long startTime = System.currentTimeMillis();

        List<T> dataList = null; //Collections.emptyList();

        int pageIndex = 0;

        do {

            dataList = getBatchData(++pageIndex, batchSize);

            if (dataList == null) {
                break;
            }

            for (T data : dataList) {

                if (isStatRatio()) {
                    statHelper.onAlarm(30, 5, 0.5, (growthRatio, ratio) -> {
                        log.info("[ {} ] 第[ {} ]次批任务执行 速率: {}, 同比上一个采样周期变化率: {}", getJobLockKey(), counter.get(), ratio, growthRatio);
                    });
                }

                //使用执行器
                Optional.ofNullable(getExecutor())
                        .orElse(Runnable::run)
                        .execute(() ->
                                //尝试锁定记录，并且处理单条记录
                                tryLockAndDoTask(getDataLockKey(data), () -> processData(data))
                        );

                try {
                    //防止过快处理，占满CPU
                    long sleepByPerRecord = getSleepByPerRecord();

                    if (sleepByPerRecord > 0) {
                        Thread.sleep(sleepByPerRecord);
                    }

                } catch (InterruptedException e) {
                    //如果被中断，退出循环
                    break;
                }

                //如果已经超时，退出循环
                if ((System.currentTimeMillis() - startTime) > timeoutMs) {
                    log.warn("*** [ {} ] 第[ {} ]次批任务执行超时，退出执行", getJobLockKey(), counter.get());
                    return;
                }
            }

            try {
                //防止过快处理，占满CPU
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //如果被中断，退出循环
                break;
            }

        } while (!isRunOnce && dataList.size() >= 1);

        log.info("[ {} ] 第[ {} ]次批任务执行完成", getJobLockKey(), counter.get());
    }

}
