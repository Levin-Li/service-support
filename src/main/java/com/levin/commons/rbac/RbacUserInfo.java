package com.levin.commons.rbac;


import com.levin.commons.dao.domain.DomainObject;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.OrganizedObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 用户基本信息
 */
@Tag(name = "RBAC 用户定义", description = "平台用户以空租户 ID 识别，可参与跨租户范围判定；租户用户只能在其租户边界内访问。管理员快捷路径仍受领域、拒绝范围与机密级别等前置门槛约束。")
public interface RbacUserInfo
        extends RbacCoreObject, MultiTenantObject, OrganizedObject, DataScope, DomainObject {

    /**
     * 超级管理员账号
     */
    String TOP_SA_ACCOUNT_NAME = "sa";

    /**
     * 获取租户 ID
     *
     * @return
     */
    @Override
    @Operation(summary = "获取用户所属租户", description = "空或空白租户 ID 表示平台用户，非空表示租户用户；该归属决定跨租户访问的基础边界。")
    default <TID extends Serializable> TID getTenantId() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取组织ID
     *
     * @return
     */
    @Override
    default <ORG_ID extends Serializable> ORG_ID getOrgId() {
        return null;
    }

    /**
     * 用户类型
     * 如 管理后台用户
     * 客户
     *
     * @return
     */
    default String getType() {
        return "";
    }

    /**
     * 登录名
     *
     * @return
     */
    String getLoginName();

    /**
     * 邮箱
     *
     * @return
     */
    String getEmail();

    /**
     * 电话
     *
     * @return
     */
    String getTelephone();

    /**
     * 密码
     *
     * @return
     */
    default String getPassword() {
        return null;
    }

    /**
     * 获取MFA密钥
     *
     * @return
     */
    default String getMfaSecretKey() {
        return null;
    }

    /**
     * 昵称
     *
     * @return
     */
    default String getNickname() {
        return null;
    }

    /**
     * 头像
     *
     * @return
     */
    default String getAvatar() {
        return null;
    }

    /**
     * 是否是平台用户。
     * <p>
     * 平台用户没有所属租户，租户 ID 为 {@code null} 或空白字符串。
     *
     * @return 是否没有所属租户
     */
    @Operation(summary = "判断是否为平台用户", description = "仅当所属租户 ID 为 null 或空白时返回 true；平台身份不自动绕过领域、拒绝范围、机密级别或资源动作授权。")
    default boolean isPlatformUser() {
        return !StringUtils.hasText(Objects.toString(getTenantId(), null));
    }

    /**
     * 是否是租户用户。
     *
     * @return 是否属于某个具体租户
     */
    @Operation(summary = "判断是否为租户用户", description = "是平台用户判断的反向结果；租户用户的跨租户访问应按数据范围和授权规则拒绝。")
    default boolean isTenantUser() {
        return !isPlatformUser();
    }

    /**
     * @deprecated 使用 {@link #isPlatformUser()} 代替；该方法名称不能准确表达“无所属租户”的语义。
     */
    @Deprecated
    default boolean isSaasUser() {
        return isPlatformUser();
    }

    /**
     * 是否是顶级超级管理员
     * 和普通超管的区别是 登录账号为sa, 并且无机密数据级别的限制
     *
     * @return
     */
    default boolean isTopSuperAdmin() {
        return TOP_SA_ACCOUNT_NAME.equals(getLoginName()) && isSuperAdmin();
    }

    /**
     * 是否超级用户
     *
     * @return
     */
    default boolean isSuperAdmin() {
        return isPlatformUser() && hasRole(RbacRoleInfo.SA_ROLE);
    }

    /**
     * 是否是SAAS管理员
     *
     * @return
     */
    default boolean isSaasAdmin() {
        return isPlatformUser() && hasRole(RbacRoleInfo.SAAS_ADMIN);
    }

    /**
     * 是否是租户管理员
     *
     * @return
     */
    default boolean isTenantAdmin() {
        return isTenantUser() && hasRole(RbacRoleInfo.ADMIN_ROLE);
    }

    /**
     * 是否是租户管理员、SAAS管理员和超级管理员中的任意一个
     *
     * @return
     */
    default boolean isAdmin() {
        return isSuperAdmin() || isSaasAdmin() || isTenantAdmin();
    }

    /**
     * 是否拥有指定角色
     *
     * @param role
     * @return
     */
    default boolean hasRole(Serializable role) {
        return hasRole(ownerRole -> ownerRole.equals((role instanceof RbacRoleInfo) ? ((RbacRoleInfo) role).getCode() : role));
    }

    /**
     * 是否拥有指定角色
     *
     * @param rolePredicate
     * @return
     */
    default boolean hasRole(Predicate<Serializable> rolePredicate) {
        return getRoleList() != null && getRoleList().stream().filter(Objects::nonNull).anyMatch(rolePredicate);
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @Schema(title = "角色编码列表", description = "保存角色编码而非角色对象ID；按用户租户选择有效同码定义，本租户优先，共享定义回退")
    default <ROLE extends Serializable> Collection<ROLE> getRoleList() {
        return Collections.emptyList();
    }

}
