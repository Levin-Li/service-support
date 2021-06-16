package com.levin.commons.service.domain;


import java.lang.annotation.*;

/*
 *
 * 签名验证注解
 *
 *
 */

/**
 * 签名标识，用于标识一个参数将参与签名
 */
@Target({ElementType.FIELD, ElementType.PARAMETER,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SignVerifier {


}
