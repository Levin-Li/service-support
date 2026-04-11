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
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.IntrospectionException;
import java.lang.reflect.Method;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.function.Supplier;


/**
 * @author echo
 */
public abstract class ObjectWrapperUtils {

    private static final Object[] EMPTY_ARGS = new Object[0];
    private static final Map<Class<?>, BeanMethodMetadata> BEAN_METHOD_CACHE = new ConcurrentReferenceHashMap<>();
    private static final Map<Method, MethodInvoker> METHOD_INVOKER_CACHE = new ConcurrentReferenceHashMap<>();

    public static <T> T wrapper2Readonly(T originalObject) {
        if (originalObject == null) {
            return null;
        }
        return wrapper2Readonly(originalObject, null, (Supplier<Boolean>) null);
    }

    public static <T> T wrapper2Readonly(T originalObject, Supplier<Boolean> isReadonly) {
        if (originalObject == null) {
            return null;
        }
        return wrapper2Readonly(originalObject, null, isReadonly);
    }

    public static <T> T wrapper2Readonly(Class<T> wrapperTargetClass, Supplier<Boolean> isReadonly) {
        return wrapper2Readonly(null, wrapperTargetClass, isReadonly);
    }

    public static <T> T wrapper2Readonly(Object originalObject, Class<T> wrapperTargetClass, Supplier<Boolean> isReadonly) {
        if (wrapperTargetClass == null && originalObject == null) {
            return null;
        }

        ReadonlyMethodOverrideHandler readonlyHandler = isReadonly != null
                ? new ReadonlyMethodOverrideHandler(resolveProxyTargetClass(originalObject, wrapperTargetClass), isReadonly)
                : ReadonlyMethodOverrideHandler.alwaysReadonly(resolveProxyTargetClass(originalObject, wrapperTargetClass));

        T readonlyProxy = wrapperByProxy(originalObject, wrapperTargetClass, readonlyHandler);

        readonlyHandler.enableReadonly();

        return readonlyProxy;
    }

    public static <T> T wrapperByProxy(T originalObject, MethodOverrideHandler methodOverrideHandler, Class<?>... newEnhanceInterfaces) {
        return wrapperByProxy(originalObject, null, methodOverrideHandler, newEnhanceInterfaces);
    }

    public static <T> T wrapperByProxy(Object originalObject, Class<T> proxyTargetClass, MethodOverrideHandler methodOverrideHandler, Class<?>... newEnhanceInterfaces) {

        if (proxyTargetClass == null) {

            if (originalObject == null) {
                return null;
            }

            proxyTargetClass = (Class<T>) AopProxyUtils.ultimateTargetClass(originalObject);
        }

        Assert.notNull(proxyTargetClass, "proxyTargetClass can not be null");

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(proxyTargetClass);

        //只有当你需要让代理类额外实现被代理类本身没有的接口时，才需要调用 setInterfaces()
        enhancer.setInterfaces(mergeInterfaces(newEnhanceInterfaces));

        enhancer.setCallback(new ProxyWrapperHandler(originalObject, proxyTargetClass, methodOverrideHandler));

        return (T) enhancer.create();
    }

    public static class ReadonlyMethodOverrideHandler implements MethodOverrideHandler {

        final Class<?> proxyTargetClass;
        final boolean defaultReadonly;
        final Supplier<Boolean> isReadonly;
        private volatile boolean readonlyEnabled = false;

        public ReadonlyMethodOverrideHandler(Class<?> proxyTargetClass, Supplier<Boolean> isReadonly) {
            this(proxyTargetClass, false, isReadonly);
        }

        private ReadonlyMethodOverrideHandler(Class<?> proxyTargetClass, boolean defaultReadonly, Supplier<Boolean> isReadonly) {
            this.proxyTargetClass = proxyTargetClass;
            this.defaultReadonly = defaultReadonly;
            this.isReadonly = isReadonly;
        }

        static ReadonlyMethodOverrideHandler alwaysReadonly(Class<?> proxyTargetClass) {
            return new ReadonlyMethodOverrideHandler(proxyTargetClass, true, null);
        }

        void enableReadonly() {
            this.readonlyEnabled = true;
        }

        protected boolean isReadonly() {
            if (!readonlyEnabled) {
                return false;
            }
            return isReadonly != null ? Boolean.TRUE.equals(isReadonly.get()) : defaultReadonly;
        }

        @Override
        public final ValueHolder<?> override(Object proxy, Method method, Object[] args) throws Throwable {

            //如果是设置方法都不允许调用,直接放回异常
            if (isReadonly()
                    && isReadonlyWriteMethod(proxyTargetClass, method)) {

                throw new UnsupportedOperationException("readonly object");
            }

            return null;
        }
    }

