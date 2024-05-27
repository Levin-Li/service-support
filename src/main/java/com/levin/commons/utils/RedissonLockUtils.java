package com.levin.commons.utils;

import lombok.SneakyThrows;
import org.redisson.api.RLock;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


/**
 * @author llw
 */
public abstract class RedissonLockUtils {

    /**
     * 锁定并执行任务, 如果任务没有执行，返回 null
     *
     * @param lock
     * @param maxTimeout
     * @param task
     * @param <R>
     * @return 如果没有执行，返回 null
     * @throws Exception
     */
    public static <R> R tryLockAndDoTask(RLock lock, long maxTimeout, Callable<R> task) throws Exception {

        if (task == null) {
            return null;
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
                return null;
            }
        }

        //如果还是没有获取到锁
        if (!lock.isHeldByCurrentThread()) {
            return null;
        }

        try {
            return task.call();
        } finally {
            //  为了安全，会先校验是否持有锁再释放，防止
            //  业务执行还没执行完，锁到期了。（此时没占用锁，再unlock就会报错）
            //  主线程异常退出、或者假死
            if (lock.isLocked()
                    && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    /**
     * 尝试锁定并执行任务
     * <p>
     * 如果没有得到锁则立刻返回，放弃执行任务
     *
     * @param lock
     * @param task
     * @return boolean true 表示获锁并执行任务
     */
    @SneakyThrows
    public static boolean tryLockAndDoTask(final RLock lock, Runnable task) {
        return tryLockAndDoTask(lock, 0, () -> {
            task.run();
            return true;
        }) != null;
    }

}
