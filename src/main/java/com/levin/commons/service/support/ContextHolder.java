package com.levin.commons.service.support;

import com.levin.commons.utils.MapUtils;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class ContextHolder<K, V> {

    /**
     * 全局上下文
     */
    public static final ContextHolder<Object, Object> globalContext = buildContext(true);

    /**
     * 全局线程上下文
     */
    public static final ContextHolder<Object, Object> threadContext = buildThreadContext(true);

    /**
     * 全局可继承线程上下文
     */
    public static final ContextHolder<Object, Object> inheritableThreadContext = buildThreadContext(true, false, true);

    /**
     * @param isStrongReference 是否是强引用
     * @param <K>
     * @param <V>
     * @return
     */
    public static final <K, V> ContextHolder<K, V> buildContext(boolean isStrongReference) {
        return buildContext(isStrongReference, false);
    }

    /**
     * 构建上下文
     *
     * @param isStrongReference
     * @param isWeakReference
     * @param <K>
     * @param <V>
     * @return
     */
    public static final <K, V> ContextHolder<K, V> buildContext(boolean isStrongReference, boolean isWeakReference) {
        return new ContextHolder<K, V>() {
            private final Map<K, V> ctx = MapUtils.newMap(isStrongReference, isWeakReference);

            @Override
            protected Map<K, V> getContext() {
                return ctx;
            }
        };
    }


    /**
     * @param isStrongReference
     * @param <K>
     * @param <V>
     * @return
     */
    public static final <K, V> ContextHolder<K, V> buildThreadContext(boolean isStrongReference) {
        return buildThreadContext(isStrongReference, false);
    }

    /**
     * @param isStrongReference
     * @param isWeakReference
     * @param <K>
     * @param <V>
     * @return
     */
    public static final <K, V> ContextHolder<K, V> buildThreadContext(boolean isStrongReference, boolean isWeakReference) {
        return buildThreadContext(isStrongReference, isWeakReference, false);
    }

    /**
     * 构建线程上下文
     *
     * @param isStrongReference
     * @param isWeakReference
     * @param isInheritableThread
     * @param <K>
     * @param <V>
     * @return
     */
    public static final <K, V> ContextHolder<K, V> buildThreadContext(boolean isStrongReference, boolean isWeakReference, boolean isInheritableThread) {

        return new ContextHolder<K, V>() {

            private final ThreadLocal<Map<K, V>> threadLocal = isInheritableThread ? new InheritableThreadLocal<>() : new ThreadLocal<>();

            @Override
            protected synchronized Map<K, V> getContext() {

                Map<K, V> context = threadLocal.get();

                if (context == null) {

                    context = MapUtils.newMap(isStrongReference, isWeakReference);

                    threadLocal.set(context);
                }

                return context;

            }
        };
    }


    /**
     * Key 转化器
     * <p>
     * 处理大小写，去除空格等作用
     */
    private Function<K, K> keyConverter;

    /**
     * 开放构造
     */
    public ContextHolder() {
    }

    public ContextHolder<K, V> setKeyConverter(Function<K, K> keyConverter) {

        this.keyConverter = keyConverter;

        return this;
    }

    protected K convertKey(K key) {
        return keyConverter == null ? key : keyConverter.apply(key);
    }

    /**
     * 确保不会返回 null
     *
     * @return
     */
    protected abstract Map<K, V> getContext();

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean contains(K key) {
        return getContext().containsKey(convertKey(key));
    }

    public <T extends V> T get(K key) {
        return (T) getContext().get(convertKey(key));
    }

    /**
     * 取出缓存，如果没有，则先去获取，然后放入缓存，在返回获取的值
     *
     * @param key
     * @param putCondition 值放入上下文的条件
     * @param suppliers
     * @param <T>
     * @return
     */
    public <T extends V> T getAndAutoPut(K key, Predicate<T> putCondition, Supplier<T>... suppliers) {
        return (T) MapUtils.getAndAutoPut(getContext(), convertKey(key), putCondition, suppliers);
    }

    public <T extends V> T get(K key, V defaultValue) {
        return (T) getContext().getOrDefault(convertKey(key), defaultValue);
    }

    public <T extends V> T remove(K key) {
        return (T) getContext().remove(convertKey(key));
    }

    public <T extends V> T put(K key, V object) {
        return (T) getContext().put(convertKey(key), object);
    }

    public <T extends V> T putIfAbsent(K key, V object) {
        return (T) getContext().putIfAbsent(convertKey(key), object);
    }

    public Map<K, V> getAll(boolean readOnly) {
        return readOnly ? Collections.unmodifiableMap(getContext()) : getContext();
    }

    public void clear() {
        getContext().clear();
    }

}
