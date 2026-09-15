package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.dao.domain.ConfidentialObject;
import com.levin.commons.dao.domain.DomainObject;
import com.levin.commons.dao.domain.ProxyWrapperObject;
import com.levin.commons.utils.ExpressionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.levin.commons.rbac.RbacMiscUtils.*;


/**
 * 加载服务
 *
 * 数据范围规则：用户六个范围字段的非 {@code null} 值（空集合也包括在内）覆盖角色配置，
 * 只有 {@code null} 才回退到生效角色的并集；拒绝规则优先于允许规则。默认实现按候选集计算，
 * 实现类可以使用等价的数据库查询或缓存优化，但不得改变上述可见性、优先级和拒绝语义。
 *
 * @author echo
 */
@Tag(name = "RBAC 数据范围服务", description = "用户范围字段非 null（含空集合）覆盖角色配置，只有 null 才继承生效角色并集。仅超级管理员 R_SA（含顶级 sa）享有全局租户/组织范围快捷路径；SaaS 管理员与普通平台用户均须匹配授权范围并服从拒绝规则，有授权允许跨组织，不设所属组织硬边界。租户管理员的组织管理范围只限自身已授权租户；领域门槛均保留，普通超管与 SaaS 管理员列表/管理入口仍检查密级。角色分配按当前完整有效目录检查范围上限，目录/规则/上下文变化须重新校验。")
public interface RbacBaseService extends RbacBaseUserService {

    Map<Class<?>, List<Field>> COPYABLE_FIELDS_CACHE = new ConcurrentReferenceHashMap<>();
    Map<Class<?>, Method> CHILDREN_SETTER_CACHE = new ConcurrentReferenceHashMap<>();
    Map<Class<?>, Method> NODE_PATH_SETTER_CACHE = new ConcurrentReferenceHashMap<>();
    Map<Class<?>, Field> CHILDREN_FIELD_CACHE = new ConcurrentReferenceHashMap<>();
    Map<Class<?>, Field> NODE_PATH_FIELD_CACHE = new ConcurrentReferenceHashMap<>();

    // 自定义 Groovy 规则的编译结果可以跨请求复用，避免每次重新编译脚本。
    Map<String, Class<Object>> ORG_SCOPE_GROOVY_CLASS_CACHE = new ConcurrentReferenceHashMap<>();

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

    @Operation(summary = "加载全部租户列表", description = "返回数据源中的租户目录，不按当前用户数据范围或动作权限过滤；onlyLoadEffectTenant 为 true 时仅返回有效租户。权限可见性应使用 loadUserAccessibleTenantList。")
    <TENANT extends RbacTenantInfo> Collection<TENANT> loadAllTenantList(boolean onlyLoadEffectTenant);

    /**
     * 加载租户列表
     *
     * @param onlyLoadEffectTenant
     * @param <TENANT>
     * @return
     */
    @Operation(summary = "加载用户能访问的租户列表", description = "平台身份仅提供跨租户资格，不自动授予范围；普通平台用户和 SaaS 管理员均按租户允许减拒绝过滤，仅 R_SA 超管（含顶级 sa）享有全局范围快捷路径。租户用户仍不得跨租户；领域、有效状态门槛保留，普通超管及 SaaS 管理员仍执行列表密级过滤。onlyLoadEffectTenant 原样传给加载器；结果始终排除自审失败对象。")
    default <TENANT extends RbacTenantInfo> Collection<TENANT> loadUserAccessibleTenantList(Serializable userPrincipal, boolean onlyLoadEffectTenant) {
        return DomainAccess.evaluate(() -> {
            final RbacUserInfo user = requireScopeUser(userPrincipal);
            final DataScope scope = getUserDataScope(user);
            if (hasNoEnumerableTenantScope(user, scope)) {
                return Collections.emptyList();
            }
            final List<TENANT> result = new ArrayList<>();
            for (TENANT tenant : this.<TENANT>loadAllTenantListSafe(onlyLoadEffectTenant)) {
                if (canAccessTenant(user, scope, tenant.getId(), tenant)) {
                    result.add(tenant);
                }
            }
            return (isGlobalScopeAdmin(user) || user.isSaasAdmin()) && !user.isTopSuperAdmin()
                    ? filterByScopedConfidentialAccess(scope, result) : result;
        });
    }

    /**
     * 加载租户
     *
     * @param tenantPrincipal
     * @param <TENANT>
     * @return
     */
    @Operation(summary = "加载租户", description = "按租户 ID 或实现支持的租户标识加载单个租户；该原始加载入口不代表当前用户已获得该租户的数据访问权限。")
    <TENANT extends RbacTenantInfo> TENANT loadTenant(Serializable tenantPrincipal);

    /** 加载业务领域目录，onlyLoadEffectDomain 指定是否只加载有效领域。 */
    @Operation(summary = "加载全部领域", description = "领域是业务领域或应用，不是域名；返回领域目录而非当前用户的授权结果。onlyLoadEffectDomain 为 true 时实现应只返回有效领域。")
    <DOMAIN extends RbacDomainInfo> Collection<DOMAIN> loadAllDomainList(boolean onlyLoadEffectDomain);

    /** 加载指定领域，不存在时返回 null。 */
    @Operation(summary = "加载领域", description = "domainPrincipal 为领域 ID 或实现支持的领域标识；未找到时返回 null。该原始加载不执行当前用户领域授权，权限可见性应使用 loadUserAccessibleDomainList。")
    <DOMAIN extends RbacDomainInfo> DOMAIN loadDomain(Serializable domainPrincipal);

    /**
     * 加载用户范围内的有效领域。领域范围不依赖租户，不隐含资源动作或机密级别授权。
     * 子类可覆盖为按有效允许ID集合直接查询；默认仅加载一次目录，不逐项查询领域。
     */
    @Operation(summary = "加载用户可访问的领域列表", description = "用户领域范围非 null 时覆盖角色范围，null 才回退到生效角色并集；拒绝集合优先于允许集合。先短路无授权范围，再批量加载并过滤无效领域，保留数据源顺序。")
    default <DOMAIN extends RbacDomainInfo> Collection<DOMAIN> loadUserAccessibleDomainList(
            Serializable userPrincipal, boolean onlyLoadEffectDomain) {
        return DomainAccess.evaluate(() -> {
            final DomainAccess domainsAccess = userDomainAccess(userPrincipal);
            if (!domainsAccess.mayAllowNonEmptyDomain()) {
                return Collections.emptyList();
            }
            final Collection<DOMAIN> domains = this.<DOMAIN>loadAllDomainList(onlyLoadEffectDomain);
            if (domains == null || domains.isEmpty()) {
                return Collections.emptyList();
            }
            final List<DOMAIN> result = new ArrayList<>();
            for (DOMAIN domain : domains) {
                if (domainsAccess.allowsLoaded(domain)) {
                    result.add(domain);
                }
            }
            return result;
        });
    }

    /**
     * 加载组织
     *
     * @param orgPrincipal
     * @param <ORG>
     */
    @Operation(summary = "加载组织", description = "orgPrincipal 可以是组织 ID 或 RbacOrgInfo；该原始加载入口不执行租户、领域或数据范围校验，访问判定应使用 checkOrgAccessible 或用户可访问列表。")
    <ORG extends RbacOrgInfo> ORG loadOrg(Serializable orgPrincipal);

    /**
     * 加载租户的组织列表
     * tenantId 为 null 时加载无租户的组织
     * 组织太多时，会导致性能问题
     *
     * @param tenantId 可为null，为 null 时加载无租户的组织
     * @return
     */
    @Operation(summary = "加载租户组织列表", description = "返回指定租户的原始组织目录，不按用户领域、数据范围或动作权限过滤；tenantId 为 null 时返回无租户组织，onlyLoadEffectOrg 为 true 时只返回有效组织。实现可在数据层或缓存预裁剪，但返回对象必须只读。")
    <ORG extends RbacOrgInfo> Collection<ORG> loadTenantOrgList(Serializable tenantId, boolean onlyLoadEffectOrg);


