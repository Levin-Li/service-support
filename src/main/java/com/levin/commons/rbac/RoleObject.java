package com.levin.commons.rbac;


import java.util.Collection;

/**
 * 角色对象
 */
public interface RoleObject {

    String SA_ROLE = "SA";

    String ADMIN_ROLE = "admin";

    /**
     * 获取授权列表
     *
     * @param <P>
     * @return
     */
    <P extends Permission> Collection<P> getPermissionList();

}
