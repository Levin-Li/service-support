package com.levin.commons.rbac;


import java.util.Collection;

/**
 * 角色对象
 */
public interface RoleObject {

    /**
     * 获取授权列表
     *
     * @param <P>
     * @return
     */
    <P extends Permission> Collection<P> getPermissionList();

}
