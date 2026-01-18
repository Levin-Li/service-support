package com.levin.commons.rbac;


import com.levin.commons.dao.domain.MultiTenantObject;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 角色对象
 *
 * @author lilw
 */
public interface RbacRoleInfo extends RbacCoreObject, MultiTenantObject, DataScopeObject {

    String ROLE_PREFIX = "R_";

    //超级管理员
    String SA_ROLE = ROLE_PREFIX + "SA";

    //SAAS角色前缀
    String SAAS_ROLE_PREFIX = ROLE_PREFIX + "SAAS_";

    //SAAS管理员
    String SAAS_ADMIN = SAAS_ROLE_PREFIX + "ADMIN";

    //系统管理员，通常是一个租户的管理员
    String ADMIN_ROLE = ROLE_PREFIX + "ADMIN";

    //部门管理员
    String ORG_ADMIN_ROLE = ROLE_PREFIX + "ORG_ADMIN";

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
     * 角色代码
     *
     * @return
     */
    String getCode();


    /**
     * 获取互斥角色代码列表
     *
     * @return
     */
    default Collection<String> getMutexCodeList() {
        return Collections.emptyList();
    }

    default String getAssignCondition() {
        return null;
    }

    /**
     * 获取授权列表
     *
     * @return
     */
    default Collection<String> getPermissionList() {
        return Collections.emptyList();
    }

}
