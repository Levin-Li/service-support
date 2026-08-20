package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;
import io.swagger.v3.oas.annotations.media.Schema;

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

    @Schema(title = "资源域", description = "通常是模块")
    String domain() default "";

    @Schema(title = "资源类型", description = "可以用-进行2级分类")
    String type() default "";

    @Schema(title = "资源", description = "资源Id,或是资源名称,如菜单名称")
    String res() default "";

    /// ////////////////// 资源许可 Permission //////////////////////

    @Schema(title = "操作名称", description = "如更新,删除")
    String action() default "";

    @Schema(title = "忽略授权验证", description = "不进行资源授权检查")
    boolean ignored() default false;

    @Schema(title = "仅要求认证", description = "不进行资源授权检查")
    boolean onlyRequireAuthenticated() default false;

    @Schema(title = "用户类型", description = "要求的用户类型，任意一个满足都视为验证通过")
    String[] anyUserTypes() default {};

    @Schema(title = "保密级别", description = "要求用户的可访问级别必须大于等于这个值；默认平台公开，不限制机密数据访问级别")
    int confidentialLevel() default ConfidentialLevel.PLATFORM_PUBLIC_CODE;

    @Schema(title = "逻辑与模式", description = "权限，角色和表达式3个条件是否都必须满足，否则任意一个满足即视为验证通过, 注意不影响[用户类型]和[保密级别]")
    boolean isAndMode() default false;

    @Schema(title = "角色", description = "用户任意一个满足都视为验证通过")
    String[] anyRoles() default {};

    @Schema(title = "验证表达式", description = "SpEL表达式, 验证表达式要返回boolean值")
    String verifyExpression() default "";

    @Schema(title = "备注", description = "")
    String remark() default "";
}
