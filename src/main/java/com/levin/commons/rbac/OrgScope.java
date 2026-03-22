package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.annotation.GenNameConstant;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.stream.Stream;

/**
 * @author echo
 */

@Schema(title = "组织范围", description = "组织范围")
@GenNameConstant
public interface OrgScope extends Serializable {

    String ALL_ROOT_ORG = "_ALL_ROOT_ORG_";

    String USER_ORG = "_USER_ORG_";

    @Getter
    @Schema(title = "范围")
    @GenNameConstant
    enum Scope implements EnumDesc {

        @Schema(title = "仅本节点", description = "不包含子部门")
        OnlySelf("/"),

        @Schema(title = "仅直接子节点", description = "不包含本节点")
        OnlyDirectChild("/*/"),

        @Schema(title = "本节点及直接子节点", description = "本部门及所有下级部门")
        SelfAndDirectChild("/*"),

        @Schema(title = "全部子部门", description = "所有下级部门")
        All("/**"),

        @Schema(title = "自定义", description = "可以是其他自定义规则, 如 /**/*部门/, 或是Groovy脚本")
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

    @NotBlank
    @Schema(title = "组织标识", description = "有3种情况, _ALL_ORG_, _USER_ORG_ 和具体的组织Id")
    String getOrgId();

    @Schema(title = "是否允许访问", description = "true: 允许, false: 拒绝")
    boolean isAllow();

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
                .filter(scope -> scope.getScopeExpression().equals(getScopeExpression()))
                .findFirst()
                .orElse(Scope.Custom);
    }

    @NotBlank
    @Schema(title = "范围表达式", description = "Ant path 或是 Groovy 脚本")
    String getScopeExpression();

}
