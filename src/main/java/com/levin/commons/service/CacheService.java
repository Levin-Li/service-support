package com.levin.commons.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.concurrent.Callable;

@Tag(name = "缓存服务", description = "缓存按 cacheName 与 key 隔离。带加载器的读取仅在未命中时调用加载器，并只缓存非 null 结果；evict 和 clear 分别删除单个键和整个缓存。")
public interface CacheService {

    interface ICache {
        /**
         * 放入
         *
         * @param key
         * @param value
         */
        @Operation(summary = "写入缓存项", description = "以 key 覆盖当前缓存项；value 的序列化和 null 处理由具体缓存实现决定。")
        void put(String key, Object value);

        /**
         * 获取
         *
         * @param key
         * @param <T>
         * @return
         */
        @Operation(summary = "读取缓存项", description = "仅读取指定 key；未命中返回值由具体缓存实现决定，不会触发加载器。")
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
        @Operation(summary = "读取或加载缓存项", description = "优先返回命中值；仅未命中时调用 valueLoader，加载结果非 null 才写入缓存。")
        <T> T get(String key, Callable<T> valueLoader);

        /**
         * 删除
         *
         * @param key
         */
        @Operation(summary = "删除缓存项", description = "只删除指定 key，不影响同一缓存中的其他键。")
        void evict(String key);

        /**
         * 清空
         */
        @Operation(summary = "清空缓存", description = "删除当前缓存中的全部键，不影响其他 cacheName。")
        void clear();
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @return
     */
    @Operation(summary = "获取命名缓存", description = "返回由 cacheName 隔离的缓存视图；不同名称之间的键和值不共享。")
    ICache getCache(String cacheName);

    /**
     * 放入
     *
     * @param cacheName
     * @param key
     * @param value
     */
    @Operation(summary = "写入命名缓存", description = "写入指定 cacheName 和 key；相同命名空间中的已有值会被覆盖。")
    void put(String cacheName, String key, Object value);

    /**
     * 获取
     *
     * @param cacheName
     * @param key
     * @return
     */
    @Operation(summary = "读取命名缓存", description = "仅从指定 cacheName 和 key 读取，不触发回退加载。")
    <T> T get(String cacheName, String key);

    /**
     * 删除
     *
     * @param cacheName
     * @param key
     */
    @Operation(summary = "删除命名缓存项", description = "删除指定 cacheName 下的单个 key，不清空其他键。")
    void evict(String cacheName, String key);

    /**
     * 清空
     *
     * @param cacheName
     */
    @Operation(summary = "清空命名缓存", description = "仅清空指定 cacheName，不影响其他缓存命名空间。")
    void clear(String cacheName);

}
