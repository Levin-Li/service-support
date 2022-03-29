package com.levin.commons.rbac;


import java.util.Collection;
import java.util.Collections;

/**
 * 角色对象
 */
public interface RoleObject<P> {

    String SA_ROLE = "SA";

    String ADMIN_ROLE = "admin";

    /**
     * 获取授权列表
     *
     * @return
     */
    default Collection<P> getPermissionList() {
        return Collections.emptyList();
    }

}
