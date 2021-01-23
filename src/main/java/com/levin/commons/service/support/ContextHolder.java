package com.levin.commons.service.support;

import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.Collections;
import java.util.Map;

public abstract class ContextHolder<K, V> {

    /**
     *
     */
    public static final ContextHolder<Object, Object> global = new ContextHolder<Object, Object>() {

        private final Map<Object, Object> ctx = new ConcurrentReferenceHashMap<>();

        @Override
        protected Map<Object, Object> getContext() {
            return ctx;
        }
    };

    /**
     * 全局线程
     */
    public static final ContextHolder<Object, Object> threadContext = build(false);

    /**
     * 全局可继承线程
     */
    public static final ContextHolder<Object, Object> inheritableThreadContext = build(true);


    /**
     * 构建
     *
     * @param inheritableThread
     * @param <K>
     * @param <V>
     * @return
     */
    public static final <K, V> ContextHolder<K, V> build(final boolean inheritableThread) {

        return new ContextHolder<K, V>() {

            private final ThreadLocal<Map<K, V>> threadContext = inheritableThread ? new InheritableThreadLocal<>() : new ThreadLocal<>();

            @Override
            protected synchronized Map<K, V> getContext() {

                Map<K, V> context = threadContext.get();

                if (context == null) {
                    context = new ConcurrentReferenceHashMap<>();
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

    public <T> T get(K key) {
        return (T) getContext().get(key);
    }

    public <T> T remove(K key) {
        return (T) getContext().remove(key);
    }

    public <T> T put(K key, V object) {
        return (T) getContext().put(key, object);
    }

    public Map<K, V> getAll(boolean readOnly) {
        return readOnly ? Collections.unmodifiableMap(getContext()) : getContext();
    }

    public void clear() {
        getContext().clear();
    }

}
