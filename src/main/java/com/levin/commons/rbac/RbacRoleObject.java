package com.levin.commons.rbac;


import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.NamedObject;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 角色对象
 */
public interface RbacRoleObject<P extends Serializable> extends MultiTenantObject, NamedObject, DataScopeObject {

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
     * 角色代码
     *
     * @return
     */
    String getCode();

    /**
     * 获取授权列表
     *
     * @return
     */
    default List<P> getPermissionList() {
        return Collections.emptyList();
    }

}
