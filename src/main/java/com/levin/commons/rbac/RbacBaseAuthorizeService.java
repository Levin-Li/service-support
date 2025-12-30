package com.levin.commons.rbac;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 加载服务
 */
@FunctionalInterface
public interface RbacBaseAuthorizeService {

    /**
     * 拷贝
     *
     * @param resAuthorize
     * @return
     */
    static ResConditionAction newResConditionAction(ResAuthorize resAuthorize) {
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
    default boolean isAuthorized(@NotNull Serializable principal, ResAuthorize resAuthorize) {
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
    boolean isAuthorized(@NotNull Serializable principal, String domain, String resType, String res, ResConditionAction conditionAction);
}
