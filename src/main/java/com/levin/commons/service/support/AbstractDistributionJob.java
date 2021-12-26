package com.levin.commons.service.support;


import com.levin.commons.utils.RedissonLockUtils;
import com.levin.commons.utils.StatHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
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
     * 单机模式，部署分布式模式
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
        log.info("分布式定时任务 " + getClass().getName() + " 初始化完成.");
    }

    /**
     * 执行任务
     *
     * @param timeoutMs 单次任务执行时间
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
                    RedissonLockUtils.tryLockAndDoTask(redissonClient.getLock(getLockKey())
                            , () -> batchProcess(timeoutMs, runOnce, batchSize));
                }
            } finally {
                running.set(false);
            }
        }

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
     * 获取任务同步锁
     *
     * @return
     */
    protected String getLockKey() {
        return getClass().getName();
    }


    /**
     * @param timeoutMs
     * @param isRunOnce
     * @param batchSize
     */
    protected void batchProcess(long timeoutMs, boolean isRunOnce, int batchSize) {

        log.debug(counter.incrementAndGet() + " 开始执行批任务...");

        final long startTime = System.currentTimeMillis();

        List<T> dataList = null; //Collections.emptyList();

        int pageIndex = 0;

        do {

            dataList = getBatchData(++pageIndex, batchSize);

            if (dataList == null) {
                break;
            }

            dataList.parallelStream().forEachOrdered(this::processData);

            //如果已经超时，退出循环
            if ((System.currentTimeMillis() - startTime) > timeoutMs) {
                break;
            }

            try {
                //防止过快处理
                Thread.sleep(300);
            } catch (InterruptedException e) {
                //如果被中断，退出循环
                break;
            }

        } while (!isRunOnce && dataList.size() >= 1);

        log.debug(counter.incrementAndGet() + " 批任务执行完成");

    }


}
