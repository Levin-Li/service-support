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
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.IntrospectionException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.function.Supplier;


/**
 * 对象代理包装工具。
 * <p>
 * 当前主要提供两类能力：创建通用 CGLIB 代理，以及在代理基础上构造只读对象。
 * 只读对象会拦截 JavaBean 写方法，并对可以安全识别的集合或 Map 读方法返回只读视图。
 *
 * @author echo
 */
public abstract class ObjectWrapperUtils {

    private static final Object[] EMPTY_ARGS = new Object[0];
    private static final Map<Class<?>, BeanMethodMetadata> BEAN_METHOD_CACHE = new ConcurrentReferenceHashMap<>();
    private static final Map<Method, MethodInvoker> METHOD_INVOKER_CACHE = new ConcurrentReferenceHashMap<>();

    /**
     * 将已有对象包装为始终只读的代理对象。
     *
     * @param originalObject 原始对象
     * @param <T>            对象类型
     * @return 只读代理对象；原始对象为 {@code null} 时返回 {@code null}
     */
    public static <T> T wrapper2Readonly(T originalObject) {
        if (originalObject == null) {
            return null;
        }
        return wrapper2Readonly(originalObject, null, (Supplier<Boolean>) null);
    }

    /**
     * 将已有对象包装为可动态判断是否只读的代理对象。
     *
     * @param originalObject 原始对象
     * @param isReadonly     只读状态提供者，返回 {@code true} 时禁止写方法
     * @param <T>            对象类型
     * @return 只读代理对象；原始对象为 {@code null} 时返回 {@code null}
     */
    public static <T> T wrapper2Readonly(T originalObject, Supplier<Boolean> isReadonly) {
        if (originalObject == null) {
            return null;
        }
        return wrapper2Readonly(originalObject, null, isReadonly);
    }

    /**
     * 按指定类型创建只读代理对象，适用于没有原始对象、只需要代理类型本身默认行为的场景。
     *
     * @param wrapperTargetClass 代理目标类型
     * @param isReadonly         只读状态提供者，返回 {@code true} 时禁止写方法
     * @param <T>                对象类型
     * @return 只读代理对象；目标类型为 {@code null} 时返回 {@code null}
     */
    public static <T> T wrapper2Readonly(Class<T> wrapperTargetClass, Supplier<Boolean> isReadonly) {
        return wrapper2Readonly(null, wrapperTargetClass, isReadonly);
    }

    /**
     * 将对象或目标类型包装为只读代理对象。
     * <p>
     * 如果传入原始对象，读方法优先从原始对象取值，以保持代理对象看到的是原始对象的实时状态。
     * 代理创建完成后才启用只读拦截，避免 CGLIB 构造代理对象期间触发 setter 时被误拦截。
     *
     * @param originalObject     原始对象，可为空
     * @param wrapperTargetClass 代理目标类型，可为空；为空时从原始对象解析
     * @param isReadonly         只读状态提供者；为空时默认始终只读
     * @param <T>                对象类型
     * @return 只读代理对象；原始对象和目标类型都为空时返回 {@code null}
     */
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

    /**
     * 使用原始对象的真实类型创建代理，并交给自定义方法覆盖处理器决定拦截逻辑。
     *
     * @param originalObject       原始对象
     * @param methodOverrideHandler 方法覆盖处理器，可为空
     * @param newEnhanceInterfaces 代理对象额外实现的接口
     * @param <T>                  对象类型
     * @return 代理对象；原始对象为 {@code null} 时返回 {@code null}
     */
    public static <T> T wrapperByProxy(T originalObject, MethodOverrideHandler methodOverrideHandler, Class<?>... newEnhanceInterfaces) {
        return wrapperByProxy(originalObject, null, methodOverrideHandler, newEnhanceInterfaces);
    }

    /**
     * 创建 CGLIB 代理对象。
     * <p>
     * 当存在原始对象时，代理方法默认转调原始对象；当不存在原始对象时，默认调用代理父类方法。
     * {@code methodOverrideHandler} 可以在默认调用前返回覆盖值，或抛出异常阻止调用。
     *
     * @param originalObject        原始对象，可为空
     * @param proxyTargetClass      代理目标类型，可为空；为空时从原始对象解析
     * @param methodOverrideHandler 方法覆盖处理器，可为空
     * @param newEnhanceInterfaces  代理对象额外实现的接口
     * @param <T>                   代理对象类型
     * @return 代理对象；无法解析代理目标类型时返回 {@code null}
     */
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

    /**
     * 只读代理的方法覆盖处理器。
     * <p>
     * 该处理器只负责拦截 JavaBean 写方法；读方法返回集合或 Map 时的只读视图包装由代理处理器统一完成。
     */
    public static class ReadonlyMethodOverrideHandler implements MethodOverrideHandler {

