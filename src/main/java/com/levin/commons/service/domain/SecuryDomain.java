package com.levin.commons.service.domain;


import java.lang.annotation.*;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
public @interface SecuryDomain {

    /**
     * 字段名称
     *
     * @return String
     */
    String value() default "";

    /**
     * 注入类型
     *
     * @return String
     */
    String type() default "default";

    /**
     * 强制注入
     * @return boolean
     */
    boolean coercion() default true;

}
