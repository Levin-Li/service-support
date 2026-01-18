package com.levin.commons.rbac;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.levin.commons.rbac.RbacMiscUtils.isEmptyOrAllBlank;
import static com.levin.commons.rbac.RbacMiscUtils.isEmptyOrAllNull;


/**
 * 加载服务
 *
 * @author lilw
 */
public interface RbacBaseService extends RbacBaseUserService {

    /**
     * 用户类型
     */
    ThreadLocal<String> userTypeEnv = new ThreadLocal<>();

    /**
     * 获取用户类型
     *
     * @return
     */
    static String getUserType() {
        return StrUtil.firstNonBlank(userTypeEnv.get(), "User");
    }

    /**
     * 设置用户类型
     *
     * @param userType
     */
    static void setUserType(String userType) {
        userTypeEnv.set(userType);
    }


    /**
     * 加载租户的部门列表
     *
     * @param tenantId
     * @param parentId
     * @return
     */
    default <ORG extends RbacOrgInfo> Collection<ORG> loadTenantOrgList(String tenantId, String parentId) {
        //获取所有部门
        Collection<ORG> orgList = loadTenantOrgList(tenantId);

        return StringUtils.hasText(parentId) ? orgList.stream().filter(o -> parentId.equals(o.getParentId())).collect(Collectors.toList()) : orgList;
    }

    /**
     * 加载租户的部门列表
     * tenantId 为 null 时加载公共部门
     *
     * @param tenantId 可为null，为 null 时加载公共部门
     * @return
     */
    <ORG extends RbacOrgInfo> Collection<ORG> loadTenantOrgList(String tenantId);


    /**
     * 是否能访问所有部门
     *
     * @param userPrincipal
     * @return
     */
    default boolean canAccessAllOrg(Serializable userPrincipal) {

        RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "请检查用户是否存在");

        if (user.isSuperAdmin()
                || user.isSaasAdmin()
                || user.getOrgDataScope() == OrgDataScope.All
                || (user.isTenantAdmin() && StrUtil.isNotBlank(user.getTenantId()))

        ) {
            //如果是管理员，或是租户管理员且有租户ID
            return true;
        }

        Collection<RbacRoleInfo> roleList = loadUserRoleList(userPrincipal);

