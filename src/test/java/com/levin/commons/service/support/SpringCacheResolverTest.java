package com.levin.commons.service.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

class SpringCacheResolverTest {

    @BeforeEach
    void setUp() {
        SpringCacheEventListener.cacheMap.clear();
        SpringCacheEventListener.eventListeners.clear();
    }

    @Test
    void shouldReturnNullWhenCacheMiss() {
        // 业务规则：缓存未命中时应返回 null，而不是抛异常。
        SpringCacheResolver resolver = newResolver("cache-miss");

        Object value = resolver.getCache("cache-miss").get("missing-key");

        Assertions.assertNull(value, "缓存未命中时应返回 null");
    }

    @Test
    void shouldPutAndGetValue() {
        // 业务规则：写入缓存后应可按 key 读回原值。
        SpringCacheResolver resolver = newResolver("cache-put-get");
        resolver.getCache("cache-put-get").put("k1", "v1");

        String value = resolver.getCache("cache-put-get").get("k1");

        Assertions.assertEquals("v1", value, "缓存写入后应能读取到同一个值");
    }

    @Test
    void shouldLoadValueThroughCallableWhenAbsent() {
        // 业务规则：get(key, callable) 在缺失时应加载并回写缓存。
        SpringCacheResolver resolver = newResolver("cache-callable");

        String value = resolver.getCache("cache-callable").get("k2", () -> "loaded");

        Assertions.assertEquals("loaded", value, "缓存缺失时应执行 callable 加载值");
        Assertions.assertEquals("loaded", resolver.getCache("cache-callable").get("k2"),
                "callable 加载后的值应被回写到缓存");
    }

    private SpringCacheResolver newResolver(String cacheName) {
        SpringCacheResolver resolver = new SpringCacheResolver();
        resolver.setCacheManager(new ConcurrentMapCacheManager(cacheName));
        resolver.afterPropertiesSet();
        return resolver;
    }
}