    @Operation(summary = "加载用户能访问的组织列表", description = "先检查租户资格，再匹配组织范围和领域；普通平台用户与 SaaS 管理员必须有明确组织授权并服从拒绝，有授权可跨组织树。仅 R_SA 超管享有全局组织范围快捷路径，租户管理员仅在自身已授权租户享有组织管理范围。普通超管与 SaaS 管理员仍执行对象密级过滤。用户非 null 字段覆盖角色、null 才继承；查询/缓存优化不得放宽结果。")
    default <ORG extends RbacOrgInfo> Collection<ORG> loadUserAccessibleOrgList(Serializable userPrincipal, boolean onlyLoadEffectOrg) {
        return DomainAccess.evaluate(() -> {
            final RbacUserInfo user = requireScopeUser(userPrincipal);
            final DataScope scope = getUserDataScope(user);
            final Set<String> tenantIds = new LinkedHashSet<>();
            if (!hasNoEnumerableTenantScope(user, scope)) {
                for (RbacTenantInfo tenant : loadAllTenantListSafe(onlyLoadEffectOrg)) {
                    if (canAccessTenant(user, scope, tenant.getId(), tenant)) {
                        tenantIds.add(scopeId(tenant.getId()));
                    }
                }
            }
            if (canAccessTenant(user, scope, null, null)) {
                tenantIds.add(null);
            }
            final List<ORG> result = new ArrayList<>();
            for (String tenantId : tenantIds) {
                if (!hasOrgAdminScope(user, tenantId) && scope.getOrgScopeList().isEmpty()) {
                    continue;
                }
                final Map<String, ORG> orgMap = scopedOrgMap(tenantId, onlyLoadEffectOrg);
                final Set<String> allowed = hasOrgAdminScope(user, tenantId)
                        ? orgMap.keySet() : accessibleOrgIds(user, scope, tenantId, orgMap);
                orgMap.forEach((orgId, org) -> {
                    if (allowed.contains(orgId) && orgDomainAllowed(scope, tenantId, org)) result.add(org);
                });
            }
            return (isGlobalScopeAdmin(user) || user.isSaasAdmin()) && !user.isTopSuperAdmin()
                    ? filterByScopedConfidentialAccess(scope, result) : result;
        });
    }

    /**
     * 加载“最大候选组织集合”。
     * 包含所有租户组织和无租户公共组织，供顶级超级管理员直接返回，也供普通超管/SaaS 管理员做机密级别过滤。
     */
    @Operation(summary = "加载最大候选组织集合", description = "聚合全部有效租户的组织和无租户公共组织，作为全局管理员后续机密级别过滤的候选集；该方法本身不完成用户范围授权。默认逐租户加载，子类可使用等价批量查询或缓存避免 N+1。")
    default <ORG extends RbacOrgInfo> Collection<ORG> loadMaxAccessibleOrgList(boolean onlyLoadEffectOrg) {
        final Collection<ORG> allOrgList = new LinkedHashSet<>();

        for (RbacTenantInfo tenant : Optional.ofNullable(loadAllTenantList(onlyLoadEffectOrg)).orElse(Collections.emptyList())) {
            if (tenant == null || !tenant.selfAudit()) {
                continue;
            }

            final Serializable tenantId = (Serializable) tenant.getId();
            allOrgList.addAll((Collection<ORG>) Optional.ofNullable(loadTenantOrgList(tenantId, onlyLoadEffectOrg))
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(Objects::nonNull)
                    .filter(RbacCoreObject::selfAudit)
                    .collect(Collectors.toCollection(LinkedHashSet::new)));
        }

        allOrgList.addAll((Collection<ORG>) Optional.ofNullable(loadTenantOrgList(null, onlyLoadEffectOrg))
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(RbacCoreObject::selfAudit)
                .collect(Collectors.toCollection(LinkedHashSet::new)));

        return allOrgList;
    }

    /**
     * 按机密级别过滤结果集合。
     * TopSA 之外的全局快捷路径都需要经过这一层，确保“直接返回最大结果”时仍尊重对象自身的机密级别。
     */
    default <T extends ConfidentialObject> Collection<T> filterByConfidentialAccess(Serializable userPrincipal, Collection<T> objectList) {
        return DomainAccess.evaluate(() -> {
            if (objectList == null || objectList.isEmpty()) return Collections.emptyList();
            return filterByScopedConfidentialAccess(getUserDataScope(userPrincipal), objectList);
        });
    }

