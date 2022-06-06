package com.levin.commons.ui.annotation;

import java.lang.annotation.*;

/**
 * 常量
 *
 * @author llw
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Const {
    /**
     * 常量列表
     *
     * @return
     */
    String[] value() default {};
}

