package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author lilw
 */
@Schema(title = "组织范围")
@Data
@Accessors(chain = true)
@ToString
@GenNameConstant
@EqualsAndHashCode(of = {"tenantMatchingExpression", "orgId", "isAllow", "orgScopeExpressionType", "orgScopeExpression"})
public class SimpleOrgScope implements OrgScope {

    @Schema(title = "租户匹配表达式", description = "空串、ALL_TENANT、DEFAULT_TENANT、租户ID、Spring PathPattern 或 #!groovy: 脚本")
    String tenantMatchingExpression = OrgScope.DEFAULT_TENANT;

    @Schema(title = "组织ID")
    String orgId;

    @Schema(title = "是否允许")
    boolean isAllow;

    @Schema(title = "表达式类型")
    ExpressionType orgScopeExpressionType;

    @Schema(title = "组织范围表达式", description = "SpringPathPattern 或是 Groovy/SpringEL 脚本")
    String orgScopeExpression;

    public SimpleOrgScope setOrgScopeMatchingPattern(ScopeMatchingPattern orgScopeMatchingPattern) {
        orgScopeExpression = orgScopeMatchingPattern == null ? "" : orgScopeMatchingPattern.getScopeExpression();
        return this;
    }
}
