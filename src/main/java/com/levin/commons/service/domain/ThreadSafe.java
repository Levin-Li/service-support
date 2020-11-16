package com.levin.commons.service.domain;


import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

/**
 * 线程安全注解，表示一个对象或是方便是线程安全的
 *
 */
public @interface ThreadSafe {

    /**
     * 描述
     *
     * @return String
     */
    String value() default "";

}
