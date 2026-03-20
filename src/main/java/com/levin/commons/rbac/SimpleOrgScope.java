package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Schema(title = "组织范围")
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class SimpleOrgScope implements OrgScope {

    @Schema(title = "组织ID")
    String orgId;

    @Schema(title = "范围表达式", description = "Ant path 或是 Groovy 脚本")
    String scopeExpression;

    @Schema(title = "是否允许")
    boolean isAllow;

    public SimpleOrgScope setScope(OrgScope.Scope scope) {
        scopeExpression = scope == null ? "" : scope.getScopeExpression();
        return this;
    }

}
