package com.levin.commons.service.support;

import com.levin.commons.service.CacheService;
import com.levin.commons.service.support.SpringCacheEventListener.Action;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.stream.Collectors;


/**
 * 模块缓存解析器
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月28日 下午3:22:51, 代码生成哈希校验码：[8664d3a69ac95a9c04740d66183a645d]，请不要修改和删除此行内容。
 */
@Slf4j
public class SpringCacheResolver implements CacheService, CacheResolver, InitializingBean, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    ApplicationContext applicationContext;

    private static final ThreadLocal<CacheOperationInvocationContext<?>> invocationContext = new ThreadLocal<>();

    @AllArgsConstructor
    static class CacheProxy implements Cache {

        Cache delegate;

        Supplier<Collection<SpringCacheEventListener>> supplier;

        /**
         * Return the cache name.
         */
        @Override
        public String getName() {
            return delegate.getName();
        }

        @Override
        public Object getNativeCache() {
            return delegate.getNativeCache();
        }

        @Override
        public ValueWrapper get(Object key) {
            ValueWrapper valueWrapper = delegate.get(key);
            onEvent(Action.Get, key, valueWrapper);
            return valueWrapper;
        }

        @Override
        public <T> T get(Object key, Class<T> type) {
            T value = delegate.get(key, type);
            onEvent(Action.Get, key, value);
            return value;
        }

        @Override
        public <T> T get(Object key, Callable<T> valueLoader) {
            T value = delegate.get(key, valueLoader);
            onEvent(Action.Get, key, value);
            return value;
        }

        @Override
        public void put(Object key, Object value) {
            delegate.put(key, value);
            onEvent(Action.Put, key, value);
        }

        @Override
        public void evict(Object key) {
            delegate.evict(key);
            onEvent(Action.Evict, key, null);
        }

        @Override
        public void clear() {
            delegate.clear();
            onEvent(Action.Clear, null, null);
        }

        private void onEvent(Action action, Object key, Object value) {

            try {

                Collection<SpringCacheEventListener> listeners = supplier != null ? supplier.get() : null;

                if (listeners != null) {
                    listeners.forEach(el -> el.onCacheEvent(invocationContext.get(), delegate, action, key, value));
                }

            } finally {
                invocationContext.remove();
            }
        }
    }

    /**
     * Set the {@link CacheManager} that this instance should use.
     */
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Return the {@link CacheManager} that this instance uses.
     */
    public CacheManager getCacheManager() {
        Assert.state(this.cacheManager != null, "No CacheManager set");
        return this.cacheManager;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(this.cacheManager, "CacheManager is required");
    }


    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext() == applicationContext) {
            event.getApplicationContext().getBeanProvider(SpringCacheEventListener.class).forEach(SpringCacheEventListener::add);
        }
    }


    /**
     * 放入
     *
     * @param cacheName
     * @param key
     * @param value
     */
    @Override
    public void put(String cacheName, String key, Object value) {
        getCache(cacheName).put(key, value);
    }

    /**
     * 获取
     *
     * @param cacheName
     * @param key
     * @return
     */
    @Override
    public <T> T get(String cacheName, String key) {
        return (T) getCache(cacheName).get(key);
    }

    /**
     * 删除
     *
     * @param cacheName
     * @param key
     */
    @Override
    public void evict(String cacheName, String key) {
        getCache(cacheName).evict(key);
    }

    /**
     * 清空
     *
     * @param cacheName
     */
    @Override
    public void clear(String cacheName) {
        getCache(cacheName).clear();
    }

    /**
     * 获取监听器
     *
     * @return
     */
    protected Collection<SpringCacheEventListener> getCacheEventListeners() {
        return SpringCacheEventListener.eventListeners;
    }

    /**
     * 获取缓存
     *
     * @return
     */
    protected Map<String, Cache> getCacheMap() {
        return SpringCacheEventListener.cacheMap;
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @return
     */
    @Override
    public ICache getCache(String cacheName) {

        Assert.hasText(cacheName, "Cache name must not be null");
        return new ICache() {

            final Cache springCache = getSpringCache(cacheName);

            @Override
            public void put(String key, Object value) {
                springCache.put(key, value);
            }

            /**
             * 获取
             *
             * @param key
             * @return
             */
            @Override
            public <T> T get(String key) {
                return (T) springCache.get(key).get();
            }

            /**
             * 默认从缓存加载，如果不存在则从loader中加载
             * 同时，如果加载不为空，则放入缓存
             *
             * @param key
             * @param valueLoader
             * @return
             */
            @Override
            public <T> T get(String key, Callable<T> valueLoader) {
                return springCache.get(key, valueLoader);
            }

            @Override
            public void evict(String key) {
                springCache.evict(key);
            }

            @Override
            public void clear() {
                springCache.clear();
            }
        };
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @return
     */
    public Cache getSpringCache(String cacheName) {

        return getCacheMap().computeIfAbsent(cacheName, key -> {

            Cache cache = getCacheManager().getCache(cacheName);

            if (cache == null) {
                throw new IllegalArgumentException("Cannot find cache named '" + cacheName + "'");
            }

            return new CacheProxy(cache, this::getCacheEventListeners);

        });
    }

    /**
     * @param context
     * @return
     */
    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {

        Collection<String> cacheNames = context.getOperation().getCacheNames();

        if (cacheNames == null || cacheNames.isEmpty()) {
            return Collections.emptyList();
        }

        invocationContext.set(context);

        return cacheNames.stream().map(this::getSpringCache).collect(Collectors.toList());
    }

}