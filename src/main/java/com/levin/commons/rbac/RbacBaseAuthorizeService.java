package com.levin.commons.rbac;


import cn.hutool.core.lang.Assert;
import com.levin.commons.service.SingleValueContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.BiConsumer;

import static com.levin.commons.rbac.RbacMiscUtils.isAllNull;
import static com.levin.commons.rbac.RbacRoleInfo.*;

/**
 * 授权服务
 */
@Tag(name = "RBAC 授权服务", description = "授权判定按资源、角色和条件执行；空的要求集合表示无需授权。具体实现必须在 Swagger 方法说明中公开权限门槛、拒绝条件、优先级及任何缓存或回退规则。")
public interface RbacBaseAuthorizeService {


    <S extends RbacBaseAuthorizeService> S setRbacBaseServiceContext(SingleValueContext<RbacBaseService> context);


    SingleValueContext<RbacBaseService> getRbacBaseServiceContext();

    /**
     * 拷贝
     *
     * @param resAuthorize
     * @return
     */
    static ResConditionAction newResConditionAction(@NotNull ResAuthorize resAuthorize) {
        return new ResConditionActionObject()
                .action(resAuthorize.action())

                .isAndMode(resAuthorize.isAndMode())
                .anyUserTypes((resAuthorize.anyUserTypes()))
                .anyRoles(resAuthorize.anyRoles())
                .verifyExpression(resAuthorize.verifyExpression())
                .confidentialLevel(resAuthorize.confidentialLevel())
                .ignored(resAuthorize.ignored())
                .onlyRequireAuthenticated(resAuthorize.onlyRequireAuthenticated())
                .remark(resAuthorize.remark());
    }

    /**
     * 拷贝
     *
     * @param domain
     * @param resType
     * @param res
     * @param conditionAction
     * @return
     */
    static ResAuthorize newResAuthorize(String domain, String resType, String res, ResConditionAction conditionAction) {
        return new ResAuthorizeObjectAction()
                .domain(domain)
                .type(resType)
                .res(res)
                .action(conditionAction.action())

                .isAndMode(conditionAction.isAndMode())
                .anyUserTypes(conditionAction.anyUserTypes())
                .anyRoles(conditionAction.anyRoles())
                .verifyExpression(conditionAction.verifyExpression())
                .confidentialLevel(conditionAction.confidentialLevel())
                .ignored(conditionAction.ignored())
                .onlyRequireAuthenticated(conditionAction.onlyRequireAuthenticated())
                .remark(conditionAction.remark())
                .cast();
    }

    /**
     * 是否授权
     *
     * @param principal    用户对象或是用户ID
     * @param resAuthorize
     * @return
     */
    default boolean isAuthorized(@NotNull Serializable principal, @NotNull ResAuthorize resAuthorize) {
        return isAuthorized(principal, resAuthorize.domain(), resAuthorize.type(), resAuthorize.res(), newResConditionAction(resAuthorize));
    }

    /**
     * 是否授权
     *
     * @param principal       用户对象或是用户ID
     * @param domain
     * @param resType
     * @param res
     * @param conditionAction
     * @return
     */
    @Operation(summary = "检查用户授权", description = "检查用户是否满足指定资源动作的授权条件；具体实现应先执行领域、租户或机密级别等前置拒绝规则，再判断角色和权限表达式。")
    boolean isAuthorized(@NotNull Serializable principal, String domain, String resType, String res, ResConditionAction conditionAction);

    /**
     * 是否授权
     *
     * @param principal    用户对象或是用户ID
     * @param isRequireAll 是否所有角色都满足
     * @param roles
     * @return
     */
    default boolean isRoleAuthorized(@NotNull Serializable principal, boolean isRequireAll, Collection<RbacRoleInfo> roles, BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer) {

        if (isAllNull(roles)) {
            return true;
        }

        return isRequireAll
                ? roles.stream().allMatch(role -> isRoleAuthorized(principal, role, matchErrorConsumer))
                : roles.stream().anyMatch(role -> isRoleAuthorized(principal, role, matchErrorConsumer));
    }

    /**
     * 是否能管理目标角色
     * 不涉及角色的权限和机密数据逻辑
     *
     * @param operatorRoleCode
     * @param targetRoleCode
     * @return
     */
    default boolean canAdmin(String operatorRoleCode, String targetRoleCode) {

        Assert.notBlank(operatorRoleCode, "操作角色不能为空");
        operatorRoleCode = operatorRoleCode.trim();
        Assert.isTrue(operatorRoleCode.startsWith(ROLE_PREFIX), "角色代码必须{}开头", ROLE_PREFIX);

        //超级管理员, 允许管理所有角色
        if (operatorRoleCode.equals(SA_ROLE)) {
            return true;
        }

        Assert.notBlank(targetRoleCode, "目标角色不能为空");
        targetRoleCode = targetRoleCode.trim();
        Assert.isTrue(targetRoleCode.startsWith(ROLE_PREFIX), "角色代码必须{}开头", ROLE_PREFIX);

        //SA角色只允许同级管理
        if (targetRoleCode.equals(SA_ROLE)) {
            return false;
        }

        //SAAS管理员
        if (operatorRoleCode.equals(SAAS_ADMIN)) {
            return true;
        }

        //允许同级管理
        if (targetRoleCode.equals(SAAS_ADMIN)) {
            return false;
        }

        //SAAS普通角色
        if (operatorRoleCode.startsWith(SAAS_ROLE_PREFIX)) {
            return true;
        }

        //SAAS角色
        if (targetRoleCode.startsWith(SAAS_ROLE_PREFIX)) {
            return false;
        }

        if (operatorRoleCode.equals(ADMIN_ROLE)) {
            return true;
        }

        return !targetRoleCode.equals(ADMIN_ROLE);

        //普通角色, 都是平权, 允许互相管理
    }

    /**
     * 是否授权
     *
     * @param principal 用户对象或是用户ID
     * @param role
     * @return
     */
    @Operation(summary = "检查用户对一个角色是否拥有授权", description = "检查用户能否使用或管理指定角色；具体实现应明确领域、租户、机密级别、管理员层级与权限表达式的拒绝优先顺序。")
    boolean isRoleAuthorized(@NotNull Serializable principal, @NotNull RbacRoleInfo role, BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer);

}
