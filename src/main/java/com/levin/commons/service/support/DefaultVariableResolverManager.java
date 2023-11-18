package com.levin.commons.service.support;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

@Slf4j
public class DefaultVariableResolverManager
        implements VariableResolverManager,
        DestructionAwareBeanPostProcessor,
        Ordered,
        BeanFactoryAware,
        ApplicationContextAware,
        SmartInitializingSingleton,
        ApplicationListener<ContextRefreshedEvent>,
        DisposableBean {

    private final List<VariableResolver> defaultVariableResolvers = new CopyOnWriteArrayList<>();

    private final List<Supplier<VariableResolver>> defaultVariableResolverSuppliers = new CopyOnWriteArrayList<>();

    @Nullable
    private BeanFactory beanFactory;

    @Nullable
    private ApplicationContext applicationContext;

    private VariableInjector variableInjector;

    public DefaultVariableResolverManager(VariableInjector variableInjector) {
        this.variableInjector = variableInjector;
        Assert.notNull(variableInjector, "variableInjector is null");
    }

    /**
     * 获取注入器
     *
     * @return
     */
    @Override
    public VariableInjector getVariableInjector() {
        return variableInjector;
    }

    @Override
    public <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, boolean isRequireNotNull, Type... expectTypes) throws VariableNotFoundException {

        //保留最后一个异常，做为异常
        final AtomicReference<Throwable> exRef = new AtomicReference();

        return getVariableResolvers().stream()
                .map(resolver -> resolver.resolve(name, originalValue, false, isRequireNotNull, expectTypes))
                .map(vh -> {
                    if (vh.getValueNotFoundCause() != null) {
                        exRef.set(vh.getValueNotFoundCause());
                    }
                    return vh;
                })
                .filter(ValueHolder::hasValue)
                .findFirst()
                .orElse(ValueHolder.notValue(throwExWhenNotFound, name, exRef.get()));
    }

    /**
     * @return
     */
    @Override
    public List<VariableResolver> getVariableResolvers() {

        var result = new ArrayList<VariableResolver>(defaultVariableResolvers.size() + 7);

        //优先获取提供者变量解析器
        defaultVariableResolverSuppliers.stream().map(Supplier::get).filter(Objects::nonNull).forEach(result::add);

        result.addAll(defaultVariableResolvers);

        return result;
    }

    /**
     * @param variableResolvers
     */
    @Override
    public synchronized VariableResolverManager add(List<VariableResolver> variableResolvers) {

        Assert.notNull(variableResolvers, "variableResolvers is null");

        List<VariableResolver> tempListRef = defaultVariableResolvers;

        variableResolvers
                .stream()
                .filter(Objects::nonNull)
                .filter(variableResolver -> !(tempListRef.contains(variableResolver)))
                .forEachOrdered(tempListRef::add);

        return this;
    }

    @Override
    public VariableResolverManager addSuppliers(List<Supplier<VariableResolver>> variableResolverSuppliers) {

        Assert.notNull(variableResolverSuppliers, "variableResolverSuppliers is null");

        List<Supplier<VariableResolver>> tempListRef = defaultVariableResolverSuppliers;

        variableResolverSuppliers
                .stream()
                .filter(Objects::nonNull)
                .filter(variableResolverSupplier -> !(tempListRef.contains(variableResolverSupplier)))
                .forEachOrdered(tempListRef::add);

        return this;
    }

    private void finishRegistration() {

        log.info("start config variable resolver manager ...");

        VariableResolverManager resolverManager = beanFactory.getBean(VariableResolverManager.class);

        //取消自动加入
        //this.beanFactory.getBeanProvider(VariableResolver.class).forEach(resolverManager::add);

        this.beanFactory.getBeanProvider(VariableResolverConfigurer.class).forEach(bean -> bean.config(resolverManager));
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void destroy() throws Exception {
        defaultVariableResolvers.clear();
    }

    @Override
    public void afterSingletonsInstantiated() {

        if (this.applicationContext == null) {
            // Not running in an ApplicationContext -> register tasks early...
            finishRegistration();
        }
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

        if (this.beanFactory == null) {
            this.beanFactory = applicationContext;
        }

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext() == this.applicationContext) {
            this.finishRegistration();
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE / 2;
    }
}
