package com.levin.commons.rbac;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 方法调用授权服务
 */
@FunctionalInterface
@Tag(name = "RBAC 方法调用授权", description = "按当前主体、目标 Bean 或类型及方法判定调用权限；返回 false 即拒绝调用，调用方不得将其回退为允许。")
public interface RbacMethodService {

    /**
     * 检查当前用户的方法调用授权
     *
     * @param principal   当前用户
     * @param beanOrClass 控制器或是服务
     * @param method      控制器或是服务的方法
     */
    @Operation(summary = "检查方法调用权限", description = "检查主体对指定控制器或服务方法的调用权限；返回 false 表示拒绝，返回 true 仅表示该方法授权通过，不替代参数或数据范围校验。")
    boolean canAccess(Serializable principal, Object beanOrClass, @NonNull Method method);

}
