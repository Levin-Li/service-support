package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.levin.commons.rbac.RbacMiscUtils.*;

/**
 * Rbac 授权服务
 * <p>
 * 1、获取可以使用的资源清单
 * 2、获取可以使用的菜单清单
 * 3、方法授权检查
 */

public interface RbacAuthorizeService extends RbacBaseAuthorizeService {

    /**
     * 获取权限分隔符
     *
     * @return
     */
    default String getPermissionDelimiter() {
        return Permission.DELIMITER;
    }

    /**
     * 获取认证上下文
     *
     * @return
     */
    default Map<String, Object> getAuthorizeContext() {
        return Collections.emptyMap();
    }


    /**
     * 获取用户加载服务
     *
     * @return
     */
    RbacBaseService getRbacBaseLoadService();

    /**
     * 返回错误信息
     *
     * @param roles
     * @return
     */
    @Operation(summary = "获取互斥到角色列表", description = "默认返回第一组互斥的角色")
    default Collection<RbacRoleInfo> getMutexRoleList(Collection<RbacRoleInfo> roles) {

        if (isEmptyOrAllNull(roles)) {
            return Collections.emptyList();
        }

        // 获取角色代码
        Map<String, RbacRoleInfo> map = roles.stream().filter(Objects::nonNull)
                .filter(role -> StrUtil.isNotBlank(role.getCode()))
                .collect(Collectors.toMap(RbacRoleInfo::getCode, role -> role));


        for (RbacRoleInfo role : roles) {

            if (role == null || isEmptyOrAllBlank(role.getMutexCodeList())) {
                continue;
            }

            RbacRoleInfo mutexRole = role.getMutexCodeList().stream()
                    .filter(StringUtils::hasText)
                    .map(map::get)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null);

            if (mutexRole != null
                    && mutexRole != role) {
                // 返回互斥角色
                return Arrays.asList(role, mutexRole);
            }
        }

