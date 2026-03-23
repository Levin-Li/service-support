package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.levin.commons.rbac.RbacMiscUtils.*;


/**
 * 加载服务
 *
 * @author echo
 */
public interface RbacBaseService extends RbacBaseUserService {

    String USER_ROLE_MAX_CONFIDENTIAL_DATA_ACCESS_LEVEL_KEY = RbacUserInfo.class.getName() + ".roleMaxConfidentialDataAccessLevel";

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
     * 是否能访问所有部门
     *
     * @param userPrincipal
     * @return
     */
    @Operation(summary = "是否能访问所有部门", description = "建议子类重新实现")
    default boolean canAccessAllOrg(Serializable userPrincipal) {

        RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "用户({})不存在", userPrincipal);

        if (user.isSuperAdmin()
                || user.isSaasAdmin()
                || (user.isTenantAdmin() && RbacMiscUtils.isNotBlank(user.getTenantId()))) {
            //如果是管理员，或是租户管理员且有租户ID
            return true;
        }

        DataScope userDataScope = getUserDataScope(user);

        if (userDataScope == null
                || userDataScope.getOrgScopeList() == null
                || userDataScope.getOrgScopeList().isEmpty()) {
            return false;
        }


        //@todo 获取用户数据权限
        return userDataScope.getOrgScopeList()
                .stream()
                .filter(Objects::nonNull)

                //允许所有部门
                .anyMatch(OrgScope::isAllowAllOrg)

                &&

                //不能有任何拒绝的
                userDataScope.getOrgScopeList()
                        .stream()
                        .filter(Objects::nonNull)

