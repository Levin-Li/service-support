package com.levin.commons.rbac;


import java.io.Serializable;
import java.util.List;

/**
 * 加载服务
 */
public interface RbacLoadService {

    /**
     * 加载用户
     *
     * @param userPrincipal
     * @return
     */
    <U extends RbacUserObject> U loadUser(Serializable userPrincipal);

    /**
     * 加载用户角色列表
     *
     * @param userPrincipal
     * @param <ROLE>
     * @return
     */
    <ROLE extends RbacUserObject> List<ROLE> loadUserRoleList(Serializable userPrincipal);

}
