package com.levin.commons.rbac;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.levin.commons.annotation.GenNameConstant;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author echo
 */

@Schema(title = "组织范围", description = "标准 ScopeMatchingMode 按组织树层级计算；Custom 的 IdPath 和 NamePath 使用 Spring PathPattern，不是 Ant Path")
@GenNameConstant
@JsonAutoDetect(
        // 只序列化字段
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        // 忽略所有 getter
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        // 忽略 isXXX
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
public interface OrgScope extends Serializable {

    String ALL_ROOT_ORG = "/*";

    // 用户组织
    String USER_ORG = "_USER_ORG_";

    //所有租户
    String ALL_TENANT = "*";

    //默认租户, 对于saas用户(无租户Id), 则默认为无租户,对于有租户Id的用户,则默认为用户的租户Id
    String DEFAULT_TENANT = "_DEFAULT_TENANT_";

    String TENANT_GROOVY_EXPRESSION_PREFIX = "#!groovy:";

    @Schema(title = "范围匹配模式")
    @GenNameConstant
    enum ScopeMatchingMode implements EnumDesc {

        @Schema(title = "仅本节点", description = "不包含子节点")
        OnlySelf,

        @Schema(title = "仅直接子节点", description = "不包含本节点")
        OnlyDirectChild,

        @Schema(title = "本节点及直接子节点", description = "本节点及直接子节点")
        SelfAndDirectChild,

        @Schema(title = "本节点及所有子节点", description = "本节点及所有层级子节点")
        All,

        @Schema(title = "自定义", description = "必须指定 IdPath、NamePath、Groovy 或 SpringEL 表达式")
        Custom;

        @Override
        public String toString() {
            return nameAndDesc();
        }
    }

    @Getter
    @Schema(title = "自定义表达式类型", description = "统一约定SpringPathPattern路径匹配规则,要求被匹配路径必须以/开头; IdPath 和 NamePath 会先去除非根路径的尾随 /，再执行一次匹配")
    @GenNameConstant
    enum ExpressionType implements EnumDesc {

        @Schema(title = "基于Id的路径", description = "SpringPathPattern表达式")
        IdPath,

        @Schema(title = "基于名称的路径", description = "SpringPathPattern表达式")
        NamePath,

        @Schema(title = "Groovy", description = "传入被匹配的节点和用户,支持的变量为: _org, _user")
        Groovy,

        @Schema(title = "SpringEL", description = "传入被匹配的节点和用户,支持的变量为: _org, _user")
        SpringEL,
        ;
    }

    @Schema(title = "是否所有根组织", description = "所有根组织作为范围起点，具体匹配范围由 ScopeMatchingMode 决定")
    default boolean isAllRootOrg() {
        return ALL_ROOT_ORG.equalsIgnoreCase(StrUtil.nullToEmpty(getOrgId()).trim());
    }

    @Schema(title = "是否用户的默认组织", description = "是否用户的默认组织")
    default boolean isUserOrg() {
        return USER_ORG.equalsIgnoreCase(StrUtil.nullToEmpty(getOrgId()).trim());
    }

    @Schema(title = "租户匹配表达式", description = "租户匹配表达式: 空串表示无租户, ALL_TENANT(*) 匹配任何租户, DEFAULT_TENANT 表示用户默认租户, 普通文本按租户ID精确匹配, 可使用*?匹配表达式(SpringPathPattern), Groovy 脚本必须使用 #!groovy: 前缀(租户和用户变量:_tenant,_user,_scope)")
    default String getTenantMatchingExpression() {
        return DEFAULT_TENANT;
    }

    @Schema(title = "是否所有租户", description = "所有租户")
    default boolean isAllTenant() {
        return !isNoTenant() && ALL_TENANT.equalsIgnoreCase(StrUtil.nullToEmpty(getTenantMatchingExpression()).trim());
    }

    @Schema(title = "是否默认租户", description = "是否用户的租户")
    default boolean isDefaultTenant() {
        return !isNoTenant() && DEFAULT_TENANT.equalsIgnoreCase(StrUtil.nullToEmpty(getTenantMatchingExpression()).trim());
    }

    @Schema(title = "是否无租户", description = "无租户")
    default boolean isNoTenant() {
        return StrUtil.isBlank(getTenantMatchingExpression());
    }

    @NotBlank
    @Schema(title = "组织标识", description = "有3种情况: /* 表示所有根组织作为范围起点, _USER_ORG_ 表示用户默认组织, 或具体组织Id")
    String getOrgId();

    @Schema(title = "访问许可", description = "true: 允许, false: 拒绝")
    boolean isAllow();

    @Schema(title = "组织范围表达式类型", description = "自定义组织范围表达式时有效")
    ExpressionType getOrgScopeExpressionType();

    @NotBlank
    @Schema(title = "组织范围表达式", description = "SpringPathPattern, Groovy或SpringEL")
    String getOrgScopeExpression();

    @Schema(title = "组织范围匹配模式", description = "标准模式直接按组织树层级计算；Custom 才使用组织范围表达式")
    ScopeMatchingMode getOrgScopeMatchingMode();

    @Schema(title = "是否允许所有组织", description = "true: 允许, false: 拒绝")
    default boolean isAllowAllOrg() {
        return isAllow() && isAllRootOrg() && getOrgScopeMatchingMode() == ScopeMatchingMode.All;
    }

    @Schema(title = "是否拒绝所有组织", description = "true: 拒绝, false: 允许")
    default boolean isDenyAllOrg() {
        return isDeny() && isAllRootOrg() && getOrgScopeMatchingMode() == ScopeMatchingMode.All;
    }

    @Schema(title = "是否拒绝访问")
    default boolean isDeny() {
        return !isAllow();
    }

    @Schema(title = "是否是自定义范围")
    default boolean isCustomOrgScope() {
        return ScopeMatchingMode.Custom.equals(getOrgScopeMatchingMode());
    }

}
