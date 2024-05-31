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
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
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
     * 获取指定类型（或注解类型）、指定包名的bean列表
     *
     * @param context
     * @param type              普通类型或是注解类型
     * @param pkgNamePrefixList 类包名前缀
     * @param <T>
     * @return
     */
    public static <T> List<T> findBeanByPkgName(ApplicationContext context, @NonNull Type type, String... pkgNamePrefixList) {
        return findBeans(context, type, true, pkgNamePrefixList);
    }

    /**
     * 获取指定类型（或注解类型）、指定bean名称前缀的bean列表
     *
     * @param context
     * @param type               普通类型或是注解类型
     * @param beanNamePrefixList bean名前缀
     * @param <T>
     * @return
     */
    public static <T> List<T> findBeanByBeanName(ApplicationContext context, @NonNull Type type, String... beanNamePrefixList) {
        return findBeans(context, type, false, beanNamePrefixList);
    }


    /**
     * 查找bean
     * @param context
     * @param type
     * @param isMatchPackageNamePrefix
     * @param prefixList
     * @return
     * @param <T>
     */
    private static <T> List<T> findBeans(ApplicationContext context, @NonNull Type type, boolean isMatchPackageNamePrefix, String... prefixList) {

        if (context == null) {
            context = getApplicationContext();
        }

        ResolvableType resolvableType = ResolvableType.forType(type);

        //按类型或是注解扫描
        Map<String, T> beans = resolvableType.resolve().isAnnotation() ? (Map<String, T>) context.getBeansWithAnnotation((Class<? extends Annotation>) type) : null;

        if (beans == null) {
            beans = new LinkedHashMap<>();
            for (String beanName : context.getBeanNamesForType(resolvableType)) {
                beans.put(beanName, (T) context.getBean(beanName));
            }
        }

        Predicate<Map.Entry<String, T>> filter = isMatchPackageNamePrefix ? (item) -> {
            if (prefixList == null || prefixList.length == 0) {
                return true;
            }
            String pkg = AopProxyUtils.ultimateTargetClass(item.getValue()).getPackage().getName();
            return Arrays.stream(prefixList).filter(StringUtils::hasText).anyMatch(pkg::startsWith);
        } : item -> {
            if (prefixList == null || prefixList.length == 0) {
                return true;
            }
            return Arrays.stream(prefixList).filter(StringUtils::hasText).anyMatch(item.getKey()::startsWith);

        };

        return beans.entrySet()
                .stream().filter(filter)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}
