package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.levin.commons.service.SingleValueContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;


/**
 * @author lilw
 */
public class HttpRequestSingleValueContext<V> implements SingleValueContext<V> {

    @AllArgsConstructor
    @Data
    @Accessors(chain = true, fluent = true)
    private static class Holder<V> {
        V value;
    }

    private final Supplier<HttpServletRequest> httpServletRequestSupplier;
    private final boolean isThrowExWhenNotValue;
    private final String key;

    public HttpRequestSingleValueContext(Supplier<HttpServletRequest> httpServletRequestSupplier, boolean isThrowExWhenNotValue) {
        this(httpServletRequestSupplier, isThrowExWhenNotValue, "SingleValueContext__" + UUID.randomUUID());
    }

    public HttpRequestSingleValueContext(Supplier<HttpServletRequest> httpServletRequestSupplier, boolean isThrowExWhenNotValue, String key) {
        this.isThrowExWhenNotValue = isThrowExWhenNotValue;
        this.httpServletRequestSupplier = Objects.requireNonNull(httpServletRequestSupplier, "httpServletRequestSupplier is null");
        Assert.notBlank(key, "key is blank");
        this.key = key;
    }

    @Override
    public boolean hasValue() {
        return httpServletRequestSupplier.get().getAttribute(key) != null;
    }

    @Override
    public SingleValueContext<V> set(V value) {

        httpServletRequestSupplier.get().setAttribute(key, new Holder<>(value));

        return this;
    }

    @Override
    public SingleValueContext<V> clear() {

        httpServletRequestSupplier.get().setAttribute(key, null);

        return this;
    }

    @Override
    public V get() {

        Holder<V> holder = (Holder<V>) httpServletRequestSupplier.get().getAttribute(key);

        Assert.isTrue(holder != null || !isThrowExWhenNotValue, " value not found with key: " + key);

        return holder == null ? null : holder.value();
    }

}
