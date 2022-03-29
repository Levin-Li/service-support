package com.levin.commons.rbac;

import java.lang.annotation.*;

/**
 * 菜单资源标记
 *
 * @author llw
 */
@Inherited
@Target({ElementType.TYPE,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MenuResTag {

    /**
     * 资源域
     *
     * @return
     */
    String domain() default "";


    /**
     * 是否是菜单资源
     *
     * @return
     */
    boolean value() default true;
}
