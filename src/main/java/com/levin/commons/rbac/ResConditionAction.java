package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;


/**
 * @author echo
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@GenNameConstant
public @interface ResConditionAction {

    /**
     * 授权的操作
     * 支持 * 通配符，支持|或选择符
     *
     * @return
     */
    String action() default "";

    /**
     * 操作类型
     *
     * @return
     */
    ActionType[] actionTypes() default {};

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
    @Schema(title = "逻辑与模式", description = "权限，角色和表达式3个条件是否都必须满足，否则任意一个满足即视为验证通过")
    boolean isAndMode() default false;

    /**
     * 要求的角色，任意一个满足都视为验证通过
     * 角色支持 * 通配符，支持|或选择符
     *
     * @return
     */
    String[] anyRoles() default {};

    /**
     * 要求的用户类型，任意一个满足都视为验证通过
     * 用户支持 * 通配符，支持|或选择符
     *
     * @return
     */
    String[] anyUserTypes() default {};

    /**
     * 访问的保密级别
     * 数值越大，级别越高
     *
     * @return
     */
    int confidentialLevel() default 0;

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
