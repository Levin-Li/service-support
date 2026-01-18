package com.levin.commons.rbac;


import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.function.BiConsumer;

import static com.levin.commons.rbac.RbacMiscUtils.isEmptyOrAllNull;

/**
 * 授权服务
 */
public interface RbacBaseAuthorizeService {

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
    @Operation(summary = "检查用户授权", description = "检查用户授权是否对指定的资源是否有某个操作权限")
    boolean isAuthorized(@NotNull Serializable principal, String domain, String resType, String res, ResConditionAction conditionAction);


    /**
     * 是否授权
     *
     * @param principal    用户对象或是用户ID
     * @param isRequireAll 是否所有角色都满足
     * @param roles
     * @return
     */
    default boolean isAuthorized(@NotNull Serializable principal, boolean isRequireAll, Collection<RbacRoleInfo> roles) {

        if (isEmptyOrAllNull(roles)) {
            return true;
        }

        return isRequireAll
                ? roles.stream().allMatch(role -> isAuthorized(principal, role, null))
                : roles.stream().anyMatch(role -> isAuthorized(principal, role, null));
    }

    /**
     * 是否授权
     *
     * @param principal 用户对象或是用户ID
     * @param role
     * @return
     */
    @Operation(summary = "检查用户对一个角色是否拥有授权", description = "")
    boolean isAuthorized(@NotNull Serializable principal, @NotNull RbacRoleInfo role, BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer);

}
