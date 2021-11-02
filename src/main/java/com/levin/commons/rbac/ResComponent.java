package com.levin.commons.rbac;

import java.lang.annotation.*;

/**
 * 组件资源注解，用来表示一个组件是否是权限管理的资源
 * <p>
 * 组件的方法会被扫描成操作
 *
 * @author llw
 */
@Inherited
@Target({ElementType.TYPE,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResComponent {

    /**
     * 资源域
     *
     * @return
     */
    String domain();

    /**
     * 资源类型
     *
     * @return
     */
    String type();

    /**
     * 资源标识
     *
     * @return
     */
    String value() default "";

}
