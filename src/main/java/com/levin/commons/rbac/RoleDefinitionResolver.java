package com.levin.commons.rbac;

import java.io.Serializable;
import java.util.*;

/**
 * 按使用者的租户选择角色定义，不做领域或密级过滤，供授权初始化与分配校验共用。
 */
final class RoleDefinitionResolver {
    private RoleDefinitionResolver() {
    }

    static <R extends RbacRoleInfo> List<R> select(Serializable tenantId, Collection<R> definitions,
                                                   Collection<?> requestedCodes) {
        if (definitions == null || definitions.isEmpty()) return Collections.emptyList();
        String tenant = Objects.toString(tenantId, null);
        Map<String, R> byCode = new LinkedHashMap<>();
        for (R role : definitions) {
            if (role == null || !role.selfAudit() || RbacMiscUtils.isBlank(role.getCode())) continue;
            String roleTenant = Objects.toString(role.getTenantId(), null);
            boolean local = Objects.equals(tenant, roleTenant);
            if (!local && !RbacMiscUtils.isBlank(role.getTenantId())) continue;
            byCode.merge(role.getCode(), role, (current, incoming) -> local ? incoming : current);
        }
        if (requestedCodes == null) return new ArrayList<>(byCode.values());
        List<R> result = new ArrayList<>();
        for (Object code : new LinkedHashSet<>(requestedCodes)) {
            if (code == null) continue;
            R role = byCode.get(code.toString());
            if (role != null) result.add(role);
        }
        return result;
    }
}
