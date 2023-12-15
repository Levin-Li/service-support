package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;

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
 * <p>
 * <p>
 * 注意，可以设置空格覆盖类定义
 *
 * @author llw
 */

@Inherited
@Target({ElementType.TYPE, ElementType.METHOD,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@GenNameConstant
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
     * 权限，角色和表达式3个条件是否都必须满足，否则任意一个满足即视为验证通过
     * <p>
     * 默认任意一个条件满足都可以
     *
     * @return
     */
    boolean isAndMode() default false;

    ///////////////////// 资源许可 Permission //////////////////////

    /**
     * 资源域
     * 可从父对象获取
     *
     * @return
     */
    String domain() default "";

    /**
     * 资源类型
     * 可从父对象获取
     *
     * @return
     */
    String type() default "";

    /**
     * 资源
     * 可从父对象获取
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
     * 需要的角色，任意一个满足都视为验证通过
     *
     * @return
     */
    String[] anyRoles() default {};

    /**
     * 验证表达式
     * <p>
     * 建议 应用 spel 表达式
     * 建议应用 spring security 表达式
     *
     * @return 表达式应该返回 true 或 false
     */
    String verifyExpression() default "";

    /**
     * 备注
     *
     * @return
     */
    String remark() default "";
}
