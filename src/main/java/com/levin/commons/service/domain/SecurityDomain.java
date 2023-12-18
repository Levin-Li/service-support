package com.levin.commons.service.domain;


import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented

/**
 *
 * 安全域
 *
 * 用来标记一个方法，参数，字段，类等数据需要安全保护
 *
 * @author lilw
 *
 */
@Deprecated
public @interface SecurityDomain {

    /**
     * 被保护的域列表
     *
     * @return
     */
    String[] value() default {};

}
