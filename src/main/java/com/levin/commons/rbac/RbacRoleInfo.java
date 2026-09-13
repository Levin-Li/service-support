package com.levin.commons.rbac;


import com.levin.commons.dao.domain.DomainObject;
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 角色对象
 *
 * @author echo
 */
@Schema(title = "角色信息", description = "不设计继承模型,以降低复杂度")
@Tag(name = "RBAC 角色定义", description = "用户持有角色编码时优先使用本租户的有效同码角色定义，缺失时回退到共享定义；角色分配必须满足前置条件、互斥与共存规则，权限表达式仅在前置授权通过后参与判定。")
public interface RbacRoleInfo extends RbacCoreObject, DataScope, MultiTenantPublicObject , DomainObject {

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
    @Schema(title = "租户ID", description = "角色定义的覆盖租户；空表示共享定义。用户持有角色编码，优先采用本租户有效同码定义，否则回退共享定义")
    @Operation(summary = "获取角色定义租户", description = "返回角色定义的覆盖租户；空值表示共享定义。解析同码角色时本租户有效定义优先，缺失时才回退共享定义。")
    default <TID extends Serializable> TID getTenantId() {
        throw new UnsupportedOperationException();
    }

    @Schema(title = "是否是公共角色", description = "没有归属租户即为公共角色")
    default boolean isPublicRole() {
        return RbacMiscUtils.isBlank(getTenantId());
    }

    @Schema(title = "角色编码", description = "以R_开头")
    String getCode();

    @Schema(title = "角色分配的前置条件", description = "目的是用于约束角色分配, 默认为groovy脚本, 是指把角色分配给用户时, 必须先满足的条件, 一般是表达式, 如 _user.type == '2'; 默认要求支持 _tenant,  _user, _role 3个变量")
    @Operation(summary = "获取角色分配前置条件", description = "角色分配前必须计算该条件；结果非 true 或脚本执行失败时拒绝分配，不会被角色权限或管理员快捷路径绕过。")
    default String getAssignPreCondition() {
        return "";
    }

    @Schema(title = "排斥的角色编码列表", description = "把当前角色分配给用户时, 用户不能拥有这些角色中的任何一个, 否则不能分配该角色给用户, 可以使用 * 和 ? 通配符; 目的是用于约束角色分配")
    @Operation(summary = "获取角色互斥规则", description = "目标用户若已拥有任一匹配角色则拒绝本角色分配；支持 * 和 ? 通配符，空集合表示没有互斥规则。")
    default Collection<String> getExclusiveRoleList() {
        return Collections.emptyList();
    }

    @Schema(title = "必须共存的角色编码列表", description = "把当前角色分配给用户时, 用户必须已经拥有全部的这些角色, 否则不能分配该角色给用户, 可以使用 * 和 ? 通配符; 目的是用于约束角色分配;这里不用依赖,“依赖”更多是从角色自身依赖；“共存”是从用户分配的角度")
    @Operation(summary = "获取角色共存规则", description = "目标用户必须拥有全部匹配的共存角色，否则拒绝本角色分配；支持 * 和 ? 通配符，空集合表示没有共存要求。")
    default Collection<String> getCoexistRoleList() {
        return Collections.emptyList();
    }

    @Schema(title = "授权列表", description = "可以支持*和|")
    @Operation(summary = "获取角色权限表达式", description = "返回角色声明的权限表达式；角色有效性、领域、租户、机密级别和管理员层级等前置拒绝规则仍优先于这些表达式。")
    default Collection<String> getPermissionList() {
        return Collections.emptyList();
    }

}
