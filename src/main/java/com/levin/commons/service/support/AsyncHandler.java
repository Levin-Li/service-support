package com.levin.commons.service.support;


import com.levin.commons.utils.StatHelper;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * 异步队列处理器
 * <p>
 * 在把任务加入队列时，捎带唤醒工作者
 * <p>
 * 可以设置跳过没有处理的任务，只执行最新的任务，可以用于数据库库存等更新
 *
 * @param <T>
 */
@Slf4j
@Data
@Accessors(chain = true)
public class AsyncHandler<T> {

    @Setter
    protected volatile ScheduledExecutorService defaultScheduler;

    /**
     *
     */
    protected String name;

    /**
     *
     */
    @Setter
    protected boolean isValid = true;

    /**
     * 可以设置所以任务的延迟执行
     */
    protected long taskDelay = 0;

    /**
     * 是否忽略未做的任务
     */
    protected boolean skipNotProcessTask = false;

    /**
     * 看门狗唤醒间隔，单位为毫秒
     */
    protected long watchDogInterval = 5 * 1000;

    /**
     * 看门狗运行标记
     */
    private final AtomicBoolean watchDogRunning = new AtomicBoolean(false);

    /**
     * 自定义 同步任务执行器
     */
    protected Consumer<T> syncTaskExecutor;

    ////////////////////////////////////////////////////////

    private final BlockingQueue<T> taskQueue;

    private final Semaphore workerController;

    protected final StatHelper addTaskStatHelper = new StatHelper();

    protected final StatHelper doTaskStatHelper = new StatHelper();

    public AsyncHandler() {
        this(1, Integer.MAX_VALUE);
    }

    public AsyncHandler(int maxWorkers, int maxTasks) {
        this.taskQueue = new LinkedBlockingQueue(maxTasks);
        this.workerController = new Semaphore(maxWorkers);
    }


    public boolean addTask(T task) {
        try {
            return addTask(null, task);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加入任务
     *
     * @param scheduler
     * @param task
     * @return
     * @throws InterruptedException
     */
    public boolean addTask(ScheduledExecutorService scheduler, T task) throws InterruptedException {

        if (!isValid) {
            //taskQueue.clear();

            throw new IllegalStateException("handler is invalid");
        }

        //  tryInit();

        boolean isOffer = taskQueue.offer(task, 7, TimeUnit.SECONDS);

        if (isOffer) {

            processQueue(false, scheduler);

            addTaskStatHelper.onAlarm(5, 100, 1.0, (scale, ratio) -> {
                log.info("异步处理器[{}]在{}秒内进入的任务速率为{}/秒，环比变化率{} ，目前队列数据包剩余数：{}"
                        , name, 5, ratio, scale, taskQueue.size());
            });

        }

        return isOffer;
    }

    protected void doQueueTask() {

        while (taskQueue.size() > 0
                && isValid
                && !Thread.currentThread().isInterrupted()) {

            T task = null;

            //忽略中间的任务，只做最新的任务
            if (skipNotProcessTask) {

                T nextTask = taskQueue.poll();

                long skipCnt = -1;

                //只取最新的一个元素
                while (nextTask != null) {
                    task = nextTask;
                    nextTask = taskQueue.poll();
                    skipCnt++;
                }

                if (skipCnt > 0) {
                    log.debug("异步处理器[{}] 跳过的任务数：{}", name, skipCnt);
                }

            } else {
                task = taskQueue.poll();
            }

            if (task != null) {

                doTaskStatHelper.onAlarm(skipNotProcessTask ? 20 : 5, 100, 1.0, (scale, ratio) -> {
                    log.info("异步处理器[{}]在{}秒内完成的任务速率为{}/秒，环比变化率{} ，目前队列数据包剩余数：{}"
                            , name, 5, ratio, scale, taskQueue.size());
                });

                //同步处理任务
                if (syncTaskExecutor != null) {
                    syncTaskExecutor.accept(task);
                } else {
                    //同步处理任务
                    syncDoTask(task);
                }

            }

        }
    }

    protected void syncDoTask(T task) {
        if (task instanceof Callable) {
            try {
                ((Callable) task).call();
            } catch (Exception e) {
                log.error("异步处理器[{}]处理任务异常", name, e);
            }
        } else if (task instanceof Runnable) {
            ((Runnable) task).run();
        } else {
            throw new IllegalArgumentException("task type " + task.getClass() + " not supported");
        }
    }


    protected void processQueue(boolean isWatchDog, ScheduledExecutorService scheduler) {

        //如果已经有人在处理，直接返回
        if (!workerController.tryAcquire()) {
            return;
        }

        if (isWatchDog) {
            log.debug("WatchDog 尝试唤醒任务处理者...");
        }

        try {

            if (scheduler == null) {
                scheduler = this.defaultScheduler;
            }

            if (taskDelay > 0) {
                scheduler.schedule(worker, taskDelay, TimeUnit.MILLISECONDS);
            } else {
                scheduler.submit(worker);
            }

            if (!isWatchDog) {
                tryWakeWatchDog(scheduler);
            }

        } finally {
            //释放
            workerController.release();
        }

    }

    void tryWakeWatchDog(ScheduledExecutorService scheduler) {

        if (watchDogInterval < 1) {
            return;
        }

        if (watchDogRunning.compareAndSet(false, true)) {

            log.debug("设置WatchDog 延迟[ {} ]ms执行.", watchDogInterval);

            scheduler.schedule(() -> {
                try {
                    processQueue(true, scheduler);
                } finally {
                    watchDogRunning.set(false);
                }
            }, taskDelay + watchDogInterval, TimeUnit.MILLISECONDS);

        }

    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "-" + name;
    }

    private final Runnable worker = () -> doQueueTask();

}
