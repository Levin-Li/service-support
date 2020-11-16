package com.levin.commons.service.proxy;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.asm.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.*;
import java.util.Collections;

@Slf4j

public class ProxyFactoryBean<T>
        implements FactoryBean<T>, ApplicationContextAware,
        InvocationHandler,
        MethodInterceptor,
        org.aopalliance.intercept.MethodInterceptor {

    public static final Object RESULT_TAKE_DOWN = new Object();

    //用于传输方法执行结果
    public static final ThreadLocal invokeResultHolder = new ThreadLocal<>();

    @Getter
    final Class<T> proxyTargetClass;


    @Getter
    boolean isSingleton = true;


    @Getter
    protected final Class<?> invocationHandlerClass;


    @Getter
    protected Object invocationHandler;


    private final Object lock = new Object();


    @Getter
    protected ApplicationContext applicationContext;


    @Getter
    @Autowired(required = false)
    protected ParameterNameDiscoverer parameterNameDiscoverer;


    public static boolean isUseSpringAop = false;


    public ProxyFactoryBean(Class<T> proxyTargetClass) {
        this(proxyTargetClass, null);
    }

    public ProxyFactoryBean(Class<T> proxyTargetClass, Class<?> invocationHandlerClass) {

        if (proxyTargetClass == null) {
            throw new IllegalArgumentException("proxyTargetClass must be set");
        }

        this.proxyTargetClass = proxyTargetClass;
        this.invocationHandlerClass = invocationHandlerClass;


        checkConfig();

    }


    protected void checkConfig() {

        if (proxyTargetClass == null) {
            throw new BeanCreationException("proxy class must be set");
        }

        //如果是本代理类必须设置，invocationHandlerClass
        if (this.getClass() == ProxyFactoryBean.class) {

            if (invocationHandlerClass == null) {
                throw new IllegalArgumentException("invocationHandlerClass must be set");
            }

            if (!(InvocationHandler.class.isAssignableFrom(invocationHandlerClass)
                    || MethodInterceptor.class.isAssignableFrom(invocationHandlerClass)
                    || org.aopalliance.intercept.MethodInterceptor.class.isAssignableFrom(invocationHandlerClass))) {

                throw new IllegalArgumentException("invocationHandlerClass type must be in[" + InvocationHandler.class.getName()
                        + "," + MethodInterceptor.class.getName() + "," + org.aopalliance.intercept.MethodInterceptor.class.getName() + "]");
            }
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    protected void tryInitHandler() {

        if (invocationHandlerClass != null && invocationHandler == null) {
            synchronized (lock) {
                if (applicationContext != null) {
                    invocationHandler = applicationContext.getAutowireCapableBeanFactory().createBean(invocationHandlerClass);
                } else {
                    invocationHandler = BeanUtils.instantiateClass(invocationHandlerClass);
                }
            }
        }

    }

    /**
     * JDK 动态代理方法
     *
     * @param proxyObj
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public final Object invoke(Object proxyObj, Method method, Object[] args) throws Throwable {
        return intercept(proxyObj, method, args, null, null);
    }

    /**
     * AOP 联盟拦截器方法
     *
     * @param methodInvocation
     * @return
     * @throws Throwable
     */
    @Override
    public final Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Object proxyObj = methodInvocation.getThis();

        Method method = methodInvocation.getMethod();

        Object[] args = methodInvocation.getArguments();

        return intercept(proxyObj, method, args, null, methodInvocation);

    }

    /**
     * 目前是最终入口
     * spring cglib 拦截器方法
     *
     * @param proxyObj
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        return intercept(proxyObj, method, args, methodProxy, null);
    }

    /**
     * 目前是最终入口
     * spring cglib 拦截器方法
     *
     * @param proxyObj
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */

    public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy methodProxy, MethodInvocation methodInvocation) throws Throwable {


        if (methodProxy == null) {
            methodProxy = findMethodProxy(proxyObj, method);
        }

        //如果是抽象方法
        boolean anAbstract = Modifier.isAbstract(method.getModifiers());

        Object returnValue = null;

        tryInitHandler();

        if (invocationHandler == null) {
            throw new IllegalStateException("invocationHandler is null");
        }

        if (ReflectionUtils.isObjectMethod(method)) {

            if (Proxy.isProxyClass(proxyObj.getClass())) {
                return method.invoke(invocationHandler, args);
            } else {
                return method.invoke(this, args);
            }

        }

        try {

            if (invocationHandler instanceof org.aopalliance.intercept.MethodInterceptor) {

                if (methodInvocation == null) {
                    //
//                    Object proxy, @Nullable Object target, Method method, @Nullable Object[] arguments,
//                            @Nullable Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers
                    try {
                        //
                        Constructor<?> constructor = ReflectiveMethodInvocation.class.getDeclaredConstructors()[0];
                        constructor.setAccessible(true);
                        methodInvocation = (MethodInvocation) constructor.newInstance(proxyObj, invocationHandler, method, args,
                                proxyTargetClass, Collections.EMPTY_LIST);
                    } catch (Exception ex) {
                        ReflectionUtils.rethrowException(ex);
                    }
                }

                returnValue = org.aopalliance.intercept.MethodInterceptor.class.cast(invocationHandler).invoke(methodInvocation);

            } else if (invocationHandler instanceof InvocationHandler) {

                returnValue = InvocationHandler.class.cast(invocationHandler).invoke(proxyObj, method, args);

            } else if (invocationHandler instanceof MethodInterceptor) {

                returnValue = MethodInterceptor.class.cast(invocationHandler).intercept(proxyObj, method, args, methodProxy);

            } else {

                throw new UnsupportedOperationException("invocationHandler [" + invocationHandler.getClass().getName() + "]");

            }

            if (anAbstract) {
                return returnValue;
            } else {
                invokeResultHolder.set(returnValue);
            }

        } catch (Throwable e) {
            if (anAbstract) {
                throw new ProxyMethodInvokeException(method.toGenericString(), e);
            } else {
                invokeResultHolder.set(new InvokeExceptionDesc(proxyObj, method, args, methodProxy, e));
            }
        }

        //继续执行父类的方法
        return methodProxy != null ? methodProxy.invokeSuper(proxyObj, args) : returnValue;

    }


    /**
     * 查找方法代理
     *
     * @param proxyObj
     * @param method
     * @return
     */
    public static MethodProxy findMethodProxy(Object proxyObj, Method method) {

        MethodProxy methodProxy = null;

        try {
            methodProxy = MethodProxy.find(proxyObj.getClass(), new Signature(method.getName(), Type.getReturnType(method), Type.getArgumentTypes(method)));

            if (methodProxy == null) {
                methodProxy = MethodProxy.find(proxyObj.getClass(), new Signature(method.getName(), Type.getMethodDescriptor(method)));
            }

        } catch (Exception e) {
        }

        return methodProxy;
    }

    @Override
    public T getObject() throws Exception {
        return createProxyObject(isUseSpringAop);
    }


    public T createProxyObject(boolean useSpringAop) {

        checkConfig();

        if (useSpringAop) {

            // SingletonTargetSource targetSource = new SingletonTargetSource(this);

            T proxy = ProxyFactory.getProxy(proxyTargetClass, this);

            if (!proxyTargetClass.isInterface()) {
                //自动装配
                proxy = (T) autowireBean(proxy);
            }

            return proxy;

        } else {

            if (proxyTargetClass.isInterface()) {
                return (T) Proxy.newProxyInstance(proxyTargetClass.getClassLoader(), new Class[]{proxyTargetClass}, this);
            } else {

                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(proxyTargetClass);
                enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
                enhancer.setCallback(this);

                return (T) autowireBean(enhancer.create());

            }

        }
    }


    protected Object autowireBean(final Object proxyBean) {
        try {
            //必须要
            applicationContext.getAutowireCapableBeanFactory().configureBean(proxyBean, proxyTargetClass.getName());
        } catch (Exception e) {
            log.warn(proxyTargetClass + "实例，自动装配错误", e);
        }

        return proxyBean;
    }

    @Override
    public Class<T> getObjectType() {
        return proxyTargetClass;
    }

    @Override
    public boolean isSingleton() {
        return this.isSingleton;
    }


    /**
     * 如果代理对象发生异常，这个方法将返回异常对象，否则返回正常的方法返回对象
     * <p/>
     * 可能获得的结果：
     * 1、正常返回
     * 2、方法执行异常，返回异常实体
     * 3、方法不需要执行代理，返回PROXY_NOOP
     *
     * @return
     */
    public static <T> T getProxyInvokeResult() {

        Object result = invokeResultHolder.get();

        invokeResultHolder.set(RESULT_TAKE_DOWN);

        if (result == RESULT_TAKE_DOWN)
            throw new IllegalStateException("执行结果已经被取走");

        if (result instanceof InvokeExceptionDesc)
            throw new ProxyMethodInvokeException(((InvokeExceptionDesc) result).ex);

        if (result instanceof NOOP)
            throw new ProxyMethodInvokeException(((NOOP) result).info);

        return (T) result;
    }


    private static class InvokeDesc {

        public final Object invokeTarget;
        public final Method method;
        public final Object[] args;

        private InvokeDesc(Object invokeTarget, Method method, Object[] args) {
            this.invokeTarget = invokeTarget;
            this.method = method;
            this.args = args;
        }

        @Override
        public String toString() {
            return method.toString();
        }
    }

    public static class NOOP extends InvokeDesc {

        public final String info;

        public NOOP(Object invokeTarget, Method method, Object[] args, String info) {
            super(invokeTarget, method, args);
            this.info = info;
        }

        @Override
        public String toString() {
            return info + ":" + super.toString();
        }
    }

    public static class InvokeExceptionDesc extends InvokeDesc {

        public final MethodProxy methodProxy;
        public final Throwable ex;

        public InvokeExceptionDesc(Object invokeTarget, Method method, Object[] args, MethodProxy methodProxy, Throwable ex) {
            super(invokeTarget, method, args);
            this.methodProxy = methodProxy;
            this.ex = ex;
        }

        @Override
        public String toString() {
            return ex.getMessage() + ":" + super.toString();
        }
    }
}
