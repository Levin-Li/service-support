package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.dao.domain.ConfidentialObject;
import com.levin.commons.dao.domain.DomainObject;
import com.levin.commons.dao.domain.ProxyWrapperObject;
import com.levin.commons.utils.ExpressionUtils;
import io.swagger.v3.oas.annotations.Operation;
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
 * @author echo
 */
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
            return isGlobalScopeAdmin(user) && !user.isTopSuperAdmin()
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
    @Operation(summary = "加载租户", description = "加载指定租户")
    <TENANT extends RbacTenantInfo> TENANT loadTenant(Serializable tenantPrincipal);

    /** 加载业务领域目录，onlyLoadEffectDomain 指定是否只加载有效领域。 */
    @Operation(summary = "加载所有领域", description = "领域是业务领域或应用，不是域名；加载实现负责数据查询，可按有效状态预裁剪")
    <DOMAIN extends RbacDomainInfo> Collection<DOMAIN> loadAllDomainList(boolean onlyLoadEffectDomain);

    /** 加载指定领域，不存在时返回 null。 */
    @Operation(summary = "加载领域", description = "domainPrincipal 为领域ID或实现支持的领域标识")
    <DOMAIN extends RbacDomainInfo> DOMAIN loadDomain(Serializable domainPrincipal);

    /**
     * 加载用户范围内的有效领域。领域范围不依赖租户，不隐含资源动作或机密级别授权。
     * 子类可覆盖为按有效允许ID集合直接查询；默认仅加载一次目录，不逐项查询领域。
     */
    @Operation(summary = "加载用户可访问的领域列表", description = "先按允许集合减拒绝集合短路，再批量加载领域；过滤无效领域，保留数据源顺序")
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
            return isGlobalScopeAdmin(user) && !user.isTopSuperAdmin()
                    ? filterByScopedConfidentialAccess(scope, result) : result;
        });
    }

    /**
     * 加载“最大候选组织集合”。
     * 包含所有租户组织和无租户公共组织，供顶级超级管理员直接返回，也供普通超管/SaaS 管理员做机密级别过滤。
     */
    @Operation(summary = "加载最大候选组织集合", description = "性能扩展点：默认实现遍历所有租户并逐个加载组织；子类可覆盖为一次性批量查询或缓存读取，避免 N+1 加载。")
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

    @Operation(summary = "获取用户数据权限", description = "六个范围字段独立覆盖：用户非null（包括空集合）替代角色，null继承生效角色并集。返回不可变快照")
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
                for (ConfidentialObject target : Arrays.asList(tenant, parent, org)) {
                    Assert.isTrue(target == null || canAccessConfidentialData(() -> level, target.getConfidentialLevel()),
                            "目标租户或组织未授权");
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
    @Operation(summary = "获取用户的机密数据访问级别", description = "性能扩展点：当用户本身没有定义访问级别时默认会扫描用户生效角色；子类可覆盖为缓存字段或预聚合查询，尽量不要多次调用")
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

    @Operation(summary = "根据角色编码表达式加载角色列表", description = "性能扩展点：默认实现会加载租户角色列表后用 * 和 ? 通配表达式过滤；子类可覆盖为按 code 批量查询或缓存匹配。公共角色会并存")
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
    @Operation(summary = "加载用户生效角色列表", description = "性能扩展点：默认实现会加载租户角色列表后按用户角色 code 归并；子类可覆盖为用户-角色关联查询或缓存。内部授权计算使用，不做角色对象的机密级别过滤")
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
    @Operation(summary = "加载用户可访问的角色列表", description = "性能扩展点：默认实现基于生效角色再做机密级别过滤；子类可覆盖为已过滤缓存或数据库条件查询。默认按角色对象自身的机密级别做可见性过滤")
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
        return user.isTopSuperAdmin() || user.isSuperAdmin() || user.isSaasAdmin();
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
        if (!isGlobalScopeAdmin(user) && (scope.getTenantScopeList().isEmpty()
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
        return isGlobalScopeAdmin(user) || (!matchesTenantRules(scope.getDeniedTenantScopeList(), user, tenantId, tenant)
                && matchesTenantRules(scope.getTenantScopeList(), user, tenantId, tenant));
    }

    /** 这里只枚举真实租户；无租户数据不参与枚举。 */
    private boolean hasNoEnumerableTenantScope(RbacUserInfo user, DataScope scope) {
        return !isGlobalScopeAdmin(user) && (scope.getTenantScopeList().isEmpty()
                || scope.getDeniedTenantScopeList().contains(DataScope.TenantScope.All.getExpression())
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

    private boolean matchesTenantRules(Set<String> rules, RbacUserInfo user, Serializable tenantId, RbacTenantInfo tenant) {
        if (matchesStaticTenantRules(rules, user, tenantId)) return true;
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
                            if (matchesOrgExpression(mode, user, scope, orgMap.get(root), orgMap.get(id), paths)) {
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

    private boolean matchesOrgExpression(String mode, RbacUserInfo user, DataScope.OrgScope scope,
                                           RbacOrgInfo root, RbacOrgInfo org, OrgScopePaths paths) {
        if (mode.startsWith(DataScope.OrgMatchingMode.IdPath.getExpression())) {
            return paths.matches(mode.substring(DataScope.OrgMatchingMode.IdPath.getExpression().length()), scopeId(org.getId()), false);
        }
        if (mode.startsWith(DataScope.OrgMatchingMode.NamePath.getExpression())) {
            return paths.matches(mode.substring(DataScope.OrgMatchingMode.NamePath.getExpression().length()), scopeId(org.getId()), true);
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
