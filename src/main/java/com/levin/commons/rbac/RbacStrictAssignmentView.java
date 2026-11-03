package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import java.io.Serializable;
import java.util.*;

/** Only accepts directories read and stabilised by a trusted server transaction. */
public final class RbacStrictAssignmentView implements RbacBaseService {
    private final RbacUserInfo actor, target;
    private final RbacRoleAssignmentSnapshot targetSnapshot;
    private final List<RbacTenantInfo> tenants;
    private final List<RbacRoleInfo> actorRoles;
    private final List<Object> initialFingerprint;

    public RbacStrictAssignmentView(RbacUserInfo actor, RbacUserInfo target, RbacRoleAssignmentSnapshot targetSnapshot,
                                    Collection<? extends RbacTenantInfo> allTenants, long tenantCount,
                                    Collection<? extends RbacRoleInfo> actorRoles, long actorRoleCount) {
        Assert.notNull(actor, "签发人不能为空"); Assert.notNull(target, "目标用户不能为空");
        Assert.notNull(targetSnapshot, "目标快照不能为空"); targetSnapshot.validateFor(target);
        Assert.isTrue(allTenants != null && tenantCount == allTenants.size() && tenantCount > 0, "租户目录缺失或截断");
        Assert.isTrue(actorRoles != null && actorRoleCount == actorRoles.size(), "签发人角色目录缺失或截断");
        this.actor = actor; this.target = target; this.targetSnapshot = targetSnapshot;
        this.tenants = List.copyOf(allTenants); this.actorRoles = List.copyOf(actorRoles);
        Set<String> ids = new HashSet<>();
        for (RbacTenantInfo tenant : tenants) Assert.isTrue(tenant != null && tenant.getId() != null && ids.add(tenant.getId().toString()), "租户目录重复或缺失");
        Assert.isTrue(ids.contains(target.getTenantId().toString()), "目标租户缺失");
        if (actor.getTenantId() != null) Assert.isTrue(ids.contains(actor.getTenantId().toString()), "签发人租户缺失");
        Set<String> keys = new HashSet<>();
        for (RbacRoleInfo role : this.actorRoles) {
            Assert.isTrue(role != null && role.getCode() != null, "签发人角色为空");
            String tenant = Objects.toString(role.getTenantId(), "");
            Assert.isTrue(tenant.isEmpty() || Objects.equals(tenant, Objects.toString(actor.getTenantId(), "")), "签发人角色跨租户");
            Assert.isTrue(keys.add(tenant + "\u0000" + role.getCode()), "签发人角色重复");
        }
        Set<String> actorCodes = new HashSet<>(actor.getRoleList() == null ? List.of() : actor.getRoleList());
        List<RbacRoleInfo> selected = RoleDefinitionResolver.select(actor.getTenantId(), this.actorRoles, actorCodes);
        Set<String> selectedCodes = selected.stream().map(RbacRoleInfo::getCode).collect(java.util.stream.Collectors.toSet());
        for (String admin : List.of(RbacRoleInfo.PLATFORM_SA, RbacRoleInfo.PLATFORM_ADMIN, RbacRoleInfo.TENANT_ADMIN)) {
            Assert.isTrue(!actorCodes.contains(admin) || selectedCodes.contains(admin), "签发人管理员角色无有效定义");
        }
        if (Objects.equals(actor.getTenantId(), target.getTenantId())) {
            List<RbacRoleInfo> targetSelected = RoleDefinitionResolver.select(actor.getTenantId(), new ArrayList<RbacRoleInfo>(targetSnapshot.roles()), actorCodes);
            RbacRoleAssignmentSnapshot.requireMatchingRoles(selected, targetSelected);
        }
        this.initialFingerprint = RbacStrictFactFingerprint.capture(this);
    }
    List<RbacTenantInfo> tenantsForFingerprint() { return tenants; }
    List<RbacRoleInfo> actorRolesForFingerprint() { return actorRoles; }
    /** Detect persistent mutations of known fields; host must still supply independent immutable facts. */
    public void verifyFactsUnchanged() {
        Assert.isTrue(initialFingerprint.equals(RbacStrictFactFingerprint.capture(this)), "严格权限快照已被修改");
    }
    void rejectDynamicRules(Collection<? extends RbacRoleInfo> finalRoles) {
        List<DataScope> scopes = new ArrayList<>(); scopes.add(actor); scopes.add(target);
        Collection<String> actorCodes = actor.getRoleList() == null ? List.of() : actor.getRoleList();
        scopes.addAll(RoleDefinitionResolver.select(actor.getTenantId(), actorRoles, actorCodes));
        Set<String> targetCodes = new HashSet<>();
        if (finalRoles != null) for (RbacRoleInfo role : finalRoles) if (role != null) targetCodes.add(role.getCode());
        scopes.addAll(RoleDefinitionResolver.select(target.getTenantId(), new ArrayList<RbacRoleInfo>(targetSnapshot.roles()), targetCodes));
        assertStaticScopes(scopes);
    }
    static void assertStaticScopes(Collection<? extends DataScope> scopes) {
        for (DataScope scope : scopes) {
            for (Set<String> rules : Arrays.asList(scope.getTenantScopeList(), scope.getDeniedTenantScopeList(),
                    scope.getDomainScopeList(), scope.getDeniedDomainScopeList(), scope.getOrgScopeList(), scope.getDeniedOrgScopeList())) {
                if (rules != null) for (String rule : rules)
                    Assert.isTrue(rule == null || !rule.toLowerCase(Locale.ROOT).contains("groovy#"), "严格角色分配不支持动态范围脚本");
            }
            if (scope instanceof RbacRoleInfo role) Assert.isTrue(role.getAssignPreCondition() == null || role.getAssignPreCondition().isBlank(), "严格角色分配不支持动态分配前置条件");
        }
    }
    /** Resolve effective target-tenant definitions using the same local-over-public rules as RBAC. */
    public List<RbacRoleInfo> resolveTargetRoles(Collection<String> codes) {
        Assert.notNull(codes, "目标角色编码不能为空");
        verifyFactsUnchanged();
        Set<String> requested = new LinkedHashSet<>(codes);
        Assert.isTrue(!requested.contains(null) && requested.stream().noneMatch(String::isBlank), "目标角色编码无效");
        List<RbacRoleInfo> result = RoleDefinitionResolver.select(target.getTenantId(),
                new ArrayList<RbacRoleInfo>(targetSnapshot.roles()), requested);
        Assert.isTrue(result.stream().map(RbacRoleInfo::getCode).collect(java.util.stream.Collectors.toSet()).containsAll(requested),
                "目标角色缺失或无有效定义");
        verifyFactsUnchanged();
        return List.copyOf(result);
    }

