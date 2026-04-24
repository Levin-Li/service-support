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

    @Schema(title = "操作名称", description = "如更新,删除")
    String action() default "";

    @Schema(title = "忽略授权验证", description = "不进行资源授权检查")
    boolean ignored() default false;

    @Schema(title = "仅要求认证", description = "不进行资源授权检查")
    boolean onlyRequireAuthenticated() default false;

    @Schema(title = "用户类型", description = "要求的用户类型，任意一个满足都视为验证通过")
    String[] anyUserTypes() default {};

    @Schema(title = "保密级别", description = "要求用户的可访问级别必须大于等于这个值")
    int confidentialLevel() default 0;

    @Schema(title = "逻辑与模式", description = "权限，角色和表达式3个条件是否都必须满足，否则任意一个满足即视为验证通过, 注意不影响[用户类型]和[保密级别]")
    boolean isAndMode() default false;

    @Schema(title = "角色", description = "用户任意一个满足都视为验证通过")
    String[] anyRoles() default {};

    @Schema(title = "验证表达式", description = "SpEL表达式, 验证表达式要返回boolean值")
    String verifyExpression() default "";

    @Schema(title = "备注", description = "")
    String remark() default "";
}
