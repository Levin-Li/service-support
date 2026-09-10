package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.annotation.GenNameConstant;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * 租户、领域、组织和机密级别的数据范围。
 * 六个范围字段分别处理：用户字段非 null 时替代角色对应配置，空集合也是显式配置；
 * 只有 null 才继承生效角色对应集合的并集。选定来源后，拒绝匹配优先于允许匹配。
 * 接口默认集合均为显式定义；需要继承角色的实现必须返回 null。
 *
 * @author lilw
 */
@Schema(title = "数据范围", description = "用户自定义数据范围优先于角色")
public interface DataScope {

    @Getter
    @Schema(title = "租户范围", description = "使用 expression 编码；除保留编码和 Groovy# 前缀外均为具体租户ID。只有平台用户可跨租户")
    enum TenantScope implements EnumDesc {

        @Schema(title = "所有", description = "所有具有租户ID的租户；无租户数据须另行指定None")
        All("_ALL_"),

        @Schema(title = "默认", description = "对于saas用户(无租户Id), 则默认为无租户, 对于有租户Id的用户, 则默认为用户的租户Id")
        Default("_DEFAULT_"),

        //无租户，就是指租户ID为空的数据
        @Schema(title = "无", description = "指租户ID为空的数据")
        None("_NONE_"),

        //Groovy 匹配表达式
        @Schema(title = "Groovy表达式", description = "Groovy 可用变量：_tenant 租户；_user 用户；")
        Groovy(true);

        @Schema(title = "匹配表达式", description = "")
        private final String expression;

        @Schema(title = "是否前缀匹配", description = "如果是前缀，则表示要用 expression 进行前缀匹配 ")
        private final boolean prefix;

        TenantScope(String expression) {
            this(expression, false);
        }

        TenantScope(boolean prefix) {
            this.prefix = prefix;
            this.expression = name() + (prefix ? "#" : "");
        }

        TenantScope(String expression, boolean prefix) {
            Objects.requireNonNull(expression);
            this.prefix = prefix;
            this.expression = expression + (prefix ? "#" : "");
        }

        @Override
        public String toString() {
            return nameAndDesc();
        }
    }


    @Getter
    @Schema(title = "起点组织", description = "如果不是这3种，那就是具体的组织Id")
    @GenNameConstant
    enum StartOrg implements EnumDesc {

        @Schema(title = "默认", description = "对于无组织归属的用户, 则默认为无组织, 对于有组织归属的用户, 则默认为归属组织")
        Default("_DEFAULT_"),

        @Schema(title = "无", description = "特别指组织Id为空的数据")
        None("_NONE_"),

        @Schema(title = "所有根节点", description = "")
        AllRoot("_ALL_ROOT_"),

        ;

        @Schema(title = "起点编码", description = "保留编码或具体组织ID")
        private final String expression;

        StartOrg(String expression) {
            Objects.requireNonNull(expression);
            this.expression = expression;
        }

        @Override
        public String toString() {
            return nameAndDesc();
        }
    }

    @Getter
    @Schema(title = "组织匹配模式", description = "")
    @GenNameConstant
    enum OrgMatchingMode implements EnumDesc {

        @Schema(title = "仅自己", description = "不包含任何子节点")
        Self,

        @Schema(title = "直接子节点", description = "不包含本节点")
        DirectChild,

        @Schema(title = "本节点及直接子节点", description = "本节点及直接子节点")
        SelfAndDirectChild,

        @Schema(title = "本节点及所有子节点", description = "本节点及所有层级子节点")
        SelfAndAllChild,

        @Schema(title = "基于Id的路径", description = "SpringPathPattern表达式")
        IdPath(true),

        @Schema(title = "基于名称的路径", description = "SpringPathPattern表达式")
        NamePath(true),

        @Schema(title = "Groovy表达式", description = "传入被匹配的节点和用户,支持的变量为: _org, _user")
        Groovy(true),

        ;

        @Schema(title = "匹配表达式", description = "")
        private final String expression;

        @Schema(title = "是否前缀匹配", description = "如果是前缀，则表示要用 expression 进行前缀匹配 ")
        private final boolean prefix;

        OrgMatchingMode() {
            this(false);
        }

        OrgMatchingMode(boolean prefix) {
            this.prefix = prefix;
            this.expression = name() + (prefix ? "#" : "");
        }
    }

