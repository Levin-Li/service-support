package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.utils.ExpressionUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.levin.commons.rbac.RbacMiscUtils.isAllBlank;
import static com.levin.commons.rbac.RbacMiscUtils.isAllNull;

/**
 * Rbac 授权服务
 * <p>
 * 1、获取可以使用的资源清单
 * 2、获取可以使用的菜单清单
 * 3、方法授权检查
 *
 * @author lilw
 */

public interface RbacAuthorizeService extends RbacBaseAuthorizeService {

    /**
     * 角色分配条件表达式缓存
     */
    Map<String, Class<Object>> ROLE_ASSIGN_GROOVY_CLASS_CACHE = new ConcurrentReferenceHashMap<>();

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


    @Operation(summary = "找出第一组互斥的角色对", description = "默认返回第一组互斥的角色")
    default <ROLE extends RbacRoleInfo> DataPair<ROLE, ROLE> findFirstExclusiveRolePair(Collection<? extends RbacRoleInfo> roles) {

        if (isAllNull(roles)) {
            return null;
        }

        // 获取角色代码
        Map<String, RbacRoleInfo> map = roles.stream().filter(Objects::nonNull)
                .filter(role -> StrUtil.isNotBlank(role.getCode()))
                .collect(Collectors.toMap(RbacRoleInfo::getCode, role -> role, (current, ignored) -> current, LinkedHashMap::new));

        for (RbacRoleInfo role : roles) {

            if (role == null || (role.getExclusiveRoleList()) == null) {
                continue;
            }

            RbacRoleInfo mutexRole = role.getExclusiveRoleList().stream()
                    .filter(StringUtils::hasText)
                    .map(rolePattern -> findFirstMatchedRole(map, rolePattern, role))
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null);

            if (mutexRole != null) {
                // 返回互斥角色
                return DataPair.of((ROLE) role, (ROLE) mutexRole);
            }
        }

