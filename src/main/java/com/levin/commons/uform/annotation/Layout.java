package com.levin.commons.uform.annotation;


/**
 *
 * 主要参考 https://uformjs.org/
 *
 * 表单字段注解
 *
 *
 */
public @interface Layout {

    enum Type {
        Default,
        Grid,

    }

    //布局名称
    String value() default "";


    String title() default "";


    Type type();

}
