package com.levin.commons.rbac;


import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.service.domain.Identifiable;

import java.util.Collection;
import java.util.Collections;

/**
 * 角色对象
 */
public interface RbacRoleObject<P> extends Identifiable, NamedObject {

    String ROLE_PREFIX = "R_";

    //超级管理员
    String SA_ROLE = ROLE_PREFIX + "SA";

    //系统管理员，通常是一个租户的管理员
    String ADMIN_ROLE = ROLE_PREFIX + "ADMIN";

    //部门管理员
    String ORG_ADMIN_ROLE = ROLE_PREFIX + "ORG_ADMIN";

    /**
     * 获取授权列表
     *
     * @return
     */
    default Collection<P> getPermissionList() {
        return Collections.emptyList();
    }

}
