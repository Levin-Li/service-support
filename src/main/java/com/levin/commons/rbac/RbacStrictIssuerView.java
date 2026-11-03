package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import java.io.Serializable;
import java.util.*;

/** Actor-only trusted transaction directory; no future target user or organisation is fabricated. */
public final class RbacStrictIssuerView implements RbacBaseService {
    private final RbacUserInfo actor;
    private final RbacTenantInfo targetTenant;
    private final List<RbacTenantInfo> tenants;
    private final List<RbacDomainInfo> domains;
    private final List<RbacOrgInfo> targetOrgs;
    private final List<RbacRoleInfo> actorRoles, targetRoles;
    private final List<Object> fingerprint;

    public RbacStrictIssuerView(RbacUserInfo actor, RbacTenantInfo targetTenant,
            Collection<? extends RbacTenantInfo> tenants, long tenantCount,
            Collection<? extends RbacDomainInfo> domains, long domainCount,
            Collection<? extends RbacOrgInfo> targetOrgs, long orgCount,
            Collection<? extends RbacRoleInfo> actorRoles, long actorRoleCount,
            Collection<? extends RbacRoleInfo> targetRoles, long targetRoleCount) {
        Assert.notNull(actor, "签发人不能为空"); Assert.notNull(targetTenant, "目标租户不能为空");
        Assert.isTrue(targetTenant.getId() != null && targetTenant.selfAudit(), "目标租户不可用");
        Assert.isTrue(tenants != null && tenants.size() == tenantCount && tenantCount > 0, "租户目录不完整");
        Assert.isTrue(domains != null && domains.size() == domainCount, "领域目录不完整");
        Assert.isTrue(targetOrgs != null && targetOrgs.size() == orgCount, "组织目录不完整");
        Assert.isTrue(actorRoles != null && actorRoles.size() == actorRoleCount, "签发人角色目录不完整");
        Assert.isTrue(targetRoles != null && targetRoles.size() == targetRoleCount, "目标角色目录不完整");
        this.actor=actor; this.targetTenant=targetTenant; this.tenants=List.copyOf(tenants);
        this.domains=List.copyOf(domains); this.targetOrgs=List.copyOf(targetOrgs);
        this.actorRoles=List.copyOf(actorRoles); this.targetRoles=List.copyOf(targetRoles);
        Set<String> ids = new HashSet<>();
        for (RbacTenantInfo t : this.tenants) Assert.isTrue(t != null && t.getId() != null && ids.add(t.getId().toString()), "租户目录重复");
        Assert.isTrue(ids.contains(targetTenant.getId().toString()), "目标租户缺失");
        RbacTenantInfo listed = this.tenants.stream().filter(t -> Objects.equals(t.getId(), targetTenant.getId())).findFirst().orElseThrow();
        Assert.isTrue(RbacStrictFactFingerprint.core(listed).equals(RbacStrictFactFingerprint.core(targetTenant)), "目标租户事实不一致");
        if (actor.getTenantId() != null) Assert.isTrue(ids.contains(actor.getTenantId().toString()), "签发人租户缺失");
        ids.clear();
        for (RbacDomainInfo d : this.domains) Assert.isTrue(d != null && d.getId()!=null && ids.add(d.getId().toString()), "领域目录重复");
        ids.clear();
        for (RbacOrgInfo o : this.targetOrgs) Assert.isTrue(o != null && o.getId()!=null && Objects.equals(o.getTenantId(), targetTenant.getId()) && ids.add(o.getId().toString()), "组织重复或跨租户");
        validateRoles(this.actorRoles, actor.getTenantId()); validateRoles(this.targetRoles, targetTenant.getId());
        Collection<String> codes = actor.getRoleList() == null ? List.of() : actor.getRoleList();
        List<RbacRoleInfo> selected = RoleDefinitionResolver.select(actor.getTenantId(), this.actorRoles, codes);
        Set<String> valid = new HashSet<>(); for (RbacRoleInfo r : selected) valid.add(r.getCode());
        for (String admin : List.of(RbacRoleInfo.PLATFORM_SA, RbacRoleInfo.PLATFORM_ADMIN, RbacRoleInfo.TENANT_ADMIN))
            Assert.isTrue(!codes.contains(admin) || valid.contains(admin), "签发人管理员角色无有效定义");
        if (Objects.equals(actor.getTenantId(), targetTenant.getId()))
            RbacRoleAssignmentSnapshot.requireMatchingRoles(selected, RoleDefinitionResolver.select(actor.getTenantId(), this.targetRoles, codes));
        this.fingerprint = RbacStrictFactFingerprint.captureIssuer(this);
    }
    private static void validateRoles(List<RbacRoleInfo> roles, Object tenant) {
        Set<String> keys = new HashSet<>();
        for (RbacRoleInfo r : roles) {
            Assert.isTrue(r != null && r.getCode()!=null, "角色缺失");
            String id=Objects.toString(r.getTenantId(), "");
            Assert.isTrue(id.isEmpty() || id.equals(Objects.toString(tenant, "")), "角色跨租户");
            Assert.isTrue(keys.add(id + "\u0000" + r.getCode()), "角色重复");
        }
    }
    /** Resolve effective target-tenant definitions using the same local-over-public rules as RBAC. */
    public List<RbacRoleInfo> resolveTargetRoles(Collection<String> codes) {
        Assert.notNull(codes, "目标角色编码不能为空");
        verifyFactsUnchanged();
        Set<String> requested = new LinkedHashSet<>(codes);
        Assert.isTrue(!requested.contains(null) && requested.stream().noneMatch(String::isBlank), "目标角色编码无效");
        List<RbacRoleInfo> result = RoleDefinitionResolver.select(targetTenant.getId(), targetRoles, requested);
        Assert.isTrue(result.stream().map(RbacRoleInfo::getCode).collect(java.util.stream.Collectors.toSet()).containsAll(requested),
                "目标角色缺失或无有效定义");
        verifyFactsUnchanged();
        return List.copyOf(result);
    }