        return null;
    }

    @Operation(summary = "找出第一组缺失共存角色的角色编码", description = "默认返回当前角色和缺失的共存角色编码表达式")
    default <ROLE extends RbacRoleInfo> DataPair<ROLE, Collection<String>> findFirstMissingCoexistRoleCodePair(Collection<? extends RbacRoleInfo> roles) {

        if (isAllNull(roles)) {
            return null;
        }

        final Set<String> roleCodes = roles.stream()
                .filter(Objects::nonNull)
                .map(RbacRoleInfo::getCode)
                .filter(StringUtils::hasText)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (roleCodes.isEmpty()) {
            return null;
        }

        for (RbacRoleInfo role : roles) {

            if (role == null || isAllBlank(role.getCoexistRoleList())) {
                continue;
            }

            final String roleCode = role.getCode();

            if (!StringUtils.hasText(roleCode)) {
                continue;
            }

            // 共存角色
            List<String> roleCodeList = role.getCoexistRoleList().stream()
                    .filter(StringUtils::hasText)
                    .filter(rolePattern -> roleCodes.stream().noneMatch(code -> roleCodeMatch(rolePattern, code)))
                    .toList();

            if (!roleCodeList.isEmpty()) {
                return DataPair.of((ROLE) role, roleCodeList);
            }
        }

        return null;
    }

    @Operation(summary = "找出第一组缺失共存角色对象", description = "默认按缺失的共存角色编码表达式在目标用户租户内加载候选角色对象")
    default <ROLE extends RbacRoleInfo> DataPair<ROLE, Collection<ROLE>> findFirstMissingCoexistRolePair(Serializable targetUserPrincipal, Collection<? extends RbacRoleInfo> roles) {

        final DataPair<ROLE, Collection<String>> missingPair = findFirstMissingCoexistRoleCodePair(roles);

        if (missingPair == null) {
            return null;
        }

        Assert.notNull(targetUserPrincipal, "targetUserPrincipal is required");

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        final RbacUserInfo targetUser = rbacBaseService.loadUser(targetUserPrincipal);
        Assert.notNull(targetUser, "target user {} not found", targetUserPrincipal);

        return DataPair.of(missingPair.getA(), rbacBaseService.loadTenantRoleListByCodePatterns(targetUser.getTenantId(), missingPair.getB()));
    }

    @Operation(summary = "校验角色分派", description = "统一检查操作人是否可分配、目标用户是否满足角色前置条件、角色集合是否存在互斥或缺失共存角色")
    default void checkRoleAssignment(Serializable operatorPrincipal, Serializable targetUserPrincipal, Collection<? extends RbacRoleInfo> finalRoles) {

        Assert.notNull(operatorPrincipal, "操作用户不能为空");
        Assert.notNull(targetUserPrincipal, "目标用户不能为空");

        if (isAllNull(finalRoles)) {
            return;
        }

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        final RbacUserInfo targetUser = rbacBaseService.loadUser(targetUserPrincipal);
        Assert.notNull(targetUser, "目标用户({})不存在", targetUserPrincipal);

        for (RbacRoleInfo role : finalRoles) {
            if (role == null) {
                continue;
            }

            Assert.isTrue(isRoleAuthorized(operatorPrincipal, role, null), "操作用户无权分配角色[{}]", role.getCode());
            Assert.isTrue(isRoleAssignPreConditionMatched(targetUser, role), "目标用户不满足角色[{}]分配前置条件", role.getCode());
        }

        final DataPair<? extends RbacRoleInfo, ? extends RbacRoleInfo> exclusivePair = findFirstExclusiveRolePair(finalRoles);
        Assert.isNull(exclusivePair, "角色[{}]与角色[{}]互斥，不能同时分配",
                exclusivePair == null ? null : exclusivePair.getA().getCode(),
                exclusivePair == null ? null : exclusivePair.getB().getCode());

        final DataPair<? extends RbacRoleInfo, Collection<String>> missingCoexistPair = findFirstMissingCoexistRoleCodePair(finalRoles);
        Assert.isNull(missingCoexistPair, "角色[{}]缺少必须共存角色{}",
                missingCoexistPair == null ? null : missingCoexistPair.getA().getCode(),
                missingCoexistPair == null ? null : missingCoexistPair.getB());
    }

    @Operation(summary = "检查目标用户是否满足角色分配前置条件", description = "用于保存用户角色前校验目标用户和目标角色，不用于操作人角色授权判断")
    default boolean isRoleAssignPreConditionMatched(Serializable targetUserPrincipal, RbacRoleInfo role) {

        Assert.notNull(targetUserPrincipal, "目标用户不能为空");
        Assert.notNull(role, "角色不能为空");

        final String expression = role.getAssignPreCondition();

        if (StrUtil.isBlank(expression)) {
            return true;
        }

        final RbacUserInfo targetUser = getRbacBaseLoadService().loadUser(targetUserPrincipal);
        Assert.notNull(targetUser, "目标用户({})不存在", targetUserPrincipal);

        final Map<String, Object> context = new LinkedHashMap<>();
        context.put("_user", targetUser);
        context.put("_role", role);

        Object value = ExpressionUtils.evalGroovy(ROLE_ASSIGN_GROOVY_CLASS_CACHE, null, expression,
                "role-assign-pre-condition-" + Integer.toHexString(expression.hashCode()) + ".groovy", context);

        return Boolean.TRUE.equals(value);
    }

    private static RbacRoleInfo findFirstMatchedRole(Map<String, RbacRoleInfo> roleMap, String rolePattern, RbacRoleInfo excludedRole) {
        return roleMap.entrySet().stream()
                .filter(entry -> roleCodeMatch(rolePattern, entry.getKey()))
                .map(Map.Entry::getValue)
                .filter(role -> role != excludedRole)
                .findFirst()
                .orElse(null);
    }

    private static boolean roleCodeMatch(String rolePattern, String roleCode) {
        return StringUtils.hasText(rolePattern)
                && StringUtils.hasText(roleCode)
                && PatternMatchUtils.simpleMatch(rolePattern.trim(), roleCode.trim());
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
    default boolean isRoleAuthorized(Serializable principal, RbacRoleInfo role, BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer) {

        Assert.notNull(principal, "无用户主体");
        Assert.notNull(role, "角色为空");

        final String roleCode = role.getCode();
        Assert.notBlank(roleCode, "角色的编码为空");

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();

        RbacUserInfo userInfo = rbacBaseService.loadUser(principal);
        Assert.notNull(userInfo, "用户({})不存在", principal);

        // 如果是顶级超级管理员
        if (userInfo.isTopSuperAdmin()) {
            return true;
        }

        if (matchErrorConsumer == null) {
            matchErrorConsumer = (permission, reason) -> {
            };
        }

        final boolean isSaasUser = userInfo.isSaasUser();

        //如果是租户用户
        if (!isSaasUser) {

            if (role.isPublicRole()) {
                //如果角色是SAAS角色，则不能访问
                if (roleCode.startsWith(RbacRoleInfo.SAAS_ROLE_PREFIX)) {
                    return false;
                }
            } else if (!role.getTenantId().equals(userInfo.getTenantId())) {
                //不能跨租户访问
                return false;
            }
        }

        principal = userInfo;

        // 检查数据访问级别, 任何用户都检查, 除了TopSA
        if (!rbacBaseService.canAccessConfidentialDataByUser(userInfo, role.getConfidentialDataAccessLevel())) {
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

        if (userInfo.isSaasAdmin()) {
            return true;
        }

        if (RbacRoleInfo.SAAS_ADMIN.equals(roleCode)) {
            return false;
        }

        //管理员要求也是管理员
        if (RbacRoleInfo.ADMIN_ROLE.equals(roleCode) && !(isSaasUser || userInfo.isTenantAdmin())) {
            return false;
        }

        //@todo  还有要检查 当前用户 可访问的数据权限是否大于等于角色的数据权限

        //除了sa 和 saas_admin, 其他都要按权限检查
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
        if (isAllBlank(requirePermissionList)) {
            return true;
        }

        //过滤空的权限列表
        requirePermissionList = requirePermissionList.stream().filter(StringUtils::hasText).collect(Collectors.toList());

        if (isAllBlank(requirePermissionList)) {
            return true;
        }

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        RbacUserInfo userInfo = rbacBaseService.loadUser(principal);
        Assert.notNull(userInfo, "用户({})不存在", principal);

        //如果是超级管理员
        if (userInfo.isTopSuperAdmin()) {
            return true;
        }

        principal = userInfo;

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
        if (requirePermissionList == null
                || requirePermissionList.isEmpty()) {
            return true;
        }

        //过滤空的权限列表
        requirePermissionList = requirePermissionList.stream().filter(StringUtils::hasText).collect(Collectors.toList());

        if (requirePermissionList.isEmpty()) {
            return true;
        }

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        RbacUserInfo userInfo = rbacBaseService.loadUser(principal);
        Assert.notNull(userInfo, "用户({})不存在", principal);
        principal = userInfo;

        //如果是超级管理员
        if (userInfo.isTopSuperAdmin()) {
            return true;
        }

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
