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
import org.springframework.boot.type.classreading.ConcurrentReferenceCachingMetadataReaderFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.*;

import static com.levin.commons.utils.ClassUtils.formatPackages;


/**
 * 代理额扫描
 */

@Slf4j
public class ProxyBeanRegistrar
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

    public static final Map<String, Object> registerBeans = new ConcurrentReferenceHashMap<>();

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

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                metadata.getAnnotationAttributes(EnableProxyBean.class.getName()));

        if (attributes == null || attributes.isEmpty()) {
            return;
        }

        List<String> registerPackages = formatPackages(attributes.getStringArray("registerPackages"));

        List<Class<?>> registerTypes = Arrays.asList(attributes.getClassArray("registerTypes"));

        //获取收集到的扫描包列表
        ScanPackagesHolder scanPackagesHolder = ScanPackagesHolder.get(beanFactory);

        scanPackagesHolder.getScanPairs().values().stream()
                .filter(scanPair -> isMatch(registerPackages, registerTypes, scanPair))
                .forEachOrdered(scanPair -> {
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


                                                registry.registerBeanDefinition(type.getName(), builder.getBeanDefinition());


                                                registerBeans.put(scanPair.scanType.getName() + "_" + type.getName(), true);

                                            }

                                    );

                        }
                );

    }

    private boolean isMatch(List<String> registerPackages, List<Class<?>> registerTypes, ScanPackagesHolder.ScanPair scanPair) {
        return (registerTypes.isEmpty() || registerTypes.contains(scanPair.scanType))
                && (registerPackages.isEmpty() || registerPackages.stream()
                .filter(StringUtils::hasText)
                .filter(p -> scanPair.scanType.getName().startsWith(p.trim()))
                .count() > 0);
    }


    private synchronized Set<Class<?>> scan(Set<String> packages, Class<? extends Annotation>... annotationTypes) {

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

}
