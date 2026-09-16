package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.levin.commons.service.SingleValueContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

public class ThreadLocalSingleValueContext<V> implements SingleValueContext<V> {

    @AllArgsConstructor
    @Data
    @Accessors(chain = true, fluent = true)
    private static class Holder<V> {
        V value;
    }

    private final ThreadLocal<Holder<V>> threadLocal;

    private final boolean isThrowExWhenNotValue;

    private ThreadLocalSingleValueContext(ThreadLocal<Holder<V>> threadLocal, boolean isThrowExWhenNotValue) {
        this.threadLocal = Objects.requireNonNull(threadLocal, " threadLocal is null");
        this.isThrowExWhenNotValue = isThrowExWhenNotValue;
    }

    public static <V> SingleValueContext<V> of(boolean isThrowExWhenNotValue) {
        return new ThreadLocalSingleValueContext<>(new ThreadLocal<>(), isThrowExWhenNotValue);
    }

    public static <V> SingleValueContext<V> ofInheritableThread(boolean isThrowExWhenNotValue) {
        return new ThreadLocalSingleValueContext<>(new InheritableThreadLocal<>(), isThrowExWhenNotValue);
    }

    @Override
    public boolean hasValue() {
        return threadLocal.get() != null;
    }

    @Override
    public SingleValueContext<V> set(V value) {

        threadLocal.set(new Holder<>(value));

        return this;
    }

    @Override
    public SingleValueContext<V> clear() {

        threadLocal.set(null);

        return this;
    }

    @Override
    public V get() {

        Holder<V> holder = threadLocal.get();

        Assert.isTrue(holder != null || !isThrowExWhenNotValue, "value not found");

        return holder == null ? null : holder.value();
    }
}
