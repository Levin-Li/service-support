package com.levin.commons.service;

import java.util.concurrent.Callable;

public interface CacheService {

    interface ICache {
        /**
         * 放入
         *
         * @param key
         * @param value
         */
        void put(String key, Object value);

        /**
         * 获取
         *
         * @param key
         * @param <T>
         * @return
         */
        <T> T get(String key);

        /**
         * 默认从缓存加载，如果不存在则从loader中加载
         * 同时，如果加载不为空，则放入缓存
         *
         * @param key
         * @param valueLoader
         * @param <T>
         * @return
         */
        <T> T get(String key, Callable<T> valueLoader);

        /**
         * 删除
         *
         * @param key
         */
        void evict(String key);

        /**
         * 清空
         */
        void clear();
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @return
     */
    ICache getCache(String cacheName);

    /**
     * 放入
     *
     * @param cacheName
     * @param key
     * @param value
     */
    void put(String cacheName, String key, Object value);

    /**
     * 获取
     *
     * @param cacheName
     * @param key
     * @return
     */
    <T> T get(String cacheName, String key);

    /**
     * 删除
     *
     * @param cacheName
     * @param key
     */
    void evict(String cacheName, String key);

    /**
     * 清空
     *
     * @param cacheName
     */
    void clear(String cacheName);

}
