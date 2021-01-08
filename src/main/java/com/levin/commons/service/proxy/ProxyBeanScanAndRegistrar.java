/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.levin.commons.service.proxy;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Consumer;

import static com.levin.commons.utils.ClassUtils.formatPackages;


/**
 * 代理额扫描
 *
 * @author llw
 */

@Slf4j
public class ProxyBeanScanAndRegistrar
        implements
        BeanFactoryAware,
        EnvironmentAware,
        BeanClassLoaderAware,
        ResourceLoaderAware,
//        ApplicationContextAware,
        ImportBeanDefinitionRegistrar {

    private ClassLoader classLoader;

    private ResourceLoader resourceLoader;

    private BeanFactory beanFactory;

    private Environment environment;

    private MetadataReaderFactory metadataReaderFactory;

    /**
     * 已经注册的 bean名字
     */
    private static final Map<String, Object> registerBeans = new ConcurrentReferenceHashMap<>();

    /**
     * 已经扫描，但未注册的
     */
    private static final List<ScanPair> notRegisterScanPairs = new Vector<>();

    //扫描类
    final ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(
            false) {
        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return true;
        }
    };

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.scanner.setEnvironment(environment);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.scanner.setResourceLoader(resourceLoader);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

        AnnotationAttributes attributes1 = AnnotationAttributes.fromMap(
                metadata.getAnnotationAttributes(ProxyBeanScan.class.getName()));

        AnnotationAttributes attributes2 = AnnotationAttributes.fromMap(
                metadata.getAnnotationAttributes(ProxyBeanScans.class.getName()));


        Consumer<AnnotationAttributes> consumer = attributes -> {

            if (attributes != null) {

                String[] basePackages = attributes.getStringArray("basePackages");

                Class<?>[] basePackageClasses = attributes.getClassArray("basePackageClasses");

                Class<? extends FactoryBean> factoryBeanClass = attributes.getClass("factoryBeanClass");
                Class<? extends InvocationHandler> invocationHandlerClass = attributes.getClass("invocationHandlerClass");

                Class<? extends Annotation> scanType = attributes.getClass("scanType");

                boolean lazyInit = attributes.getBoolean("lazyInit");
                boolean onlyScan = attributes.getBoolean("onlyScan");

                ScanPair scanPair = new ScanPair()
                        .setLazyInit(lazyInit)
                        .setOnlyScan(onlyScan)
                        .setScanType(scanType)
                        .setFactoryBeanClass(factoryBeanClass)
                        .setInvocationHandlerClass(invocationHandlerClass);

                updateScanPairBasePackages(metadata, scanPair, basePackages, basePackageClasses);

                if (onlyScan) {
                    //只是扫描，不注册 bean
                    notRegisterScanPairs.add(scanPair);
                } else {
                    registerBeans(registry, scanPair);
                }

            }

        };

        //  consumer.accept(attributes1);

        Optional.ofNullable(attributes1).ifPresent(e -> consumer.accept(e));


        Optional.ofNullable(attributes2).ifPresent(e -> {
            for (AnnotationAttributes attributes : e.getAnnotationArray("value")) {
                consumer.accept(attributes);
            }
        });


        //开始处理 EnableProxyBean注解

        Optional.ofNullable(AnnotationAttributes.fromMap(
                metadata.getAnnotationAttributes(EnableProxyBean.class.getName()))).ifPresent(attrs -> {

            log.info("process annotation EnableProxyBean,notRegisterScanPairs: " + notRegisterScanPairs);

            //重复扫描注册，不删除
            notRegisterScanPairs.stream()
                    .filter(scanPair -> isMatch(Arrays.asList(attrs.getStringArray("registerPackages")), Arrays.asList(attrs.getClassArray("registerTypes")), scanPair))
                    .forEach(scanPair -> registerBeans(registry, scanPair));

            //重复注册，不删除
//            for (ScanPair scanPair : new ArrayList<>(notRegisterScanPairs)) {
//                if (isMatch(Arrays.asList(registerPackages), Arrays.asList(registerTypes), scanPair)) {
//                    registerBeans(registry, scanPair);
//                }
//            }

        });


