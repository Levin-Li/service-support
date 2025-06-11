package com.levin.commons.ui.annotation;

import java.lang.annotation.*;

/**
 * ui忽略
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface UiIgnore {

    /**
     * 忽略的属性
     * 默认忽略当前属性
     *
     * @return
     */
    String[] value() default {};
}