    private <T extends ConfidentialObject> Collection<T> filterByScopedConfidentialAccess(DataScope scope, Collection<T> objectList) {
        final DomainAccess domains = domainAccess(scope);
        return objectList.stream().filter(Objects::nonNull)
                .filter(obj -> !(obj instanceof DomainObject domainObject) || domains.allowsObject(domainObject))
                .filter(obj -> canAccessConfidentialData(scope::getConfidentialDataAccessLevel, obj.getConfidentialLevel()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Operation(summary = "组装组织树", description = "按 rootIdList 从传入组织集合组装树；指定根节点时只返回对应子树，未指定时回退为全部根节点。该方法不新增权限过滤，调用方应先传入已授权的候选集合。")
    default <ORG extends RbacOrgInfo> Collection<ORG> assembleOrgTree(Collection<ORG> orgList, String... rootIdList) {
        return assembleOrgTree(orgList, true, rootIdList);
    }

    @Operation(summary = "组装组织树", description = "按租户分别建立索引并复制节点，避免修改传入的扁平组织集合；buildNodePath 决定是否构建节点路径。指定 rootIdList 时仅返回对应子树，未指定时返回全部根节点；不执行权限过滤。")
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

        final Map<String, List<ORG>> sourceOrgListByTenant = new LinkedHashMap<>();
        for (ORG sourceOrg : sourceOrgList) {
            sourceOrgListByTenant.computeIfAbsent(getOrgTenantMapKey(sourceOrg), key -> new ArrayList<>()).add(sourceOrg);
        }

        if (sourceOrgListByTenant.size() > 1) {
            final List<ORG> rootList = new ArrayList<>();
            sourceOrgListByTenant.values().forEach(tenantOrgList ->
                    rootList.addAll(assembleOrgTreeInSingleTenant(tenantOrgList, buildNodePath, rootIdList)));
            return rootList;
        }

        return assembleOrgTreeInSingleTenant(sourceOrgList, buildNodePath, rootIdList);
    }

    private <ORG extends RbacOrgInfo> Collection<ORG> assembleOrgTreeInSingleTenant(List<ORG> sourceOrgList,
                                                                                   boolean buildNodePath,
                                                                                   String... rootIdList) {

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

    private String getOrgTenantMapKey(RbacOrgInfo org) {
        try {
            Serializable tenantId = org.getTenantId();
            return Objects.toString(tenantId, "");
        } catch (UnsupportedOperationException ignored) {
            return "";
        }
    }

    /**
     * 加载当前用户有权限访问的组织列表
     *
     * @param userPrincipal
     * @param assembleTree
     * @param rootIdList    指定部分的根节点ID
     * @return 组织信息集合，可能是树形结构
     */
    @Operation(summary = "加载当前用户有权限访问的组织列表", description = "先按数据范围和拒绝优先规则筛选组织，再按 rootIdList 裁剪；assembleTree 为 true 时将筛选结果组装为树。rootIdList 为空时回退为所有可访问根节点。")
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
    @Operation(summary = "是否能访问所有组织", description = "仅在租户范围、组织全量范围及每个实际组织均通过时返回 true；平台用户还必须覆盖全部租户和无租户组织。拒绝范围或任一未覆盖目标都会返回 false，且本方法不代表业务动作权限或机密级别授权。子类可用用户标记、角色或权限缓存实现等价 O(1) 判断。")
    default boolean canAccessAllOrg(Serializable userPrincipal) {
        return DomainAccess.evaluate(() -> {
            final RbacUserInfo user = requireScopeUser(userPrincipal);
            if (!user.isPlatformUser()) {
                return canAccessAllOrg(user, user.getTenantId());
            }
            // 保留平台用户的全局语义：不仅覆盖当前枚举对象，也覆盖全部租户及无租户组织。
            final DataScope scope = getUserDataScope(user);
            if (!isGlobalScopeAdmin(user) && (!scope.getTenantScopeList().contains(DataScope.TenantScope.All.getExpression())
                    || !scope.getDeniedTenantScopeList().isEmpty() || !declaresAllOrg(scope))) {
                return false;
            }
            for (RbacTenantInfo tenant : loadAllTenantListSafe(true)) {
                if (!canAccessTenant(user, scope, tenant.getId(), tenant)
                        || !coversAllExistingOrg(user, scope, tenant.getId())) return false;
            }
            return canAccessTenant(user, scope, null, null) && coversAllExistingOrg(user, scope, null);
        });
    }

    /** 判断目标租户内所有非空组织是否被完整授权。 */
    default boolean canAccessAllOrg(Serializable userPrincipal, Serializable tenantId) {
        return DomainAccess.evaluate(() -> {
            final RbacUserInfo user = requireScopeUser(userPrincipal);
            final DataScope scope = getUserDataScope(user);
            return canAccessTenant(user, scope, tenantId, null)
                    && (hasOrgAdminScope(user, tenantId) || declaresAllOrg(scope))
                    && coversAllExistingOrg(user, scope, tenantId);
        });
    }

    /** 只判断租户范围资格；业务操作权限和机密级别仍须单独校验。 */
    default boolean canAccessTenant(Serializable userPrincipal, Serializable tenantId) {
        return DomainAccess.evaluate(() -> {
            final RbacUserInfo user = requireScopeUser(userPrincipal);
            return canAccessTenant(user, getUserDataScope(user), tenantId, null);
        });
    }

    /** 领域范围支持 All、None 和具体 ID；非空 ID 须存在且有效，不享有管理员绕过。 */
    default boolean canAccessDomain(Serializable userPrincipal, String domainId) {
        return DomainAccess.evaluate(() -> {
            return userDomainAccess(userPrincipal).allows(domainId);
        });
    }

    /** 空领域不增加对象级限制；非空领域是管理员快捷路径之前的共同门槛。 */
    @Override
    default boolean canAccessObjectDomain(Serializable userPrincipal, DomainObject object) {
        return DomainAccess.evaluate(() -> {
            if (object == null) return false;
            if (scopeId(object.getDomainId()) == null) return true;
            return userDomainAccess(userPrincipal).allowsObject(object);
        });
    }

    @Override
    default boolean canAccessUserDomain(Serializable userPrincipal, RbacUserInfo target) {
        return DomainAccess.evaluate(() -> {
            if (target == null) return false;
            final DomainAccess domains = userDomainAccess(userPrincipal);
            if (!domains.allowsObject(target)) return false;
            final String tenantId = scopeId(target.getTenantId());
            if (tenantId == null) return true;
            final RbacTenantInfo tenant = domains.tenant(tenantId, id -> loadTenant(id));
            return tenant != null && tenant.selfAudit() && tenantId.equals(scopeId(tenant.getId())) && domains.allowsObject(tenant);
        });
    }

    /** 批次内复用领域查询结果，按原顺序返回授权对象。 */
    default <T extends DomainObject> Collection<T> filterByDomainAccess(Serializable userPrincipal, Collection<T> objects) {
        return DomainAccess.evaluate(() -> {
            if (objects == null || objects.isEmpty()) return Collections.emptyList();
            final DomainAccess domains = userDomainAccess(userPrincipal);
            final List<T> result = new ArrayList<>();
            for (T object : objects) {
                if (object != null && domains.allowsObject(object)) result.add(object);
            }
            return result;
        });
    }

    private DomainAccess userDomainAccess(Serializable userPrincipal) {
        // 保留实现类覆盖有效数据范围的契约；默认快照通过原始候选构建，不回调本方法。
        return domainAccess(getUserDataScope(userPrincipal));
    }

    private DomainAccess domainAccess(DataScope scope) {
        return DomainAccess.forScope(this, scope, id -> loadDomain(id));
    }

    /** 只在最终对象上过滤领域，不从组织树索引移除祖先。 */
    private boolean orgDomainAllowed(DataScope scope, Serializable tenantId, RbacOrgInfo org) {
        if (org == null) return false;
        final DomainAccess domains = domainAccess(scope);
        final String id = scopeId(tenantId);
        if (id == null) return domains.allowsObject(org);
        final RbacTenantInfo tenant = domains.tenant(id, key -> loadTenant(key));
        if (tenant == null || !tenant.selfAudit() || !id.equals(scopeId(tenant.getId()))) return false;
        final String orgDomain = scopeId(org.getDomainId());
        final String tenantDomain = scopeId(tenant.getDomainId());
        return (orgDomain == null || tenantDomain == null || orgDomain.equals(tenantDomain))
                && domains.allowsObject(tenant) && domains.allowsObject(org);
    }

    /** 组织范围判断包含目标租户资格；空组织 ID 按 None 规则判断。 */
    default boolean canAccessOrg(Serializable userPrincipal, Serializable tenantId, Serializable orgId) {
        return DomainAccess.evaluate(() -> {
            final RbacUserInfo user = requireScopeUser(userPrincipal);
            final DataScope scope = getUserDataScope(user);
            if (!canAccessTenant(user, scope, tenantId, null)) {
                return false;
            }
            if (scopeId(orgId) == null) {
                return hasOrgAdminScope(user, tenantId)
                        || (!scope.getOrgScopeList().isEmpty()
                        && !matchesNoOrg(scope.getDeniedOrgScopeList(), user, tenantId)
                        && matchesNoOrg(scope.getOrgScopeList(), user, tenantId));
            }
            if (!hasOrgAdminScope(user, tenantId) && scope.getOrgScopeList().isEmpty()) return false;
            final Map<String, RbacOrgInfo> orgMap = scopedOrgMap(tenantId, true);
            return orgMap.containsKey(scopeId(orgId)) && orgDomainAllowed(scope, tenantId, orgMap.get(scopeId(orgId)))
                    && (hasOrgAdminScope(user, tenantId)
                    || accessibleOrgIds(user, scope, tenantId, orgMap, scopeId(orgId)).contains(scopeId(orgId)));

        });
    }

    @Operation(summary = "获取用户数据权限", description = "六个范围字段独立处理：用户字段非 null（包括空集合）覆盖对应角色配置，只有 null 才回退到生效角色并集；后续范围匹配中拒绝规则优先于允许规则。返回不可变快照。")
    default DataScope getUserDataScope(Serializable userPrincipal) {
        return DomainAccess.evaluate(() -> {
            final RbacUserInfo user = requireScopeUser(userPrincipal);
            final boolean needsRoles = user.getTenantScopeList() == null || user.getDeniedTenantScopeList() == null
                    || user.getDomainScopeList() == null || user.getDeniedDomainScopeList() == null
                    || user.getOrgScopeList() == null || user.getDeniedOrgScopeList() == null
                    || user.getConfidentialDataAccessLevel() == null;
            final Collection<RbacRoleInfo> candidates = needsRoles ? loadCandidateUserRoles(user, true) : Collections.emptyList();
            final Set<String> allowedDomains = resolveScopeField(user, candidates, DataScope::getDomainScopeList, "domain");
            final Set<String> deniedDomains = resolveScopeField(user, candidates, DataScope::getDeniedDomainScopeList, "domain");
            final DomainAccess domains = DomainAccess.obtain(this, allowedDomains, deniedDomains, id -> loadDomain(id));
            final Collection<RbacRoleInfo> roles = candidates.stream().filter(domains::allowsObject).collect(Collectors.toList());
            Integer level = user.isTopSuperAdmin() ? Integer.valueOf(Integer.MAX_VALUE) : user.getConfidentialDataAccessLevel();
            if (level == null) {
                level = roles.stream().filter(Objects::nonNull).filter(RbacCoreObject::selfAudit)
                        .map(RbacRoleInfo::getConfidentialDataAccessLevel).filter(Objects::nonNull)
                        .max(Integer::compareTo).orElse(null);
            }
            return new EffectiveDataScope(
                    resolveScopeField(user, roles, DataScope::getTenantScopeList, "tenant"),
                    resolveScopeField(user, roles, DataScope::getDeniedTenantScopeList, "tenant"),
                    allowedDomains, deniedDomains,
                    resolveScopeField(user, roles, DataScope::getOrgScopeList, "org"),
                    resolveScopeField(user, roles, DataScope::getDeniedOrgScopeList, "org"), level);
        });
    }

    /** 角色授予范围按目标用户解释，目录快照仅用于当前分配判定。 */
    @Operation(summary = "校验角色分配的数据范围上限", description = "按当前完整且一致的有效领域、租户、组织目录验证每个角色独立范围及分配后有效范围均包含于操作者范围；DEFAULT 分别按双方归属解释，角色自身拒绝先扣除，不能借其他角色拒绝或目标用户覆盖掩盖单角色越权。管理员仍受领域与密级上限约束；密级须同时满足有效快照和公开密级检查。含 Groovy 的范围要求目标真实对象已携带最终角色编码，否则拒绝，避免按分配前状态误放行；操作人须为当前已授权上下文。仅证明本次快照，目录/规则/上下文变化后须重新校验。目录返回 null 或检查异常时拒绝，不建立跨请求授权缓存。")
    default void checkRoleDataScopeAssignment(RbacUserInfo operator, RbacUserInfo target,
                                             Collection<? extends RbacRoleInfo> assignedRoles) {
        DomainAccess.evaluate(() -> {
            Assert.notNull(operator, "操作用户不能为空");
            Assert.notNull(target, "目标用户不能为空");
            Assert.notNull(assignedRoles, "角色集合不能为空");
            final DataScope operatorScope = getUserDataScope(operator);
            final List<RbacRoleInfo> roles = new ArrayList<>(assignedRoles);
            for (RbacRoleInfo role : roles) Assert.notNull(role, "角色不能为空");
            // 不用代理冒充真实用户：自定义类型、属性和 hasRole 均可能被脚本读取。
            if (hasAssignmentScript(target) || roles.stream().anyMatch(this::hasAssignmentScript)) {
                Set<String> finalCodes = roles.stream().map(RbacRoleInfo::getCode).collect(Collectors.toSet());
                Set<String> targetCodes = Optional.ofNullable(target.getRoleList()).orElse(Collections.emptyList()).stream()
                        .filter(Objects::nonNull).map(Object::toString).collect(Collectors.toSet());
                Assert.isTrue(finalCodes.equals(targetCodes), "含脚本范围的目标用户必须提供分配后的最终角色列表");
            }
            final Collection<RbacDomainInfo> domains = this.<RbacDomainInfo>loadAllDomainList(true);
            final Collection<RbacTenantInfo> directory = this.<RbacTenantInfo>loadAllTenantList(true);
            Assert.notNull(domains, "领域目录加载失败，无法验证角色范围");
            Assert.notNull(directory, "租户目录加载失败，无法验证角色范围");
            final Collection<RbacTenantInfo> tenants = directory.stream().filter(Objects::nonNull)
                    .filter(RbacCoreObject::selfAudit).collect(Collectors.toList());
            for (RbacRoleInfo role : roles) {
                Assert.notNull(role, "角色不能为空");
                DataScope grant = assignmentScope(role, Collections.emptyList(), false);
                boolean global = grantsGlobalAdmin(target, Collections.singletonList(role));
                boolean orgAdmin = global || target.isTenantUser() && RbacRoleInfo.ADMIN_ROLE.equals(role.getCode());
                checkScopeContained(operator, operatorScope, target, grant, global, orgAdmin, domains, tenants);
            }
            boolean global = grantsGlobalAdmin(target, roles);
            boolean orgAdmin = global || target.isTenantUser() && roles.stream().anyMatch(r -> RbacRoleInfo.ADMIN_ROLE.equals(r.getCode()));
            boolean top = target.isPlatformUser() && RbacUserInfo.TOP_SA_ACCOUNT_NAME.equals(target.getLoginName())
                    && roles.stream().anyMatch(r -> RbacRoleInfo.SA_ROLE.equals(r.getCode()));
            final DataScope effective = assignmentScope(target, roles, top);
            checkScopeContained(operator, operatorScope, target, effective, global, orgAdmin, domains, tenants);
            return null;
        });
    }

    private boolean hasAssignmentScript(DataScope scope) {
        return Arrays.asList(scope.getTenantScopeList(), scope.getDeniedTenantScopeList()).stream().filter(Objects::nonNull)
                .flatMap(Collection::stream).filter(Objects::nonNull)
                .anyMatch(rule -> rule.startsWith(DataScope.TenantScope.Groovy.getExpression()))
                || Arrays.asList(scope.getOrgScopeList(), scope.getDeniedOrgScopeList()).stream().filter(Objects::nonNull)
                .flatMap(Collection::stream).filter(StrUtil::isNotBlank).map(DataScope.OrgScope::parse)
                .anyMatch(rule -> rule.orgMatchingMode().startsWith(DataScope.OrgMatchingMode.Groovy.getExpression()));
    }

    private boolean grantsGlobalAdmin(RbacUserInfo target, Collection<RbacRoleInfo> roles) {
        return target.isPlatformUser() && roles.stream().anyMatch(r -> RbacRoleInfo.SA_ROLE.equals(r.getCode()));
    }

    private DataScope assignmentScope(DataScope source, Collection<RbacRoleInfo> roles, boolean top) {
        Set<String> allowDomains = resolveScopeField(source, roles, DataScope::getDomainScopeList, "domain");
        Set<String> denyDomains = resolveScopeField(source, roles, DataScope::getDeniedDomainScopeList, "domain");
        DomainAccess access = DomainAccess.obtain(this, allowDomains, denyDomains, id -> loadDomain(id));
        Collection<RbacRoleInfo> qualified = roles.stream().filter(access::allowsObject).collect(Collectors.toList());
        Integer level = top ? Integer.valueOf(Integer.MAX_VALUE) : source.getConfidentialDataAccessLevel();
        if (level == null) level = qualified.stream().map(RbacRoleInfo::getConfidentialDataAccessLevel)
                .filter(Objects::nonNull).max(Integer::compareTo).orElse(null);
        return new EffectiveDataScope(resolveScopeField(source, qualified, DataScope::getTenantScopeList, "tenant"),
                resolveScopeField(source, qualified, DataScope::getDeniedTenantScopeList, "tenant"), allowDomains, denyDomains,
                resolveScopeField(source, qualified, DataScope::getOrgScopeList, "org"),
                resolveScopeField(source, qualified, DataScope::getDeniedOrgScopeList, "org"), level);
    }

    private void checkScopeContained(RbacUserInfo operator, DataScope operatorScope, RbacUserInfo target,
                                     DataScope grant, boolean globalGrant, boolean orgAdminGrant,
                                     Collection<RbacDomainInfo> domains, Collection<RbacTenantInfo> tenants) {
        Assert.isTrue(canAccessConfidentialData(operatorScope::getConfidentialDataAccessLevel, grant.getConfidentialDataAccessLevel())
                        && canAccessConfidentialDataByUser(operator, grant.getConfidentialDataAccessLevel()),
                "分配后的机密数据访问级别超出操作用户上限");
        DomainAccess grantedDomains = domainAccess(grant);
        DomainAccess operatorDomains = domainAccess(operatorScope);
        Assert.isTrue(!grantedDomains.allows(null) || operatorDomains.allows(null), "角色无领域范围超出操作用户上限");
        for (RbacDomainInfo domain : domains) {
            Assert.isTrue(!grantedDomains.allowsLoaded(domain) || operatorDomains.allowsLoaded(domain), "角色领域范围超出操作用户上限");
        }
        checkTenantScopeContained(operator, operatorScope, target, grant, globalGrant, orgAdminGrant, null, null);
        for (RbacTenantInfo tenant : tenants) {
            checkTenantScopeContained(operator, operatorScope, target, grant, globalGrant, orgAdminGrant, tenant.getId(), tenant);
        }
    }

    private void checkTenantScopeContained(RbacUserInfo operator, DataScope operatorScope, RbacUserInfo target,
                                           DataScope grant, boolean globalGrant, boolean orgAdminGrant,
                                           Serializable tenantId, RbacTenantInfo tenant) {
        if (!withinTenantBoundary(target, tenantId)
                || !canAccessTenantWithinBoundary(target, grant, tenantId, tenant, globalGrant)) return;
        Assert.isTrue(canAccessTenant(operator, operatorScope, tenantId, tenant), "角色租户范围[{}]超出操作用户上限", tenantId);
        boolean operatorAdmin = hasOrgAdminScope(operator, tenantId);
        boolean grantsNoOrg = orgAdminGrant || !grant.getOrgScopeList().isEmpty()
                && !matchesNoOrg(grant.getDeniedOrgScopeList(), target, tenantId) && matchesNoOrg(grant.getOrgScopeList(), target, tenantId);
        boolean operatorNoOrg = operatorAdmin || !operatorScope.getOrgScopeList().isEmpty()
                && !matchesNoOrg(operatorScope.getDeniedOrgScopeList(), operator, tenantId)
                && matchesNoOrg(operatorScope.getOrgScopeList(), operator, tenantId);
        Assert.isTrue(!grantsNoOrg || operatorNoOrg, "角色无组织范围超出操作用户上限");
        if (!orgAdminGrant && grant.getOrgScopeList().isEmpty()) return;
        final Collection<RbacOrgInfo> directory = this.<RbacOrgInfo>loadTenantOrgList(tenantId, true);
        Assert.notNull(directory, "组织目录加载失败，无法验证角色范围");
        final Map<String, RbacOrgInfo> orgs = new LinkedHashMap<>();
        for (RbacOrgInfo org : directory) {
            if (org != null && org.selfAudit() && scopeId(org.getId()) != null
                    && Objects.equals(scopeId(tenantId), scopeId(org.getTenantId()))) {
                orgs.putIfAbsent(scopeId(org.getId()), org);
            }
        }
        Set<String> grantedIds = orgAdminGrant ? orgs.keySet() : accessibleOrgIds(target, grant, tenantId, orgs);
        Set<String> operatorIds = operatorAdmin ? orgs.keySet() : accessibleOrgIds(operator, operatorScope, tenantId, orgs);
        for (String id : grantedIds) {
            RbacOrgInfo org = orgs.get(id);
            if (!orgDomainAllowed(grant, tenantId, org)) continue;
            Assert.isTrue(operatorIds.contains(id) && orgDomainAllowed(operatorScope, tenantId, org),
                    "角色组织范围[{}:{}]超出操作用户上限", tenantId, id);
        }
    }

    private Set<String> resolveScopeField(DataScope user, Collection<RbacRoleInfo> roles,
                                          Function<DataScope, Set<String>> getter, String dimension) {
        Set<String> configured = getter.apply(user);
        final Set<String> result = new LinkedHashSet<>();
        if (configured != null) {
            result.addAll(configured);
        } else {
            roles.stream().filter(Objects::nonNull).filter(RbacCoreObject::selfAudit)
                    .map(getter).filter(Objects::nonNull).forEach(result::addAll);
        }
        final Set<String> normalized = new LinkedHashSet<>();
        for (String rule : result) {
            Assert.isTrue(StrUtil.isNotBlank(rule), "数据范围规则不能为空");
            if ("org".equals(dimension)) {
                normalized.add(DataScope.OrgScope.format(DataScope.OrgScope.parse(rule)));
            } else {
                if ("tenant".equals(dimension) && rule.startsWith(DataScope.TenantScope.Groovy.getExpression())) {
                    Assert.isTrue(StrUtil.isNotBlank(rule.substring(DataScope.TenantScope.Groovy.getExpression().length())),
                            "租户 Groovy 表达式不能为空");
                }
                normalized.add(rule);
            }
        }
        return Collections.unmodifiableSet(normalized);
    }

    @Operation(summary = "加载直接下级组织", description = "按 parentId 从指定租户的有效组织目录中筛选直接子节点；不执行当前用户的数据范围或动作权限校验。默认实现内存过滤，子类可覆盖为等价的按 parentId 查询。")
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
    @Operation(summary = "加载所有直系父组织", description = "从目标组织向根节点回溯，按由近到远顺序返回；containsSelf 决定是否包含目标自身，selfAudit 决定是否筛除未通过自审的节点。该目录查询不执行用户授权；子类可用递归 SQL、闭包表或 nodePath 实现等价结果。")
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
    @Operation(summary = "检查用户组织可访问性", description = "租户身份边界、目标状态、领域和范围失败均抛异常拒绝。仅超管 R_SA（含顶级 sa）享有全局范围快捷路径；SaaS 管理员必须分别获父组织、目标组织授权，二者均为空时须获无组织范围授权。目标组织密级为 null 时，本次访问整体跳过租户、父组织和目标组织的密级比较，不限制操作者密级；其他租户、领域和组织范围门槛仍须通过。目标组织密级非 null 时，依次校验租户、父组织和目标组织密级，并准确提示密级不足的对象。目标组织等于用户所属组织时提示“自己的目标组织”，否则提示“他人目标组织”。租户管理员只在本租户内管理；普通非管理员保留父节点及根管理限制。有授权可跨组织，不以所属组织硬限制替代授权。")
    default void checkOrgAccessible(Serializable userPrincipal, Serializable tenantId, Serializable parentId, Serializable orgId) {
        DomainAccess.evaluate(() -> {
            final RbacUserInfo user = requireScopeUser(userPrincipal);
            Assert.isTrue(withinTenantBoundary(user, tenantId), "非关联的租户[{}]", tenantId);
            final RbacTenantInfo tenant = scopeId(tenantId) == null ? null : loadTenant(tenantId);
            if (scopeId(tenantId) != null) {
                Assert.notNull(tenant, "租户[{}]不存在", tenantId);
                Assert.isTrue(Objects.equals(scopeId(tenant.getId()), scopeId(tenantId)), "非关联的租户[{}]", tenantId);
                Assert.isTrue(tenant.selfAudit(), "租户[{}]不可用", tenantId);
            }
            // 必须在目标租户内解析组织，不能通过全局 loadOrg(id) 借用其他租户的同 ID 节点。
            final Map<String, RbacOrgInfo> targetOrgs = new LinkedHashMap<>();
            for (RbacOrgInfo org : Optional.ofNullable(loadTenantOrgList(tenantId, false)).orElse(Collections.emptyList())) {
                if (org != null && Objects.equals(scopeId(org.getTenantId()), scopeId(tenantId))) {
                    targetOrgs.putIfAbsent(scopeId(org.getId()), org);
                }
            }
            final RbacOrgInfo parent = scopeId(parentId) == null ? null : targetOrgs.get(scopeId(parentId));
            final RbacOrgInfo org = scopeId(orgId) == null ? null : targetOrgs.get(scopeId(orgId));
            if (scopeId(parentId) != null) {
                Assert.notNull(parent, "父组织机构[{}]不存在于租户[{}]", parentId, tenantId);
                Assert.isTrue(parent.selfAudit(), "父组织机构[{}]不可用", parentId);
            }
            if (scopeId(orgId) != null) {
                Assert.notNull(org, "组织机构[{}]不存在于租户[{}]", orgId, tenantId);
                Assert.isTrue(org.selfAudit(), "组织机构[{}]不可用", orgId);
            }
            final DataScope scope = getUserDataScope(user);
            Assert.isTrue(canAccessTenant(user, scope, tenantId, tenant), "租户[{}]未授权", tenantId);
            Assert.isTrue(parent == null || orgDomainAllowed(scope, tenantId, parent), "父组织机构[{}]领域未授权或与租户不一致", parentId);
            Assert.isTrue(org == null || orgDomainAllowed(scope, tenantId, org), "组织机构[{}]领域未授权或与租户不一致", orgId);
            if (user.isTopSuperAdmin()) {
                return null;
            }
            if (user.isSuperAdmin() || user.isSaasAdmin()) {
                final Integer level = scope.getConfidentialDataAccessLevel();
                // 目标组织未设密级时，视为该访问请求不受密级约束；不再由租户或父组织密级间接拒绝。
                if (org == null || org.getConfidentialLevel() != null) {
                    final String orgConfidentialLevelError = org != null
                            && Objects.equals(scopeId(user.getOrgId()), scopeId(org.getId()))
                            ? "当前用户机密级别不足以访问自己的目标组织"
                            : "当前用户机密级别不足以访问他人目标组织";
                    Assert.isTrue(tenant == null || canAccessConfidentialData(() -> level, tenant.getConfidentialLevel()),
                            "当前用户机密级别不足以访问目标租户");
                    Assert.isTrue(parent == null || canAccessConfidentialData(() -> level, parent.getConfidentialLevel()),
                            "当前用户机密级别不足以访问目标组织的父组织");
                    Assert.isTrue(org == null || canAccessConfidentialData(() -> level, org.getConfidentialLevel()),
                            orgConfidentialLevelError);
                }
                if (!user.isSuperAdmin()) {
                    Assert.isTrue(parent == null || canAccessOrg(user, tenantId, parentId), "父组织机构[{}]未授权", parentId);
                    Assert.isTrue(org == null || canAccessOrg(user, tenantId, orgId), "组织机构[{}]未授权", orgId);
                    Assert.isTrue(parent != null || org != null || canAccessOrg(user, tenantId, null), "无组织范围未授权");
                }
                return null;
            }
            if (hasOrgAdminScope(user, tenantId)) {
                return null;
            }
            // 根节点管理仍保留给管理员；跨租户数据访问资格不授予根节点管理权限。
            Assert.isTrue(scopeId(parentId) != null, "组织机构上级节点不能为空");
            final Map<String, RbacOrgInfo> effectiveOrgs = new LinkedHashMap<>();
            targetOrgs.forEach((id, node) -> {
                if (id != null && node.selfAudit()) effectiveOrgs.put(id, node);
            });
            final Set<String> allowed = accessibleOrgIds(user, scope, tenantId, effectiveOrgs);
            Assert.isTrue(allowed.contains(scopeId(parentId)), "父组织机构[{}]未授权", parentId);
            Assert.isTrue(scopeId(orgId) == null || allowed.contains(scopeId(orgId)), "组织机构[{}]未授权", orgId);

            return null;
        });
    }

    /**
     * 获取用户的机密数据访问级别
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    @Override
    @Operation(summary = "获取用户的机密数据访问级别", description = "顶级超级管理员直接返回最大级别；否则优先使用用户自身级别，仅在其为 null 时回退到生效角色中的最高级别。角色回退不使用机密级别过滤后的展示角色，避免权限语义分叉。子类可用缓存字段或预聚合查询实现等价结果。")
    default Integer getUserConfidentialDataAccessLevel(Serializable userPrincipal) {
        return DomainAccess.evaluate(() -> {
            final RbacUserInfo user = requireScopeUser(userPrincipal);
            if (user.isTopSuperAdmin()) {
                return Integer.MAX_VALUE;
            }
            if (user.getConfidentialDataAccessLevel() != null) {
                return user.getConfidentialDataAccessLevel();
            }
            // 与数据范围快照使用同一批生效角色，不经过可见角色过滤，避免递归和同码角色语义分叉。
            return loadUserOwnerRoleList(user).stream().filter(Objects::nonNull).filter(RbacCoreObject::selfAudit)
                    .map(RbacRoleInfo::getConfidentialDataAccessLevel).filter(Objects::nonNull)
                    .max(Integer::compareTo).orElse(null);
        });
    }

    /**
     * 加载角色
     *
     * @param rolePrincipal
     */
    @Operation(summary = "加载角色", description = "按角色 ID 或实现支持的角色标识加载；角色不存在时返回 null。该原始加载不代表用户可见、可使用或可分配该角色。")
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
    @Operation(summary = "加载租户角色列表", description = "返回指定租户角色与公共角色；tenantId 为 null 时只返回公共角色。onlyLoadEffectRole 为 true 时仅返回有效角色；该目录查询不执行当前用户的领域、机密级别或角色授权过滤。")
    <R extends RbacRoleInfo> Collection<R> loadTenantRoleList(Serializable tenantId, boolean onlyLoadEffectRole);

    /**
     * 加载角色列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    @Operation(summary = "按角色编码加载角色列表", description = "从目标租户和公共角色中按编码筛选，不按角色有效状态过滤；同一编码的租户角色与公共角色可同时返回，未在此入口按优先级合并。子类可使用等价批量查询。")
    default <R extends RbacRoleInfo> Collection<R> loadTenantRoleListByCodes(final Serializable tenantId, Collection<String> roleCodeList) {

        if (isAllBlank(roleCodeList)) {
            return Collections.emptyList();
        }

        return (Collection<R>) loadTenantRoleList(tenantId, false)
                .stream()
                .filter(Objects::nonNull)

                // 过滤租户
                .filter(r -> RbacMiscUtils.isBlank(tenantId)
                        ? RbacMiscUtils.isBlank(r.getTenantId())
                        : tenantId.equals(r.getTenantId()) || RbacMiscUtils.isBlank(r.getTenantId()))

                //
                .filter(r -> roleCodeList.contains(r.getCode()))
                .collect(Collectors.toSet());
    }

    @Operation(summary = "按角色编码模式加载角色列表", description = "用 * 和 ? 通配模式从目标租户及公共角色中筛选，不按角色有效状态过滤且不做同码优先级合并。子类可使用等价的批量查询或缓存匹配，但不得改变模式匹配结果。")
    default <R extends RbacRoleInfo> Collection<R> loadTenantRoleListByCodePatterns(final Serializable tenantId, Collection<String> roleCodePatternList) {

        if (isAllBlank(roleCodePatternList)) {
            return Collections.emptyList();
        }

        final Collection<String> patterns = roleCodePatternList.stream()
                .filter(StrUtil::isNotBlank)
                .map(String::trim)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (patterns.isEmpty()) {
            return Collections.emptyList();
        }

        final Map<String, Pattern> singleCharRolePatternCache = new LinkedHashMap<>();
        final BiPredicate<String, String> roleCodePatternMatcher = (rolePattern, roleCode) -> {

            if (StrUtil.isBlank(rolePattern) || StrUtil.isBlank(roleCode)) {
                return false;
            }

            if (rolePattern.indexOf('?') < 0) {
                return PatternMatchUtils.simpleMatch(rolePattern, roleCode);
            }

            final Pattern regexPattern = singleCharRolePatternCache.computeIfAbsent(rolePattern, pattern -> {
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

        return (Collection<R>) loadTenantRoleList(tenantId, false)
                .stream()
                .filter(Objects::nonNull)
                .filter(r -> RbacMiscUtils.isBlank(tenantId)
                        ? RbacMiscUtils.isBlank(r.getTenantId())
                        : tenantId.equals(r.getTenantId()) || RbacMiscUtils.isBlank(r.getTenantId()))
                .filter(r -> StrUtil.isNotBlank(r.getCode()))
                .filter(r -> patterns.stream().anyMatch(pattern -> roleCodePatternMatcher.test(pattern, r.getCode())))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * 加载用户角色列表
     *
     * @param userPrincipal
     * @return
     */
    @Operation(summary = "加载用户生效角色列表", description = "同角色编码按角色定义解析器的优先级归并，并先过滤无效或不属于用户租户的候选；再按领域范围过滤，拒绝优先。供内部授权与权限汇总使用，不做角色对象机密级别过滤。子类可用关联查询或缓存实现等价结果。")
    default <R extends RbacRoleInfo> Collection<R> loadUserOwnerRoleList(Serializable userPrincipal, boolean onlyLoadEffectRole) {
        return DomainAccess.evaluate(() -> {
            final RbacUserInfo user = requireScopeUser(userPrincipal);
            final Collection<RbacRoleInfo> candidates = loadCandidateUserRoles(user, onlyLoadEffectRole);
            final DomainAccess domains = domainAccess(getUserDataScope(user));
            return (Collection<R>) candidates.stream().filter(domains::allowsObject).collect(Collectors.toList());
        });
    }

    /** 生效角色的原始候选：只处理归属、状态和同码优先级，不调用领域或完整数据范围检查。 */
    private <R extends RbacRoleInfo> Collection<R> loadCandidateUserRoles(Serializable userPrincipal, boolean onlyLoadEffectRole) {

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

        return (Collection<R>) (Collection<?>) RoleDefinitionResolver.select(
                user.getTenantId(), roleList, user.getRoleList());
    }

    /**
     * 加载用户“可见”的角色列表。
     * 这个方法面向展示或外部读取语义，可以按角色对象本身的机密级别做过滤；
     * 内部授权、权限汇总、数据范围汇总请使用 loadUserEffectiveRoleList，避免递归并保持用户已有角色语义稳定。
     */
    @Operation(summary = "加载用户可访问的角色列表", description = "先按生效角色的同码优先级、归属和领域范围确定候选，再按角色自身机密级别过滤；该展示语义不回退为未过滤角色。子类可用已过滤缓存或数据库条件查询，但不得放宽可见性。")
    default <R extends RbacRoleInfo> Collection<R> loadUserAccessibleRoleList(Serializable userPrincipal, boolean onlyLoadEffectRole) {
        return DomainAccess.evaluate(() -> {
            final RbacUserInfo user = loadUser(userPrincipal);
            Assert.notNull(user, "用户[{}]无法加载", userPrincipal);
            final DataScope scope = getUserDataScope(user);
            return filterByScopedConfidentialAccess(scope, this.<R>loadCandidateUserRoles(user, onlyLoadEffectRole));
        });
    }

    /**
     * 获取指定角色的权限列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    @Operation(summary = "按角色编码加载权限列表", description = "委托集合参数重载汇总权限；该入口不执行用户、领域或角色可见性授权，角色编码为空时返回空集合。子类可直接按角色编码查询等价权限表达式。")
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
    @Operation(summary = "按角色编码集合加载权限列表", description = "从指定租户和公共角色中汇总匹配编码的非空权限表达式并去重；不按角色有效状态过滤，也不执行用户授权。子类可使用角色权限表批量查询或缓存，但必须保持相同集合语义。")
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

    @Operation(summary = "加载用户生效角色列表", description = "使用同码优先级归并后的生效角色，排除禁用角色并按领域范围过滤；不做角色对象机密级别过滤，供授权与权限汇总使用。子类可用用户角色缓存或关联表查询实现等价结果。")
    default <R extends RbacRoleInfo> Collection<R> loadUserOwnerRoleList(Serializable userPrincipal) {
        return loadUserOwnerRoleList(userPrincipal, true);
    }


    @Operation(summary = "加载用户角色列表", description = "在生效角色基础上再按角色对象机密级别过滤，供展示或外部读取；不会回退为未过滤的授权角色。子类可用可见角色缓存或数据库条件查询实现等价结果。")
    default <R extends RbacRoleInfo> Collection<R> loadUserAccessibleRoleList(Serializable userPrincipal) {
        return loadUserAccessibleRoleList(userPrincipal, true);
    }


    /**
     * 加载用户角色编码列表
     *
     * @param userPrincipal 用户对象或是用户ID
     * @return
     */
    @Operation(summary = "加载用户角色编码列表", description = "从生效角色提取编码，而非从机密级别过滤后的可见角色提取，避免实际授权被展示过滤削弱。子类可直接读取用户角色编码或使用缓存，但结果必须反映生效角色变化。")
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
    @Operation(summary = "加载用户权限表达式列表", description = "基于生效角色（而非机密级别过滤后的可见角色）汇总权限，避免因展示过滤丢失实际授权。子类可用权限缓存或关联表聚合查询，但缓存失效后必须反映角色、领域与范围变化。")
    default Collection<String> loadUserPermissionExprList(Serializable userPrincipal) {
        return DomainAccess.evaluate(() -> {

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
        });
    }

    private RbacUserInfo requireScopeUser(Serializable principal) {
        RbacUserInfo user = principal instanceof RbacUserInfo ? (RbacUserInfo) principal : loadUser(principal);
        Assert.notNull(user, "用户({})不存在", principal);
        return user;
    }

    private String scopeId(Object id) {
        return id == null || StrUtil.isBlank(id.toString()) ? null : id.toString();
    }

    private boolean isGlobalScopeAdmin(RbacUserInfo user) {
        return user.isTopSuperAdmin() || user.isSuperAdmin();
    }

    private boolean withinTenantBoundary(RbacUserInfo user, Serializable tenantId) {
        return user.isPlatformUser() || Objects.equals(scopeId(user.getTenantId()), scopeId(tenantId));
    }

    private boolean hasOrgAdminScope(RbacUserInfo user, Serializable tenantId) {
        return withinTenantBoundary(user, tenantId) && (isGlobalScopeAdmin(user)
                || (user.isTenantAdmin() && scopeId(user.getTenantId()) != null
                && Objects.equals(scopeId(user.getTenantId()), scopeId(tenantId))));
    }

    private boolean declaresAllOrg(DataScope scope) {
        return scope.getOrgScopeList().contains(DataScope.StartOrg.AllRoot.getExpression() + "|"
                + DataScope.OrgMatchingMode.SelfAndAllChild.getExpression())
                && scope.getDeniedOrgScopeList().isEmpty();
    }

    private boolean coversAllExistingOrg(RbacUserInfo user, DataScope scope, Serializable tenantId) {
        final Map<String, RbacOrgInfo> orgMap = scopedOrgMap(tenantId, true);
        // 即使声明了所有根节点，孤儿节点或孤立环也不在任何根树内，不能据此跳过数据过滤。
        for (RbacOrgInfo org : orgMap.values()) {
            if (!orgDomainAllowed(scope, tenantId, org)) return false;
        }
        return hasOrgAdminScope(user, tenantId)
                || accessibleOrgIds(user, scope, tenantId, orgMap).containsAll(orgMap.keySet());
    }

    private <TENANT extends RbacTenantInfo> Collection<TENANT> loadAllTenantListSafe(boolean onlyEffective) {
        return (Collection<TENANT>) Optional.ofNullable(loadAllTenantList(onlyEffective)).orElse(Collections.emptyList())
                .stream().filter(Objects::nonNull).filter(RbacCoreObject::selfAudit).collect(Collectors.toList());
    }

    private boolean canAccessTenant(RbacUserInfo user, DataScope scope, Serializable tenantId, RbacTenantInfo tenant) {
        if (!withinTenantBoundary(user, tenantId)) {
            return false;
        }
        return canAccessTenantWithinBoundary(user, scope, tenantId, tenant, isGlobalScopeAdmin(user));
    }

    private boolean canAccessTenantWithinBoundary(RbacUserInfo user, DataScope scope, Serializable tenantId, RbacTenantInfo tenant, boolean globalAdmin) {
        if (!globalAdmin && (scope.getTenantScopeList().isEmpty() || deniesAllTenants(scope)
                || matchesStaticTenantRules(scope.getDeniedTenantScopeList(), user, tenantId))) {
            return false;
        }
        if (scopeId(tenantId) != null) {
            final DomainAccess domains = domainAccess(scope);
            if (tenant != null) domains.rememberTenant(scopeId(tenantId), tenant);
            tenant = tenant != null ? tenant : domains.tenant(scopeId(tenantId), id -> loadTenant(id));
            if (tenant == null || !tenant.selfAudit() || !Objects.equals(scopeId(tenant.getId()), scopeId(tenantId))) {
                return false;
            }
        }
        if (tenant != null && !domainAccess(scope).allowsObject(tenant)) return false;
        return globalAdmin || (!matchesTenantGroovyRules(scope.getDeniedTenantScopeList(), user, tenant)
                && matchesTenantRules(scope.getTenantScopeList(), user, tenantId, tenant));
    }

    /** 这里只枚举真实租户；无租户数据不参与枚举。 */
    private boolean hasNoEnumerableTenantScope(RbacUserInfo user, DataScope scope) {
        return !isGlobalScopeAdmin(user) && (scope.getTenantScopeList().isEmpty()
                || deniesAllTenants(scope)
                || (!user.isPlatformUser()
                && matchesStaticTenantRules(scope.getDeniedTenantScopeList(), user, user.getTenantId())));
    }

    private boolean matchesStaticTenantRules(Set<String> rules, RbacUserInfo user, Serializable tenantId) {
        final String target = scopeId(tenantId);
        return (target != null && (rules.contains(DataScope.TenantScope.All.getExpression())
                || (!target.startsWith(DataScope.TenantScope.Groovy.getExpression())
                && !target.equals(DataScope.TenantScope.None.getExpression())
                && !target.equals(DataScope.TenantScope.Default.getExpression()) && rules.contains(target))))
                || (target == null && (rules.contains(DataScope.TenantScope.All.getExpression())
                || rules.contains(DataScope.TenantScope.None.getExpression())))
                || (Objects.equals(scopeId(user.getTenantId()), target)
                && rules.contains(DataScope.TenantScope.Default.getExpression()));
    }

    /** 拒绝所有是租户范围的吸收规则，允许规则及其他拒绝规则均不再需要匹配。 */
    private boolean deniesAllTenants(DataScope scope) {
        return scope.getDeniedTenantScopeList().contains(DataScope.TenantScope.All.getExpression());
    }

    private boolean matchesTenantRules(Set<String> rules, RbacUserInfo user, Serializable tenantId, RbacTenantInfo tenant) {
        if (matchesStaticTenantRules(rules, user, tenantId)) return true;
        return matchesTenantGroovyRules(rules, user, tenant);
    }

    /** 静态拒绝规则已在加载目标租户前处理，此处只保留无法预先抵消的动态规则。 */
    private boolean matchesTenantGroovyRules(Set<String> rules, RbacUserInfo user, RbacTenantInfo tenant) {
        for (String rule : rules) {
            if (rule.startsWith(DataScope.TenantScope.Groovy.getExpression())) {
                Map<String, Object> context = new LinkedHashMap<>();
                context.put("_user", user);
                context.put("_tenant", tenant);
                if (evalScopeGroovy(rule.substring(DataScope.TenantScope.Groovy.getExpression().length()), context)) return true;
            }
        }
        return false;
    }

    private <ORG extends RbacOrgInfo> Map<String, ORG> scopedOrgMap(Serializable tenantId, boolean onlyEffective) {
        final Map<String, ORG> result = new LinkedHashMap<>();
        for (ORG org : this.<ORG>loadTenantOrgListSafe(tenantId, onlyEffective)) {
            if (scopeId(org.getId()) != null && Objects.equals(scopeId(tenantId), scopeId(org.getTenantId()))) {
                result.putIfAbsent(scopeId(org.getId()), org);
            }
        }
        return result;
    }

    private <ORG extends RbacOrgInfo> Collection<ORG> loadTenantOrgListSafe(Serializable tenantId, boolean onlyEffective) {
        return (Collection<ORG>) Optional.ofNullable(loadTenantOrgList(tenantId, onlyEffective)).orElse(Collections.emptyList())
                .stream().filter(Objects::nonNull).filter(RbacCoreObject::selfAudit).collect(Collectors.toList());
    }

    private boolean matchesNoOrg(Set<String> rules, RbacUserInfo user, Serializable tenantId) {
        for (String rule : rules) {
            DataScope.OrgScope parsed = DataScope.OrgScope.parse(rule);
            if (DataScope.StartOrg.None.getExpression().equals(parsed.startOrg())
                    || (DataScope.StartOrg.Default.getExpression().equals(parsed.startOrg())
                    && scopeId(user.getOrgId()) == null
                    && Objects.equals(scopeId(user.getTenantId()), scopeId(tenantId)))) {
                return true;
            }
        }
        return false;
    }

    private <ORG extends RbacOrgInfo> Set<String> accessibleOrgIds(RbacUserInfo user, DataScope scope,
                                                                  Serializable tenantId, Map<String, ORG> orgMap) {
        return accessibleOrgIds(user, scope, tenantId, orgMap, null);
    }

    private <ORG extends RbacOrgInfo> Set<String> accessibleOrgIds(RbacUserInfo user, DataScope scope,
                                                                  Serializable tenantId, Map<String, ORG> orgMap,
                                                                  String requiredOrgId) {
        if (orgMap.isEmpty() || scope.getOrgScopeList().isEmpty()) return Collections.emptySet();
        final Map<String, List<String>> children = buildChildrenByParentId(orgMap);
        final Set<String> nonTargets;
        if (requiredOrgId == null) {
            nonTargets = Collections.emptySet();
        } else {
            nonTargets = new HashSet<>(orgMap.keySet());
            nonTargets.remove(requiredOrgId);
        }
        final Set<String> denied = matchedOrgIds(scope.getDeniedOrgScopeList(), user, tenantId,
                orgMap, children, nonTargets);
        if (requiredOrgId != null) {
            if (denied.contains(requiredOrgId)) return Collections.emptySet();
            // 允许和拒绝均只求值目标节点，完整树仍用于计算路径和祖先关系。
            denied.addAll(nonTargets);
        }
        // AllRoot 不覆盖孤儿或孤立环，必须确认实际候选被拒绝后才能直接返回。
        if (denied.size() == orgMap.size()) return Collections.emptySet();
        return matchedOrgIds(scope.getOrgScopeList(), user, tenantId, orgMap, children, denied);
    }

    private <ORG extends RbacOrgInfo> Set<String> matchedOrgIds(Set<String> rules, RbacUserInfo user,
                                                               Serializable tenantId, Map<String, ORG> orgMap,
                                                               Map<String, List<String>> children, Set<String> excluded) {
        final Set<String> matched = new LinkedHashSet<>();
        final List<DataScope.OrgScope> ordered = rules.stream().map(DataScope.OrgScope::parse)
                .sorted(Comparator.comparingInt(scope -> scope.orgMatchingMode().startsWith(DataScope.OrgMatchingMode.Groovy.getExpression()) ? 2
                        : scope.orgMatchingMode().contains("#") ? 1 : 0))
                .collect(Collectors.toList());
        String cachedRoot = null;
        Set<String> candidates = Collections.emptySet();
        OrgScopePaths paths = null;
        for (DataScope.OrgScope scope : ordered) {
            final String start = scope.startOrg();
            if (DataScope.StartOrg.None.getExpression().equals(start)) continue;
            final Set<String> roots = new LinkedHashSet<>();
            if (DataScope.StartOrg.Default.getExpression().equals(start)) {
                if (Objects.equals(scopeId(user.getTenantId()), scopeId(tenantId)) && scopeId(user.getOrgId()) != null) {
                    roots.add(scopeId(user.getOrgId()));
                }
            } else if (DataScope.StartOrg.AllRoot.getExpression().equals(start)) {
                orgMap.forEach((id, org) -> {
                    if (scopeId(org.getParentId()) == null) roots.add(id);
                });
            } else {
                roots.add(start);
            }
            final String mode = scope.orgMatchingMode();
            // 同一规则只拆分一次；路径编译仍在首个未排除的候选匹配时发生。
            final boolean namePath = mode.startsWith(DataScope.OrgMatchingMode.NamePath.getExpression());
            final String pathExpression = namePath
                    ? mode.substring(DataScope.OrgMatchingMode.NamePath.getExpression().length())
                    : mode.startsWith(DataScope.OrgMatchingMode.IdPath.getExpression())
                    ? mode.substring(DataScope.OrgMatchingMode.IdPath.getExpression().length()) : null;
            for (String root : roots) {
                if (!orgMap.containsKey(root)) continue;
                if (DataScope.OrgMatchingMode.Self.getExpression().equals(mode)) {
                    matched.add(root);
                } else if (DataScope.OrgMatchingMode.DirectChild.getExpression().equals(mode)) {
                    matched.addAll(children.getOrDefault(root, Collections.emptyList()));
                } else if (DataScope.OrgMatchingMode.SelfAndDirectChild.getExpression().equals(mode)) {
                    matched.add(root);
                    matched.addAll(children.getOrDefault(root, Collections.emptyList()));
                } else {
                    if (!root.equals(cachedRoot)) {
                        candidates = collectDescendantOrgIds(Collections.singleton(root), orgMap, children);
                        validateSelectedOrgTreeAcyclic(candidates, orgMap);
                        cachedRoot = root;
                        paths = null;
                    }
                    if (DataScope.OrgMatchingMode.SelfAndAllChild.getExpression().equals(mode)) {
                        matched.addAll(candidates);
                    } else {
                        if (paths == null) paths = new OrgScopePaths(root, orgMap);
                        for (String id : candidates) {
                            if (excluded.contains(id) || matched.contains(id)) continue;
                            if (matchesOrgExpression(mode, pathExpression, user, scope, orgMap.get(root), orgMap.get(id), paths)) {
                                matched.add(id);
                            }
                        }
                    }
                }
            }
            matched.removeAll(excluded);
            if (matched.size() + excluded.size() == orgMap.size()) break;
        }
        return matched;
    }

    private boolean matchesOrgExpression(String mode, String pathExpression, RbacUserInfo user, DataScope.OrgScope scope,
                                           RbacOrgInfo root, RbacOrgInfo org, OrgScopePaths paths) {
        if (mode.startsWith(DataScope.OrgMatchingMode.IdPath.getExpression())) {
            return paths.matches(pathExpression, scopeId(org.getId()), false);
        }
        if (mode.startsWith(DataScope.OrgMatchingMode.NamePath.getExpression())) {
            return paths.matches(pathExpression, scopeId(org.getId()), true);
        }
        Assert.isTrue(mode.startsWith(DataScope.OrgMatchingMode.Groovy.getExpression()), "无效的组织匹配模式[{}]", mode);
        Map<String, Object> context = new LinkedHashMap<>();
        context.put("_user", user);
        context.put("_org", org);
        context.put("_rootOrg", root);
        context.put("_scope", scope);
        context.put("_relativeIdPath", paths.path(scopeId(org.getId()), false));
        context.put("_relativeNamePath", paths.path(scopeId(org.getId()), true));
        return evalScopeGroovy(mode.substring(DataScope.OrgMatchingMode.Groovy.getExpression().length()), context);
    }

    private boolean evalScopeGroovy(String expression, Map<String, Object> context) {
        Assert.isTrue(StrUtil.isNotBlank(expression), "Groovy范围表达式不能为空");
        return Boolean.TRUE.equals(ExpressionUtils.evalGroovy(ORG_SCOPE_GROOVY_CLASS_CACHE, null, expression,
                "data-scope-" + Integer.toHexString(expression.hashCode()) + ".groovy", context));
    }

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
    @Operation(summary = "复制组树用组织节点", description = "复制组织节点供组树使用，避免修改调用方传入的扁平组织对象；默认通过 AOP 脱壳、BeanUtils 和反射复制。子类可用构造器或 mapper 优化，但不得返回原对象或改变树组装结果。")
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
