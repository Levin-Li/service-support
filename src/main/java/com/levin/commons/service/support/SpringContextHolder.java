package com.levin.commons.service.support;

import com.levin.commons.conditional.ConditionalOn;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

@Configuration("com.levin.commons.service.support.SpringContextHolder")
@Slf4j
@ConditionalOn(action = ConditionalOn.Action.OnMissingBean, types = SpringContextHolder.class)
public class SpringContextHolder implements EnvironmentAware,
        BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware, ResourceLoaderAware {

    @PostConstruct
    public void init() {
        log.debug("*** on PostConstruct ...");
    }

    @Getter
    private static ApplicationContext applicationContext;

    @Getter
    private static BeanFactory beanFactory;

    @Getter
    private static ResourceLoader resourceLoader;

    @Getter
    private static ClassLoader classLoader;

    @Getter
    private static Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
    }

    @Override
    public void setBeanFactory(BeanFactory factory) throws BeansException {
        beanFactory = factory;
    }

    @Override
    public void setResourceLoader(ResourceLoader loader) {
        resourceLoader = loader;
    }

    @Override
    public void setBeanClassLoader(ClassLoader loader) {
        classLoader = loader;
    }

    @Override
    public void setEnvironment(Environment env) {
        environment = env;
    }

}