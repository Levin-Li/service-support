package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 可信服务端事务内读取的角色分配目录。调用方必须以权威数据库完成无截断查询并独立计数，
 * 且不得接受客户端提供的目录或数量；本对象不能自行证明数据库查询完整或事务隔离。
 */
public record RbacRoleAssignmentSnapshot(
        RbacTenantInfo tenant,
        Collection<? extends RbacDomainInfo> domains, long domainCount,
        Collection<? extends RbacRoleInfo> roles, long roleCount,
        Collection<? extends RbacOrgInfo> organizations, long organizationCount) {

    public RbacRoleAssignmentSnapshot {
        // 固定集合结构，防止调用期间列表被修改；对象本身由事务中的可信调用方负责稳定性。
        if (domains != null) domains = List.copyOf(domains);
        if (roles != null) roles = List.copyOf(roles);
        if (organizations != null) organizations = List.copyOf(organizations);
    }

    /** 检查结构；完整来源、独立计数和事务一致性由可信调用方保证。 */
    void validateFor(RbacUserInfo target) {
        Assert.notNull(target, "目标用户不能为空");
        String tenant = Objects.toString(target.getTenantId(), null);
        String orgId = Objects.toString(target.getOrgId(), null);
        Assert.isTrue(target.isTenantUser() && StrUtil.isNotBlank(tenant) && StrUtil.isNotBlank(orgId), "快照只支持具有真实组织的租户用户");
        Assert.isTrue(this.tenant() != null && this.tenant().selfAudit()
                && Objects.equals(tenant, Objects.toString(this.tenant().getId(), null)), "快照目标租户不存在或不匹配");
        Assert.isTrue(this.domains() != null && this.domainCount() >= 0 && this.domains().size() == this.domainCount(), "领域快照缺失或不完整");
        Assert.isTrue(this.roles() != null && this.roleCount() >= 0 && this.roles().size() == this.roleCount(), "角色快照缺失或不完整");
        Assert.isTrue(this.organizations() != null && this.organizationCount() > 0 && this.organizations().size() == this.organizationCount(), "组织快照缺失或不完整");
        Set<String> domainIds = new HashSet<>();
        for (RbacDomainInfo domain : this.domains()) {
            Assert.isTrue(domain != null && domain.getId() != null && domainIds.add(domain.getId().toString()), "领域快照存在空值或重复");
        }
        Assert.isTrue(StrUtil.isBlank(target.getDomainId()) || domainIds.contains(target.getDomainId()), "目标用户领域不在快照中");
        Set<String> roleKeys = new HashSet<>();
        for (RbacRoleInfo role : this.roles()) {
            Assert.isTrue(role != null && StrUtil.isNotBlank(role.getCode()), "角色快照存在空值");
            String roleTenant = Objects.toString(role.getTenantId(), null);
            Assert.isTrue(StrUtil.isBlank(roleTenant) || tenant.equals(roleTenant), "角色快照包含其他租户");
            Assert.isTrue(roleKeys.add(Objects.toString(roleTenant, "") + "\u0000" + role.getCode()), "角色快照存在同作用域重复编码");
        }
        Set<String> orgIds = new HashSet<>();
        for (RbacOrgInfo org : this.organizations()) {
            Assert.isTrue(org != null && org.getId() != null && tenant.equals(Objects.toString(org.getTenantId(), null)), "组织快照包含空值或其他租户");
            Assert.isTrue(orgIds.add(org.getId().toString()), "组织快照存在重复节点");
        }
        Assert.isTrue(orgIds.contains(orgId), "组织快照缺少目标组织");
        // 目标及父链必须完整；缺失父节点或循环无法安全解释组织范围。
        Map<String, RbacOrgInfo> byId = this.organizations().stream().collect(Collectors.toMap(o -> o.getId().toString(), Function.identity()));
        Set<String> visited = new HashSet<>();
        String cursor = orgId;
        while (StrUtil.isNotBlank(cursor)) {
            Assert.isTrue(visited.add(cursor), "组织快照包含父级循环");
            RbacOrgInfo org = byId.get(cursor);
            Assert.isTrue(org != null && org.selfAudit(), "目标组织或其父级不可用");
            cursor = Objects.toString(org.getParentId(), null);
        }
    }

    void requireMatchingCachedBoundaries(RbacBaseService service) {
        RbacTenantInfo oldTenant = service.loadTenant(tenant.getId());
        Assert.isTrue(oldTenant != null && Objects.equals(oldTenant.getId(), tenant.getId())
                && Objects.equals(oldTenant.getDomainId(), tenant.getDomainId())
                && Objects.equals(oldTenant.getConfidentialLevel(), tenant.getConfidentialLevel())
                && oldTenant.selfAudit() == tenant.selfAudit(), "租户缓存与事务快照不一致");
        Set<String> ids = new HashSet<>();
        for (RbacDomainInfo domain : domains) {
            ids.add(domain.getId().toString());
            RbacDomainInfo old = service.loadDomain(domain.getId());
            Assert.isTrue(old != null && Objects.equals(old.getId(), domain.getId())
                    && old.selfAudit() == domain.selfAudit(), "领域缓存与事务快照不一致");
        }
        Assert.isTrue(StrUtil.isBlank(tenant.getDomainId()) || ids.contains(tenant.getDomainId()), "租户领域不在快照中");
        for (RbacOrgInfo org : organizations) {
            Assert.isTrue(StrUtil.isBlank(org.getDomainId()) || ids.contains(org.getDomainId()), "组织领域不在快照中");
        }
        for (RbacRoleInfo role : roles) {
            Assert.isTrue(StrUtil.isBlank(role.getDomainId()) || ids.contains(role.getDomainId()), "角色领域不在快照中");
        }
    }

    static void requireUniqueCachedRoles(Collection<? extends RbacRoleInfo> directory, String tenant,
                                         Set<String> requestedCodes) {
        Assert.notNull(directory, "角色缓存缺失");
        Set<String> seen = new HashSet<>();
        for (RbacRoleInfo role : directory) {
            if (role == null || !requestedCodes.contains(role.getCode())) continue;
            String roleTenant = Objects.toString(role.getTenantId(), "");
            if (StrUtil.isNotBlank(roleTenant) && !Objects.equals(roleTenant, tenant)) continue;
            Assert.isTrue(seen.add(roleTenant + "\u0000" + role.getCode()), "角色缓存存在同作用域重复编码");
        }
    }

    /** 比较框架可见的角色安全字段；宿主扩展字段的并发控制由调用方负责。 */
    static void requireMatchingRoles(Collection<? extends RbacRoleInfo> cached,
                                     Collection<? extends RbacRoleInfo> authoritative) {
        Map<String, RbacRoleInfo> byCode = new HashMap<>();
        for (RbacRoleInfo role : cached) {
            Assert.isTrue(byCode.put(role.getCode(), role) == null, "缓存角色编码重复");
        }
        Assert.isTrue(byCode.size() == authoritative.size(), "角色缓存与事务快照不一致");
        for (RbacRoleInfo role : authoritative) {
            RbacRoleInfo old = byCode.get(role.getCode());
            Assert.isTrue(old != null && roleFacts(old).equals(roleFacts(role)), "角色缓存与事务快照不一致：{}", role.getCode());
        }
    }

    private static List<Object> roleFacts(RbacRoleInfo role) {
        return Arrays.asList(role.getId(), role.getTenantId(), role.getCode(), role.getName(), role.getType(),
                role.getState(), role.isEnable(), role.getExpiredTime(), role.getDomainId(), role.getConfidentialLevel(),
                role.getConfidentialDataAccessLevel(), role.getAssignPreCondition(), role.getPermissionList(),
                role.getExclusiveRoleList(), role.getCoexistRoleList(), role.getTenantScopeList(),
                role.getDeniedTenantScopeList(), role.getDomainScopeList(), role.getDeniedDomainScopeList(),
                role.getOrgScopeList(), role.getDeniedOrgScopeList());
    }

}
