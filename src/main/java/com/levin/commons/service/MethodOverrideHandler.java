package com.levin.commons.service;

import com.levin.commons.service.support.ValueHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.reflect.Method;

/**
 * @author echo
 */
@Tag(name = "方法覆盖处理器", description = "处理器通过 ValueHolder 决定原方法是否执行：hasValue 为 true 时以持有值覆盖原方法结果；没有值时回退到原方法。处理器也可通过异常明确拒绝执行。")
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
    @Operation(summary = "处理方法覆盖", description = "返回 hasValue 为 true 的 ValueHolder 时跳过原方法并使用其值；返回无值 Holder 时回退执行原方法；抛出异常时拒绝本次调用。")
    ValueHolder<?> override(Object proxy, Method method, Object[] args) throws Throwable;
}
