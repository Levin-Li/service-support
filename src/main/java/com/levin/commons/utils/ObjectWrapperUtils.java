package com.levin.commons.utils;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjUtil;
import com.levin.commons.dao.domain.ProxyWrapperObject;
import com.levin.commons.service.MethodOverrideHandler;
import com.levin.commons.service.support.ValueHolder;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.function.Supplier;


/**
 * @author lilw
 */
public abstract class ObjectWrapperUtils {

    public static <T> T wrapper2Readonly(Object originalObject) {
        return wrapper2Readonly(originalObject, null, () -> true);
    }

    public static <T> T wrapper2Readonly(Object originalObject, Supplier<Boolean> isReadonly) {
        return wrapper2Readonly(originalObject, null, isReadonly);
    }

    public static <T> T wrapper2Readonly(Class<T> wrapperTargetClass, Supplier<Boolean> isReadonly) {
        return wrapper2Readonly(null, wrapperTargetClass, isReadonly);
    }

    public static <T> T wrapper2Readonly(Object originalObject, Class<T> wrapperTargetClass, Supplier<Boolean> isReadonly) {
        return wrapperByProxy(originalObject, wrapperTargetClass, new ReadonlyMethodOverrideHandler(isReadonly));
    }

    public static <T> T wrapperByProxy(T originalObject, MethodOverrideHandler methodOverrideHandler, Class<?>... newEnhanceInterfaces) {
        return wrapperByProxy(originalObject, null, methodOverrideHandler, newEnhanceInterfaces);
    }

    public static <T> T wrapperByProxy(Object originalObject, Class<T> proxyTargetClass, MethodOverrideHandler methodOverrideHandler, Class<?>... newEnhanceInterfaces) {

        Assert.isTrue(originalObject != null || proxyTargetClass != null
                , "originalObject or proxyTargetClass must has one");

        if (proxyTargetClass == null) {
            proxyTargetClass = (Class<T>) AopProxyUtils.ultimateTargetClass(originalObject);
        }

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(proxyTargetClass);

        //只有当你需要让代理类额外实现被代理类本身没有的接口时，才需要调用 setInterfaces()
        enhancer.setInterfaces(newEnhanceInterfaces);

        enhancer.setCallback(new ProxyWrapperHandler(originalObject, proxyTargetClass, methodOverrideHandler));

        return (T) enhancer.create();
    }


    public static class ReadonlyMethodOverrideHandler implements MethodOverrideHandler {

        final Supplier<Boolean> isReadonly;

        public ReadonlyMethodOverrideHandler(Supplier<Boolean> isReadonly) {
            this.isReadonly = isReadonly;
        }

        @Override
        public final ValueHolder<?> override(Object proxy, Method method, Object[] args) throws Throwable {

            //如果是设置方法都不允许调用,直接放回异常
            if (isReadonly != null
                    && Boolean.TRUE.equals(isReadonly.get()) // 只读对象

                    && method.getParameterCount() == 1
                    && method.getName().startsWith("set")
                    && !Character.isLowerCase(method.getName().charAt(3))) {

                throw new UnsupportedOperationException("readonly object");
            }

            return null;
        }
    }

    ;


    private static class ProxyWrapperHandler implements ProxyWrapperObject, MethodInterceptor {

        private final Object originalObject;
        private final Class<?> proxyTargetClass;
        private final MethodOverrideHandler methodOverrideHandler;

        ProxyWrapperHandler(Object originalObject, Class<?> proxyTargetClass, MethodOverrideHandler methodOverrideHandler) {
            this.originalObject = originalObject;
            this.proxyTargetClass = proxyTargetClass;
            this.methodOverrideHandler = methodOverrideHandler;
        }

        /**
         * 获取代理对象类型
         *
         * @return
         */
        @Override
        public <T> Class<T> proxyTargetClass() {
            return (Class<T>) proxyTargetClass;
        }

        /**
         * 获取原代理被对象
         *
         * @return
         */
        @Override
        public <T> T getOriginalObject() {
            return (T) originalObject;
        }

        @Override
        public Object intercept(Object proxyObj /// CGLIB 生成的代理对象（子类实例）
                , Method method, Object[] params, MethodProxy methodProxy) throws Throwable {

            if (method.getDeclaringClass() == ProxyWrapperObject.class) {
                return method.invoke(this, params);
            }

            if (this.methodOverrideHandler != null) {

                ValueHolder<?> overrideValue = methodOverrideHandler.override(ObjUtil.defaultIfNull(originalObject, proxyObj), method, params);

                if (overrideValue != null
                        && overrideValue.isHasValue()) {

                    //如果有覆盖的值, 则直接返回
                    return overrideValue.get();
                }
            }

            if (originalObject != null) {
                //包装的对象, 优先使用
                return method.invoke(originalObject, params);
            }

            //默认调用父类方法
            return methodProxy.invokeSuper(proxyObj, params);
        }
    }

}
