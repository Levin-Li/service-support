package com.levin.commons.rbac;

import java.util.List;

/**
 * Explicit trusted opt-in. Implementations must not read mutable external state and must validate
 * complete permission, field/value and data-scope containment from a single effective actor role.
 * Implementing this interface does not itself establish trust or freeze catalogs.
 */
public interface RbacStrictAssignmentParticipant {
    /**
     * Bind immutable action and attribute catalogs from a single proven consistent version.
     * Must run synchronously on the current thread. If opening fails after partial binding,
     * the implementation must clean up before throwing. The returned binding must always be non-null.
     */
    StrictCatalogBinding openStrictAuthorizationCatalog(RbacStrictAssignmentView view);

    /** Bind the same audited immutable catalogs for actor-only issuance. */
    StrictCatalogBinding openStrictIssuerCatalog(RbacStrictIssuerView view);

    /** Prove issuer permissions and composite field/value grants without fabricating a target user. */
    void checkStrictIssuerPermissionCoverage(RbacStrictIssuerView view, List<RbacRoleInfo> targetRoles);

    /** Explicitly recognize only reviewed exact veto provider implementations. */
    boolean supportsStrictIssuerVeto(RbacStrictIssuerVeto veto);

    interface StrictCatalogBinding extends AutoCloseable {
        @Override void close();
    }

    void checkStrictPermissionCoverage(RbacStrictAssignmentView view, List<RbacRoleInfo> targetRoles);
}