        return Collections.emptyList();
    }

    /**
     * 是否能分配角色
     *
     * @param sourcePrincipal
     * @param roleList
     * @return
     */
    default boolean canAssignRole(Serializable sourcePrincipal, Collection<RbacRoleInfo> roleList, BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer) {

        if (isEmptyOrAllNull(roleList)) {
            return true;
        }

        return roleList.stream().anyMatch(role -> isAuthorized(sourcePrincipal, role, matchErrorConsumer));
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
    @Override
    default boolean isAuthorized(@NotNull Serializable principal, String domain, String resType, String res, ResConditionAction conditionAction) {
        return isAuthorized(principal, String.join(getPermissionDelimiter(), domain, resType, res), conditionAction);
    }

    /**
     * 用户对指定的注解是否有权限
     * 最基础的权限检查
     *
     * @param principal
     * @param resExpr   资源表达式
     * @param action
     * @return
     */
    @Operation(summary = "用户对指定的资源是否有权限")
    boolean isAuthorized(Serializable principal, String resExpr, ResConditionAction action);

    /**
     * 是否授权
     *
     * @param principal 用户对象或是用户ID
     * @param role
     * @return
     */
    @Override
    default boolean isAuthorized(Serializable principal, RbacRoleInfo role, BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer) {

        Assert.notNull(principal, "无用户主体");
        Assert.notNull(role, "角色为空");

        final String roleCode = role.getCode();
        Assert.notBlank(roleCode, "角色的编码为空");

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();

        RbacUserInfo userInfo = rbacBaseService.loadUser(principal);

        if (userInfo.isTopSuperAdmin()) {
            return true;
        }

        principal = userInfo;

        // 检查数据访问级别, 任何用户都检查
        if (!rbacBaseService.canAccessConfidentialData(role.getConfidentialDataAccessLevel(), userInfo)) {
            return false;
        }

        //1、如果是超级管理员
        if (userInfo.isSuperAdmin()) {
            return true;
        }

        //只有超级管理员才能分配超级管理员
        if (RbacRoleInfo.SA_ROLE.equals(roleCode)) {
            return false;
        }

        //如果角色是SAAS角色，但是当前用户不是SAAS用户，则不能分配
        if (roleCode.startsWith(RbacRoleInfo.SAAS_ROLE_PREFIX) && !userInfo.isSaasUser()) {
            return false;
        }

        //接下来开始检查角色的权限列表,比对角色需要的权限列表 和 用户拥有的权限列表

        return isAuthorized(principal, true, rbacBaseService.loadRolePermissionList(role.getTenantId(), roleCode), matchErrorConsumer);
    }

    /**
     * 当前用户是否 拥有指定的权限列表
     *
     * @param matchErrorConsumer
     * @param isRequireAllPermission 是否要求匹配所有的权限
     * @param requirePermissionList
     * @return
     */
    default boolean isAuthorized(Serializable principal, BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer,
                                 boolean isRequireAllPermission, String... requirePermissionList) {
        return isAuthorized(principal, isRequireAllPermission, Arrays.asList(requirePermissionList), matchErrorConsumer);
    }

    /**
     * 当前用户是否 拥有指定的权限列表
     *
     * @param isRequireAllPermission 是否要求匹配所有的权限
     * @param requirePermissionList  权限列表可以包括角色，如果
     * @param matchErrorConsumer
     * @return
     */
    default boolean isAuthorized(Serializable principal, boolean isRequireAllPermission, Collection<String> requirePermissionList,
                                 BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer) {

        Assert.notNull(principal, "无用户主体");

        //如果不需要权限
        if (isEmptyOrAllBlank(requirePermissionList)) {
            return true;
        }

        //过滤空的权限列表
        requirePermissionList = requirePermissionList.stream().filter(StringUtils::hasText).collect(Collectors.toList());

        if (isEmptyOrAllBlank(requirePermissionList)) {
            return true;
        }

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        RbacUserInfo userInfo = rbacBaseService.loadUser(principal);
        //如果是超级管理员
        if (userInfo.isTopSuperAdmin()) {
            return true;
        }
        principal =userInfo;

        return isAuthorized(userInfo, rbacBaseService.loadUserRoleCodeList(userInfo), rbacBaseService.loadUserPermissionExprList(userInfo),
                isRequireAllPermission, requirePermissionList, matchErrorConsumer);
    }

    /**
     * 授权验证，是否可以访问指定资源
     * <p>
     * 关键方法
     *
     * @param principal              用户主体
     * @param ownerRoleCodeList      已经拥有的角色列表
     * @param ownerPermissionList    已经拥有的权限列表
     * @param isRequireAllPermission 是否要求匹配所有的权限
     * @param requirePermissionList  请求的权限
     * @param matchErrorConsumer     匹配错误回调 参数1为请求的权限，参数2为错误原因
     * @return 是否可以访问指定资源
     */
    default boolean isAuthorized(Serializable principal, Collection<String> ownerRoleCodeList, Collection<String> ownerPermissionList,
                                 boolean isRequireAllPermission, Collection<String> requirePermissionList,
                                 BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer) {
        //如果不需要权限
        if (isEmptyOrAllBlank(requirePermissionList)) {
            return true;
        }

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        RbacUserInfo userInfo = rbacBaseService.loadUser(principal);
        //如果是超级管理员
        if (userInfo.isTopSuperAdmin()) {
            return true;
        }
        principal =userInfo;

        //过滤空的权限列表
        requirePermissionList = requirePermissionList.stream().filter(StringUtils::hasText).collect(Collectors.toList());

        ///////////////////////////////////////////////////////////////

        Predicate<String> predicate = requirePermission -> isAuthorized(userInfo, ownerRoleCodeList, ownerPermissionList, requirePermission, matchErrorConsumer);

        //是否要求匹配所有权限
        return isRequireAllPermission ?
                requirePermissionList.stream().allMatch(predicate)
                : requirePermissionList.stream().anyMatch(predicate);
    }


    /**
     * 授权验证
     *
     * @param principal           用户主体
     * @param ownerRoleCodeList   拥有的角色列表
     * @param ownerPermissionList 拥有的权限列表
     * @param requirePermission   需要的权限
     * @param matchErrorConsumer  匹配错误回调
     * @return
     */
    boolean isAuthorized(Serializable principal, Collection<String> ownerRoleCodeList, Collection<String> ownerPermissionList, String requirePermission
            , BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer);

}
