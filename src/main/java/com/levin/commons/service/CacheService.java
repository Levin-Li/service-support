package com.levin.commons.service;

public interface CacheService {

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
