package com.levin.commons.rbac;


import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.service.domain.Identifiable;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


/**
 * 用户基本信息
 */
public interface RbacUserObject<ROLE extends Serializable>
        extends Serializable, MultiTenantObject, OrganizedObject, Identifiable, NamedObject {

    /**
     * 是否超级用户
     *
     * @return
     */
    default boolean isSuperAdmin() {
        return hasRole((ROLE) RbacRoleObject.SA_ROLE);
    }

    /**
     * 是否是SAAS管理员
     *
     * @return
     */
    default boolean isSaasAdmin() {
        return hasRole((ROLE) RbacRoleObject.SAAS_ADMIN);
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
     * 是否是租户管理员
     *
     * @return
     */
    default boolean isTenantAdmin() {
        return hasRole((ROLE) RbacRoleObject.ADMIN_ROLE);
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
    default boolean hasRole(ROLE role) {
        return role != null && getRoleList() != null && getRoleList().contains(role);
    }

    /**
     * 是否拥有指定角色
     *
     * @param rolePredicate
     * @return
     */
    default boolean hasRole(Predicate<ROLE> rolePredicate) {
        return getRoleList() != null && getRoleList().stream().filter(Objects::nonNull).anyMatch(rolePredicate);
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    default List<ROLE> getRoleList() {
        return Collections.emptyList();
    }

}