                        //不能有任何拒绝的
                        .noneMatch(OrgScope::isDeny)
                ;
    }


    @Operation(summary = "获取用户数据权限", description = "优先使用用户的数据权限,没有才获取角色上的定义的数据权限")
    default DataScope getUserDataScope(Serializable userPrincipal) {

        RbacUserInfo user = loadUser(userPrincipal);
        Assert.notNull(user, "用户({})不存在", userPrincipal);

        Map<String, Object> transientExInfo = user.getTransientExInfo();

        //缓存加载
        SimpleDataScope dataScope = transientExInfo != null ? (SimpleDataScope) transientExInfo.get(DataScope.class.getName()) : null;

        if (dataScope != null) {
            return dataScope;
        }

        dataScope = new SimpleDataScope();

        //优先使用用户自定义的数据权限
        dataScope.setConfidentialDataAccessLevel(user.getConfidentialDataAccessLevel());

        //优先使用用户自定义的组织数据权限
        dataScope.setOrgScopeList(mergeOrgScopeList(user.getOrgScopeList()));

        //
        final boolean notUserOrgScope = isAllNull(dataScope.getOrgScopeList());

        if (notUserOrgScope || dataScope.getConfidentialDataAccessLevel() == null) {

            //加载用户的所有角色
            final List<RbacRoleInfo> roleList = loadUserRoleList(user);

            if (dataScope.getConfidentialDataAccessLevel() == null) {

                OptionalInt max = roleList.stream()
                        .filter(Objects::nonNull)
                        .mapToInt(RbacRoleInfo::getConfidentialDataAccessLevel)
                        .filter(Objects::nonNull)
                        .max();

                //获取最高权限
                if (max.isPresent()) {
                    dataScope.setConfidentialDataAccessLevel(max.getAsInt());
                }
            }

            if (notUserOrgScope) {

                dataScope.setOrgScopeList(

                        //合并
                        mergeOrgScopeList(

                                //角色权限
                                roleList.stream()
                                        .filter(Objects::nonNull)
                                        .map(RbacRoleInfo::getOrgScopeList)
                                        .filter(Objects::nonNull)
                                        .flatMap(Collection::stream)
                                        .collect(Collectors.toList())

                        )
                );
            }

        }

        //放入缓存
        if (transientExInfo != null) {
            transientExInfo.put(DataScope.class.getName(), dataScope);
        }

        return dataScope;

    }


    @Operation(summary = "合并组织权限列表", description = "合并组织权限列表")
    default Collection<OrgScope> mergeOrgScopeList(Collection<OrgScope> orgScopeList) {

        if (orgScopeList == null || orgScopeList.isEmpty()) {
            return Collections.emptyList();
        }

        final List<OrgScope> result = new ArrayList<>(orgScopeList.size());

        final Map<String, OrgScope> scopeMap = new HashMap<>();

        boolean hasDeny = false;

        OrgScope allowAllScope = null;

        //用普通循环
        for (OrgScope scope : orgScopeList) {

            //忽略无效数据
            if (scope == null
                    || StrUtil.isBlank(scope.getOrgId())
                    || StrUtil.isBlank(scope.getScopeExpression())) {
                continue;
            }

            final String scopeKey = String.join("_", scope.getOrgId(), "" + scope.isAllow(), scope.getScopeExpression());

            if (scopeMap.containsKey(scopeKey)) {
                //如果有重复的,则忽略
                continue;
            }
            //添加
            scopeMap.put(scopeKey, scope);

            if (scope.isDeny()) {

                hasDeny = true;

                if (scope.isDenyAllOrg()) {

                    //拒绝所有部门, 则忽略其他
                    result.clear();
                    result.add(scope);

                    break;
                }

            } else if (scope.isAllowAllOrg()) {

                allowAllScope = scope;

            }

            result.add(scope);

        }// 循环结束

        //如果没有拒绝的, 有允许所有的, 则忽略其他
        if (!hasDeny && allowAllScope != null) {
            result.clear();
            result.add(allowAllScope);
        }


        return result;
    }

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
     * 加载所有父部门
     *
     * @param tenantId
     * @param orgPrincipal orgId 或是 RbacOrgInfo
     * @return
     */
    @Operation(summary = "加载所有的直系父组织", description = "要求按由近到远的顺序返回")
    default <ORG extends RbacOrgInfo> List<ORG> loadOrgParentList(Serializable tenantId, boolean containsSelf, Serializable orgPrincipal, boolean selfAudit) {

        RbacOrgInfo leafOrg = null;

        Assert.isTrue(RbacMiscUtils.isNotBlank(orgPrincipal), "orgPrincipal为空");

        if (orgPrincipal instanceof RbacOrgInfo) {

            leafOrg = (ORG) orgPrincipal;

            orgPrincipal = leafOrg.getId();

        }

        final List<ORG> orgList = loadTenantOrgList(tenantId, false);

        //@todo 优化效率, 当列表太大时,用map查找,是否性能更好
        final Map<Serializable, ORG> orgMap = orgList.stream().filter(Objects::nonNull).collect(Collectors.toMap(RbacOrgInfo::getId, Function.identity()));

        //Function<Serializable, ORG> getOrg = tempOrgId -> orgList.stream().filter(Objects::nonNull).filter(o -> o.getId().equals(tempOrgId)).findAny().orElse(null)

        if (leafOrg == null) {
            leafOrg = orgMap.get(orgPrincipal);
        }

        Assert.notNull(leafOrg, "组织[{}]不存在", orgPrincipal);

        if (selfAudit && !leafOrg.selfAudit()) {
            throw new IllegalStateException("组织不可用-" + leafOrg.getName());
        }

        final List<ORG> parentList = new ArrayList<>();

        if (containsSelf) {
            parentList.add((ORG) leafOrg);
        }

        //获取所有父部门 , 防止递归死循环
        while (leafOrg != null
                && RbacMiscUtils.isNotBlank(leafOrg.getParentId())) {

            Assert.isTrue(!leafOrg.getId().equals(leafOrg.getParentId()), "组织[{}]的父组织ID[{}]与自身ID相同", leafOrg.getName(), leafOrg.getParentId());

            RbacOrgInfo tempOrg = leafOrg;

            leafOrg = orgMap.get(leafOrg.getParentId());

            //这个一个非常难找的编译时bug , tempOrg.getParentId() 返回一个泛型, 在编译时, 会被转换为数组
            Assert.notNull(leafOrg, "组织[{}]不存在", (Object /* 强制转换,防止被编译成数组 */) tempOrg.getParentId());

            if (selfAudit && !leafOrg.selfAudit()) {
                throw new IllegalStateException("组织不可用-" + leafOrg.getName());
            }

            //@todo 防止递归死循环
            final String tempLeafOrgId = leafOrg.getId();
            Assert.isTrue(parentList.stream().noneMatch(o -> o.getId().equals(tempLeafOrgId))

                    , "组织节点[{}-{}]出现循环引用:{}"
                    , leafOrg.getId(), leafOrg.getName()
                    , parentList.stream().map(RbacCoreObject::getId).map(Objects::toString).collect(Collectors.joining(" -> ")) + " -> " + tempLeafOrgId
            );

            parentList.add((ORG) leafOrg);
        }

        return parentList;
    }


    /**
     * 校验用户是否是否可以访问机构
     *
     * @param userPrincipal
     * @param parentId
     * @param orgId
     */
    default void checkOrgAccessible(Serializable userPrincipal, Serializable tenantId, Serializable parentId, Serializable orgId) {

        RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "用户({})不存在", userPrincipal);

        userPrincipal = user;

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

        Collection<RbacOrgInfo> orgList = loadUserOrgList(user, false);

        Assert.isTrue(orgList != null && !orgList.isEmpty(), "无可用的组织机构，请检查是否授权");

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

        RbacUserInfo userInfo = loadUser(userPrincipal);

        // 0 重要逻辑,任何角色都要检查机密数据级别,除了顶级SA账号, 其他账号都要检查
        if (userInfo.isTopSuperAdmin()) {
            return Integer.MAX_VALUE;
        }

        //优先使用用户自身的数据访问级别
        if (userInfo.getConfidentialDataAccessLevel() != null) {
            return userInfo.getConfidentialDataAccessLevel();
        }

        //@todo 优化效率 , 如何使用缓存???

        Map<String, Object> transientExInfo = userInfo.getTransientExInfo();

        if (transientExInfo != null
                && transientExInfo.containsKey(USER_ROLE_MAX_CONFIDENTIAL_DATA_ACCESS_LEVEL_KEY)) {
            return (Integer) transientExInfo.get(USER_ROLE_MAX_CONFIDENTIAL_DATA_ACCESS_LEVEL_KEY);
        }

        Integer maxConfidentialDataAccessLevel = null;

        //获取用户角色
        final Collection<RbacRoleInfo> roleList = loadUserRoleList(userInfo);

        if (!isAllNull(roleList)) {

            // 获取用户拥有角色中最大数据访问级别
            OptionalInt max = roleList.stream()
                    .filter(Objects::nonNull)
                    .filter(role -> role.getConfidentialDataAccessLevel() != null)
                    .mapToInt(RbacRoleInfo::getConfidentialDataAccessLevel)
                    .max();

            // 返回最大数据访问级别
            maxConfidentialDataAccessLevel = max.isPresent() ? max.getAsInt() : null;
        }

        if (transientExInfo != null) {
            transientExInfo.put(USER_ROLE_MAX_CONFIDENTIAL_DATA_ACCESS_LEVEL_KEY, maxConfidentialDataAccessLevel);
        }

        return maxConfidentialDataAccessLevel;
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
    @Operation(summary = "加载租户的角色列表", description = "同时也会加载公共角色, onlyLoadEffectRole 是否只加载有效角色, 否则加载所有角色")
    <R extends RbacRoleInfo> Collection<R> loadTenantRoleList(Serializable tenantId, boolean onlyLoadEffectRole);

    /**
     * 加载角色列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    @Operation(summary = "根据角色代码加载角色列表", description = "不管角色是否处于有效状态,公共角色会并存")
    default <R extends RbacRoleInfo> Collection<R> loadTenantRoleListByCodes(final Serializable tenantId, Collection<String> roleCodeList) {
        return (Collection<R>) loadTenantRoleList(tenantId, false)
                .stream()

                // 过滤租户
                .filter(r -> RbacMiscUtils.isBlank(tenantId) ? RbacMiscUtils.isBlank(r.getTenantId()) : tenantId.equals(r.getTenantId()))

                //
                .filter(r -> roleCodeList.contains(r.getCode()))
                .collect(Collectors.toSet());
    }

    /**
     * 加载用户角色列表
     *
     * @param userPrincipal
     * @return
     */
    @Operation(summary = "加载用户角色列表", description = "")
    default <R extends RbacRoleInfo> List<R> loadUserRoleList(Serializable userPrincipal, boolean onlyLoadEffectRole) {

        RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "用户[{}]无法加载", userPrincipal);

        //如果用户没有角色
        if (isAllBlank(user.getRoleList())) {
            return Collections.emptyList();
        }

        //获取租户的角色列表
        final Collection<RbacRoleInfo> roleList = loadTenantRoleList(user.getTenantId(), onlyLoadEffectRole);

        if (isAllNull(roleList)) {
            return Collections.emptyList();
        }

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
                .stream()
                .filter(Objects::nonNull)
                .filter(r -> r.getPermissionList() != null)
                //过滤出指定的角色
                .filter(r -> roleCodeList.contains(r.getCode()))

                .flatMap(r -> r.getPermissionList().stream())

                .filter(StrUtil::isNotBlank)

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
                .filter(StrUtil::isNotBlank)

                .collect(Collectors.toSet());
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
                .filter(StrUtil::isNotBlank)

                .collect(Collectors.toSet());
    }

}