//        if (attributes1 == null && attributes2 == null) {
//            throw new IllegalArgumentException(getClass() + " only support [ProxyBeanScan or ProxyBeanScans]");
//        }

    }

    private static void updateScanPairBasePackages(AnnotationMetadata metadata, ScanPair scanPair, String[] basePackages, Class<?>[] basePackageClasses) {

        Set<String> packagesToScan = scanPair.scanPackages;

        packagesToScan.addAll(Arrays.asList(basePackages));

        for (Class<?> basePackageClass : basePackageClasses) {
            packagesToScan.add(ClassUtils.getPackageName(basePackageClass));
        }

        if (!packagesToScan.isEmpty()) {
            //如果没有指定，就默认加入被注解所在的包
            String packageName = ClassUtils.getPackageName(metadata.getClassName());
            if (StringUtils.hasText(packageName)) {
                packagesToScan.add(packageName);
            }
        }
    }


    public void registerBeans(BeanDefinitionRegistry registry, ScanPair scanPair) {

        scan(scanPair.getScanPackages(), scanPair.scanType).stream()
                .filter(type -> !registerBeans.containsKey(scanPair.scanType.getName() + "_" + type.getName()))
                .forEachOrdered(type -> {

                            Class<? extends FactoryBean> factoryBeanClass = scanPair.factoryBeanClass;

                            Class<?> invocationHandlerClass = scanPair.invocationHandlerClass;

                            BeanDefinitionBuilder builder = BeanDefinitionBuilder
                                    .genericBeanDefinition(factoryBeanClass)
                                    .setLazyInit(scanPair.lazyInit);


                            int handlerClassModifiers = invocationHandlerClass.getModifiers();

                            int factoryBeanClassModifiers = factoryBeanClass.getModifiers();

                            //
                            boolean isHandlerClassAbstract = invocationHandlerClass == Void.class
                                    || Modifier.isInterface(handlerClassModifiers)
                                    || Modifier.isAbstract(handlerClassModifiers);

                            boolean isFactoryBeanClassAbstract = Modifier.isInterface(factoryBeanClassModifiers) || Modifier.isAbstract(factoryBeanClassModifiers);


                            if (isFactoryBeanClassAbstract) {
                                throw new BeanDefinitionValidationException("" + ProxyBeanScan.class + " factoryBeanClass is an abstract class");
                            }

                            if (factoryBeanClass == ProxyFactoryBean.class
                                    && isHandlerClassAbstract) {
                                throw new BeanDefinitionValidationException("" + ProxyBeanScan.class + " ProxyFactoryBean require invocationHandlerClass");
                            }

                            //不做限制
                            if (ClassUtils.hasConstructor(factoryBeanClass, Class.class, Class.class)) {
                                builder.addConstructorArgValue(type)
                                        .addConstructorArgValue(invocationHandlerClass);
                            } else if (ClassUtils.hasConstructor(factoryBeanClass, Class.class)) {
                                builder.addConstructorArgValue(type);
                            }

                            //如果处理类是抽象类未实现
//                                if (isHandlerClassAbstract) {
//                                    //如果有一个构造参数
//                                    if (ClassUtils.hasConstructor(factoryBeanClass, Class.class)) {
//                                        builder.addConstructorArgValue(type);
//                                    }
//                                } else {
//                                    if (ClassUtils.hasConstructor(factoryBeanClass, Class.class, Class.class)) {
//                                        builder.addConstructorArgValue(type)
//                                                .addConstructorArgValue(invocationHandlerClass);
//                                    } else if (ClassUtils.hasConstructor(factoryBeanClass, Class.class)) {
//                                        builder.addConstructorArgValue(type);
//                                    }
//                                }
                            log.info("registerBeanDefinition beanClass: " + type.getName()
                                    + "(@" + scanPair.scanType.getName() + ") factoryBeanClass:" + factoryBeanClass);

                            final String key = scanPair.scanType.getName() + "_" + type.getName();

                            final String beanName = (registry.isBeanNameInUse(type.getName())
                                    || registry.containsBeanDefinition(type.getName())) ? key : type.getName();

                            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());

                            registerBeans.put(key, true);
                        }

                );
    }

    private static boolean isMatch(List<String> registerPackages, List<Class<?>> registerTypes, ScanPair scanPair) {
        return (registerTypes == null || registerTypes.isEmpty() || registerTypes.contains(scanPair.scanType))
                && (registerPackages == null || registerPackages.isEmpty() || registerPackages.stream()
                .filter(StringUtils::hasText)
                .filter(p -> scanPair.scanType.getName().startsWith(p.trim()))
                .count() > 0);
    }


    public synchronized Set<Class<?>> scan(Set<String> packages, Class<? extends Annotation>... annotationTypes) {

        if (packages.isEmpty()) {
            return Collections.emptySet();
        }

        List<String> minList = formatPackages(packages);

        scanner.resetFilters(false);

        for (Class<? extends Annotation> annotationType : annotationTypes) {
            scanner.addIncludeFilter(new AnnotationTypeFilter(annotationType));
        }

        Set<Class<?>> entitySet = new LinkedHashSet<>();

        for (String basePackage : minList) {
            if (StringUtils.hasText(basePackage)) {
                for (BeanDefinition candidate : scanner.findCandidateComponents(basePackage)) {
                    try {
                        //加载类
                        entitySet.add(ClassUtils.forName(candidate.getBeanClassName(), classLoader));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        log.debug(scanner.hashCode() + " Scan packages:" + minList + ",types:" + Arrays.asList(annotationTypes) + ",result:" + entitySet);

        return entitySet;
    }

    @Data
    @Accessors(chain = true)
    public static class ScanPair {

        /**
         * 只是扫描不注册bean
         */
        boolean onlyScan;

        /**
         * 延迟初始化
         */
        boolean lazyInit;

        /**
         * 要扫描的注解
         *
         * @return
         */
        Class<? extends Annotation> scanType;

        /**
         * factoryBeanClass
         *
         * @return
         */
        Class<? extends FactoryBean> factoryBeanClass;


        /**
         *
         */
        Class<?> invocationHandlerClass;

        /**
         *
         */
        final Set<String> scanPackages = new LinkedHashSet<>();

    }
}
