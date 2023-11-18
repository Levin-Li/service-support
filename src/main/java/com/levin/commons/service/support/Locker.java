package com.levin.commons.service.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Locker {

    /**
     * 默认的全局锁
     */
    public static final Locker GLOBAL = build();

    /**
     * 构建锁
     *
     * @return
     */
    public static Locker build() {

        return new Locker() {

            private final Map<Object, Object> locks = new ConcurrentHashMap<>();

            @Override
            public Object clearLock(Object key) {
                return locks.remove(key);
            }

            @Override
            public Object getLock(Object key) {
                return locks.computeIfAbsent(key, k -> new Object());
            }
        };
    }

    /**
     * 确保私有构造
     */
    private Locker() {
    }


    /**
     * 清楚锁
     *
     * @param key
     * @return
     */
    public abstract Object clearLock(Object key);

    /**
     * 获取锁
     *
     * @param key
     * @return
     */
    public abstract Object getLock(Object key);

}
