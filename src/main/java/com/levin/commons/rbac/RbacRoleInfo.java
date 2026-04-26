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
@Schema(title = "角色信息", description = "不设计继承模型,以降低复杂度")
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

    @Schema(title = "是否是公共角色", description = "没有归属租户即为公共角色")
    default boolean isPublicRole() {
        return RbacMiscUtils.isBlank(getTenantId());
    }

    @Schema(title = "角色编码", description = "以R_开头")
    String getCode();

    @Schema(title = "角色分配的前置条件", description = "目的是用于约束角色分配, 默认为groovy脚本, 是指把角色分配给用户时, 必须先满足的条件, 一般是表达式, 如 _user.type == '2'; 默认要求支持 _user, _role 2个变量")
    default String getAssignPreCondition() {
        return "";
    }

    @Schema(title = "排斥的角色编码列表", description = "把当前角色分配给用户时, 用户不能拥有这些角色中的任何一个, 否则不能分配该角色给用户, 可以使用*?通配符; 目的是用于约束角色分配")
    default Collection<String> getExclusiveRoleList() {
        return Collections.emptyList();
    }

    @Schema(title = "必须共存的角色编码列表", description = "把当前角色分配给用户时, 用户必须已经拥有全部的这些角色, 否则不能分配该角色给用户, 可以使用*?通配符; 目的是用于约束角色分配")
    default Collection<String> getCoexistRoleList() {
        return Collections.emptyList();
    }

    @Schema(title = "授权列表")
    default Collection<String> getPermissionList() {
        return Collections.emptyList();
    }

}
