package com.levin.commons.rbac;


import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.service.domain.Identifiable;

import java.util.Collection;
import java.util.Collections;

/**
 * 角色对象
 */
public interface RbacRoleObject<P> extends MultiTenantObject, Identifiable, NamedObject {

    String SA_ROLE = "SA";

    String ADMIN_ROLE = "ADMIN";

    /**
     * 获取授权列表
     *
     * @return
     */
    default Collection<P> getPermissionList() {
        return Collections.emptyList();
    }

}
