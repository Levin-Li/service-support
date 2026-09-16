package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.levin.commons.service.MapValueContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author lilw
 */
public abstract class HttpRequestMapValueContext<V> implements MapValueContext<String, V> {

    @AllArgsConstructor
    @Data
    @Accessors(chain = true, fluent = true)
    private static class Holder<V> {
        V value;
    }

    private final boolean isThrowExWhenNotValue;

    protected HttpRequestMapValueContext(boolean isThrowExWhenNotValue) {
        this.isThrowExWhenNotValue = isThrowExWhenNotValue;
    }

    protected abstract HttpServletRequest getHttpRequest();

    @Override
    public boolean hasValue(String key) {
        return getHttpRequest().getAttribute(key) != null;
    }

    @Override
    public MapValueContext<String, V> set(String key, V value) {

        getHttpRequest().setAttribute(key, new Holder<>(value));

        return this;
    }

    @Override
    public MapValueContext<String, V> clear(String key) {

        getHttpRequest().setAttribute(key, null);

        return this;
    }

    @Override
    public V get(String key) {

        Holder<V> holder = (Holder<V>) getHttpRequest().getAttribute(key);

        Assert.isTrue(holder != null || !isThrowExWhenNotValue, " value not found with key: " + key);

        return holder == null ? null : holder.value();
    }

}
