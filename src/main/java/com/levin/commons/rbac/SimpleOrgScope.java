package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Schema(title = "组织范围")
@Data
@Accessors(chain = true)
@ToString
@GenNameConstant
@EqualsAndHashCode(of = {"tenantExpression", "orgId", "isAllow", "expressionType", "orgScopeExpression"})
public class SimpleOrgScope implements OrgScope {

    @Schema(title = "租户匹配表达式")
    String tenantExpression = OrgScope.DEFAULT_TENANT;

    @Schema(title = "组织ID")
    String orgId;

    @Schema(title = "是否允许")
    boolean isAllow;

    @Schema(title = "表达式类型")
    ExpressionType expressionType;

    @Schema(title = "组织范围表达式", description = "Ant path 或是 Groovy 脚本")
    String orgScopeExpression;

    public SimpleOrgScope setScope(OrgScope.Scope scope) {
        orgScopeExpression = scope == null ? "" : scope.getScopeExpression();
        return this;
    }

}
