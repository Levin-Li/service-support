package com.levin.commons.rbac;


import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 加载服务
 */
public interface RbacBaseUserService {

    /**
     * 获取用户最大数据访问级别
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    default Integer getUserMaxDataAccessLevel(Serializable userPrincipal) {

        RbacUserInfo loadUser = loadUser(userPrincipal);

        List<RbacRoleObject> roleList = loadUserRoleList(loadUser);

        // 获取角色最大数据访问级别
        int maxLevel = roleList.stream().filter(Objects::nonNull)
                .mapToInt(RbacRoleObject::getDataAccessLevel)
                .max()
                .orElse(loadUser.getDataAccessLevel());

        return maxLevel > loadUser.getDataAccessLevel() ? maxLevel : loadUser.getDataAccessLevel();
    }

    /**
     * 加载用户
     *
     * @param tenantId
     * @param account  手机号或是邮箱或是其他用于登录的用户名称
     * @param <U>
     * @return
     */
    <U extends RbacUserInfo> U loadUser(Serializable tenantId, String account);

    /**
     * 加载用户
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    <U extends RbacUserInfo> U loadUser(Serializable userPrincipal);

    /**
     * 加载用户角色列表
     *
     * @param userPrincipal
     * @param <ROLE>
     * @return
     */
    <ROLE extends RbacRoleObject> List<ROLE> loadUserRoleList(Serializable userPrincipal);


    /**
     * 加载用户角色编码列表
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    default List<String> loadUserRoleCodeList(Serializable userPrincipal) {
        return loadUserRoleList(userPrincipal).stream().filter(Objects::nonNull)
                .map(RbacRoleObject::getCode)
                .filter(StringUtils::hasText)
                .collect(Collectors.toList());
    }

    /**
     * 加载用户权限列表
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    default List<String> loadUserPermissionExprList(Serializable userPrincipal) {
        return loadUserRoleList(userPrincipal).stream()
                .filter(Objects::nonNull)
                .map(RbacRoleObject::getPermissionList)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .filter(StringUtils::hasText)
                .collect(Collectors.toList());
    }
}
