package com.levin.commons.service.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Locker {

    /**
     *
     */
    public static final Locker global = build();

    /**
     * 构建锁
     *
     * @return
     */
    public static final Locker build() {

        return new Locker() {

            final Map locks = new ConcurrentHashMap();

            @Override
            public Object getLock(Object key) {

                Object lock = this;

                synchronized (lock) {

                    lock = locks.get(key);

                    if (lock == null) {
                        locks.put(key, lock = new Object());
                    }

                }

                return lock;
            }
        };
    }


    /**
     * 确保私有构造
     */
    private Locker() {
    }


    public abstract Object getLock(Object key);

}