        final Class<?> proxyTargetClass;
        final boolean defaultReadonly;
        final Supplier<Boolean> isReadonly;
        private volatile boolean readonlyEnabled = false;

        /**
         * 创建一个可动态判断只读状态的处理器。
         *
         * @param proxyTargetClass 代理目标类型
         * @param isReadonly       只读状态提供者，返回 {@code true} 时禁止写方法
         */
        public ReadonlyMethodOverrideHandler(Class<?> proxyTargetClass, Supplier<Boolean> isReadonly) {
            this(proxyTargetClass, false, isReadonly);
        }

        /**
         * 创建只读处理器。
         *
         * @param proxyTargetClass 代理目标类型
         * @param defaultReadonly  未提供动态只读状态时的默认只读值
         * @param isReadonly       只读状态提供者，可为空
         */
        private ReadonlyMethodOverrideHandler(Class<?> proxyTargetClass, boolean defaultReadonly, Supplier<Boolean> isReadonly) {
            this.proxyTargetClass = proxyTargetClass;
            this.defaultReadonly = defaultReadonly;
            this.isReadonly = isReadonly;
        }

        /**
         * 创建始终只读的处理器。
         *
         * @param proxyTargetClass 代理目标类型
         * @return 始终只读的处理器
         */
        static ReadonlyMethodOverrideHandler alwaysReadonly(Class<?> proxyTargetClass) {
            return new ReadonlyMethodOverrideHandler(proxyTargetClass, true, null);
        }

        /**
         * 启用只读拦截。
         * <p>
         * 代理对象创建过程中会调用构造器和初始化逻辑，因此需要在代理创建完成后再开启只读限制。
         */
        void enableReadonly() {
            this.readonlyEnabled = true;
        }

        /**
         * 判断当前是否处于只读状态。
         *
         * @return {@code true} 表示需要拦截写方法
         */
        protected boolean isReadonly() {
            if (!readonlyEnabled) {
                return false;
            }
            return isReadonly != null ? Boolean.TRUE.equals(isReadonly.get()) : defaultReadonly;
        }

        /**
         * 拦截只读对象的 JavaBean 写方法。
         *
         * @param proxy 代理对象或原始对象
         * @param method 当前调用的方法
         * @param args   方法参数
         * @return 这里不覆盖正常返回值，返回 {@code null} 表示交给后续默认调用
         * @throws Throwable 写方法在只读状态下会抛出 {@link UnsupportedOperationException}
         */
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

    /**
     * 解析代理目标类型。
     *
     * @param originalObject   原始对象
     * @param proxyTargetClass 显式指定的代理目标类型
     * @return 优先返回显式目标类型，否则从原始对象中解析真实目标类型
     */
    private static Class<?> resolveProxyTargetClass(Object originalObject, Class<?> proxyTargetClass) {
        if (proxyTargetClass != null) {
            return proxyTargetClass;
        }
        return originalObject != null ? AopProxyUtils.ultimateTargetClass(originalObject) : null;
    }

    /**
     * 获取并缓存 JavaBean 读写方法元数据。
     *
     * @param beanType Bean 类型
     * @return Bean 方法元数据
     */
    private static BeanMethodMetadata getBeanMethodMetadata(Class<?> beanType) {
        return BEAN_METHOD_CACHE.computeIfAbsent(beanType, BeanMethodMetadata::new);
    }

    /**
     * 判断方法是否为目标类型的 JavaBean 写方法。
     *
     * @param beanType Bean 类型
     * @param method   待判断方法
     * @return {@code true} 表示该方法是写方法
     */
    private static boolean isReadonlyWriteMethod(Class<?> beanType, Method method) {
        return beanType != null && getBeanMethodMetadata(beanType).writeMethods.contains(method);
    }

    /**
     * 判断方法是否为目标类型的 JavaBean 读方法。
     *
     * @param beanType Bean 类型
     * @param method   待判断方法
     * @return {@code true} 表示该方法是读方法
     */
    private static boolean isReadonlyReadMethod(Class<?> beanType, Method method) {
        return beanType != null && getBeanMethodMetadata(beanType).readMethods.contains(method);
    }

