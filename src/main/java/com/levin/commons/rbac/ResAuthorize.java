package com.levin.commons.rbac;

import java.lang.annotation.*;

/**
 * 资源访问验证
 * <p>
 * 可以注解在类上，表示应用在所有的方法上。
 * <p>
 * 在调用方法前验证方法是否指定的资源授权。
 * <p>
 * 该注解出于简单考虑
 * <p>
 * <p>
 * 复杂的判断建议使用表达式
 *
 * @author llw
 */

@Inherited
@Target({ElementType.TYPE, ElementType.METHOD,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResAuthorize {

    /**
     * 忽略的
     * 不做资源授权检查
     *
     * @return
     */
    boolean ignored() default false;

    /**
     * 仅要求认证，不做资源授权
     * <p>
     * 默认是要做资源授权
     *
     * @return
     */
    boolean onlyRequireAuthenticated() default false;

    /**
     * 资源域
     *
     * @return
     */
    String domain() default "";

    /**
     * 资源类型
     *
     * @return
     */
    String type() default "";

    /**
     * 资源
     *
     * @return
     */
    String res() default "";

    /**
     * 授权的操作
     *
     * @return
     */
    String action() default "";

    /**
     * 需要的角色
     *
     * @return
     */
    String[] orRoles() default {};

    /**
     * 验证表达式
     * <p>
     * 建议 应用 spel 表达式
     * 建议应用 spring security 表达式
     *
     * @return
     */
    String expression() default "";

}
