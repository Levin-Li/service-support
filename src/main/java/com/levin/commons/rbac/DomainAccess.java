package com.levin.commons.rbac;

import com.levin.commons.dao.domain.DomainObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

/** 单次授权计算的领域授权与加载缓存，不跨用户或请求复用。 */
final class DomainAccess {
    private static final ThreadLocal<Evaluation> EVALUATIONS = new ThreadLocal<>();

    private static final class Evaluation {
        final Map<Key, DomainAccess> rules = new HashMap<>();
        final Map<Object, Map<DataScope, DomainAccess>> scopes = new IdentityHashMap<>();
    }

    /** 嵌套调用共享本次计算；最外层退出时无条件移除，异常也不留下授权缓存。 */
    static <T> T evaluate(Supplier<T> action) {
        if (EVALUATIONS.get() != null) return action.get();
        EVALUATIONS.set(new Evaluation());
        try {
            return action.get();
        } finally {
            EVALUATIONS.remove();
        }
    }

    static DomainAccess obtain(Object service, Set<String> allowed, Set<String> denied,
                               Function<String, RbacDomainInfo> loader) {
        Evaluation evaluation = EVALUATIONS.get();
        if (evaluation == null) return new DomainAccess(allowed, denied, loader);
        return evaluation.rules.computeIfAbsent(new Key(service, allowed, denied),
                ignored -> new DomainAccess(allowed, denied, loader));
    }

    static DomainAccess forScope(Object service, DataScope scope, Function<String, RbacDomainInfo> loader) {
        Evaluation evaluation = EVALUATIONS.get();
        if (evaluation == null) return obtain(service, scope.getDomainScopeList(), scope.getDeniedDomainScopeList(), loader);
        Map<DataScope, DomainAccess> scopes = evaluation.scopes.computeIfAbsent(service, ignored -> new IdentityHashMap<>());
        return scopes.computeIfAbsent(scope,
                ignored -> obtain(service, scope.getDomainScopeList(), scope.getDeniedDomainScopeList(), loader));
    }

    private record Key(Object service, Set<String> allowed, Set<String> denied) {
        @Override public boolean equals(Object other) {
            return other instanceof Key key && service == key.service
                    && allowed.equals(key.allowed) && denied.equals(key.denied);
        }
        @Override public int hashCode() {
            return 31 * (31 * System.identityHashCode(service) + allowed.hashCode()) + denied.hashCode();
        }
    }

    private final Set<String> allowed;
    private final Set<String> denied;
    private final Function<String, RbacDomainInfo> loader;
    private final Map<String, Boolean> decisions = new HashMap<>();
    private final Map<String, RbacTenantInfo> tenants = new HashMap<>();

    DomainAccess(Set<String> allowed, Set<String> denied, Function<String, RbacDomainInfo> loader) {
        this.allowed = allowed;
        this.denied = denied;
        this.loader = loader;
    }

    Set<String> permittedIds() {
        Set<String> result = new HashSet<>(allowed);
        result.removeAll(denied);
        return result;
    }

    boolean allows(String domainId) {
        if (domainId == null || domainId.isBlank() || denied.contains(domainId) || !allowed.contains(domainId)) {
            return false;
        }
        return decisions.computeIfAbsent(domainId, id -> {
            RbacDomainInfo domain = loader.apply(id);
            return domain != null && id.equals(String.valueOf((Object) domain.getId())) && domain.selfAudit();
        });
    }

    boolean allowsObject(DomainObject object) {
        if (object == null) return false;
        final String domainId = object.getDomainId();
        return domainId == null || domainId.isBlank() || allows(domainId);
    }

    RbacTenantInfo tenant(String id, Function<String, RbacTenantInfo> loader) {
        if (!tenants.containsKey(id)) tenants.put(id, loader.apply(id));
        return tenants.get(id);
    }

    void rememberTenant(String id, RbacTenantInfo tenant) {
        tenants.put(id, tenant);
    }
}
