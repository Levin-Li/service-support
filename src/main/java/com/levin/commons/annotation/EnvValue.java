package com.levin.commons.annotation;

import java.lang.annotation.*;

/**
 * SPEL 表达式
 * 默认变量：
 * _val  字段当前值
 * this 为字段所在的类对象
 */

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EnvValue {

    /**
     * 是否要求值条件表达式
     *
     * @return
     */
    String condition() default "true";


    /**
     * 求值SpEL表达式
     *
     * @return
     */
    String value();

}
