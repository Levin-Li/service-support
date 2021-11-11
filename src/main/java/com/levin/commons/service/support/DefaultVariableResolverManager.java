package com.levin.commons.service.support;

import lombok.extern.slf4j.Slf4j;
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

import java.util.*;
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

    private final List<VariableResolver> defaultVariableResolvers = new LinkedList<>();

    private final VariableInjector variableInjector;

    @Nullable
    private BeanFactory beanFactory;

    @Nullable
    private ApplicationContext applicationContext;


    public DefaultVariableResolverManager(VariableInjector variableInjector) {

        this.variableInjector = variableInjector;

        //加入默认的空解析器
        this.add(Collections.emptyMap());

    }

    /**
     * @return
     */
    @Override
    public List<VariableResolver> getVariableResolvers() {
        return Collections.unmodifiableList(defaultVariableResolvers);
    }

    /**
     * @param variableResolvers
     */
    @Override
    public synchronized VariableResolverManager add(List<VariableResolver> variableResolvers) {

        List<VariableResolver> tempListRef = defaultVariableResolvers;

        variableResolvers
                .stream()
                .filter(Objects::nonNull)
                .filter(variableResolver -> !(tempListRef.contains(variableResolver)))
                .forEachOrdered(tempListRef::add);

        return this;
    }


    /**
     * @param suppliers
     */
    @Override
    public VariableResolverManager add(Supplier<List<Map<String, Object>>>... suppliers) {

        add(variableInjector.getVariableResolvers(suppliers));

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