    ;

    private static Class<?> resolveProxyTargetClass(Object originalObject, Class<?> proxyTargetClass) {
        if (proxyTargetClass != null) {
            return proxyTargetClass;
        }
        return originalObject != null ? AopProxyUtils.ultimateTargetClass(originalObject) : null;
    }

    private static BeanMethodMetadata getBeanMethodMetadata(Class<?> beanType) {
        return BEAN_METHOD_CACHE.computeIfAbsent(beanType, BeanMethodMetadata::new);
    }

    private static boolean isReadonlyWriteMethod(Class<?> beanType, Method method) {
        return beanType != null && getBeanMethodMetadata(beanType).writeMethods.contains(method);
    }

    private static boolean isReadonlyReadMethod(Class<?> beanType, Method method) {
        return beanType != null && getBeanMethodMetadata(beanType).readMethods.contains(method);
    }

    private static Object wrapReadonlyLiveValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof NavigableMap<?, ?> navigableMap) {
            return Collections.unmodifiableNavigableMap(navigableMap);
        }

        if (value instanceof SortedMap<?, ?> sortedMap) {
            return Collections.unmodifiableSortedMap(sortedMap);
        }

        if (value instanceof Map<?, ?> map) {
            return Collections.unmodifiableMap(map);
        }

        if (value instanceof NavigableSet<?> navigableSet) {
            return Collections.unmodifiableNavigableSet(navigableSet);
        }

        if (value instanceof SortedSet<?> sortedSet) {
            return Collections.unmodifiableSortedSet(sortedSet);
        }

        if (value instanceof Set<?> set) {
            return Collections.unmodifiableSet(set);
        }

        if (value instanceof List<?> list) {
            return Collections.unmodifiableList(list);
        }

        if (value instanceof Collection<?> collection) {
            return Collections.unmodifiableCollection(collection);
        }

        return value;
    }

    private static MethodInvoker getMethodInvoker(Method method) {
        return METHOD_INVOKER_CACHE.computeIfAbsent(method, MethodInvoker::new);
    }

    private static Class<?>[] mergeInterfaces(Class<?>... newEnhanceInterfaces) {
        LinkedHashSet<Class<?>> interfaces = new LinkedHashSet<>();
        interfaces.add(ProxyWrapperObject.class);

        if (newEnhanceInterfaces != null) {
            interfaces.addAll(Arrays.asList(newEnhanceInterfaces));
        }

        return interfaces.toArray(new Class<?>[0]);
    }


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
                if ("proxyTargetClass".equals(method.getName())) {
                    return proxyTargetClass;
                }
                if ("getOriginalObject".equals(method.getName())) {
                    return originalObject;
                }
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
                Object value = getMethodInvoker(method).invoke(originalObject, params);
                return isReadonlyReadMethod(proxyTargetClass, method) ? wrapReadonlyLiveValue(value) : value;
            }

            //默认调用父类方法
            return methodProxy.invokeSuper(proxyObj, params);
        }
    }

    private static final class MethodInvoker {

        private final MethodHandle spreader;

        private MethodInvoker(Method method) {
            this.spreader = initSpreader(method);
        }

        private static MethodHandle initSpreader(Method method) {
            try {
                ReflectionUtils.makeAccessible(method);

                MethodHandles.Lookup lookup = MethodHandles.lookup();
                MethodHandle rawHandle;

                try {
                    rawHandle = lookup.unreflect(method);
                } catch (IllegalAccessException ex) {
                    rawHandle = MethodHandles.privateLookupIn(method.getDeclaringClass(), lookup).unreflect(method);
                }

                return rawHandle.asSpreader(Object[].class, method.getParameterCount());
            } catch (IllegalAccessException ex) {
                throw new IllegalStateException("Can't create method handle for " + method, ex);
            }
        }

        private Object invoke(Object target, Object[] args) throws Throwable {
            return spreader.invoke(target, args != null ? args : EMPTY_ARGS);
        }
    }

    private static final class BeanMethodMetadata {

        private final Set<Method> readMethods = new LinkedHashSet<>();
        private final Set<Method> writeMethods = new LinkedHashSet<>();

        private BeanMethodMetadata(Class<?> beanType) {
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(beanType);
                Arrays.stream(beanInfo.getPropertyDescriptors()).forEach(pd -> {
                    if (pd.getReadMethod() != null) {
                        readMethods.add(pd.getReadMethod());
                    }
                    if (pd.getWriteMethod() != null) {
                        writeMethods.add(pd.getWriteMethod());
                    }
                });
            } catch (IntrospectionException ex) {
                throw new IllegalStateException("Can't introspect bean methods for " + beanType, ex);
            }
        }
    }

}
