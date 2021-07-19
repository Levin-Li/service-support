package com.levin.commons.service.domain;


import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

/**
 *
 * 注入域
 *
 * @author lilw
 *
 */

public @interface InjectVars {

    /**
     * 多个注入表达式
     *
     * @return
     */
    InjectVar[] value();

    /**
     * 备注
     * 当变量找不到，应该抛出异常包含的备注信息
     *
     * @return
     */
    String remark() default "";

}