    /**
     * 根据 JavaBean 读方法声明的返回类型，将集合或 Map 的返回值包装为只读视图。
     * <p>
     * 这里只在方法返回类型可以被确定为标准集合接口时包装，避免把业务自定义集合实现替换成 JDK 的只读包装类。
     *
     * @param value            原始返回值
     * @param proxyTargetClass 代理目标类型，用于解析继承链上的泛型实参
     * @param method           当前调用的读方法
     * @return 只读包装后的返回值，或原始返回值
     */
    private static Object wrapReadonlyLiveValue(Object value, Class<?> proxyTargetClass, Method method) {
        if (value == null) {
            return null;
        }

        final Class<?> expectedType = resolveReadonlyWrapperType(proxyTargetClass, method);

        if (expectedType == null) {
            return value;
        }

        if (expectedType == NavigableMap.class && value instanceof NavigableMap<?, ?> navigableMap) {
            return Collections.unmodifiableNavigableMap(navigableMap);
        }

        if (expectedType == SortedMap.class && value instanceof SortedMap<?, ?> sortedMap) {
            return Collections.unmodifiableSortedMap(sortedMap);
        }

        if (expectedType == Map.class && value instanceof Map<?, ?> map) {
            return Collections.unmodifiableMap(map);
        }

        if (expectedType == NavigableSet.class && value instanceof NavigableSet<?> navigableSet) {
            return Collections.unmodifiableNavigableSet(navigableSet);
        }

        if (expectedType == SortedSet.class && value instanceof SortedSet<?> sortedSet) {
            return Collections.unmodifiableSortedSet(sortedSet);
        }

        if (expectedType == Set.class && value instanceof Set<?> set) {
            return Collections.unmodifiableSet(set);
        }

        if (expectedType == List.class && value instanceof List<?> list) {
            return Collections.unmodifiableList(list);
        }

        if (expectedType == Collection.class && value instanceof Collection<?> collection) {
            return Collections.unmodifiableCollection(collection);
        }

        return value;
    }

    /**
     * 解析只读包装应该依据的返回类型。
     * <p>
     * Java 的 {@link Method#getReturnType()} 只能拿到擦除后的类型，例如 {@code T extends List<String>} 会被擦除成
     * {@code List}，容易误判为可以安全包装。这里先结合 {@code proxyTargetClass} 用 Spring 泛型工具解析真实返回类型；
     * 如果返回类型中仍然存在未解析的泛型变量或通配符，则返回 {@code null}，由调用方保留原对象。
     *
     * @param proxyTargetClass 代理目标类型
     * @param method           当前调用的读方法
     * @return 可安全用于只读包装判断的返回类型；无法安全解析时返回 {@code null}
     */
    private static Class<?> resolveReadonlyWrapperType(Class<?> proxyTargetClass, Method method) {

        if (proxyTargetClass == null || method == null) {
            return null;
        }

        final Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
        final Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(proxyTargetClass);

        if (hasUnresolvedGenericTypes(bridgedMethod.getGenericReturnType(), typeVariableMap, new LinkedHashSet<>())) {
            return null;
        }

        final ResolvableType returnType = ResolvableType.forMethodReturnType(
                bridgedMethod,
                proxyTargetClass
        );

        return returnType == ResolvableType.NONE || returnType.resolve() == null || returnType.hasUnresolvableGenerics()
                ? null
                : returnType.resolve();
    }

    /**
     * 判断返回类型中是否仍包含无法由目标类型解析出来的泛型信息。
     * <p>
     * 如果泛型变量没有在 {@code proxyTargetClass} 的继承关系中绑定到具体类型，Spring 可能会退回到泛型上界。
     * 这类结果对只读包装不够安全，因此视为未解析，避免产生 {@link ClassCastException} 或改变原返回值契约。
     *
     * @param type            待检查的泛型类型
     * @param typeVariableMap 目标类型解析出的泛型变量映射
     * @param visiting        当前递归路径中的泛型变量，用于避免循环引用
     * @return {@code true} 表示仍有未解析泛型，应保留原对象
     */
    private static boolean hasUnresolvedGenericTypes(Type type, Map<TypeVariable, Type> typeVariableMap, Set<TypeVariable> visiting) {
        if (type instanceof TypeVariable<?> typeVariable) {
            Type resolvedType = typeVariableMap.get(typeVariable);

            if (resolvedType == null || resolvedType.equals(typeVariable) || !visiting.add(typeVariable)) {
                return true;
            }

            try {
                return hasUnresolvedGenericTypes(resolvedType, typeVariableMap, visiting);
            } finally {
                visiting.remove(typeVariable);
            }
        }

        if (type instanceof ParameterizedType parameterizedType) {
            for (Type actualTypeArgument : parameterizedType.getActualTypeArguments()) {
                if (hasUnresolvedGenericTypes(actualTypeArgument, typeVariableMap, visiting)) {
                    return true;
                }
            }

            Type ownerType = parameterizedType.getOwnerType();
            return ownerType != null && hasUnresolvedGenericTypes(ownerType, typeVariableMap, visiting);
        }

        if (type instanceof GenericArrayType genericArrayType) {
            return hasUnresolvedGenericTypes(genericArrayType.getGenericComponentType(), typeVariableMap, visiting);
        }

        if (type instanceof Class<?> returnClass && returnClass.isArray()) {
            return hasUnresolvedGenericTypes(returnClass.getComponentType(), typeVariableMap, visiting);
        }

        return type instanceof WildcardType;
    }

