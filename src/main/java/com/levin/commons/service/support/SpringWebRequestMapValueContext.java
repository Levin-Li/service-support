package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.levin.commons.service.MapValueContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


/**
 * @author lilw
 */
public class SpringWebRequestMapValueContext<V> implements MapValueContext<String, V> {

    @AllArgsConstructor
    @Data
    @Accessors(chain = true, fluent = true)
    private static class Holder<V> {
        V value;
    }

    private final boolean isThrowExWhenNotValue;

    public SpringWebRequestMapValueContext(boolean isThrowExWhenNotValue) {
        this.isThrowExWhenNotValue = isThrowExWhenNotValue;
    }

    protected RequestAttributes getRequestAttributes() {
        return RequestContextHolder.getRequestAttributes();
    }

    @Override
    public boolean hasValue(String key) {
        return getRequestAttributes().getAttribute(key, RequestAttributes.SCOPE_REQUEST) != null;
    }

    @Override
    public MapValueContext<String, V> set(String key, V value) {

        getRequestAttributes().setAttribute(key, new Holder<>(value), RequestAttributes.SCOPE_REQUEST);

        return this;
    }

    @Override
    public MapValueContext<String, V> clear(String key) {

        getRequestAttributes().setAttribute(key, null, RequestAttributes.SCOPE_REQUEST);

        return this;
    }

    @Override
    public V get(String key) {

        Holder<V> holder = (Holder<V>) getRequestAttributes().getAttribute(key, RequestAttributes.SCOPE_REQUEST);

        Assert.isTrue(holder != null || !isThrowExWhenNotValue, " value not found with key: " + key);

        return holder == null ? null : holder.value();
    }

}
