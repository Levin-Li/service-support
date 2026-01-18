package com.levin.commons.rbac;


import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 方法调用授权服务
 */
@FunctionalInterface
public interface RbacMethodService{

    /**
     * 检查当前用户的方法调用授权
     *
     * @param principal   当前用户
     * @param beanOrClass 控制器或是服务
     * @param method      控制器或是服务的方法
     */
    boolean canAccess(Serializable principal, Object beanOrClass, @NonNull Method method);

}
