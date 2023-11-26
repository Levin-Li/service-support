package com.levin.commons.rbac;


import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.service.domain.Identifiable;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


/**
 * 用户基本信息
 */
public interface RbacUserObject<ROLE> extends Serializable, MultiTenantObject, Identifiable, NamedObject {

    /**
     * 是否超级用户
     *
     * @return
     */
    default boolean isSuperAdmin() {
        return getRoleList() != null && getRoleList().contains(RbacRoleObject.SA_ROLE);
    }

    /**
     * 是否是租户管理员
     *
     * @return
     */
    default boolean isTenantAdmin() {
        return getRoleList() != null && getRoleList().contains(RbacRoleObject.ADMIN_ROLE);
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
