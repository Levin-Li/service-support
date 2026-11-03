package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import java.time.temporal.TemporalAccessor;
import java.util.*;

/** Framework-visible fields only; does not prove immutability of host extensions. */
final class RbacStrictFactFingerprint {
    private RbacStrictFactFingerprint() { }

    static List<Object> capture(RbacStrictAssignmentView view) {
        List<Object> values = new ArrayList<>();
        values.add(user(view.actor())); values.add(user(view.target()));
        values.add(core(view.targetSnapshot().tenant()));
        for (RbacDomainInfo d : view.targetSnapshot().domains()) values.add(core(d));
        for (RbacRoleInfo r : view.targetSnapshot().roles()) values.add(role(r));
        for (RbacOrgInfo o : view.targetSnapshot().organizations()) values.add(org(o));
        for (RbacTenantInfo t : view.tenantsForFingerprint()) values.add(core(t));
        for (RbacRoleInfo r : view.actorRolesForFingerprint()) values.add(role(r));
        return List.copyOf(values);
    }

    static List<Object> captureIssuer(RbacStrictIssuerView view) {
        List<Object> values = new ArrayList<>(); values.add(user(view.actor())); values.add(core(view.targetTenant()));
        for (RbacTenantInfo t : view.tenants()) values.add(core(t));
        for (RbacDomainInfo d : view.domains()) values.add(core(d));
        for (RbacOrgInfo o : view.targetOrgs()) values.add(org(o));
        for (RbacRoleInfo r : view.actorRoles()) values.add(role(r));
        for (RbacRoleInfo r : view.targetRoles()) values.add(role(r));
        return List.copyOf(values);
    }

    static List<Object> core(RbacCoreObject o) {
        Assert.isTrue(o.getTransientExInfo() == null || o.getTransientExInfo().isEmpty(), "严格权限快照不支持未审计的临时扩展信息");
        List<Object> v = new ArrayList<>();
        add(v, o.getId(), o.getType(), o.getName(), o.getState(), o.isEnable(), o.getExpiredTime(),
                o.isEditable(), o.getConfidentialLevel(), o.selfAudit());
        if (o instanceof com.levin.commons.dao.domain.DomainObject d) add(v, d.getDomainId());
        return Collections.unmodifiableList(v);
    }
    private static List<Object> scope(DataScope s) {
        List<Object> v = new ArrayList<>();
        add(v, s.getTenantScopeList(), s.getDeniedTenantScopeList(), s.getDomainScopeList(), s.getDeniedDomainScopeList(),
                s.getOrgScopeList(), s.getDeniedOrgScopeList(), s.getConfidentialDataAccessLevel());
        return Collections.unmodifiableList(v);
    }
    private static List<Object> user(RbacUserInfo u) {
        List<Object> v = new ArrayList<>();
        add(v, core(u), scope(u), u.getTenantId(), u.getOrgId(), u.getLoginName(), u.getType(), u.getRoleList(),
                u.getEmail(), u.getTelephone(), u.getNickname(), u.getAvatar(), u.isPlatformUser(), u.isTenantUser(),
                u.isSuperAdmin(), u.isTopSuperAdmin(), u.isPlatformAdmin(), u.isTenantAdmin());
        return Collections.unmodifiableList(v);
    }
    private static List<Object> role(RbacRoleInfo r) {
        List<Object> v = new ArrayList<>();
        add(v, core(r), scope(r), r.getTenantId(), r.getCode(), r.getAssignPreCondition(), r.getExclusiveRoleList(),
                r.getCoexistRoleList(), r.getPermissionList());
        return Collections.unmodifiableList(v);
    }
    private static List<Object> org(RbacOrgInfo o) {
        List<Object> v = new ArrayList<>(); add(v, core(o), o.getTenantId(), o.getParentId(), o.getNodePath());
        return Collections.unmodifiableList(v);
    }
    private static void add(List<Object> dest, Object... values) {
        for (Object v : values) dest.add(freeze(v));
    }
    private static Object freeze(Object v) {
        if (v instanceof Number) return v.getClass().getName() + ":" + v;
        if (v == null || v instanceof String || v instanceof Boolean || v instanceof Character
                || v instanceof UUID || v instanceof Enum<?> || v instanceof TemporalAccessor) return v;
        if (v instanceof Set<?> s) {
            Set<Object> out = new HashSet<>(); for (Object x : s) out.add(freeze(x));
            return Collections.unmodifiableSet(out);
        }
        if (v instanceof Collection<?> c) {
            List<Object> out = new ArrayList<>(); for (Object x : c) out.add(freeze(x));
            return Collections.unmodifiableList(out);
        }
        throw new IllegalArgumentException("严格权限快照包含不支持的可变字段类型: " + v.getClass().getName());
    }
}
