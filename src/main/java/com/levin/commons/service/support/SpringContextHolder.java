package com.levin.commons.service.support;

import com.levin.commons.conditional.ConditionalOn;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
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
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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


    /**
     * 获取指定类型、指定包名的bean列表
     *
     * @param context
     * @param type
     * @param pkgNamePrefixList 包名前缀
     * @param <T>
     * @return
     */
    public static <T> List<T> findBeanByPkgName(ApplicationContext context, @NonNull Class<T> type, String... pkgNamePrefixList) {

        if (context == null) {
            context = getApplicationContext();
        }

        return context.getBeansOfType(type).values()
                .parallelStream().filter(bean -> {
                    if (pkgNamePrefixList == null || pkgNamePrefixList.length == 0) {
                        return true;
                    }
                    String pkg = AopProxyUtils.ultimateTargetClass(bean).getPackage().getName();
                    return Arrays.stream(pkgNamePrefixList).filter(StringUtils::hasText).anyMatch(pkg::startsWith);
                })
                .collect(Collectors.toList());
    }

    /**
     * 获取指定类型、指定bean名称前缀的bean列表
     *
     * @param context
     * @param type
     * @param beanNamePrefixList
     * @param <T>
     * @return
     */
    public static <T> List<T> findBeanByBeanName(ApplicationContext context, @NonNull Class<T> type, String... beanNamePrefixList) {

        if (context == null) {
            context = getApplicationContext();
        }

        return context.getBeansOfType(type).entrySet()
                .parallelStream().filter(item -> {
                    if (beanNamePrefixList == null || beanNamePrefixList.length == 0) {
                        return true;
                    }
                    return Arrays.stream(beanNamePrefixList).filter(StringUtils::hasText).anyMatch(item.getKey()::startsWith);
                }).map(item -> item.getValue()).collect(Collectors.toList());
    }

}
