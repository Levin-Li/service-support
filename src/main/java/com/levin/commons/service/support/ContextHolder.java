package com.levin.commons.service.support;

import com.levin.commons.utils.MapUtils;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public abstract class ContextHolder<K, V> {

    /**
     * 全局上下文
     */
    public static final ContextHolder<Object, Object> globalContext = buildContext(true);

    /**
     * 全局线程上下文
     */
    public static final ContextHolder<Object, Object> threadContext = buildThreadContext(true, false);

    /**
     * 全局可继承线程上下文
     */
    public static final ContextHolder<Object, Object> inheritableThreadContext = buildThreadContext(true, true);

    /**
     * 构建普通上下文
     *
     * @param <K>
     * @param <V>
     * @return
     */
    public static final <K, V> ContextHolder<K, V> buildContext(boolean isStrongReference) {
        return new ContextHolder<K, V>() {
            private final Map<K, V> ctx = isStrongReference ? new ConcurrentHashMap<>(16) : new ConcurrentReferenceHashMap<>(16);

            @Override
            protected Map<K, V> getContext() {
                return ctx;
            }
        };
    }

    /**
     * 构建线程上下文
     *
     * @param inheritableThread
     * @param <K>
     * @param <V>
     * @return
     */
    public static final <K, V> ContextHolder<K, V> buildThreadContext(final boolean isStrongReference, final boolean inheritableThread) {

        return new ContextHolder<K, V>() {

            private final ThreadLocal<Map<K, V>> threadContext = inheritableThread ? new InheritableThreadLocal<>() : new ThreadLocal<>();

            @Override
            protected synchronized Map<K, V> getContext() {

                Map<K, V> context = threadContext.get();

                if (context == null) {

                    context = isStrongReference ? new ConcurrentHashMap<>(16) : new ConcurrentReferenceHashMap<>(16);

                    threadContext.set(context);
                }

                return context;

            }
        };
    }


    /**
     * 确保私有构造
     */
    private ContextHolder() {
    }

    /**
     * 确保不会返回 null
     *
     * @return
     */
    protected abstract Map<K, V> getContext();

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean contains(K key) {
        return getContext().containsKey(key);
    }

    public <T extends V> T get(K key) {
        return (T) getContext().get(key);
    }

    /**
     * 取出缓存，如果没有，则先去获取，然后放入缓存，在返回获取的值
     *
     * @param key
     * @param suppliers
     * @param <T>
     * @return
     */
    public <T extends V> T getAndAutoPut(K key, Supplier<T>... suppliers) {
        return (T) MapUtils.getAndAutoPut(getContext(), key, suppliers);
    }

    public <T extends V> T get(K key, V defaultValue) {
        return (T) getContext().getOrDefault(key, defaultValue);
    }

    public <T extends V> T remove(K key) {
        return (T) getContext().remove(key);
    }

    public <T extends V> T put(K key, V object) {
        return (T) getContext().put(key, object);
    }

    public <T extends V> T putIfAbsent(K key, V object) {
        return (T) getContext().putIfAbsent(key, object);
    }

    public Map<K, V> getAll(boolean readOnly) {
        return readOnly ? Collections.unmodifiableMap(getContext()) : getContext();
    }

    public void clear() {
        getContext().clear();
    }

}
