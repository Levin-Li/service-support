package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.annotation.GenNameConstant;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.stream.Stream;

/**
 * @author echo
 */

@Schema(title = "组织范围", description = "组织范围")
@GenNameConstant
public interface OrgScope extends Serializable {

    String ALL_ROOT_ORG = "_ALL_ROOT_ORG_";

    // 用户组织
    String USER_ORG = "_USER_ORG_";

    //所有租户
    String ALL_TENANT = "*";

    //默认租户, 对于saas用户(无租户Id), 则默认为无租户,对于有租户Id的用户,则默认为用户的租户Id
    String DEFAULT_TENANT = "_DEFAULT_TENANT_";

    @Getter
    @Schema(title = "范围")
    @GenNameConstant
    enum Scope implements EnumDesc {

        @Schema(title = "仅本节点", description = "不包含子部门")
        OnlySelf("/"),

        @Schema(title = "仅直接子节点", description = "不包含本节点")
        OnlyDirectChild("/*/"),

        @Schema(title = "本节点及直接子节点", description = "本节点及直接子节点")
        SelfAndDirectChild("/*"),

        @Schema(title = "本节点及所有子节点", description = "本节点及所有层级子节点")
        All("/**"),

        @Schema(title = "自定义", description = "可以是其他自定义规则, 如 /**/*部门/, 或是Groovy,SpringEL脚本")
        Custom(""),

        ;
        @Schema(title = "范围表达式")
        private final String scopeExpression;

        Scope(String scopeExpression) {
            Assert.notNull(scopeExpression, "scopeExpression is null");
            this.scopeExpression = scopeExpression;
        }

        @Override
        public String toString() {
            return nameAndDesc();
        }
    }

    @Getter
    @Schema(title = "自定义表达式类型")
    @GenNameConstant
    enum ExpressionType implements EnumDesc {

        @Schema(title = "基于Id的路径", description = "AntPath类型的表达式")
        IdAntPath,

        @Schema(title = "基于名称的路径", description = "AntPath类型的表达式")
        NameAntPath,

        @Schema(title = "Groovy脚本", description = "传入被匹配的节点和用户,变量名称为org,user")
        Groovy,

        @Schema(title = "Spring el", description = "传入被匹配的节点和用户,变量名称为org,user")
        SpringEL,
        ;
    }

    /**
     * 所有组织
     */
    @Schema(title = "是否所有根组织", description = "所有根组织")
    default boolean isAllRootOrg() {
        return ALL_ROOT_ORG.equalsIgnoreCase(StrUtil.nullToEmpty(getOrgId()).trim());
    }

    /**
     * 是否是用户的所在的部门
     */
    @Schema(title = "是否用户的默认组织", description = "是否用户的默认组织")
    default boolean isUserOrg() {
        return USER_ORG.equalsIgnoreCase(StrUtil.nullToEmpty(getOrgId()).trim());
    }

    @Schema(title = "租户匹配表达式", description = "租户匹配表达式, 4种情况: 无租户, ALL_TENANT(匹配任何租户) , DEFAULT_TENANT(对于无租户的平台用户, 则默认为无租户,对于有租户Id的用户,则默认为用户的租户Id), Groovy脚本(租户和用户变量:tenant,user)")
    default String getTenantExpression() {
        return DEFAULT_TENANT;
    }

    @Schema(title = "是否所有租户", description = "所有租户")
    default boolean isAllTenant() {
        return !isNoTenant() && ALL_TENANT.equalsIgnoreCase(StrUtil.nullToEmpty(getTenantExpression()).trim());
    }

    @Schema(title = "是否默认租户", description = "是否用户的租户")
    default boolean isDefaultTenant() {
        return !isNoTenant() && DEFAULT_TENANT.equalsIgnoreCase(StrUtil.nullToEmpty(getTenantExpression()).trim());
    }

    @Schema(title = "是否无租户", description = "无租户")
    default boolean isNoTenant() {
        return StrUtil.isBlank(getTenantExpression());
    }

    @NotBlank
    @Schema(title = "组织标识", description = "有3种情况, _ALL_ORG_, _USER_ORG_ 和具体的组织Id")
    String getOrgId();

    @Schema(title = "是否允许访问", description = "true: 允许, false: 拒绝")
    boolean isAllow();

    @Schema(title = "表达式类型", description = "自定义时有效")
    ExpressionType getExpressionType();

    @NotBlank
    @Schema(title = "组织范围表达式", description = "Ant path,  Groovy或SpringEL 脚本")
    String getOrgScopeExpression();

    @Schema(title = "是否允许所有组织", description = "true: 允许, false: 拒绝")
    default boolean isAllowAllOrg() {
        return isAllow() && isAllRootOrg() && getScope() == Scope.All;
    }

    @Schema(title = "是否拒绝所有组织", description = "true: 拒绝, false: 允许")
    default boolean isDenyAllOrg() {
        return isDeny() && isAllRootOrg() && getScope() == Scope.All;
    }

    @Schema(title = "是否拒绝访问")
    default boolean isDeny() {
        return !isAllow();
    }

    @Schema(title = "是否是自定义范围")
    default boolean isCustomScope() {
        return Scope.Custom.equals(getScope());
    }

    @Schema(title = "是否是自定义范围")
    default Scope getScope() {
        return Stream.of(Scope.values())
                .filter(scope -> scope.getScopeExpression().equals(getOrgScopeExpression()))
                .findFirst()
                .orElse(Scope.Custom);
    }

}
