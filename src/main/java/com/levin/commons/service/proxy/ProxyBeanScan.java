/*
 * Copyright 2012-2017 the original author or authors.
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

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author llw
 * @see ProxyBeanScanAndRegistrar
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ProxyBeanScanAndRegistrar.class)
public @interface ProxyBeanScan {

    /**
     * 要扫描的注解
     *
     * @return
     */
    Class<? extends Annotation> scanType();


    /**
     * Spring FactoryBean 工厂类
     *
     *
     * @return
     */
    Class<? extends FactoryBean> factoryBeanClass() default ProxyFactoryBean.class;

    /**
     * 执行代理类
     * <p>
     * 支持 Spring 装配
     * <p>
     * 支持3种代理类
     * <p>
     * 1-org.aopalliance.intercept.MethodInterceptor
     * <p>
     * 2-java.lang.reflect.InvocationHandler
     * <p>
     * 3-org.springframework.cglib.proxy.MethodInterceptor
     *
     * @return
     */
    Class<?> invocationHandlerClass() default Void.class;


    /**
     * 只是扫描注解，不注册Bean
     *
     * @return
     */
    boolean onlyScan() default false;


    /**
     * 延迟初始化
     *
     * @return
     */
    boolean lazyInit() default true;

    /**
     * Alias for the {@link #basePackages()} attribute. Allows for more concise annotation
     * declarations e.g.: {@code @EntityRepositoryScan("org.my.pkg")} instead of
     * {@code @EntityRepositoryScan(basePackages="org.my.pkg")}.
     *
     * @return the base packages to scan
     */
    @AliasFor("basePackages")
    String[] value() default {};

    /**
     * Base packages to scan for entities. {@link #value()} is an alias for (and mutually
     * exclusive with) this attribute.
     * <p>
     * Use {@link #basePackageClasses()} for a type-safe alternative to String-based
     * package names.
     *
     * @return the base packages to scan
     */
    @AliasFor("value")
    String[] basePackages() default {};

    /**
     * Type-safe alternative to {@link #basePackages()} for specifying the packages to
     * scan for entities. The package of each class specified will be scanned.
     * <p>
     * Consider creating a special no-op marker class or interface in each package that
     * serves no purpose other than being referenced by this attribute.
     *
     * @return classes from the base packages to scan
     */
    Class<?>[] basePackageClasses() default {};

}
