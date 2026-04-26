package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.dao.domain.ConfidentialObject;
import com.levin.commons.dao.domain.ProxyWrapperObject;
import com.levin.commons.utils.ExpressionUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

    Map<Class<?>, List<Field>> COPYABLE_FIELDS_CACHE = new ConcurrentReferenceHashMap<>();
    Map<Class<?>, Method> CHILDREN_SETTER_CACHE = new ConcurrentReferenceHashMap<>();
    Map<Class<?>, Method> NODE_PATH_SETTER_CACHE = new ConcurrentReferenceHashMap<>();
    Map<Class<?>, Field> CHILDREN_FIELD_CACHE = new ConcurrentReferenceHashMap<>();
    Map<Class<?>, Field> NODE_PATH_FIELD_CACHE = new ConcurrentReferenceHashMap<>();

    // 自定义 Groovy 规则的编译结果可以跨请求复用，避免每次重新编译脚本。
    Map<String, Class<Object>> ORG_SCOPE_GROOVY_CLASS_CACHE = new ConcurrentReferenceHashMap<>();

    // SpEL 解析本身也有成本，这里缓存编译后的表达式对象。
    Map<String, Expression> ORG_SCOPE_SPEL_CACHE = new ConcurrentReferenceHashMap<>();

    SpelExpressionParser ORG_SCOPE_SPEL_PARSER = new SpelExpressionParser();

    /**
     * 组织关系匹配器
     */
    AntPathMatcher ORG_SCOPE_PATH_MATCHER = new AntPathMatcher();

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

    @Operation(summary = "加载所有的租户列表", description = "onlyEffectOrg 可以指定是否只加载有效租户")
    <TENANT extends RbacTenantInfo> Collection<TENANT> loadAllTenantList(boolean onlyLoadEffectTenant);

    /**
     * 加载租户列表
     *
     * @param onlyLoadEffectTenant
     * @param <TENANT>
     * @return
     */
    @Operation(summary = "加载用户能访问的租户列表", description = "性能扩展点：默认实现会先加载候选租户再在内存中按数据范围过滤；子类可覆盖为按用户、租户表达式或缓存直接裁剪。onlyEffectOrg 可以指定是否只加载有效租户")
    default <TENANT extends RbacTenantInfo> Collection<TENANT> loadUserAccessibleTenantList(Serializable userPrincipal, boolean onlyLoadEffectTenant) {

        final RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "用户({})不存在", userPrincipal);

        final Collection<TENANT> allTenantList = (Collection<TENANT>) Optional.ofNullable(loadAllTenantList(onlyLoadEffectTenant))
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (allTenantList.isEmpty()) {
            return Collections.emptyList();
        }

        // 只有顶级超级管理员可以跳过所有范围判断直接拿到最大结果。
        if (user.isTopSuperAdmin()) {
            return allTenantList;
        }

        // 普通超级管理员和 SaaS 管理员不再走 DataScope 过滤，只补一层机密级别过滤即可。
        if (user.isSuperAdmin() || user.isSaasAdmin()) {
            return filterByConfidentialAccess(user, allTenantList);
        }

        final DataScope dataScope = getUserDataScope(user);
        final Collection<? extends OrgScope> orgScopeList = dataScope != null ? dataScope.getOrgScopeList() : Collections.emptyList();
        final Set<String> accessibleTenantIds = resolveUserAccessibleTenantIds(user, orgScopeList, allTenantList);

        if (accessibleTenantIds.isEmpty()) {
            return Collections.emptyList();
        }

        return allTenantList.stream()
                .filter(tenant -> accessibleTenantIds.contains(Objects.toString(tenant.getId(), "")))
                .collect(Collectors.toList());

    }

    /**
     * 加载租户
     *
     * @param tenantPrincipal
     * @param <TENANT>
     * @return
     */
    @Operation(summary = "加载租户", description = "加载指定租户")
    <TENANT extends RbacTenantInfo> TENANT loadTenant(Serializable tenantPrincipal);

    /**
     * 加载组织
     *
     * @param orgPrincipal
     * @param <ORG>
     */
    @Operation(summary = "加载组织", description = "orgPrincipal 参数可以是orgId 或是 RbacOrgInfo")
    <ORG extends RbacOrgInfo> ORG loadOrg(Serializable orgPrincipal);

    /**
     * 加载租户的组织列表
     * tenantId 为 null 时加载无租户的组织
     * 组织太多时，会导致性能问题
     *
     * @param tenantId 可为null，为 null 时加载无租户的组织
     * @return
     */
    @Operation(summary = "加载租户的组织列表", description = "性能扩展点：组织量大时应由子类在数据层按租户、状态、根节点或 nodePath 预裁剪。tenantId 为 null 时加载无租户组织, onlyEffectOrg 指定是否只加载有效组织, 要求方法返回只读对象")
    <ORG extends RbacOrgInfo> Collection<ORG> loadTenantOrgList(Serializable tenantId, boolean onlyLoadEffectOrg);

    @Operation(summary = "加载用户能访问的组织列表", description = "性能扩展点：默认实现会按租户加载候选组织后在内存中计算 DataScope；子类可覆盖为 SQL/缓存直接计算用户可访问组织。onlyEffect 可以指定是否只加载有效组织")
    default <ORG extends RbacOrgInfo> Collection<ORG> loadUserAccessibleOrgList(Serializable userPrincipal, boolean onlyLoadEffectOrg) {

        RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "用户({})不存在", userPrincipal);

        // 顶级超级管理员直接拿最大组织结果，不再受 tenantExpression / orgScope 的候选收敛影响。
        if (user.isTopSuperAdmin()) {
            return loadMaxAccessibleOrgList(onlyLoadEffectOrg);
        }

        // 普通超级管理员和 SaaS 管理员直接取最大候选组织，再按机密级别做过滤。
        if (user.isSuperAdmin() || user.isSaasAdmin()) {
            return filterByConfidentialAccess(user, loadMaxAccessibleOrgList(onlyLoadEffectOrg));
        }

        final DataScope dataScope = getUserDataScope(user);
        final Collection<? extends OrgScope> orgScopeList = dataScope != null ? dataScope.getOrgScopeList() : Collections.emptyList();

        // tenantExpression 负责决定组织范围作用于哪些租户，这里先把“可枚举租户”收敛出来，再按表达式挑选候选租户。
        final Set<String> tenantIdSet = resolveScopedTenantIds(user, orgScopeList, loadAllTenantList(onlyLoadEffectOrg));

        final Collection<ORG> orgList = new LinkedHashSet<>();

        for (String tenantId : tenantIdSet) {
            if (tenantId == null) {
                continue;
            }
            orgList.addAll((Collection<ORG>) loadTenantOrgList(tenantId, onlyLoadEffectOrg).stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toCollection(LinkedHashSet::new)));
        }

        if (tenantIdSet.contains(null)) {
            // tenantExpression 命中了无租户场景时，还要把公共组织一起纳入候选集合。
            orgList.addAll(loadTenantOrgList(null, onlyLoadEffectOrg));
        }

        if (orgList.isEmpty()) {
            return Collections.emptyList();
        }

        if (canAccessAllOrg(user, dataScope)) {
            return orgList;
        }

        final Map<String, ORG> orgMap = orgList.stream()
                .filter(org -> RbacMiscUtils.isNotBlank(org.getId()))
                .collect(Collectors.toMap(org -> Objects.toString(org.getId(), ""),
                        Function.identity(),
                        (left, right) -> left,
                        LinkedHashMap::new));

        if (orgMap.isEmpty()) {
            return Collections.emptyList();
        }

        final Set<String> accessibleOrgIds = calcAccessibleOrgIds(user, orgScopeList, orgList, orgMap);

        if (accessibleOrgIds.isEmpty()) {
            return Collections.emptyList();
        }

        return orgList.stream()
                .filter(org -> accessibleOrgIds.contains(Objects.toString(org.getId(), "")))
                .collect(Collectors.toList());

    }

    /**
     * 加载“最大候选组织集合”。
     * 包含所有租户组织和无租户公共组织，供顶级超级管理员直接返回，也供普通超管/SaaS 管理员做机密级别过滤。
     */
    @Operation(summary = "加载最大候选组织集合", description = "性能扩展点：默认实现遍历所有租户并逐个加载组织；子类可覆盖为一次性批量查询或缓存读取，避免 N+1 加载。")
    default <ORG extends RbacOrgInfo> Collection<ORG> loadMaxAccessibleOrgList(boolean onlyLoadEffectOrg) {
        final Collection<ORG> allOrgList = new LinkedHashSet<>();

        for (RbacTenantInfo tenant : Optional.ofNullable(loadAllTenantList(onlyLoadEffectOrg)).orElse(Collections.emptyList())) {
            if (tenant == null) {
                continue;
            }

            final Serializable tenantId = (Serializable) tenant.getId();
            allOrgList.addAll((Collection<ORG>) Optional.ofNullable(loadTenantOrgList(tenantId, onlyLoadEffectOrg))
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toCollection(LinkedHashSet::new)));
        }

        allOrgList.addAll((Collection<ORG>) Optional.ofNullable(loadTenantOrgList(null, onlyLoadEffectOrg))
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(LinkedHashSet::new)));

        return allOrgList;
    }

    /**
     * 按机密级别过滤结果集合。
     * TopSA 之外的全局快捷路径都需要经过这一层，确保“直接返回最大结果”时仍尊重对象自身的机密级别。
     */
    default <T extends ConfidentialObject> Collection<T> filterByConfidentialAccess(Serializable userPrincipal, Collection<T> objectList) {


        if (objectList == null || objectList.isEmpty()) {
            return Collections.emptyList();
        }

        final RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "用户({})不存在", userPrincipal);
        final Integer userConfidentialAccessLevel = getUserConfidentialDataAccessLevel(user);

        return objectList.stream()
                .filter(Objects::nonNull)
                // 集合过滤前先把用户机密级别取出来，避免在循环中重复触发 getUserConfidentialDataAccessLevel(...)。
                .filter(obj -> canAccessConfidentialData(() -> userConfidentialAccessLevel, obj.getConfidentialLevel()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Operation(summary = "组装组织树", description = "性能扩展点：组织量大或已有数据库树查询能力时，子类可覆盖为直接返回预裁剪/预组装树。rootIdList有指定时,表示只返回指定的根节点, 否则返回所有的根节点")
    default <ORG extends RbacOrgInfo> Collection<ORG> assembleOrgTree(Collection<ORG> orgList, String... rootIdList) {
        return assembleOrgTree(orgList, true, rootIdList);
    }

    @Operation(summary = "组装组织树", description = "性能扩展点：默认实现会建立索引、复制节点并可选构建 nodePath；子类可覆盖为数据层递归查询/物化路径查询或轻量 DTO 组树。buildNodePath 指定是否构建 nodePath, rootIdList有指定时,表示只返回指定的根节点, 否则返回所有的根节点")
    default <ORG extends RbacOrgInfo> Collection<ORG> assembleOrgTree(Collection<ORG> orgList, boolean buildNodePath, String... rootIdList) {

        if (orgList == null || orgList.isEmpty()) {
            return Collections.emptyList();
        }

        final List<ORG> sourceOrgList = orgList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (sourceOrgList.isEmpty()) {
            return Collections.emptyList();
        }

        final Map<String, ORG> sourceOrgMap = sourceOrgList.stream()
                .filter(org -> RbacMiscUtils.isNotBlank(org.getId()))
                .collect(Collectors.toMap(org -> Objects.toString(org.getId(), ""),
                        Function.identity(),
                        (left, right) -> left,
                        LinkedHashMap::new));

        if (sourceOrgMap.isEmpty()) {
            return Collections.emptyList();
        }

        final Map<String, List<String>> childrenByParentId = buildChildrenByParentId(sourceOrgMap);
        final Set<String> selectedRootIds = normalizeOrgIdSet(Arrays.asList(rootIdList));
        final Set<String> selectedOrgIds = selectedRootIds.isEmpty()
                ? new LinkedHashSet<>(sourceOrgMap.keySet())
                : collectDescendantOrgIds(selectedRootIds, sourceOrgMap, childrenByParentId);

        if (selectedOrgIds.isEmpty()) {
            return Collections.emptyList();
        }

        validateSelectedOrgTreeAcyclic(selectedOrgIds, sourceOrgMap);

        final Map<String, ORG> copiedOrgMap = new LinkedHashMap<>();
        final Map<String, String> nodePathCache = buildNodePath ? new HashMap<>() : Collections.emptyMap();

        for (ORG sourceOrg : sourceOrgMap.values()) {
            final String orgId = Objects.toString(sourceOrg.getId(), "");
            if (!selectedOrgIds.contains(orgId)) {
                continue;
            }
            copiedOrgMap.put(orgId, copyOrgNodeForAssembleTree(sourceOrg));
        }

        copiedOrgMap.values().forEach(this::resetCopiedNode);

        final List<ORG> rootList = new ArrayList<>();

        for (ORG sourceOrg : sourceOrgMap.values()) {
            final String orgId = Objects.toString(sourceOrg.getId(), "");

            if (!selectedOrgIds.contains(orgId)) {
                continue;
            }

            ORG copiedOrg = copiedOrgMap.get(orgId);

            if (buildNodePath) {
                setNodePathOnCopy(copiedOrg, resolveNodePath(sourceOrg, sourceOrgMap, nodePathCache));
            }

            final String parentId = Objects.toString(sourceOrg.getParentId(), "");

            if (StrUtil.isBlank(parentId) || !selectedOrgIds.contains(parentId)) {
                rootList.add(copiedOrg);
                continue;
            }

            ORG copiedParent = copiedOrgMap.get(parentId);

            if (copiedParent == null || !appendChild(copiedParent, copiedOrg)) {
                rootList.add(copiedOrg);
            }
        }

        return rootList;
    }

    /**
     * 加载当前用户有权限访问的组织列表
     *
     * @param userPrincipal
     * @param assembleTree
     * @param rootIdList    指定部分的根节点ID
     * @return 组织信息集合，可能是树形结构
     */
    @Operation(summary = "加载当前用户有权限访问的组织列表", description = "assembleTree 为 true 时返回树形结构")
    default <ORG extends RbacOrgInfo> Collection<ORG> loadUserOrgList(Serializable userPrincipal, boolean assembleTree, String... rootIdList) {

        final Collection<ORG> accessibleOrgList = loadUserAccessibleOrgList(userPrincipal, true);

        if (!assembleTree) {
            return accessibleOrgList;
        }

        return assembleOrgTree(accessibleOrgList, rootIdList);

    }


    /**
     * 是否能访问所有组织
     *
     * @param userPrincipal
     * @return
     */
    @Operation(summary = "是否能访问所有组织", description = "性能扩展点：建议子类覆盖为基于用户标记、角色缓存或权限缓存的 O(1) 判断，避免重复解析 DataScope")
    default boolean canAccessAllOrg(Serializable userPrincipal) {

        RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "用户({})不存在", userPrincipal);

        if (user.isTopSuperAdmin()
                || (user.isTenantAdmin() && RbacMiscUtils.isNotBlank(user.getTenantId()))) {
            // 顶级超级管理员可以直接视为全量可访问；租户管理员仍按本租户的全组织处理。
            return true;
        }

        return canAccessAllOrg(user, getUserDataScope(user));
    }


    @Operation(summary = "获取用户数据权限", description = "性能扩展点：默认实现会合并用户和角色数据范围；子类可覆盖为缓存后的 DataScope 或一次性查询结果。优先使用用户的数据权限,没有才获取角色上的定义的数据权限")
    default DataScope getUserDataScope(Serializable userPrincipal) {

        RbacUserInfo user = loadUser(userPrincipal);
        Assert.notNull(user, "用户({})不存在", userPrincipal);

        final SimpleDataScope dataScope = new SimpleDataScope();

        //优先使用用户自定义的数据权限
        dataScope.setConfidentialDataAccessLevel(user.getConfidentialDataAccessLevel());


        //优先使用用户自定义的组织数据权限
        dataScope.setOrgScopeList(mergeOrgScopeList(user.getOrgScopeList()));

        //
        final boolean notUserOrgScope = isAllNull(dataScope.getOrgScopeList());


        if (notUserOrgScope || dataScope.getConfidentialDataAccessLevel() == null) {

            // 这里必须使用“生效角色”而不是“可见角色”，否则一旦角色可见性再按机密级别过滤，就会和用户机密级别计算互相递归。
            final Collection<RbacRoleInfo> roleList = loadUserOwnerRoleList(user);

            if (dataScope.getConfidentialDataAccessLevel() == null) {

                OptionalInt max = roleList.stream()
                        .filter(Objects::nonNull)
                        .filter(role -> role.getConfidentialDataAccessLevel() != null)
                        .mapToInt(RbacRoleInfo::getConfidentialDataAccessLevel)
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

        return dataScope;
    }


    @Operation(summary = "合并组织权限列表", description = "性能扩展点：若数据范围已在存储层归并或缓存，子类可覆盖以避免每次内存去重和 allow/deny 收敛。")
    default Collection<OrgScope> mergeOrgScopeList(Collection<OrgScope> orgScopeList) {

        if (orgScopeList == null || orgScopeList.isEmpty()) {
            return Collections.emptyList();
        }

        final Collection<OrgScope> result = new ArrayList<>(orgScopeList.size());

        final Map<String, OrgScope> scopeMap = new HashMap<>();

        boolean hasDeny = false;

        OrgScope allowAllScope = null;

        //用普通循环
        for (OrgScope scope : orgScopeList) {

            //忽略无效数据
            if (scope == null
                    || StrUtil.isBlank(scope.getOrgId())
                    || StrUtil.isBlank(scope.getOrgScopeExpression())) {
                continue;
            }

            // 去重时不仅要看 orgId/allow/expression，还要保留表达式类型，
            // 否则同一段文本在 IdAntPath、NameAntPath、Groovy、SpEL 之间会被误判成同一条规则。
            final String scopeKey = String.join("_",
                    String.valueOf(scope.getTenantExpression()),
                    scope.getOrgId(),
                    String.valueOf(scope.isAllow()),
                    String.valueOf(scope.getExpressionType()),
                    scope.getOrgScopeExpression());

            if (scopeMap.containsKey(scopeKey)) {
                //如果有重复的,则忽略
                continue;
            }
            //添加
            scopeMap.put(scopeKey, scope);

            if (scope.isDeny()) {

                hasDeny = true;

                if (scope.isDenyAllOrg()) {

                    //拒绝所有组织, 则忽略其他
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

    @Operation(summary = "加载直接下级组织", description = "性能扩展点：默认实现会加载租户组织列表后内存过滤；子类应优先覆盖为按 parentId 直接查询。orgPrincipal 参数可以是orgId 或是 RbacOrgInfo")
    default <ORG extends RbacOrgInfo> Collection<ORG> loadOrgChildren(Serializable tenantId, Serializable orgPrincipal) {

        Assert.isTrue(RbacMiscUtils.isNotBlank(orgPrincipal), "父节点不能为空");

        if (orgPrincipal instanceof RbacOrgInfo) {
            orgPrincipal = ((RbacOrgInfo) orgPrincipal).getId();
        }

        Serializable orgId = orgPrincipal;

        //获取所有组织
        return (Collection<ORG>) loadTenantOrgList(tenantId, true).stream()
                .filter(o -> orgId.equals(o.getParentId()))
                .collect(Collectors.toList());
    }

    /**
     * 加载所有父组织
     *
     * @param tenantId
     * @param orgPrincipal orgId 或是 RbacOrgInfo
     * @return
     */
    @Operation(summary = "加载所有的直系父组织", description = "性能扩展点：默认实现会加载租户全量组织再回溯父链；子类可覆盖为递归 SQL、闭包表或 nodePath 查询。要求按由近到远的顺序返回")
    default <ORG extends RbacOrgInfo> Collection<ORG> loadOrgParentList(Serializable tenantId, boolean containsSelf, Serializable orgPrincipal, boolean selfAudit) {

        RbacOrgInfo leafOrg = null;

        Assert.isTrue(RbacMiscUtils.isNotBlank(orgPrincipal), "orgPrincipal为空");

        if (orgPrincipal instanceof RbacOrgInfo) {

            leafOrg = (ORG) orgPrincipal;

            orgPrincipal = leafOrg.getId();

        }

        final Collection<ORG> orgList = loadTenantOrgList(tenantId, false);

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

        final Collection<ORG> parentList = new ArrayList<>();
        final Set<String> visitedParentIds = new LinkedHashSet<>();

        if (containsSelf) {
            parentList.add((ORG) leafOrg);
            visitedParentIds.add(Objects.toString(leafOrg.getId(), ""));
        }

        //获取所有父组织 , 防止递归死循环
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
            Assert.isTrue(visitedParentIds.add(tempLeafOrgId)

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
    @Operation(summary = "检查用户组织可访问性", description = "性能扩展点：默认实现可能加载用户可访问组织列表后做内存 contains；子类可覆盖为 exists 查询或权限缓存判断。")
    default void checkOrgAccessible(Serializable userPrincipal, Serializable tenantId, Serializable parentId, Serializable orgId) {

        RbacUserInfo user = loadUser(userPrincipal);

        Assert.notNull(user, "用户({})不存在", userPrincipal);

        userPrincipal = user;

        //优化效率
        // 只有顶级超级管理员可以完全跳过组织可访问性校验。
        if (user.isTopSuperAdmin()) {
            return;
        }

        // 普通超级管理员和 SaaS 管理员直接按目标对象的机密级别做校验，不再受租户相等和 DataScope 限制。
        if (user.isSuperAdmin() || user.isSaasAdmin()) {
            final Integer userConfidentialAccessLevel = getUserConfidentialDataAccessLevel(user);

            if (RbacMiscUtils.isNotBlank(tenantId)) {
                final RbacTenantInfo tenant = loadTenant(tenantId);
                Assert.notNull(tenant, "租户[{}]不存在", tenantId);
                Assert.isTrue(canAccessConfidentialData(() -> userConfidentialAccessLevel, tenant.getConfidentialLevel()), "租户[{}]未授权", tenantId);
            }

            if (RbacMiscUtils.isNotBlank(parentId)) {
                final RbacOrgInfo parentOrg = loadOrg(parentId);
                Assert.notNull(parentOrg, "父组织机构[{}]不存在", parentId);
                Assert.isTrue(canAccessConfidentialData(() -> userConfidentialAccessLevel, parentOrg.getConfidentialLevel()), "父组织机构[{}]未授权", parentId);
            }

            if (RbacMiscUtils.isNotBlank(orgId)) {
                final RbacOrgInfo org = loadOrg(orgId);
                Assert.notNull(org, "组织机构[{}]不存在", orgId);
                Assert.isTrue(canAccessConfidentialData(() -> userConfidentialAccessLevel, org.getConfidentialLevel()), "组织机构[{}]未授权", orgId);
            }

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
        final Set<String> accessibleOrgIdSet = orgList.stream()
                .filter(Objects::nonNull)
                .map(RbacOrgInfo::getId)
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .collect(Collectors.toSet());

        Assert.isTrue(RbacMiscUtils.isBlank(parentId) || accessibleOrgIdSet.contains(Objects.toString(parentId, "")), "父组织机构[{}]未授权", parentId);

        Assert.isTrue(RbacMiscUtils.isBlank(orgId) || accessibleOrgIdSet.contains(Objects.toString(orgId, "")), "组织机构[{}]未授权", orgId);

    }

    /**
     * 获取用户的机密数据访问级别
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    @Override
    @Operation(summary = "获取用户的机密数据访问级别", description = "性能扩展点：当用户本身没有定义访问级别时默认会扫描用户生效角色；子类可覆盖为缓存字段或预聚合查询，尽量不要多次调用")
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
        // 计算用户自身机密级别时必须基于“生效角色”，不能对角色列表再做机密过滤，否则会形成递归。
        final Collection<RbacRoleInfo> roleList = loadUserOwnerRoleList(userInfo);

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
    @Operation(summary = "根据角色代码加载角色列表", description = "性能扩展点：默认实现会加载租户角色列表后内存按 code 过滤；子类可覆盖为按 code 批量查询。不管角色是否处于有效状态,公共角色会并存")
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
    @Operation(summary = "加载用户生效角色列表", description = "性能扩展点：默认实现会加载租户角色列表后按用户角色 code 归并；子类可覆盖为用户-角色关联查询或缓存。内部授权计算使用，不做角色对象的机密级别过滤")
    default <R extends RbacRoleInfo> Collection<R> loadUserOwnerRoleList(Serializable userPrincipal, boolean onlyLoadEffectRole) {

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

        final String userTenantId = Objects.toString(user.getTenantId(), null);
        final Map<String, RbacRoleInfo> roleByCode = new LinkedHashMap<>();

        for (RbacRoleInfo roleInfo : roleList) {
            if (roleInfo == null || StrUtil.isBlank(roleInfo.getCode())) {
                continue;
            }

            final String roleTenantId = Objects.toString(roleInfo.getTenantId(), null);
            final boolean sameTenant = Objects.equals(roleTenantId, userTenantId);
            final boolean publicRole = RbacMiscUtils.isBlank(roleInfo.getTenantId());

            if (!sameTenant && !publicRole) {
                continue;
            }

            roleByCode.merge(roleInfo.getCode(), roleInfo,
                    (current, incoming) -> Objects.equals(Objects.toString(incoming.getTenantId(), null), userTenantId) ? incoming : current);
        }

        final Collection<R> result = new ArrayList<>();

        for (Object roleCode : user.getRoleList()) {
            if (roleCode == null) {
                continue;
            }

            final RbacRoleInfo roleInfo = roleByCode.get(Objects.toString(roleCode, ""));
            if (roleInfo != null) {
                result.add((R) roleInfo);
            }
        }

        return result;
    }

    /**
     * 加载用户“可见”的角色列表。
     * 这个方法面向展示或外部读取语义，可以按角色对象本身的机密级别做过滤；
     * 内部授权、权限汇总、数据范围汇总请使用 loadUserEffectiveRoleList，避免递归并保持用户已有角色语义稳定。
     */
    @Operation(summary = "加载用户可访问的角色列表", description = "性能扩展点：默认实现基于生效角色再做机密级别过滤；子类可覆盖为已过滤缓存或数据库条件查询。默认按角色对象自身的机密级别做可见性过滤")
    default <R extends RbacRoleInfo> Collection<R> loadUserAccessibleRoleList(Serializable userPrincipal, boolean onlyLoadEffectRole) {
        final RbacUserInfo user = loadUser(userPrincipal);
        Assert.notNull(user, "用户[{}]无法加载", userPrincipal);
        return filterByConfidentialAccess(user, loadUserOwnerRoleList(user, onlyLoadEffectRole));
    }

    /**
     * 获取指定角色的权限列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    @Operation(summary = "根据角色代码加载权限列表", description = "性能扩展点：子类可覆盖为按角色 code 直接查询权限表达式，避免加载完整角色对象。")
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
    @Operation(summary = "根据角色代码加载权限列表", description = "性能扩展点：默认实现会加载租户角色列表后汇总权限；子类可覆盖为角色权限表批量查询或权限缓存。不管角色是否处于有效状态")
    default Collection<String> loadRolePermissionList(Serializable tenantId, Collection<String> roleCodeList) {

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

    @Operation(summary = "加载用户生效角色列表", description = "性能扩展点：子类可覆盖为用户角色缓存或关联表查询。不包括已经禁用的角色，不做角色对象机密级别过滤")
    default <R extends RbacRoleInfo> Collection<R> loadUserOwnerRoleList(Serializable userPrincipal) {
        return loadUserOwnerRoleList(userPrincipal, true);
    }


    @Operation(summary = "加载用户角色列表", description = "性能扩展点：子类可覆盖为用户可见角色缓存或数据库条件查询。不包括已经禁用的角色")
    default <R extends RbacRoleInfo> Collection<R> loadUserAccessibleRoleList(Serializable userPrincipal) {
        return loadUserAccessibleRoleList(userPrincipal, true);
    }


    /**
     * 加载用户角色编码列表
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    @Operation(summary = "加载用户角色编码列表", description = "性能扩展点：默认实现会加载用户生效角色对象再提取 code；子类可覆盖为直接读取用户角色 code 或缓存。")
    default Collection<String> loadUserRoleCodeList(Serializable userPrincipal) {

        // 授权判断依赖的是“用户实际拥有的角色”，不能因为角色对象不可见就丢失角色编码。
        return loadUserOwnerRoleList(userPrincipal).stream()
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
    @Operation(summary = "加载用户权限表达式列表", description = "性能扩展点：默认实现会加载用户生效角色并汇总权限；子类可覆盖为权限表达式缓存或关联表聚合查询。")
    default Collection<String> loadUserPermissionExprList(Serializable userPrincipal) {

        // 权限汇总也必须基于生效角色，不能使用带机密过滤的可见角色列表。
        return loadUserOwnerRoleList(userPrincipal).stream()
                .filter(Objects::nonNull)

                .map(RbacRoleInfo::getPermissionList)
                .filter(Objects::nonNull)

                .flatMap(Collection::stream)
                .filter(Objects::nonNull)

                .map(Object::toString)
                .filter(StrUtil::isNotBlank)

                .collect(Collectors.toSet());
    }

    private boolean canAccessAllOrg(RbacUserInfo user, DataScope userDataScope) {
        if (userDataScope == null
                || userDataScope.getOrgScopeList() == null
                || userDataScope.getOrgScopeList().isEmpty()) {
            return false;
        }

        boolean hasAllowAll = false;

        // 这里只消费已经算好的 DataScope，避免在高频调用链上重复触发 getUserDataScope(...)。
        for (OrgScope scope : userDataScope.getOrgScopeList()) {
            if (scope == null) {
                continue;
            }
            if (scope.isDeny()) {
                return false;
            }
            if (scope.isAllowAllOrg() && scope.isAllTenant()) {
                hasAllowAll = true;
            }
        }

        return hasAllowAll;
    }

    private <ORG extends RbacOrgInfo> Set<String> calcAccessibleOrgIds(RbacUserInfo user,
                                                                       Collection<? extends OrgScope> orgScopeList,
                                                                       Collection<ORG> tenantOrgList,
                                                                       Map<String, ORG> orgMap) {

        if (orgScopeList == null || orgScopeList.isEmpty()) {
            return Collections.emptySet();
        }

        // 为标准范围预先建立父子索引，避免每个 scope 都全表扫描组织树。
        final Map<String, List<String>> childrenByParentId = buildChildrenByParentId(orgMap);
        // 同一个 root 的整棵子树可能被多个 scope 复用，缓存后代集合以降低重复遍历成本。
        final Map<String, Set<String>> subtreeOrgIdsCache = new HashMap<>();
        // scope 根节点解析也可能重复出现，这里按 orgId 做一次调用级缓存。
        final Map<String, Set<String>> scopeRootIdsCache = new HashMap<>();

        final List<OrgScope> allowScopes = new ArrayList<>();
        final List<OrgScope> denyScopes = new ArrayList<>();

        boolean hasAllowAll = false;

        for (OrgScope scope : orgScopeList) {

            if (scope == null || StrUtil.isBlank(scope.getOrgId()) || StrUtil.isBlank(scope.getOrgScopeExpression())) {
                continue;
            }

            if (scope.isDenyAllOrg() && scope.isAllTenant()) {
                return Collections.emptySet();
            }

            if (scope.isAllowAllOrg() && scope.isAllTenant()) {
                hasAllowAll = true;
            }

            if (scope.isAllow()) {
                allowScopes.add(scope);
            } else {
                denyScopes.add(scope);
            }
        }

        if (allowScopes.isEmpty()) {
            return Collections.emptySet();
        }

        if (hasAllowAll && denyScopes.isEmpty()) {
            return new LinkedHashSet<>(orgMap.keySet());
        }

        final Map<String, String> relativeIdPathCache = new HashMap<>();
        final Map<String, String> relativeNamePathCache = new HashMap<>();

        // 先收敛允许集合，再按明确 deny 进行过滤，避免 deny 为空时的额外处理。
        final Set<String> allowOrgIds = collectMatchedOrgIds(allowScopes, user, orgMap,
                childrenByParentId, subtreeOrgIdsCache, scopeRootIdsCache,
                relativeIdPathCache, relativeNamePathCache);

        if (allowOrgIds.isEmpty() || denyScopes.isEmpty()) {
            return allowOrgIds;
        }

        final Set<String> denyOrgIds = collectMatchedOrgIds(
                denyScopes,
                user,
                orgMap,
                childrenByParentId,
                subtreeOrgIdsCache,
                scopeRootIdsCache,
                relativeIdPathCache,
                relativeNamePathCache
        );

        if (denyOrgIds.isEmpty()) {
            return allowOrgIds;
        }

        allowOrgIds.removeAll(denyOrgIds);

        return allowOrgIds;
    }

    private <ORG extends RbacOrgInfo> Set<String> collectMatchedOrgIds(Collection<? extends OrgScope> scopeList,
                                                                       RbacUserInfo user,
                                                                       Map<String, ORG> orgMap,
                                                                       Map<String, List<String>> childrenByParentId,
                                                                       Map<String, Set<String>> subtreeOrgIdsCache,
                                                                       Map<String, Set<String>> scopeRootIdsCache,
                                                                       Map<String, String> relativeIdPathCache,
                                                                       Map<String, String> relativeNamePathCache) {

        final Set<String> matchedOrgIds = new LinkedHashSet<>();

        for (OrgScope scope : scopeList) {
            final Set<String> scopeRootIds = resolveScopeRootIds(scope, user, orgMap, scopeRootIdsCache);

            if (scopeRootIds.isEmpty()) {
                continue;
            }

            for (String scopeRootId : scopeRootIds) {
                // 标准范围直接走树索引，自定义范围仅在 scope root 的子树内做表达式判断。
                final Set<String> candidateOrgIds = collectScopeCandidateOrgIds(scope, scopeRootId, orgMap, childrenByParentId, subtreeOrgIdsCache);

                if (candidateOrgIds.isEmpty()) {
                    continue;
                }

                if (!scope.isCustomScope()) {
                    candidateOrgIds.stream()
                            .filter(orgId -> matchesScopeTenantByOrg(scope, user, orgMap.get(orgId)))
                            .forEach(matchedOrgIds::add);
                    continue;
                }

                for (String orgId : candidateOrgIds) {
                    if (StrUtil.isBlank(orgId)) {
                        continue;
                    }

                    if (!matchesScopeTenantByOrg(scope, user, orgMap.get(orgId))) {
                        continue;
                    }

                    if (matchesScope(scope, user, scopeRootId, orgId, orgMap, relativeIdPathCache, relativeNamePathCache)) {
                        matchedOrgIds.add(orgId);
                    }
                }
            }
        }

        return matchedOrgIds;
    }

    // 先把父子关系索引出来，后续标准 scope 就不需要对组织集合做重复全表扫描。
    private <ORG extends RbacOrgInfo> Map<String, List<String>> buildChildrenByParentId(Map<String, ORG> orgMap) {
        final Map<String, List<String>> childrenByParentId = new HashMap<>();

        for (ORG org : orgMap.values()) {
            if (org == null || isBlank(org.getId()) || isBlank(org.getParentId())) {
                continue;
            }

            final String parentId = Objects.toString(org.getParentId(), "");
            final String orgId = Objects.toString(org.getId(), "");

            if (!orgMap.containsKey(parentId)) {
                continue;
            }

            childrenByParentId.computeIfAbsent(parentId, key -> new ArrayList<>()).add(orgId);
        }

        return childrenByParentId;
    }

    // 标准 scope 直接通过树索引拿候选节点，自定义 scope 则把 root 子树作为表达式匹配范围。
    private <ORG extends RbacOrgInfo> Set<String> collectScopeCandidateOrgIds(OrgScope scope,
                                                                              String scopeRootId,
                                                                              Map<String, ORG> orgMap,
                                                                              Map<String, List<String>> childrenByParentId,
                                                                              Map<String, Set<String>> subtreeOrgIdsCache) {

        switch (scope.getScope()) {
            case OnlySelf:
                return orgMap.containsKey(scopeRootId)
                        ? new LinkedHashSet<>(Collections.singleton(scopeRootId))
                        : Collections.emptySet();
            case OnlyDirectChild:
                return new LinkedHashSet<>(childrenByParentId.getOrDefault(scopeRootId, Collections.emptyList()));
            case SelfAndDirectChild:
                final Set<String> selfAndChildren = new LinkedHashSet<>();
                if (orgMap.containsKey(scopeRootId)) {
                    selfAndChildren.add(scopeRootId);
                }
                selfAndChildren.addAll(childrenByParentId.getOrDefault(scopeRootId, Collections.emptyList()));
                return selfAndChildren;
            case All:
            case Custom:
            default:
                return subtreeOrgIdsCache.computeIfAbsent(scopeRootId,
                        key -> collectSubtreeOrgIds(key, orgMap, childrenByParentId));
        }
    }

    // root 子树会被 allow/deny/custom 多次复用，因此单独抽成缓存友好的 helper。
    private <ORG extends RbacOrgInfo> Set<String> collectSubtreeOrgIds(String rootId,
                                                                       Map<String, ORG> orgMap,
                                                                       Map<String, List<String>> childrenByParentId) {
        if (StrUtil.isBlank(rootId) || !orgMap.containsKey(rootId)) {
            return Collections.emptySet();
        }

        final Set<String> subtreeOrgIds = new LinkedHashSet<>();
        final Deque<String> stack = new ArrayDeque<>();
        stack.push(rootId);

        while (!stack.isEmpty()) {
            final String orgId = stack.pop();

            if (!subtreeOrgIds.add(orgId)) {
                continue;
            }

            childrenByParentId.getOrDefault(orgId, Collections.emptyList()).forEach(stack::push);
        }

        return subtreeOrgIds;
    }

    private <ORG extends RbacOrgInfo> Set<String> resolveScopeRootIds(OrgScope scope,
                                                                      RbacUserInfo user,
                                                                      Map<String, ORG> orgMap,
                                                                      Map<String, Set<String>> scopeRootIdsCache) {

        final String scopeCacheKey = String.join("@",
                String.valueOf(scope.getTenantExpression()),
                String.valueOf(scope.getOrgId()));

        return scopeRootIdsCache.computeIfAbsent(scopeCacheKey, key -> {
            if (scope.isAllRootOrg()) {
                return orgMap.values().stream()
                        .filter(Objects::nonNull)
                        .filter(org -> matchesScopeTenantByOrg(scope, user, org))
                        .filter(org -> isBlank(org.getParentId()) || !orgMap.containsKey(Objects.toString(org.getParentId(), "")))
                        .map(RbacOrgInfo::getId)
                        .filter(orgId -> RbacMiscUtils.isNotBlank((Serializable) orgId))
                        .map(String::valueOf)
                        .collect(Collectors.toCollection(LinkedHashSet::new));
            }

            if (scope.isUserOrg()) {
                return normalizeOrgIdSet(Collections.singletonList(user.getOrgId()))
                        .stream()
                        .filter(orgId -> matchesScopeTenantByOrg(scope, user, orgMap.get(orgId)))
                        .collect(Collectors.toCollection(LinkedHashSet::new));
            }

            return normalizeOrgIdSet(Collections.singletonList(scope.getOrgId()))
                    .stream()
                    .filter(orgMap::containsKey)
                    .filter(orgId -> matchesScopeTenantByOrg(scope, user, orgMap.get(orgId)))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        });
    }

    // tenantExpression 统一收敛到这里：空串=无租户，DEFAULT_TENANT=当前用户租户，*=所有租户，其余按 Groovy 脚本求值。
    private boolean matchesScopeTenantByOrg(OrgScope scope, RbacUserInfo user, RbacOrgInfo org) {
        return org != null && matchesScopeTenantByTenantId(scope, user, (Serializable) org.getTenantId());
    }

    private boolean matchesScopeTenantByTenantId(OrgScope scope, RbacUserInfo user, Serializable tenantId) {
        final String tenantExpr = StrUtil.nullToEmpty(scope.getTenantExpression()).trim();
        final String tenantIdStr = tenantId == null ? null : Objects.toString(tenantId, "");

        if (scope.isNoTenant()) {
            return RbacMiscUtils.isBlank(tenantId);
        }

        if (scope.isAllTenant()) {
            return true;
        }

        if (scope.isDefaultTenant()) {
            return Objects.equals(resolveDefaultTenantId(user), tenantIdStr);
        }

        // 普通文本默认按租户标识精确匹配，只有未命中时才回退到 Groovy 表达式。
        if (Objects.equals(tenantExpr, tenantIdStr)) {
            return true;
        }

        if (isLiteralTenantExpression(tenantExpr)) {
            return false;
        }

        final Map<String, Object> context = new LinkedHashMap<>();
        context.put("tenant", tenantId);
        context.put("tenantId", tenantIdStr);
        context.put("user", user);
        context.put("scope", scope);

        Object value = ExpressionUtils.evalGroovy(ORG_SCOPE_GROOVY_CLASS_CACHE, null, tenantExpr,
                "org-scope-tenant-" + Integer.toHexString(tenantExpr.hashCode()) + ".groovy", context);

        return Boolean.TRUE.equals(value);
    }

    private Set<String> resolveScopedTenantIds(RbacUserInfo user,
                                               Collection<? extends OrgScope> orgScopeList,
                                               Collection<? extends RbacTenantInfo> allTenantList) {
        final Set<String> candidateTenantIds = new LinkedHashSet<>();
        final Set<String> enumerableTenantIds = resolveUserAccessibleTenantIds(user, orgScopeList, allTenantList);

        final String defaultTenantId = resolveDefaultTenantId(user);

        if (orgScopeList == null || orgScopeList.isEmpty()) {
            if (defaultTenantId != null) {
                candidateTenantIds.add(defaultTenantId);
            } else {
                candidateTenantIds.add(null);
            }
            return candidateTenantIds;
        }

        for (OrgScope scope : orgScopeList) {
            if (scope == null) {
                continue;
            }

            if (scope.isNoTenant()) {
                candidateTenantIds.add(null);
                continue;
            }

            if (scope.isDefaultTenant()) {
                candidateTenantIds.add(defaultTenantId);
                continue;
            }

            // ALL_TENANT 和 Groovy 表达式都需要在“可枚举租户”集合上求值。
            if (defaultTenantId != null) {
                enumerableTenantIds.add(defaultTenantId);
            }

            for (String tenantId : enumerableTenantIds) {
                if (matchesScopeTenantByTenantId(scope, user, tenantId)) {
                    candidateTenantIds.add(tenantId);
                }
            }

            if (matchesScopeTenantByTenantId(scope, user, null)) {
                candidateTenantIds.add(null);
            }
        }

        return candidateTenantIds;
    }

    /**
     * 基于用户数据范围里的 tenantExpression 计算可访问租户集合。
     * 这里按“allow 收敛，再减 deny”处理，和组织授权的总语义保持一致。
     */
    private Set<String> resolveUserAccessibleTenantIds(RbacUserInfo user,
                                                       Collection<? extends OrgScope> orgScopeList,
                                                       Collection<? extends RbacTenantInfo> allTenantList) {
        final Set<String> allTenantIds = Optional.ofNullable(allTenantList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(RbacTenantInfo::getId)
                .filter(tenantId -> RbacMiscUtils.isNotBlank((Serializable) tenantId))
                .map(String::valueOf)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        final String defaultTenantId = resolveDefaultTenantId(user);

        if (orgScopeList == null || orgScopeList.isEmpty()) {
            return defaultTenantId == null
                    ? Collections.emptySet()
                    : new LinkedHashSet<>(Collections.singleton(defaultTenantId));
        }

        final List<OrgScope> allowScopes = new ArrayList<>();
        final List<OrgScope> denyScopes = new ArrayList<>();
        boolean hasAllowAllTenant = false;

        for (OrgScope scope : orgScopeList) {
            if (scope == null || StrUtil.isBlank(scope.getOrgId()) || StrUtil.isBlank(scope.getOrgScopeExpression())) {
                continue;
            }

            if (scope.isNoTenant()) {
                continue;
            }

            if (scope.isDenyAllOrg() && scope.isAllTenant()) {
                return Collections.emptySet();
            }

            if (scope.isAllowAllOrg() && scope.isAllTenant()) {
                hasAllowAllTenant = true;
            }

            if (scope.isAllow()) {
                allowScopes.add(scope);
            } else {
                denyScopes.add(scope);
            }
        }

        if (allowScopes.isEmpty()) {
            return Collections.emptySet();
        }

        if (hasAllowAllTenant && denyScopes.isEmpty()) {
            return allTenantIds;
        }

        final Set<String> allowTenantIds = collectMatchedTenantIds(allowScopes, user, allTenantIds, defaultTenantId);
        if (allowTenantIds.isEmpty() || denyScopes.isEmpty()) {
            return allowTenantIds;
        }

        final Set<String> denyTenantIds = collectMatchedTenantIds(denyScopes, user, allTenantIds, defaultTenantId);
        allowTenantIds.removeAll(denyTenantIds);
        return allowTenantIds;
    }

    private Set<String> collectMatchedTenantIds(Collection<? extends OrgScope> scopeList,
                                                RbacUserInfo user,
                                                Set<String> allTenantIds,
                                                String defaultTenantId) {
        final Set<String> matchedTenantIds = new LinkedHashSet<>();

        for (OrgScope scope : scopeList) {
            if (scope == null || scope.isNoTenant()) {
                continue;
            }

            if (scope.isDefaultTenant()) {
                if (defaultTenantId != null) {
                    matchedTenantIds.add(defaultTenantId);
                }
                continue;
            }

            for (String tenantId : allTenantIds) {
                if (matchesScopeTenantByTenantId(scope, user, tenantId)) {
                    matchedTenantIds.add(tenantId);
                }
            }
        }

        return matchedTenantIds;
    }

    private boolean isLiteralTenantExpression(String tenantExpr) {
        return StrUtil.isNotBlank(tenantExpr)
                && tenantExpr.chars().noneMatch(ch -> Character.isWhitespace(ch)
                || ch == '=' || ch == '!' || ch == '&' || ch == '|'
                || ch == '(' || ch == ')' || ch == '{' || ch == '}'
                || ch == ';' || ch == '+' || ch == '<' || ch == '>');
    }

    private String resolveDefaultTenantId(RbacUserInfo user) {
        return RbacMiscUtils.isBlank(user.getTenantId()) ? null : Objects.toString(user.getTenantId(), "");
    }

    private <ORG extends RbacOrgInfo> boolean matchesScope(OrgScope scope,
                                                           RbacUserInfo user,
                                                           String scopeRootId,
                                                           String orgId,
                                                           Map<String, ORG> orgMap,
                                                           Map<String, String> relativeIdPathCache,
                                                           Map<String, String> relativeNamePathCache) {

        final String cacheKey = scopeRootId + "->" + orgId;
        final String relativeIdPath = relativeIdPathCache.computeIfAbsent(cacheKey, key -> buildRelativePath(scopeRootId, orgId, orgMap, false));

        if (relativeIdPath == null) {
            return false;
        }

        switch (scope.getScope()) {
            case OnlySelf:
                return "/".equals(relativeIdPath);
            case OnlyDirectChild:
                return getRelativeDepth(relativeIdPath) == 1;
            case SelfAndDirectChild:
                return "/".equals(relativeIdPath) || getRelativeDepth(relativeIdPath) == 1;
            case All:
                return true;
            case Custom:
            default:
                final String relativeNamePath = relativeNamePathCache.computeIfAbsent(cacheKey, key -> buildRelativePath(scopeRootId, orgId, orgMap, true));
                return matchesCustomScope(scope, user, orgMap.get(scopeRootId), orgMap.get(orgId), relativeIdPath, relativeNamePath);
        }
    }

    private boolean matchesCustomScope(OrgScope scope,
                                       RbacUserInfo user,
                                       RbacOrgInfo rootOrg,
                                       RbacOrgInfo org,
                                       String relativeIdPath,
                                       String relativeNamePath) {

        final OrgScope.ExpressionType expressionType = scope.getExpressionType();

        if (expressionType == null || OrgScope.ExpressionType.IdAntPath.equals(expressionType)) {
            return matchAntPath(scope.getOrgScopeExpression(), relativeIdPath);
        }

        if (OrgScope.ExpressionType.NameAntPath.equals(expressionType)) {
            return matchAntPath(scope.getOrgScopeExpression(), relativeNamePath);
        }

        final Map<String, Object> context = new LinkedHashMap<>();

        context.put("user", user);
        context.put("org", org);
        context.put("rootOrg", rootOrg);
        context.put("scope", scope);
        context.put("relativeIdPath", relativeIdPath);
        context.put("relativeNamePath", relativeNamePath);

        // 自定义脚本是慢路径，优先复用编译结果，减少高频授权检查中的解析开销。
        Object value = OrgScope.ExpressionType.Groovy.equals(expressionType)
                ? ExpressionUtils.evalGroovy(ORG_SCOPE_GROOVY_CLASS_CACHE, null, scope.getOrgScopeExpression(),
                "org-scope-" + Integer.toHexString(scope.getOrgScopeExpression().hashCode()) + ".groovy", context)
                : evalCachedSpEL(org, scope.getOrgScopeExpression(), context);

        return Boolean.TRUE.equals(value);
    }

    private Object evalCachedSpEL(Object rootObject, String expression, Map<String, Object> context) {
        final Expression spelExpression = ORG_SCOPE_SPEL_CACHE.computeIfAbsent(expression, ORG_SCOPE_SPEL_PARSER::parseExpression);
        return ExpressionUtils.evalSpEL(rootObject, null, spelExpression, Collections.singletonList(context));
    }

    private boolean matchAntPath(String expression, String relativePath) {

        if (StrUtil.isBlank(expression) || StrUtil.isBlank(relativePath)) {
            return false;
        }

        if (ORG_SCOPE_PATH_MATCHER.match(expression, relativePath)) {
            return true;
        }

        return !"/".equals(relativePath)
                && ORG_SCOPE_PATH_MATCHER.match(expression, StrUtil.removeSuffix(relativePath, "/"));
    }

    private int getRelativeDepth(String relativePath) {
        if ("/".equals(relativePath)) {
            return 0;
        }

        return (int) Arrays.stream(relativePath.split("/"))
                .filter(StrUtil::isNotBlank)
                .count();
    }

    private <ORG extends RbacOrgInfo> String buildRelativePath(String rootId,
                                                               String orgId,
                                                               Map<String, ORG> orgMap,
                                                               boolean useNamePath) {

        if (StrUtil.isBlank(rootId) || StrUtil.isBlank(orgId)) {
            return null;
        }

        if (rootId.equals(orgId)) {
            return "/";
        }

        final List<String> segments = new ArrayList<>();
        // 组织路径是沿父链向上回溯，一旦重复遇到节点就说明组织树存在环，需要立即失败而不是静默吞掉。
        final Set<String> visited = new LinkedHashSet<>();

        ORG current = orgMap.get(orgId);

        while (current != null) {

            final String currentId = Objects.toString(current.getId(), "");

            if (!visited.add(currentId)) {
                throwOrgCycleException(currentId, visited, orgMap);
            }

            if (rootId.equals(currentId)) {
                Collections.reverse(segments);
                return "/" + String.join("/", segments) + "/";
            }

            segments.add(currentSegment(useNamePath, current));

            final Serializable parentId = current.getParentId();

            if (isBlank(parentId)) {
                return null;
            }

            final String parentIdStr = Objects.toString(parentId, "");
            current = orgMap.get(parentIdStr);
        }

        return null;
    }

    private String currentSegment(boolean useNamePath, RbacOrgInfo org) {
        return StrUtil.blankToDefault(useNamePath ? org.getName() : Objects.toString(org.getId(), ""), "");
    }

    private Set<String> normalizeOrgIdSet(Collection<?> orgIds) {
        if (orgIds == null || orgIds.isEmpty()) {
            return Collections.emptySet();
        }

        return orgIds.stream()
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private <ORG extends RbacOrgInfo> Set<String> collectDescendantOrgIds(Set<String> rootIds,
                                                                          Map<String, ORG> orgMap,
                                                                          Map<String, List<String>> childrenByParentId) {
        final Set<String> selectedOrgIds = new LinkedHashSet<>();
        final Deque<String> stack = new ArrayDeque<>();

        for (String rootId : rootIds) {
            if (orgMap.containsKey(rootId)) {
                stack.push(rootId);
            }
        }

        while (!stack.isEmpty()) {
            final String orgId = stack.pop();

            if (!selectedOrgIds.add(orgId)) {
                continue;
            }

            childrenByParentId.getOrDefault(orgId, Collections.emptyList()).forEach(stack::push);
        }

        return selectedOrgIds;
    }

    private <ORG extends RbacOrgInfo> void validateSelectedOrgTreeAcyclic(Set<String> selectedOrgIds,
                                                                          Map<String, ORG> orgMap) {
        final Set<String> checkedOrgIds = new HashSet<>();

        for (String orgId : selectedOrgIds) {
            if (checkedOrgIds.contains(orgId)) {
                continue;
            }

            final Set<String> visitingOrgIds = new LinkedHashSet<>();
            String currentId = orgId;

            while (StrUtil.isNotBlank(currentId)
                    && selectedOrgIds.contains(currentId)
                    && !checkedOrgIds.contains(currentId)) {

                if (!visitingOrgIds.add(currentId)) {
                    throwOrgCycleException(currentId, visitingOrgIds, orgMap);
                }

                final ORG currentOrg = orgMap.get(currentId);

                if (currentOrg == null || isBlank(currentOrg.getParentId())) {
                    break;
                }

                currentId = Objects.toString(currentOrg.getParentId(), "");
            }

            checkedOrgIds.addAll(visitingOrgIds);
        }
    }

    /**
     * 复制组树用的组织节点。
     * <p>
     * 默认实现会兼容只读代理对象和未知实体类型，因此需要 AOP 脱壳、BeanUtils 和字段反射。
     * 如果业务实现明确知道组织对象类型，建议覆盖本方法，用构造器或 mapper 只复制必要字段，
     * 这样可以减少大组织树装配时的反射成本。
     * <p>
     * 注意：返回对象必须是独立的新对象，并且 children/nodePath 可写；默认组树流程会重置 children，
     * 不能直接返回原始 sourceOrg，否则会修改调用方传入的扁平列表对象。
     */
    @Operation(summary = "复制组树用组织节点", description = "性能扩展点：默认实现使用 AOP 脱壳、BeanUtils 和字段反射；子类知道组织类型时应覆盖为构造器/mapper 复制，以减少大组织树装配时的反射成本。")
    default <ORG extends RbacOrgInfo> ORG copyOrgNodeForAssembleTree(ORG sourceOrg) {
        return copyOrgNodeByReflection(sourceOrg);
    }

    private <ORG extends RbacOrgInfo> ORG copyOrgNodeByReflection(ORG sourceOrg) {
        Object source = unwrapOrgSource(sourceOrg);
        Class<?> sourceType = resolveOrgSourceClass(source);

        Assert.notNull(sourceType, "组织节点类型不能为空");

        ORG copiedOrg = (ORG) BeanUtils.instantiateClass(sourceType);
        BeanUtils.copyProperties(source, copiedOrg);
        copyFieldState(source, copiedOrg, sourceType);

        return copiedOrg;
    }

    private Object unwrapOrgSource(Object source) {
        Object unwrapped = source;

        while (unwrapped instanceof ProxyWrapperObject) {
            Object next = ((ProxyWrapperObject) unwrapped).getOriginalObject();
            if (next == null || next == unwrapped) {
                break;
            }
            unwrapped = next;
        }

        return unwrapped;
    }

    private Class<?> resolveOrgSourceClass(Object source) {
        if (source == null) {
            return null;
        }
        return AopProxyUtils.ultimateTargetClass(source);
    }

    private void copyFieldState(Object source, Object target, Class<?> sourceType) {
        getCopyableFields(sourceType).forEach(field ->
                ReflectionUtils.setField(field, target, ReflectionUtils.getField(field, source))
        );
    }

    private List<Field> getCopyableFields(Class<?> sourceType) {
        return COPYABLE_FIELDS_CACHE.computeIfAbsent(sourceType, key -> {
            final List<Field> fields = new ArrayList<>();

            ReflectionUtils.doWithFields(key, field -> {
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                    return;
                }

                ReflectionUtils.makeAccessible(field);
                fields.add(field);
            });

            return Collections.unmodifiableList(fields);
        });
    }

    private <ORG extends RbacOrgInfo> String resolveNodePath(ORG org,
                                                             Map<String, ORG> orgMap,
                                                             Map<String, String> nodePathCache) {
        try {
            String nodePath = org.getNodePath();
            if (StrUtil.isNotBlank(nodePath)) {
                return nodePath;
            }
        } catch (UnsupportedOperationException ignored) {
        }

        return buildAbsoluteNodePath(Objects.toString(org.getId(), ""), orgMap, nodePathCache, new LinkedHashSet<>());
    }

    private <ORG extends RbacOrgInfo> String buildAbsoluteNodePath(String orgId,
                                                                   Map<String, ORG> orgMap,
                                                                   Map<String, String> nodePathCache,
                                                                   Set<String> visiting) {
        if (StrUtil.isBlank(orgId)) {
            return null;
        }

        if (nodePathCache.containsKey(orgId)) {
            return nodePathCache.get(orgId);
        }

        if (!visiting.add(orgId)) {
            throwOrgCycleException(orgId, visiting, orgMap);
        }

        ORG current = orgMap.get(orgId);

        if (current == null) {
            visiting.remove(orgId);
            return null;
        }

        try {
            String nodePath = current.getNodePath();
            if (StrUtil.isNotBlank(nodePath)) {
                visiting.remove(orgId);
                nodePathCache.put(orgId, nodePath);
                return nodePath;
            }
        } catch (UnsupportedOperationException ignored) {
        }

        final String currentId = Objects.toString(current.getId(), "");
        String nodePath;

        if (isBlank(current.getParentId())) {
            nodePath = "/" + currentId + "/";
        } else {
            final String parentId = Objects.toString(current.getParentId(), "");
            final String parentPath = buildAbsoluteNodePath(parentId, orgMap, nodePathCache, visiting);
            nodePath = StrUtil.isBlank(parentPath)
                    ? "/" + currentId + "/"
                    : parentPath + currentId + "/";
        }

        visiting.remove(orgId);
        nodePathCache.put(orgId, nodePath);

        return nodePath;
    }

    /**
     * 组织树出现环时立刻抛异常，避免后续路径匹配或组树逻辑进入死循环。
     */
    private <ORG extends RbacOrgInfo> void throwOrgCycleException(String repeatedOrgId,
                                                                  Set<String> visitedOrgIds,
                                                                  Map<String, ORG> orgMap) {
        final List<String> cyclePath = new ArrayList<>();
        boolean started = false;

        for (String orgId : visitedOrgIds) {
            if (!started && Objects.equals(orgId, repeatedOrgId)) {
                started = true;
            }
            if (started) {
                cyclePath.add(describeOrg(orgId, orgMap));
            }
        }

        cyclePath.add(describeOrg(repeatedOrgId, orgMap));

        throw new IllegalStateException("组织节点出现循环引用: " + String.join(" -> ", cyclePath));
    }

    private <ORG extends RbacOrgInfo> String describeOrg(String orgId, Map<String, ORG> orgMap) {
        final ORG org = orgMap.get(orgId);
        if (org == null) {
            return orgId;
        }
        return Objects.toString(org.getId(), "") + "-" + StrUtil.blankToDefault(org.getName(), "");
    }

    private boolean resetCopiedNode(RbacOrgInfo org) {
        Collection<RbacOrgInfo> newChildren = new ArrayList<>();
        return setChildren(org, newChildren);
    }

    private boolean appendChild(RbacOrgInfo parent, RbacOrgInfo child) {

        try {
            Collection<RbacOrgInfo> children = parent.getChildren();
            if (children != null) {
                children.add(child);
                return true;
            }
        } catch (UnsupportedOperationException | IllegalArgumentException ignored) {
        }

        Collection<RbacOrgInfo> newChildren = new ArrayList<>();
        newChildren.add(child);

        return setChildren(parent, newChildren);
    }

    private boolean setChildren(RbacOrgInfo org, Collection<RbacOrgInfo> children) {
        Method setter = findCachedCompatibleSetter(org.getClass(), "setChildren", Collection.class, CHILDREN_SETTER_CACHE);

        if (setter != null) {
            ReflectionUtils.invokeMethod(setter, org, adaptChildrenCollection(children, setter.getParameterTypes()[0]));
            return true;
        }

        Field field = findCachedField(org.getClass(), "children", Collection.class, CHILDREN_FIELD_CACHE);
        if (field == null) {
            return false;
        }

        ReflectionUtils.setField(field, org, adaptChildrenCollection(children, field.getType()));
        return true;
    }

    private Collection<RbacOrgInfo> adaptChildrenCollection(Collection<RbacOrgInfo> children, Class<?> targetType) {
        final Collection<RbacOrgInfo> safeChildren = children != null ? children : Collections.emptyList();

        if (targetType.isInstance(safeChildren)) {
            return safeChildren;
        }

        if (targetType.isAssignableFrom(ArrayList.class)) {
            return new ArrayList<>(safeChildren);
        }

        if (targetType.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet<>(safeChildren);
        }

        if (targetType.isAssignableFrom(HashSet.class)) {
            return new HashSet<>(safeChildren);
        }

        if (targetType.isAssignableFrom(LinkedList.class)) {
            return new LinkedList<>(safeChildren);
        }

        if (!targetType.isInterface() && !java.lang.reflect.Modifier.isAbstract(targetType.getModifiers())) {
            Collection<RbacOrgInfo> targetChildren = (Collection<RbacOrgInfo>) BeanUtils.instantiateClass(targetType);
            targetChildren.addAll(safeChildren);
            return targetChildren;
        }

        throw new IllegalArgumentException("不支持的组织 children 集合类型: " + targetType.getName());
    }

    private boolean setNodePathOnCopy(RbacOrgInfo org, String nodePath) {
        return writeProperty(org, nodePath, "setNodePath", CharSequence.class, "nodePath",
                NODE_PATH_SETTER_CACHE, NODE_PATH_FIELD_CACHE);
    }

    private boolean writeProperty(RbacOrgInfo org,
                                  Object value,
                                  String setterName,
                                  Class<?> setterParamType,
                                  String fieldName,
                                  Map<Class<?>, Method> setterCache,
                                  Map<Class<?>, Field> fieldCache) {
        Method setter = findCachedCompatibleSetter(org.getClass(), setterName, setterParamType, setterCache);

        if (setter != null) {
            ReflectionUtils.invokeMethod(setter, org, value);
            return true;
        }

        Field field = findCachedField(org.getClass(), fieldName, setterParamType, fieldCache);
        if (field == null) {
            return false;
        }

        ReflectionUtils.setField(field, org, value);
        return true;
    }

    private Method findCachedCompatibleSetter(Class<?> type,
                                              String setterName,
                                              Class<?> setterParamType,
                                              Map<Class<?>, Method> setterCache) {
        Method setter = setterCache.get(type);
        if (setter != null) {
            return setter;
        }

        setter = Arrays.stream(type.getMethods())
                .filter(method -> setterName.equals(method.getName()))
                .filter(method -> method.getParameterCount() == 1)
                .filter(method -> setterParamType.isAssignableFrom(method.getParameterTypes()[0]))
                .findFirst()
                .orElse(null);

        if (setter != null) {
            ReflectionUtils.makeAccessible(setter);
            setterCache.put(type, setter);
        }

        return setter;
    }

    private Field findCachedField(Class<?> type,
                                  String fieldName,
                                  Class<?> fieldType,
                                  Map<Class<?>, Field> fieldCache) {
        Field field = fieldCache.get(type);
        if (field != null) {
            return field;
        }

        field = ReflectionUtils.findField(type, fieldName);
        if (field != null && fieldType.isAssignableFrom(field.getType())) {
            ReflectionUtils.makeAccessible(field);
            fieldCache.put(type, field);
            return field;
        }

        return null;
    }

    /**
     * 加载直接下级组织
     *
     * @param tenantId
     * @param orgPrincipal id 或是 RbacOrgInfo
     * @return
     */

}