    public RbacUserInfo actor() { return actor; }
    public RbacTenantInfo targetTenant() { return targetTenant; }
    public List<RbacRoleInfo> targetRoles() { return targetRoles; }
    List<RbacRoleInfo> actorRoles() { return actorRoles; }
    List<RbacTenantInfo> tenants() { return tenants; }
    List<RbacDomainInfo> domains() { return domains; }
    List<RbacOrgInfo> targetOrgs() { return targetOrgs; }
    void rejectDynamicRules(Collection<? extends RbacRoleInfo> selectedTargetRoles) {
        List<DataScope> scopes = new ArrayList<>(); scopes.add(actor);
        Collection<String> codes = actor.getRoleList() == null ? List.of() : actor.getRoleList();
        scopes.addAll(RoleDefinitionResolver.select(actor.getTenantId(), actorRoles, codes));
        scopes.addAll(selectedTargetRoles);
        RbacStrictAssignmentView.assertStaticScopes(scopes);
    }
    public void verifyFactsUnchanged() { Assert.isTrue(fingerprint.equals(RbacStrictFactFingerprint.captureIssuer(this)), "签发权限快照已被修改"); }
    @Override public String encryptUserPwd(String p) { throw new UnsupportedOperationException(); }
    @Override public <U extends RbacUserInfo> U loadUser(Serializable tenant, String account) { throw new UnsupportedOperationException(); }
    @Override public <U extends RbacUserInfo> U loadUser(Serializable principal) {
        if (principal == actor || Objects.equals(principal, actor.getId())) return (U) actor;
        throw new IllegalArgumentException("用户不在签发快照内");
    }
    @Override public <U extends RbacUserInfo> U auditUser(U u) { throw new UnsupportedOperationException(); }
    @Override public <U extends RbacUserInfo> U auditUserLogin(U u, Serializable t, String p, String d, String ip, String device, Map<String,Serializable> ex) { throw new UnsupportedOperationException(); }
    @Override public <T extends RbacTenantInfo> Collection<T> loadAllTenantList(boolean effective) { return (Collection<T>) tenants; }
    @Override public <T extends RbacTenantInfo> T loadTenant(Serializable id) { return (T) tenants.stream().filter(t -> Objects.equals(t.getId(),id)).findFirst().orElseThrow(() -> new IllegalArgumentException("租户不在快照内")); }
    @Override public <D extends RbacDomainInfo> Collection<D> loadAllDomainList(boolean effective) { return (Collection<D>) domains; }
    @Override public <D extends RbacDomainInfo> D loadDomain(Serializable id) { return (D) domains.stream().filter(d -> Objects.equals(d.getId(),id)).findFirst().orElse(null); }
    @Override public <O extends RbacOrgInfo> O loadOrg(Serializable id) { return (O) targetOrgs.stream().filter(o -> Objects.equals(o.getId(),id)).findFirst().orElseThrow(() -> new IllegalArgumentException("组织不在快照内")); }
    @Override public <O extends RbacOrgInfo> Collection<O> loadTenantOrgList(Serializable id, boolean effective) {
        Assert.isTrue(Objects.equals(id,targetTenant.getId()), "组织目录未覆盖该租户"); return (Collection<O>) targetOrgs;
    }
    @Override public <O extends RbacOrgInfo> O copyOrgNodeForAssembleTree(O org, String path) { throw new UnsupportedOperationException(); }
    @Override public <R extends RbacRoleInfo> R loadRole(Serializable id) { throw new UnsupportedOperationException(); }
    @Override public <R extends RbacRoleInfo> Collection<R> loadTenantRoleList(Serializable id, boolean effective) {
        if (Objects.equals(id,targetTenant.getId())) return (Collection<R>) targetRoles;
        if (Objects.equals(id,actor.getTenantId())) return (Collection<R>) actorRoles;
        throw new IllegalArgumentException("角色目录未覆盖该租户");
    }
}
