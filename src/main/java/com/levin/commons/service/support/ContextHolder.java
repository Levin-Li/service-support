package com.levin.commons.service.support;

import com.levin.commons.utils.MapUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
@Data
@Accessors(chain = true)
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

            private final Map<K, V> ctx = MapUtils.newMap(isConcurrentMap(), isStrongReference, isWeakReference);

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
            protected Map<K, V> getContext() {

                Map<K, V> context = threadLocal.get();

                if (context == null) {

                    context = MapUtils.newMap(isConcurrentMap(), isStrongReference, isWeakReference);

                    threadLocal.set(context);
                }

                return context;
            }

            @Override
            public boolean isConcurrentMap() {
                return false;
            }

            @Override
            public ContextHolder<K, V> clear() {
                threadLocal.remove();
                return this;
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
     *
     */

    /**
     * 开放构造
     */
    public ContextHolder() {
    }

    /**
     * @param key
     * @return
     */
    protected K convertKey(K key) {
        return keyConverter == null ? key : keyConverter.apply(key);
    }

    /**
     * 确保不会返回 null
     *
     * @return
     */
    protected abstract Map<K, V> getContext();

    /**
     * 是否 ConcurrentMap
     *
     * @return
     */
    public boolean isConcurrentMap() {
        return true;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param key
     * @return
     */
    public boolean containsKey(K key) {
        return getContext().containsKey(convertKey(key));
    }

    /**
     * @param key
     * @param <T>
     * @return
     */
    public <T extends V> T get(K key) {
        return getOrDefault(key, null);
    }

    /**
     * 取出缓存，如果没有，则先去获取，然后放入缓存，在返回获取的值
     *
     * @param key
     * @param putCondition         值放入上下文的条件
     * @param backupValueSuppliers 备选值列表
     * @param <T>
     * @return
     */
    public <T extends V> T getAndAutoPut(K key, Predicate<T> putCondition, Supplier<T>... backupValueSuppliers) {
        return (T) MapUtils.getAndAutoPut(getContext(), convertKey(key), putCondition, backupValueSuppliers);
    }

    public <T extends V> T getOrDefault(K key, V defaultValue) {
        return (T) getContext().getOrDefault(convertKey(key), defaultValue);
    }

    public boolean isEmpty() {
        return getContext().isEmpty();
    }

    public int size() {
        return getContext().size();
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

    /**
     * @param consumer
     * @return
     */
    public ContextHolder<K, V> forEach(BiConsumer<K, V> consumer) {

        if (consumer != null) {
            getContext().forEach(consumer);
        }

        return this;
    }

    public ContextHolder<K, V> putAll(Map<K, V> values) {
        getContext().putAll(values);
        return this;
    }

    public ContextHolder<K, V> clear() {
        getContext().clear();
        return this;
    }

}
