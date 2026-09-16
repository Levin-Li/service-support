package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.levin.commons.service.SingleValueContext;

public class FixedSingleValueContext<V> implements SingleValueContext<V> {


    V value;

    private boolean hasValue;

    private final boolean isThrowExWhenNotValue;

    private FixedSingleValueContext(boolean isThrowExWhenNotValue) {
        this.isThrowExWhenNotValue = isThrowExWhenNotValue;
    }

    public static <V> SingleValueContext<V> of(boolean isThrowExWhenNotValue) {
        return new FixedSingleValueContext<>(isThrowExWhenNotValue);
    }


    @Override
    public boolean hasValue() {
        return hasValue;
    }

    @Override
    public SingleValueContext<V> set(V value) {

        hasValue = true;

        this.value = value;

        return this;

    }

    @Override
    public SingleValueContext<V> clear() {

        hasValue = false;

        this.value = null;

        return this;

    }

    @Override
    public V get() {

        Assert.isTrue(hasValue || !isThrowExWhenNotValue, "value not found");

        return value;

    }
}