    @Schema(title = "组织范围", description = "格式为起点组织|匹配模式；标准模式按树层级计算，IdPath#和NamePath#使用相对起点的Spring PathPattern")
    @GenNameConstant
    interface OrgScope extends Serializable {

        @Schema(title = "起点组织", description = "参考 StartOrg 枚举；起点组织为None时，组织匹配模式将无意义")
        default String startOrg() {
            return StartOrg.Default.getExpression();
        }

        @Schema(title = "组织匹配模式", description = "参考 OrgMatchingMode 枚举；起点组织为None时，组织匹配模式将无意义")
        default String orgMatchingMode() {
            return OrgMatchingMode.Self.getExpression();
        }

        /**
         * 字符串解析成 OrgScope
         *
         * @param orgScope
         * @return
         */
        static OrgScope parse(String orgScope) {

            if (orgScope == null || orgScope.isBlank()) {
                return null;
            }

            int indexOf = orgScope.indexOf("|");

            Assert.isTrue(indexOf >= 0, "组织范围[{}]格式不正确", orgScope);

            String startOrg = orgScope.substring(0, indexOf).trim();
            String orgMatchingMode = orgScope.substring(indexOf + 1).trim();

            Assert.isTrue(!startOrg.isEmpty(), "组织范围[{}]起点不能为空", orgScope);
            if (!StartOrg.None.getExpression().equals(startOrg)) {
                boolean validMode = false;
                for (OrgMatchingMode mode : OrgMatchingMode.values()) {
                    if (mode.isPrefix()
                            ? orgMatchingMode.startsWith(mode.getExpression())
                            && !orgMatchingMode.substring(mode.getExpression().length()).isBlank()
                            : mode.getExpression().equals(orgMatchingMode)) {
                        validMode = true;
                        break;
                    }
                }
                Assert.isTrue(validMode, "组织范围[{}]匹配模式无效或表达式为空", orgScope);
            }

            return new OrgScope() {
                @Override
                public String startOrg() {
                    return startOrg;
                }

                @Override
                public String orgMatchingMode() {
                    return orgMatchingMode;
                }
            };
        }

        /**
         * 转换为“起点组织|匹配模式”的存储字符串。
         *
         * @param orgScope
         * @return
         */
        static String format(OrgScope orgScope) {

            if (orgScope == null) {
                return null;
            }

            return StrUtil.format("{}|{}", orgScope.startOrg(), (orgScope.orgMatchingMode()));
        }
    }

    /// /////////////////////////////////////////////////////////

    @Schema(title = "允许的租户范围", description = "具体参考 TenantScope 枚举；空集合表示没有允许的租户")
    default Set<String> getTenantScopeList() {
        return Set.of(TenantScope.Default.expression);
    }

    @Schema(title = "拒绝的租户范围", description = "具体参考 TenantScope 枚举；空集合表示没有拒绝的租户; 拒绝优先")
    default Set<String> getDeniedTenantScopeList() {
        return Set.of();
    }

    /// /////////////////////////////////////////////////////////

    @Schema(title = "允许的领域范围", description = "具体的领域Id, 注意不是域名, DomainObject 接口关联；空集合表示没有允许的领域")
    default Set<String> getDomainScopeList() {
        return Set.of();
    }

    @Schema(title = "拒绝的领域范围", description = "具体的领域Id, 注意不是域名, DomainObject 接口关联；空集合表示没有拒绝的领域; 拒绝优先")
    default Set<String> getDeniedDomainScopeList() {
        return Set.of();
    }

    /// /////////////////////////////////////////////////////////

    @Schema(title = "允许的组织范围", description = "使用 OrgScope.parse 进行解析；空集合表示没有允许的组织")
    default Set<String> getOrgScopeList() {
        return Set.of();
    }

    @Schema(title = "拒绝的组织范围", description = "使用 OrgScope.parse 进行解析；空集合表示没有拒绝的组织; 拒绝优先")
    default Set<String> getDeniedOrgScopeList() {
        return Set.of();
    }

    /// /////////////////////////////////////////////////////////

    /**
     * 获取机密数据的访问级别
     * 数值越大，级别越高
     *
     * @return
     */
    @Schema(title = "机密数据访问级别", description = "能访问的级别")
    default Integer getConfidentialDataAccessLevel() {
        return null;
    }

}
