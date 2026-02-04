package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;

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

@Data
@Accessors(fluent = true, chain = true)
@GenNameConstant
public class ResConditionActionObject implements ResConditionAction {

    @Schema(title = "ID")
    String id;

    @Schema(title = "是否忽略")
    boolean ignored = false;

    @Schema(title = "仅要求认证")
    boolean onlyRequireAuthenticated = false;

    @Schema(title = "操作")
    String action = "";

    @Schema(title = "操作类型")
    ActionType[] actionTypes = {};

    @Schema(title = "备注")
    String remark = "";
    /// /////////////////////////////////////////////////////////////////////////

    @Schema(title = "匹配模式")
    boolean isAndMode = false;

    @Schema(title = "验证表达式")
    String verifyExpression = "";

    @Schema(title = "数据保密级别", description = "数值越大，级别越高")
    int confidentialLevel = ConfidentialLevel.TENANT_SHARED.code();

    @Schema(title = "匹配的任意角色")
    String[] anyRoles = {};

    @Schema(title = "匹配的任意用户类型")
    String[] anyUserTypes = {};


    public <T extends ResConditionActionObject> T cast() {
        return (T) this;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return ResConditionAction.class;
    }
}
