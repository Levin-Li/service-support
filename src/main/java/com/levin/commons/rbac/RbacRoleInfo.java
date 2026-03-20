package com.levin.commons.rbac;


import com.levin.commons.dao.domain.MultiTenantPublicObject;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 角色对象
 *
 * @author echo
 */
public interface RbacRoleInfo extends RbacCoreObject, DataScope, MultiTenantPublicObject {

    String ROLE_PREFIX = "R_";

    //超级管理员
    String SA_ROLE = ROLE_PREFIX + "SA";

    //SAAS角色前缀
    String SAAS_ROLE_PREFIX = ROLE_PREFIX + "SAAS_";

    //SAAS管理员
    String SAAS_ADMIN = SAAS_ROLE_PREFIX + "ADMIN";

    //系统管理员，通常是一个租户的管理员
    String ADMIN_ROLE = ROLE_PREFIX + "ADMIN";

    String ORG_ADMIN_ROLE = ROLE_PREFIX + "ORG_ADMIN";

    @Override
    @Schema(title = "租户ID")
    default <TID extends Serializable> TID getTenantId() {
        throw new UnsupportedOperationException();
    }

    @Schema(title = "是否是公共角色", description = "没有归属租户即为公共角色角色")
    default boolean isPublicRole() {
        return RbacMiscUtils.isBlank(getTenantId());
    }

    @Schema(title = "角色编码", description = "以R_开头")
    String getCode();

    @Schema(title = "继承的角色编码列表", description = "可以使用*?通配符")
    default Collection<String> getInheritedRoleList() {
        return Collections.emptyList();
    }

    @Schema(title = "角色分配的前置条件", description = "是指把角色分配给用户时, 必须先满足的条件, 一般是表达式,如 user.type = '2' ")
    default String getRoleAssignPreCondition() {
        return "";
    }

    @Schema(title = "排斥的角色编码列表", description = "是指把角色分配给用户时,和这个角色不能共存的角色, 可以使用*?通配符")
    default Collection<String> getExclusiveRoleList() {
        return Collections.emptyList();
    }

    @Schema(title = "授权列表")
    default Collection<String> getPermissionList() {
        return Collections.emptyList();
    }

}
