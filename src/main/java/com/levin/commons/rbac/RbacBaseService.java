package com.levin.commons.rbac;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.levin.commons.rbac.RbacMiscUtils.isAllBlank;
import static com.levin.commons.rbac.RbacMiscUtils.isAllNull;


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
     * 加载部门
     *
     * @param orgPrincipal
     * @param <ORG>
     */
    @Operation(summary = "加载部门", description = "orgPrincipal 参数可以是orgId 或是 RbacOrgInfo")
    <ORG extends RbacOrgInfo> ORG loadOrg(Serializable orgPrincipal);

    /**
     * 加载直接下级组织
     *
     * @param tenantId
     * @param orgPrincipal id 或是 RbacOrgInfo
     * @return
     */
    @Operation(summary = "加载直接下级组织", description = "orgPrincipal 参数可以是orgId 或是 RbacOrgInfo")
    default <ORG extends RbacOrgInfo> List<ORG> loadOrgChildren(Serializable tenantId, Serializable orgPrincipal) {

        Assert.isTrue(RbacMiscUtils.isNotBlank(orgPrincipal), "父节点不能为空");

        if (orgPrincipal instanceof RbacOrgInfo) {
            orgPrincipal = ((RbacOrgInfo) orgPrincipal).getId();
        }

        Serializable orgId = orgPrincipal;

        //获取所有部门
        return (List<ORG>) loadTenantOrgList(tenantId, true).stream()
                .filter(o -> orgId.equals(o.getParentId()))
                .collect(Collectors.toList());
    }

    /**
     * 加载租户的部门列表
     * tenantId 为 null 时加载公共部门
     * 部门太多时，会导致性能问题
     *
     * @param tenantId 可为null，为 null 时加载公共部门
     * @return
     */
    @Operation(summary = "加载租户的部门列表", description = "tenantId 为 null 时加载公共部门, onlyEffectOrg 可以指定是否只加载有效组织")
    <ORG extends RbacOrgInfo> List<ORG> loadTenantOrgList(Serializable tenantId, boolean onlyLoadEffectOrg);

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
                || (user.isTenantAdmin() && RbacMiscUtils.isNotBlank(user.getTenantId()))

        ) {
            //如果是管理员，或是租户管理员且有租户ID
            return true;
        }

        List<RbacRoleInfo> roleList = loadUserRoleList(userPrincipal);

        return !CollUtil.isEmpty(roleList)
                && roleList.stream().filter(Objects::nonNull)
                .anyMatch(roleInfo -> OrgDataScope.All == roleInfo.getOrgDataScope());
    }


    /**
     * 加载所有父部门
     *
     * @param tenantId
     * @param orgPrincipal orgId 或是 RbacOrgInfo
     * @return
     */
    @Operation(summary = "加载所有的直系父组织", description = "要求按由近到远的顺序返回")
    default <ORG extends RbacOrgInfo> List<ORG> loadAllParentOrg(Serializable tenantId, boolean containsSelf, Serializable orgPrincipal) {

        RbacOrgInfo leafOrg = null;

        Assert.isTrue(RbacMiscUtils.isNotBlank(orgPrincipal), "orgPrincipal为空");

        if (orgPrincipal instanceof RbacOrgInfo) {

            leafOrg = (ORG) orgPrincipal;

            orgPrincipal = leafOrg.getId();

        }

        List<ORG> orgList = loadTenantOrgList(tenantId, false);

        //@todo 优化效率, 当列表太大时,用map查找,是否性能更好
        Map<Serializable, ORG> orgMap = orgList.stream().filter(Objects::nonNull).collect(Collectors.toMap(RbacOrgInfo::getId, Function.identity()));

        //Function<Serializable, ORG> getOrg = tempOrgId -> orgList.stream().filter(Objects::nonNull).filter(o -> o.getId().equals(tempOrgId)).findAny().orElse(null)

        if (leafOrg == null) {
            leafOrg = orgMap.get(orgPrincipal);
        }

        Assert.notNull(leafOrg, "组织[{}]不存在", orgPrincipal);

        List<ORG> parentList = new ArrayList<>();

        if (containsSelf) {
            parentList.add((ORG) leafOrg);
        }

        //获取所有父部门
        while (leafOrg != null
                && RbacMiscUtils.isNotBlank(leafOrg.getParentId())) {

            leafOrg = orgMap.get(leafOrg.getParentId());

            parentList.add((ORG) leafOrg);
        }

        return parentList;
    }

    /**
     * 加载当前用户有权限访问的部门列表
     *
     * @param userPrincipal
     * @param assembleTree
     * @param rootIdList    指定部分的根节点ID
     * @return 部门信息集合，可能是树形结构
     */
    @Operation(summary = "加载当前用户有权限访问的部门列表", description = "assembleTree 为 true 时返回树形结构")
    <ORG extends RbacOrgInfo> List<ORG> loadUserOrgList(Serializable userPrincipal, boolean assembleTree, String... rootIdList);

    /**
     * 校验用户是否是否可以访问机构
     *
     * @param userPrincipal
     * @param parentId
     * @param orgId
     */
    default void checkOrgAccessible(Serializable userPrincipal, Serializable tenantId, Serializable parentId, Serializable orgId) {

        RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "用户不能为空");

        //优化效率
        if (user.isSuperAdmin() || user.isSaasAdmin()) {
            return;
        }

        //租户ID必须相等
        Assert.isTrue(RbacMiscUtils.isBlank(tenantId) || user.getTenantId().equals(tenantId), "非关联的租户[{}]", tenantId);

        //优化效率
        if (user.isTenantAdmin()) {
            return;
        }

        //只有租户管理员可以操作根节点
        Assert.isTrue(RbacMiscUtils.isNotBlank(parentId) || user.isTenantAdmin(), "组织机构上级节点不能为空");

        Collection<RbacOrgInfo> orgList = loadUserOrgList(userPrincipal, false);

        Assert.notNull(orgList, "无可用的组织机构，请检查是否授权");

        Assert.isFalse(orgList.isEmpty(), "无可用的组织机构，请检查是否授权");

        Assert.isTrue(RbacMiscUtils.isBlank(parentId) || orgList.stream().anyMatch(org -> org.getId().equals(parentId)), "父组织机构[{}]未授权", parentId);

        Assert.isTrue(RbacMiscUtils.isBlank(orgId) || orgList.stream().anyMatch(org -> org.getId().equals(orgId)), "组织机构[{}]未授权", orgId);

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

        if (isAllNull(roleList)) {
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
     * 加载角色
     *
     * @param rolePrincipal
     */
    @Operation(summary = "加载角色", description = "角色不存在时返回null")
    <R extends RbacRoleInfo> R loadRole(Serializable rolePrincipal);

    /**
     * 加载租户的角色列表
     * <p>
     * tenantId 为 null 时加载公共角色
     *
     * @param tenantId           可为null，为 null 时加载公共角色
     * @param onlyLoadEffectRole 是否只加载有效角色 ,否则加载所有角色
     * @return
     */
    @Operation(summary = "加载租户的角色列表", description = "onlyLoadEffectRole 是否只加载有效角色 ,否则加载所有角色")
    <R extends RbacRoleInfo> Collection<R> loadTenantRoleList(Serializable tenantId, boolean onlyLoadEffectRole);

    /**
     * 加载角色列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    @Operation(summary = "根据角色代码加载角色列表", description = "不管角色是否处于有效状态")
    default <R extends RbacRoleInfo> Collection<R> loadTenantRoleListByCodes(Serializable tenantId, Collection<String> roleCodeList) {
        return (Collection<R>) loadTenantRoleList(tenantId, false).stream().filter(r -> roleCodeList.contains(r.getCode())).collect(Collectors.toSet());
    }

    /**
     * 加载用户角色列表
     *
     * @param userPrincipal
     * @return
     */
    @Operation(summary = "加载用户角色列表", description = "不返回机密的角色数据")
    default <R extends RbacRoleInfo> List<R> loadUserRoleList(Serializable userPrincipal, boolean onlyLoadEffectRole) {

        RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "用户[{}]无法加载", userPrincipal);

        //如果用户没有角色
        if (isAllBlank(user.getRoleList())) {
            return Collections.emptyList();
        }

        //获取租户的角色列表
        Collection<RbacRoleInfo> roleList = loadTenantRoleList(user.getTenantId(), onlyLoadEffectRole);

        if (isAllNull(roleList)) {
            return Collections.emptyList();
        }

        //获取用户自身数据访问级别
        Supplier<Integer> userConfidentialDataAccessLevelSupplier = new CacheSupplier<>(() -> getUserConfidentialDataAccessLevel(user));

        //从全局角色和租户角色中找角色
        //如果出现同个角色编码的，优先从租户自己的角色中查找
        return (List<R>) user.getRoleList().stream().filter(Objects::nonNull)
                .map(code ->
                        roleList.stream()
                                .filter(roleInfo ->
                                        //如果出现同个角色编码的，优先从租户自己的角色中查找
                                        (RbacMiscUtils.isBlank(roleInfo.getTenantId()) || roleInfo.getTenantId().equals(user.getTenantId()))
                                                && roleInfo.getCode().equals(code)
                                )
                                //优先获取租户自己的角色
                                .findFirst()
                                .orElse(null)
                ).filter(Objects::nonNull)

                //过滤访问级别正常的
                .filter(roleInfo -> canAccessConfidentialData(userConfidentialDataAccessLevelSupplier, roleInfo.getConfidentialDataAccessLevel()))

                //启用的

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
    @Operation(summary = "根据角色代码加载权限列表", description = "不管角色是否处于有效状态")
    default Collection<String> loadRolePermissionList(Serializable tenantId, List<String> roleCodeList) {

        if (isAllBlank(roleCodeList)) {
            //如果没有角色编码，则返回空
            return Collections.emptyList();
        }

        return loadTenantRoleList(tenantId, false)
                .stream().filter(Objects::nonNull)
                .filter(r -> r.getPermissionList() != null)
                //过滤出指定的角色
                .filter(r -> roleCodeList.contains(r.getCode()))

                .flatMap(r -> r.getPermissionList().stream())

                .filter(RbacMiscUtils::isNotBlank)

                //去重
                //  .distinct()
                .collect(Collectors.toSet());
    }


    @Operation(summary = "加载用户角色列表", description = "不包括已经禁用的角色")
    default <R extends RbacRoleInfo> List<R> loadUserRoleList(Serializable userPrincipal) {
        return loadUserRoleList(userPrincipal, true);
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
                .filter(RbacMiscUtils::isNotBlank)
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
                .filter(RbacMiscUtils::isNotBlank)
                .collect(Collectors.toList());
    }

}
