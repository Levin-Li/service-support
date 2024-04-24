package com.levin.commons.service.domain;


import java.lang.annotation.*;


/**
 * 自定义控制器方法禁止注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DisableApiOperation {

    /**
     * 禁止的接口
     * <p>
     * 方法签名 或是方法名称，或是swagger Operation注解的方法名称，summary 属性值
     *
     * @return
     */
    String[] value() default {};

    /**
     * 备注
     *
     * @return
     */
    String remark() default "";

}