    public RbacUserInfo actor() { return actor; }
    public RbacUserInfo target() { return target; }
    public RbacRoleAssignmentSnapshot targetSnapshot() { return targetSnapshot; }
    @Override public String encryptUserPwd(String p) { throw new UnsupportedOperationException(); }
    @Override public <U extends RbacUserInfo> U loadUser(Serializable tenant, String account) { throw new UnsupportedOperationException(); }
    @Override public <U extends RbacUserInfo> U loadUser(Serializable principal) {
        if (principal == actor || Objects.equals(principal, actor.getId())) return (U) actor;
        if (principal == target || Objects.equals(principal, target.getId())) return (U) target;
        throw new IllegalArgumentException("用户不在事务快照内");
    }
    @Override public <U extends RbacUserInfo> U auditUser(U u) { throw new UnsupportedOperationException(); }
    @Override public <U extends RbacUserInfo> U auditUserLogin(U u, Serializable t, String p, String d, String ip, String device, Map<String,Serializable> ex) { throw new UnsupportedOperationException(); }
    @Override public <T extends RbacTenantInfo> Collection<T> loadAllTenantList(boolean effective) { return (Collection<T>) tenants; }
    @Override public <T extends RbacTenantInfo> T loadTenant(Serializable id) {
        return (T) tenants.stream().filter(t -> Objects.equals(t.getId(), id)).findFirst().orElseThrow(() -> new IllegalArgumentException("租户不在事务快照内"));
    }
    @Override public <D extends RbacDomainInfo> Collection<D> loadAllDomainList(boolean effective) { return (Collection<D>) targetSnapshot.domains(); }
    @Override public <D extends RbacDomainInfo> D loadDomain(Serializable id) {
        return (D) targetSnapshot.domains().stream().filter(d -> Objects.equals(d.getId(), id)).findFirst().orElse(null);
    }
    @Override public <O extends RbacOrgInfo> O loadOrg(Serializable id) {
        return (O) targetSnapshot.organizations().stream().filter(o -> Objects.equals(o.getId(), id)).findFirst().orElseThrow(() -> new IllegalArgumentException("组织不在事务快照内"));
    }
    @Override public <O extends RbacOrgInfo> Collection<O> loadTenantOrgList(Serializable id, boolean effective) {
        Assert.isTrue(Objects.equals(id, target.getTenantId()), "组织目录未覆盖该租户");
        return (Collection<O>) targetSnapshot.organizations();
    }
    @Override public <O extends RbacOrgInfo> O copyOrgNodeForAssembleTree(O org, String path) { throw new UnsupportedOperationException(); }
    @Override public <R extends RbacRoleInfo> R loadRole(Serializable id) { throw new UnsupportedOperationException(); }
    @Override public <R extends RbacRoleInfo> Collection<R> loadTenantRoleList(Serializable id, boolean effective) {
        if (Objects.equals(id, target.getTenantId())) return (Collection<R>) targetSnapshot.roles();
        if (Objects.equals(id, actor.getTenantId())) return (Collection<R>) actorRoles;
        throw new IllegalArgumentException("角色目录未覆盖该租户");
    }
}
