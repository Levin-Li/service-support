package com.levin.commons.ui.annotation;

import java.lang.annotation.*;

/**
 * ui忽略
 * @author lilw
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface UiIgnore {

    String[] value() default {};
}