    /**
     * 获取并缓存方法调用器。
     *
     * @param method 待调用方法
     * @return 方法调用器
     */
    private static MethodInvoker getMethodInvoker(Method method) {
        return METHOD_INVOKER_CACHE.computeIfAbsent(method, MethodInvoker::new);
    }

    /**
     * 合并代理对象需要实现的接口。
     * <p>
     * 所有代理对象都会实现 {@link ProxyWrapperObject}，便于外部获取代理目标类型和原始对象。
     *
     * @param newEnhanceInterfaces 额外增强接口
     * @return 去重后的接口数组
     */
    private static Class<?>[] mergeInterfaces(Class<?>... newEnhanceInterfaces) {
        LinkedHashSet<Class<?>> interfaces = new LinkedHashSet<>();
        interfaces.add(ProxyWrapperObject.class);

        if (newEnhanceInterfaces != null) {
            interfaces.addAll(Arrays.asList(newEnhanceInterfaces));
        }

        return interfaces.toArray(new Class<?>[0]);
    }


    /**
     * CGLIB 方法拦截器。
     * <p>
     * 负责处理代理元信息方法、自定义覆盖逻辑、原始对象转调，以及只读读方法的返回值包装。
     */
    private static class ProxyWrapperHandler implements ProxyWrapperObject, MethodInterceptor {

        private final Object originalObject;
        private final Class<?> proxyTargetClass;
        private final MethodOverrideHandler methodOverrideHandler;

        /**
         * 创建代理方法拦截器。
         *
         * @param originalObject        原始对象，可为空
         * @param proxyTargetClass      代理目标类型
         * @param methodOverrideHandler 方法覆盖处理器，可为空
         */
        ProxyWrapperHandler(Object originalObject, Class<?> proxyTargetClass, MethodOverrideHandler methodOverrideHandler) {
            this.originalObject = originalObject;
            this.proxyTargetClass = proxyTargetClass;
            this.methodOverrideHandler = methodOverrideHandler;
        }

        /**
         * 获取代理对象类型
         *
         * @return 代理目标类型
         */
        @Override
        public <T> Class<T> proxyTargetClass() {
            return (Class<T>) proxyTargetClass;
        }

        /**
         * 获取原代理被对象
         *
         * @return 原始对象
         */
        @Override
        public <T> T getOriginalObject() {
            return (T) originalObject;
        }

        /**
         * 拦截代理对象上的方法调用。
         * <p>
         * 调用顺序为：先处理 {@link ProxyWrapperObject} 元信息方法，再执行自定义覆盖处理器；
         * 如果没有覆盖值，则优先转调原始对象，否则调用 CGLIB 父类方法。
         *
         * @param proxyObj    CGLIB 生成的代理对象
         * @param method      当前调用的方法
         * @param params      方法参数
         * @param methodProxy CGLIB 方法代理
         * @return 方法调用结果
         * @throws Throwable 方法调用或覆盖处理过程中抛出的异常
         */
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
                return isReadonlyReadMethod(proxyTargetClass, method) ? wrapReadonlyLiveValue(value, proxyTargetClass, method) : value;
            }

            //默认调用父类方法
            return methodProxy.invokeSuper(proxyObj, params);
        }
    }

    /**
     * 基于 {@link MethodHandle} 的方法调用器。
     * <p>
     * 方法调用器会被缓存，避免代理每次转调原始对象时重复做反射可访问性处理和方法句柄创建。
     */
    private static final class MethodInvoker {

        private final MethodHandle spreader;

        /**
         * 创建方法调用器。
         *
         * @param method 待调用方法
         */
        private MethodInvoker(Method method) {
            this.spreader = initSpreader(method);
        }

        /**
         * 初始化可以接收 {@code Object[]} 参数的 {@link MethodHandle}。
         *
         * @param method 待调用方法
         * @return 参数已展开适配的方法句柄
         */
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

        /**
         * 在指定目标对象上调用方法。
         *
         * @param target 调用目标对象
         * @param args   方法参数，可为空
         * @return 方法调用返回值
         * @throws Throwable 方法调用过程中抛出的异常
         */
        private Object invoke(Object target, Object[] args) throws Throwable {
            return spreader.invoke(target, args != null ? args : EMPTY_ARGS);
        }
    }

    /**
     * JavaBean 方法元数据。
     * <p>
     * 通过 {@link Introspector} 识别属性读写方法，用于判断只读代理中哪些方法需要拦截。
     */
    private static final class BeanMethodMetadata {

        private final Set<Method> readMethods = new LinkedHashSet<>();
        private final Set<Method> writeMethods = new LinkedHashSet<>();

        /**
         * 解析指定 Bean 类型的读写方法。
         *
         * @param beanType Bean 类型
         */
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
