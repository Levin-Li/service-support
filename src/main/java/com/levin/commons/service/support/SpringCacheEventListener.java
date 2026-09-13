
package com.levin.commons.service.support;

import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.util.Assert;
import org.springframework.util.PatternMatchUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@FunctionalInterface
@Tag(name = "Spring 缓存事件监听器", description = "监听器按缓存名、键与动作过滤后才接收事件；过滤不匹配时静默跳过。注册和移除影响全局监听器集合，事件通知不改变原缓存操作结果。")
public interface SpringCacheEventListener {

    Map<String, Cache> cacheMap = new ConcurrentHashMap<>();

    Set<SpringCacheEventListener> eventListeners = new LinkedHashSet<>();

    enum Action implements EnumDesc {

        @Schema(title = "读取")
        Get,

        @Schema(title = "放入")
        Put,

        @Schema(title = "剔除")
        Evict,

        @Schema(title = "清除")
        Clear,
    }

    @Schema(title = "简单监听器")
    @AllArgsConstructor
    class SimpleListener implements SpringCacheEventListener {

        @Getter
        SpringCacheEventListener delegate;

        String cacheNamePattern;
        String keyPattern;

        List<Action> actions;

        @Override
        public void onCacheEvent(CacheOperationInvocationContext<?> cacheOperationInvocationContext, Cache cache, Action action, Object key, Object value) {
            if (cacheNamePattern == null || PatternMatchUtils.simpleMatch(cacheNamePattern, cache.getName())) {
                if (keyPattern == null || (key instanceof CharSequence && PatternMatchUtils.simpleMatch(keyPattern, key.toString()))) {
                    if (actions == null || actions.contains(action)) {
                        delegate.onCacheEvent(cacheOperationInvocationContext, cache, action, key, value);
                    }
                }
            }
        }
    }

    /**
     * 增加监听器
     *
     * @param listener
     * @param cacheNamePattern 为 null 匹配所有, 支持*通配符
     * @param keyPattern       为 null 匹配所有，支持*通配符
     * @param actions          为 null 匹配所有
     * @return
     */
    @Operation(summary = "添加带过滤条件的缓存监听器", description = "cacheNamePattern、keyPattern 或 actions 为 null 时分别匹配全部；名称和键支持 * 通配符，任一过滤条件不匹配时不通知委托监听器。")
    static void add(SpringCacheEventListener listener, String cacheNamePattern, String keyPattern, Action... actions) {
        Assert.notNull(listener, "listener is null");
        add(new SimpleListener(listener, cacheNamePattern, keyPattern, Arrays.asList(actions)));
    }

    /**
     * 增加监听器
     *
     * @param listeners
     * @return
     */
    @Operation(summary = "添加缓存监听器", description = "将非空监听器加入全局集合；重复与并发集合语义由实现决定，注册不回放既有缓存事件。")
    static void add(SpringCacheEventListener... listeners) {
        if (listeners != null) {
            for (SpringCacheEventListener listener : listeners) {
                if (listener != null) {
                    eventListeners.add(listener);
                }
            }
        }
    }

    /**
     * 移除监听器
     *
     * @param listeners
     * @return
     */
    @Operation(summary = "移除缓存监听器", description = "移除同一监听器或其包装监听器；移除后只阻止后续事件，不撤销已经发生的缓存操作或通知。")
    static void remove(SpringCacheEventListener... listeners) {
        if (listeners != null) {
            for (SpringCacheEventListener listener : listeners) {
                if (listener != null) {
                    eventListeners.removeIf(l -> l == listener || (l instanceof SimpleListener && ((SimpleListener) l).getDelegate() == listener));
                }
            }
        }
    }

    /**
     * 缓存事件
     *
     * @param cacheOperationInvocationContext
     * @param cache
     * @param action
     * @param key
     * @param value
     */
    @Operation(summary = "处理缓存事件", description = "在缓存 Get、Put、Evict 或 Clear 操作发生时接收事件上下文；监听器应观察事件，不应改变原缓存操作的成功/失败语义。")
    void onCacheEvent(CacheOperationInvocationContext<?> cacheOperationInvocationContext, Cache cache, Action action, Object key, Object value);
}
