package com.levin.commons.utils;

import lombok.SneakyThrows;
import org.redisson.api.RLock;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


/**
 * @author llw
 */
public abstract class RedissonLockUtils {


    /**
     * 尝试获取锁，并且执行任务
     *
     * @param lock
     * @param maxTimeout
     * @param tasks
     * @return
     */
    @SneakyThrows
    public static boolean tryLockAndDoTask(RLock lock, long maxTimeout, Runnable... tasks) {

        if (tasks == null
                || tasks.length < 1
                || Stream.of(tasks).noneMatch(Objects::nonNull)) {
            return false;
        }

        //Redisson 自动续租看门狗机制
//        lockWatchdogTimeout（监控锁的看门狗超时，单位：毫秒）
//        默认值：30000
//        监控锁的看门狗超时时间单位为毫秒。该参数只适用于分布式锁的加锁请求中未明确使用leaseTimeout参数的情况。

        //分布式锁，不设定租借时间，由看门狗超机制自动续租

        if (maxTimeout > 0) {

            while (maxTimeout > 0
                    && !lock.isHeldByCurrentThread()
                    && !Thread.currentThread().isInterrupted()
                    && !lock.tryLock(1, TimeUnit.MILLISECONDS)) {
                //减少等待时间，每次等待1毫秒
                maxTimeout--;
            }

        } else {
            //尝试获取锁
            if (!lock.tryLock()) {
                return false;
            }
        }

        //如果还是没有获取到锁
        if (!lock.isHeldByCurrentThread()) {
            return false;
        }

        try {
            Stream.of(tasks)
                    .filter(Objects::nonNull)
                    .forEachOrdered(Runnable::run);
        } finally {
            //  为了安全，会先校验是否持有锁再释放，防止
            //  业务执行还没执行完，锁到期了。（此时没占用锁，再unlock就会报错）
            //  主线程异常退出、或者假死
            if (lock.isLocked()
                    && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

        return true;
    }

    /**
     * 尝试锁定并执行任务
     * <p>
     * 如果没有得到锁则立刻返回，放弃执行任务
     *
     * @param lock
     * @param tasks
     * @return boolean true 表示获锁并执行任务
     */
    @SneakyThrows
    public static boolean tryLockAndDoTask(final RLock lock, Runnable... tasks) {
        return tryLockAndDoTask(lock, 0, tasks);
    }

}
