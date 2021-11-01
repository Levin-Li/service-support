package com.levin.commons.rbac;


import java.util.Collection;

/**
 * 用户对象
 */
public interface UserObject {

    /**
     * 获取角色列表
     *
     * @param <P>
     * @return
     */
    <P extends RoleObject> Collection<P> getRoleList();

}
