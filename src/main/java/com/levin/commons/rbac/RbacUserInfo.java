package com.levin.commons.rbac;


import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.OrganizedObject;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.springframework.util.StringUtils;

/**
 * 用户基本信息
 */
public interface RbacUserInfo
        extends RbacCoreObject, MultiTenantObject, OrganizedObject, DataScope {

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
    default boolean isPlatformUser() {
        return !StringUtils.hasText(Objects.toString(getTenantId(), null));
    }

    /**
     * 是否是租户用户。
     *
     * @return 是否属于某个具体租户
     */
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
    @Schema(title = "角色列表")
    default <ROLE extends Serializable> Collection<ROLE> getRoleList() {
        return Collections.emptyList();
    }

}
