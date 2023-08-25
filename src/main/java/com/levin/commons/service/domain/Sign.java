package com.levin.commons.service.domain;


import java.lang.annotation.*;

/**
 * 签名标识，用于标识一个参数将参与签名
 *
 * @author Laishr
 * @version 1.0, 2015/11/06
 * Copyright (c) 2015.
 */

@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Sign {

    /**
     * @return
     */
    String value() default "";

    /**
     * 签名顺序
     *
     * @return
     */
    int order() default 1;

}
