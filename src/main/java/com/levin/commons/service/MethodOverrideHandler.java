package com.levin.commons.service;

import com.levin.commons.service.support.ValueHolder;

import java.lang.reflect.Method;

/**
 * @author echo
 */
public interface MethodOverrideHandler {
    /**
     * 放回覆盖的值
     * <p>
     * 如果ValueHolder hasValue 则表示方法执行被覆盖
     * <p>
     * 如果不允许执行方法, 可以抛出异常
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    ValueHolder<?> override(Object proxy, Method method, Object[] args) throws Throwable;
}
