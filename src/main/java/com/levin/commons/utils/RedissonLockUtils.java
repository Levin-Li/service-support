package com.levin.commons.utils;

import lombok.SneakyThrows;
import org.redisson.api.RLock;

import java.util.Objects;
import java.util.stream.Stream;


/**
 * @author llw
 */
public abstract class RedissonLockUtils {

    /**
     * 尝试锁定并执行任务
     *
     * @param lock
     * @param tasks
     * @return boolean true 表示获锁并执行任务
     */
    @SneakyThrows
    public static boolean tryLockAndDoTask(final RLock lock, Runnable... tasks) {

        //Redisson 自动续租看门狗机制
//        lockWatchdogTimeout（监控锁的看门狗超时，单位：毫秒）
//        默认值：30000
//        监控锁的看门狗超时时间单位为毫秒。该参数只适用于分布式锁的加锁请求中未明确使用leaseTimeout参数的情况。

        //分布式锁，不设定租借时间，由看门狗超机制自动续租
        if (!lock.isHeldByCurrentThread()
                && !lock.tryLock()) {
            return false;
        }

        try {
            if (tasks != null) {
                Stream.of(tasks)
                        .filter(Objects::nonNull)
                        .forEachOrdered(Runnable::run);
            }
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

}