        return !CollUtil.isEmpty(roleList)
                && roleList.stream().filter(Objects::nonNull)
                .anyMatch(roleInfo -> OrgDataScope.All == roleInfo.getOrgDataScope());
    }

    /**
     * 加载当前用户有权限访问的部门列表
     *
     * @param userPrincipal
     * @param assembleTree
     * @param rootIdList    指定部分的根节点ID
     * @return 部门信息集合，可能是树形结构
     */
    <ORG extends RbacOrgInfo> Collection<ORG> loadUserOrgList(Serializable userPrincipal, boolean assembleTree, String... rootIdList);

    /**
     * 校验用户是否是否可以访问机构
     *
     * @param userPrincipal
     * @param parentId
     * @param orgId
     */
    default void checkOrgAccessible(Serializable userPrincipal, String tenantId, String parentId, String orgId) {

        RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "用户不能为空");

        //优化效率
        if (user.isSuperAdmin() || user.isSaasAdmin()) {
            return;
        }

        //租户ID必须相等
        Assert.isTrue(StrUtil.isBlank(tenantId) || user.getTenantId().equals(tenantId), "非关联的租户[{}]", tenantId);

        //优化效率
        if (user.isTenantAdmin()) {
            return;
        }

        //只有租户管理员可以操作根节点
        Assert.isTrue(StrUtil.isNotBlank(parentId) || user.isTenantAdmin(), "组织机构上级节点不能为空");

        Collection<RbacOrgInfo> orgList = loadUserOrgList(userPrincipal, false);

        Assert.notNull(orgList, "无可用的组织机构，请检查是否授权");

        Assert.isFalse(orgList.isEmpty(), "无可用的组织机构，请检查是否授权");

        Assert.isTrue(StrUtil.isBlank(parentId) || orgList.stream().anyMatch(org -> org.getId().equals(parentId)), "父组织机构[{}]未授权", parentId);

        Assert.isTrue(StrUtil.isBlank(orgId) || orgList.stream().anyMatch(org -> org.getId().equals(orgId)), "组织机构[{}]未授权", orgId);

    }


    /// /////////////////////////////////////////////

    /**
     * 检查数据访问级别
     *
     * @param userPrincipal
     * @param requireDataConfidentialLevels
     * @return
     */
    @Operation(summary = "获取用户的机密数据访问级别", description = "当用户本身没有定义访问级别时,运行成本比较高,尽量不要多次调用")
    default boolean canAccessConfidentialDataByUser(Serializable userPrincipal, Integer... requireDataConfidentialLevels) {
        return canAccessConfidentialData(() -> getUserConfidentialDataAccessLevel(userPrincipal), requireDataConfidentialLevels);
    }

    /**
     * 检查数据访问级别
     *
     * @param userConfidentialDataAccessLevelSupplier 用户的机密数据访问级别
     * @param requireDataConfidentialLevels           目标数据机密级别  , null 表示非机密数据
     * @return
     */
    default boolean canAccessConfidentialData(Supplier<Integer> userConfidentialDataAccessLevelSupplier, Integer... requireDataConfidentialLevels) {

        if (requireDataConfidentialLevels == null
                || requireDataConfidentialLevels.length == 0) {
            return true;
        }

        if (!(userConfidentialDataAccessLevelSupplier instanceof CacheSupplier)) {
            //缓存提升性能
            userConfidentialDataAccessLevelSupplier = new CacheSupplier<>(userConfidentialDataAccessLevelSupplier);
        }

        for (Integer requireDataConfidentialLevel : requireDataConfidentialLevels) {

            //非机密数据, 允许访问
            if (requireDataConfidentialLevel == null) {
                continue;
            }

            Integer userConfidentialDataAccessLevel = userConfidentialDataAccessLevelSupplier.get();

            if (userConfidentialDataAccessLevel == null
                    || userConfidentialDataAccessLevel < requireDataConfidentialLevel) {
                return false;
            }
        }

        return true;
    }

    /**
     * 获取用户的机密数据访问级别
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    @Operation(summary = "获取用户的机密数据访问级别", description = "当用户本身没有定义访问级别时,运行成本比较高,尽量不要多次调用")
    default Integer getUserConfidentialDataAccessLevel(Serializable userPrincipal) {

        RbacUserInfo loadUser = loadUser(userPrincipal);

        // 0 重要逻辑,任何角色都要检查机密数据级别,除了顶级SA账号, 其他账号都要检查
        if (loadUser.isTopSuperAdmin()) {
            return Integer.MAX_VALUE;
        }

        //优先使用用户自身的数据访问级别
        if (loadUser.getConfidentialDataAccessLevel() != null) {
            return loadUser.getConfidentialDataAccessLevel();
        }

        //获取用户角色
        Collection<RbacRoleInfo> roleList = loadUserRoleList(loadUser);

        if (isEmptyOrAllNull(roleList)) {
            return null;
        }

        // 获取用户拥有角色中最大数据访问级别
        OptionalInt max = roleList.stream()
                .filter(Objects::nonNull)
                .filter(role -> role.getConfidentialDataAccessLevel() != null)
                .mapToInt(RbacRoleInfo::getConfidentialDataAccessLevel)
                .max();

        // 返回最大数据访问级别
        return max.isPresent() ? max.getAsInt() : null;
    }


    /**
     * 加载租户的角色列表
     * <p>
     * tenantId 为 null 时加载公共角色
     *
     * @param tenantId 可为null，为 null 时加载公共角色
     * @return
     */
    <R extends RbacRoleInfo> Collection<R> loadTenantRoleList(String tenantId);

    /**
     * 加载角色列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    default <R extends RbacRoleInfo> Collection<R> loadTenantRoleListByCodes(String tenantId, Collection<String> roleCodeList) {
        return (Collection<R>) loadTenantRoleList(tenantId).stream().filter(r -> roleCodeList.contains(r.getCode())).collect(Collectors.toSet());
    }

    /**
     * 加载用户角色列表
     *
     * @param userPrincipal
     * @return
     */
    @Operation(summary = "加载用户角色列表", description = "不返回机密的角色数据")
    default <R extends RbacRoleInfo> Collection<R> loadUserRoleList(Serializable userPrincipal, boolean includeDisable) {

        RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "用户[{}]无法加载", userPrincipal);

        //如果用户没有角色
        if (isEmptyOrAllBlank(user.getRoleList())) {
            return Collections.emptyList();
        }

        //获取租户的角色列表
        Collection<RbacRoleInfo> roleList = loadTenantRoleList(user.getTenantId());

        if (isEmptyOrAllNull(roleList)) {
            return Collections.emptyList();
        }

        //获取用户自身数据访问级别
        Supplier<Integer> userConfidentialDataAccessLevelSupplier = new CacheSupplier<>(() -> getUserConfidentialDataAccessLevel(user));

        //从全局角色和租户角色中找角色
        //如果出现同个角色编码的，优先从租户自己的角色中查找
        return (Collection<R>) user.getRoleList().stream().filter(Objects::nonNull)
                .map(code ->
                        roleList.stream()
                                .filter(roleInfo ->
                                        //如果出现同个角色编码的，优先从租户自己的角色中查找
                                        (!StringUtils.hasText(roleInfo.getTenantId()) || roleInfo.getTenantId().equals(user.getTenantId()))
                                                && roleInfo.getCode().equals(code)
                                )
                                //优先获取租户自己的角色
                                .findFirst()
                                .orElse(null)
                ).filter(Objects::nonNull)

                //过滤访问级别正常的
                .filter(roleInfo -> canAccessConfidentialData(userConfidentialDataAccessLevelSupplier, roleInfo.getConfidentialDataAccessLevel()))

                //启用的
                .filter(roleInfo -> includeDisable || roleInfo.isEnable())
                .collect(Collectors.toList());
    }

    /**
     * 获取指定角色的权限列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    default Collection<String> loadRolePermissionList(Serializable tenantId, String... roleCodeList) {
        return loadRolePermissionList(tenantId, Arrays.asList(roleCodeList));
    }

    /**
     * 获取指定角色的权限列表
     *
     * @param tenantId
     * @param roleCodeList 过滤出指定的角色
     * @return
     */
    default Collection<String> loadRolePermissionList(Serializable tenantId, List<String> roleCodeList) {

        if (isEmptyOrAllBlank(roleCodeList)) {
            //如果没有角色编码，则返回空
            return Collections.emptyList();
        }

        //复制列表
        Collection<RbacRoleInfo> roleList = new ArrayList<>(loadTenantRoleList((String) tenantId));

        return roleList
                .stream().filter(Objects::nonNull)
                .filter(r -> !isEmptyOrAllBlank(r.getPermissionList()))
                //过滤出指定的角色
                .filter(r -> roleCodeList.contains(r.getCode()))

                .flatMap(r -> r.getPermissionList().stream())

                .filter(StrUtil::isNotBlank)

                //去重
                //  .distinct()
                .collect(Collectors.toSet());
    }


    @Operation(summary = "加载用户角色列表", description = "不包括已经禁用的角色")
    default <R extends RbacRoleInfo> Collection<R> loadUserRoleList(Serializable userPrincipal) {
        return loadUserRoleList(userPrincipal, false);
    }


    /**
     * 加载用户角色编码列表
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    default Collection<String> loadUserRoleCodeList(Serializable userPrincipal) {
        return loadUserRoleList(userPrincipal).stream()
                .filter(Objects::nonNull)
                .map(RbacRoleInfo::getCode)
                .filter(StringUtils::hasText)
                .collect(Collectors.toList());
    }

    /**
     * 加载用户权限列表
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    default Collection<String> loadUserPermissionExprList(Serializable userPrincipal) {
        return loadUserRoleList(userPrincipal).stream()
                .filter(Objects::nonNull)
                .map(RbacRoleInfo::getPermissionList)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .filter(StringUtils::hasText)
                .collect(Collectors.toList());
    }

}
