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

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动指定的注解实例化
 *
 * @see ProxyBeanRegistrar
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ProxyBeanRegistrar.class)
public @interface EnableProxyBean {

    /**
     * 要启用的注解类型
     * <p>
     * 默认启用所有的类型
     *
     * @return
     */
    Class<? extends Annotation>[] registerTypes() default {};


    /**
     * Annotation 匹配的包名
     * <p>
     * 默认不做限制
     *
     * @return
     */
    String[] registerPackages() default {};

}
