package com.levin.commons.service.domain;


import java.lang.annotation.*;
import java.util.function.Predicate;


@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Repeatable(InjectDomains.class)
/**
 *
 * 注入域
 *
 * 可以字段，参数，方法
 *
 */
public @interface InjectDomain2 {

    /**
     * 注入域的名字
     *
     * @return
     */
    String name() default "";

    /**
     * 注入域的类型
     *
     * @return String
     */
    String type() default "";


    /**
     * 备注
     *
     * @return
     */
    String remark() default "注入字段说明：例如注入用户 ID";

}
