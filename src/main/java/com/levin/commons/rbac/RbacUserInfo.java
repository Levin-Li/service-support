package com.levin.commons.rbac;


import com.levin.commons.dao.domain.*;
import com.levin.commons.service.domain.Identifiable;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


/**
 * 用户基本信息
 */
public interface RbacUserInfo
        extends Serializable, MultiTenantObject, StatefulObject, ExpiredObject, Identifiable, NamedObject, OrganizedObject, DataScopeObject {

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
     * 获取对象状态
     *
     * @return
     */
    @Override
    default <STATE extends Serializable> STATE getState() {
        return null;
    }

    /**
     * 过期时间
     *
     * @return date
     */
    @Override
    default Date getExpiredTime() {
        return null;
    }

    /**
     * 是否SAAS用户
     *
     * @return
     */
    default boolean isSaasUser() {
        //租户ID为空
        return getTenantId() == null || getTenantId().toString().trim().isEmpty();
    }

    /**
     * 是否超级用户
     *
     * @return
     */
    default boolean isSuperAdmin() {
        return isSaasUser() && hasRole(RbacRoleObject.SA_ROLE);
    }

    /**
     * 是否是SAAS管理员
     *
     * @return
     */
    default boolean isSaasAdmin() {
        return isSaasUser() && hasRole(RbacRoleObject.SAAS_ADMIN);
    }

    /**
     * 是否是租户管理员
     *
     * @return
     */
    default boolean isTenantAdmin() {
        return !isSaasUser() && hasRole(RbacRoleObject.ADMIN_ROLE);
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
     * 是否是管理员或者自己
     *
     * @param userOrId user对象或是userId
     * @return
     */
    default boolean isAdminOrSelf(Serializable userOrId) {
        return isAdmin() || Objects.equals(getId(), (userOrId instanceof RbacUserInfo) ? ((RbacUserInfo) userOrId).getId() : userOrId);
    }

    /**
     * 是否拥有指定角色
     *
     * @param role
     * @return
     */
    default boolean hasRole(Serializable role) {
        return hasRole(ownerRole -> Objects.equals(role, ownerRole)
                || ((ownerRole instanceof RbacRoleObject) ? ((RbacRoleObject) ownerRole).getCode() : ownerRole).equals(role)
        );
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
    default <ROLE extends Serializable> List<ROLE> getRoleList() {
        return Collections.emptyList();
    }

}
