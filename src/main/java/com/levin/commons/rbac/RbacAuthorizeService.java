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
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;
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


    @Operation(summary = "找出互斥的角色对", description = "默认返回第一组互斥的角色, 如果返回null表示无互斥的角色")
    default <ROLE extends RbacRoleInfo> DataPair<ROLE, ROLE> findExclusiveRolePair(Serializable targetUserPrincipal, Collection<? extends ROLE> roleList) {

        if (isAllNull(roleList)) {
            return null;
        }

        final Map<String, Pattern> singleCharRolePatternCache = new LinkedHashMap<>();
        final BiPredicate<String, String> roleCodePatternMatcher = (rolePattern, roleCode) -> {

            final String trimmedRolePattern = rolePattern != null ? rolePattern.trim() : null;

            if (!StringUtils.hasText(trimmedRolePattern) || !StringUtils.hasText(roleCode)) {
                return false;
            }

            if (trimmedRolePattern.indexOf('?') < 0) {
                return PatternMatchUtils.simpleMatch(trimmedRolePattern, roleCode);
            }

            final Pattern regexPattern = singleCharRolePatternCache.computeIfAbsent(trimmedRolePattern, pattern -> {
                final StringBuilder regex = new StringBuilder(pattern.length() * 2);
                for (int i = 0; i < pattern.length(); i++) {
                    final char ch = pattern.charAt(i);
                    if (ch == '*') {
                        regex.append(".*");
                    } else if (ch == '?') {
                        regex.append('.');
                    } else {
                        regex.append(Pattern.quote(String.valueOf(ch)));
                    }
                }
                return Pattern.compile(regex.toString());
            });

            return regexPattern.matcher(roleCode).matches();
        };

        final Map<String, ROLE> roleMap = roleList.stream()
                .filter(Objects::nonNull)
                .filter(role -> StrUtil.isNotBlank(role.getCode()))
                .collect(Collectors.toMap(RbacRoleInfo::getCode, role -> role, (current, ignored) -> current, LinkedHashMap::new));

        for (ROLE role : roleList) {

            if (role == null || isAllBlank(role.getExclusiveRoleList())) {
                continue;
            }

            for (String rolePattern : role.getExclusiveRoleList()) {

                if (StrUtil.isBlank(rolePattern)) {
                    continue;
                }

                final ROLE exclusiveRole = roleMap.entrySet().stream()
                        .filter(entry -> roleCodePatternMatcher.test(rolePattern, entry.getKey()))
                        .map(Map.Entry::getValue)
                        .filter(matchRole -> matchRole != role)
                        .findFirst()
                        .orElse(null);

                if (exclusiveRole != null) {
                    return DataPair.of(role, exclusiveRole);
                }
            }
        }

        return null;
    }

    @Operation(summary = "找出缺失的共存角色", description = "默认按目标用户租户返回当前角色和缺失的共存角色对象；如果共存角色编码表达式无法加载到角色对象，则抛出异常")
    default <ROLE extends RbacRoleInfo> DataPair<ROLE, Collection<ROLE>> findMissingCoexistRolePair(Serializable targetUserPrincipal, Collection<? extends ROLE> roleList) {

        if (isAllNull(roleList)) {
            return null;
        }

        final Set<String> roleCodes = roleList.stream()
                .filter(Objects::nonNull)
                .map(RbacRoleInfo::getCode)
                .filter(StringUtils::hasText)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (roleCodes.isEmpty()) {
            return null;
        }

        Assert.notNull(targetUserPrincipal, "目标用户不能为空");

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        final RbacUserInfo targetUser = targetUserPrincipal instanceof RbacUserInfo
                ? (RbacUserInfo) targetUserPrincipal
                : rbacBaseService.loadUser(targetUserPrincipal);

        Assert.notNull(targetUser, "目标用户({})不存在", targetUserPrincipal);

        final Map<String, Pattern> singleCharRolePatternCache = new LinkedHashMap<>();
        final BiPredicate<String, String> roleCodePatternMatcher = (rolePattern, roleCode) -> {

            final String trimmedRolePattern = rolePattern != null ? rolePattern.trim() : null;

            if (!StringUtils.hasText(trimmedRolePattern) || !StringUtils.hasText(roleCode)) {
                return false;
            }

            if (trimmedRolePattern.indexOf('?') < 0) {
                return PatternMatchUtils.simpleMatch(trimmedRolePattern, roleCode);
            }

            final Pattern regexPattern = singleCharRolePatternCache.computeIfAbsent(trimmedRolePattern, pattern -> {
                final StringBuilder regex = new StringBuilder(pattern.length() * 2);
                for (int i = 0; i < pattern.length(); i++) {
                    final char ch = pattern.charAt(i);
                    if (ch == '*') {
                        regex.append(".*");
                    } else if (ch == '?') {
                        regex.append('.');
                    } else {
                        regex.append(Pattern.quote(String.valueOf(ch)));
                    }
                }
                return Pattern.compile(regex.toString());
            });

            return regexPattern.matcher(roleCode).matches();
        };

        final Map<String, Collection<ROLE>> roleListByCodePattern = new LinkedHashMap<>();

        for (ROLE role : roleList) {

            if (role == null || StrUtil.isBlank(role.getCode()) || isAllBlank(role.getCoexistRoleList())) {
                continue;
            }

            final Set<String> effectiveRoleCodes = new LinkedHashSet<>(roleCodes);
            final Map<String, ROLE> missingRoleMap = new LinkedHashMap<>();
            final Set<String> checkedRoleCodes = new LinkedHashSet<>();
            final Deque<ROLE> waitCheckRoles = new ArrayDeque<>();

            waitCheckRoles.add(role);

            while (!waitCheckRoles.isEmpty()) {

                final ROLE checkingRole = waitCheckRoles.poll();

                if (checkingRole == null
                        || StrUtil.isBlank(checkingRole.getCode())
                        || !checkedRoleCodes.add(checkingRole.getCode())
                        || isAllBlank(checkingRole.getCoexistRoleList())) {
                    continue;
                }

                final List<String> missingRoleCodePatterns = checkingRole.getCoexistRoleList().stream()
                        .filter(StringUtils::hasText)
                        .map(String::trim)
                        .filter(rolePattern -> effectiveRoleCodes.stream().noneMatch(roleCode -> roleCodePatternMatcher.test(rolePattern, roleCode)))
                        .collect(Collectors.toList());

                if (missingRoleCodePatterns.isEmpty()) {
                    continue;
                }

                final Collection<String> unloadedRoleCodePatterns = missingRoleCodePatterns.stream()
                        .filter(rolePattern -> !roleListByCodePattern.containsKey(rolePattern))
                        .collect(Collectors.toList());

                if (!unloadedRoleCodePatterns.isEmpty()) {
                    final Collection<ROLE> loadedRoleList = rbacBaseService.loadTenantRoleListByCodePatterns(targetUser.getTenantId(), unloadedRoleCodePatterns);

                    for (String rolePattern : unloadedRoleCodePatterns) {
                        final List<ROLE> matchedRoleList = loadedRoleList.stream()
                                .filter(Objects::nonNull)
                                .filter(loadedRole -> StrUtil.isNotBlank(loadedRole.getCode()))
                                .filter(loadedRole -> roleCodePatternMatcher.test(rolePattern, loadedRole.getCode()))
                                .collect(Collectors.toList());

                        roleListByCodePattern.put(rolePattern, matchedRoleList);
                    }
                }

                final Collection<ROLE> missingRoleList = missingRoleCodePatterns.stream()
                        .map(roleListByCodePattern::get)
                        .filter(Objects::nonNull)
                        .flatMap(Collection::stream)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toCollection(LinkedHashSet::new));

                for (String rolePattern : missingRoleCodePatterns) {
                    final boolean loaded = !isAllNull(roleListByCodePattern.get(rolePattern));

                    Assert.isTrue(loaded, "角色{}({})配置的共存角色表达式[{}]无法加载到角色对象",
                            checkingRole.getName(), checkingRole.getCode(), rolePattern);
                }

                for (ROLE missingRole : missingRoleList) {

                    if (missingRole == null || StrUtil.isBlank(missingRole.getCode())) {
                        continue;
                    }

                    if (!roleCodes.contains(missingRole.getCode())) {
                        missingRoleMap.putIfAbsent(missingRole.getCode(), missingRole);
                    }

                    if (effectiveRoleCodes.add(missingRole.getCode())
                            && !checkedRoleCodes.contains(missingRole.getCode())) {
                        waitCheckRoles.add(missingRole);
                    }
                }
            }

            if (!missingRoleMap.isEmpty()) {
                return DataPair.of(role, missingRoleMap.values());
            }

        }

        return null;
    }


    @Operation(summary = "校验角色分配", description = "统一检查操作人是否可分配、目标用户是否满足角色前置条件、角色集合是否存在互斥或缺失共存角色")
    default void checkRoleAssignment(Serializable operatorPrincipal, Serializable targetUserPrincipal, Collection<? extends RbacRoleInfo> finalRoles) {

        Assert.notNull(operatorPrincipal, "操作用户不能为空");
        Assert.notNull(targetUserPrincipal, "目标用户不能为空");

        if (isAllNull(finalRoles)) {
            return;
        }

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        final RbacUserInfo targetUser = targetUserPrincipal instanceof RbacUserInfo
                ? (RbacUserInfo) targetUserPrincipal
                : rbacBaseService.loadUser(targetUserPrincipal);

        Assert.notNull(targetUser, "目标用户({})不存在", targetUserPrincipal);

        for (RbacRoleInfo role : finalRoles) {

            if (role == null) {
                continue;
            }

            Assert.isTrue(isRoleAuthorized(operatorPrincipal, role, null), "操作用户无权分配角色{}({})", role.getName(), role.getCode());

            Assert.isTrue(isRoleAssignPreConditionMatched(targetUser, role), "目标用户不满足角色{}({})分配前置条件", role.getName(), role.getCode());
        }

        final DataPair<? extends RbacRoleInfo, ? extends RbacRoleInfo> exclusivePair = findExclusiveRolePair(targetUser, finalRoles);

        if (exclusivePair != null) {
            Assert.isNull(exclusivePair, "角色{}({})与角色{}({})互斥，不能同时分配",
                    exclusivePair.getA().getName(), exclusivePair.getA().getCode(),
                    exclusivePair.getB().getName(), exclusivePair.getB().getCode()
            );
        }

        final DataPair<? extends RbacRoleInfo, ? extends Collection<? extends RbacRoleInfo>> missingCoexistPair = findMissingCoexistRolePair(targetUser, finalRoles);
        if (missingCoexistPair != null) {
            Assert.isNull(
                    missingCoexistPair, "角色{}({})缺少必须共存的角色:{}",
                    missingCoexistPair.getA().getName(),
                    missingCoexistPair.getA().getCode(),
                    missingCoexistPair.getB().stream()
                            .filter(Objects::nonNull)
                            .map(role -> role.getName() + "(" + role.getCode() + ")")
                            .collect(Collectors.joining(", "))
            );
        }
    }

    @Operation(summary = "检查目标用户是否满足角色分配前置条件", description = "用于保存用户角色前校验目标用户和目标角色，不用于操作人角色授权判断")
    default boolean isRoleAssignPreConditionMatched(Serializable targetUserPrincipal, RbacRoleInfo role) {

        Assert.notNull(targetUserPrincipal, "目标用户不能为空");
        Assert.notNull(role, "角色不能为空");

        final String expression = role.getAssignPreCondition();

        if (StrUtil.isBlank(expression)) {
            return true;
        }

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();
        final RbacUserInfo targetUser = targetUserPrincipal instanceof RbacUserInfo
                ? (RbacUserInfo) targetUserPrincipal
                : rbacBaseService.loadUser(targetUserPrincipal);
        Assert.notNull(targetUser, "目标用户({})不存在", targetUserPrincipal);

        final Map<String, Object> context = new LinkedHashMap<>();
        final RbacTenantInfo targetTenant = expression.contains("_tenant") && RbacMiscUtils.isNotBlank(targetUser.getTenantId())
                ? rbacBaseService.loadTenant(targetUser.getTenantId())
                : null;

        context.put("_tenant", targetTenant);
        context.put("_user", targetUser);
        context.put("_role", role);

        Object value = ExpressionUtils.evalGroovy(ROLE_ASSIGN_GROOVY_CLASS_CACHE, null, expression,
                "role-assign-pre-condition-" + Integer.toHexString(expression.hashCode()) + ".groovy", context);

        return Boolean.TRUE.equals(value);
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
        Assert.isTrue(role.selfAudit(), "角色[{}]不可用", role.getCode());

        final String roleCode = role.getCode();
        Assert.notBlank(roleCode, "角色的编码为空");

        final RbacBaseService rbacBaseService = getRbacBaseLoadService();

        RbacUserInfo userInfo = principal instanceof RbacUserInfo
                ? (RbacUserInfo) principal
                : rbacBaseService.loadUser(principal);
        Assert.notNull(userInfo, "用户({})不存在", principal);

        // 如果是顶级超级管理员
        if (userInfo.isTopSuperAdmin()) {
            return true;
        }

        if (matchErrorConsumer == null) {
            matchErrorConsumer = (permission, reason) -> {
            };
        }

        final boolean isPlatformUser = userInfo.isPlatformUser();

        //如果是租户用户
        if (!isPlatformUser) {

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
        if (RbacRoleInfo.ADMIN_ROLE.equals(roleCode) && !(isPlatformUser || userInfo.isTenantAdmin())) {
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
        RbacUserInfo userInfo = principal instanceof RbacUserInfo
                ? (RbacUserInfo) principal
                : rbacBaseService.loadUser(principal);
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
        RbacUserInfo userInfo = principal instanceof RbacUserInfo
                ? (RbacUserInfo) principal
                : rbacBaseService.loadUser(principal);
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
