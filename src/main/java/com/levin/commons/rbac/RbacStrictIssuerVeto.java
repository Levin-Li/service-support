package com.levin.commons.rbac;

/** Trusted, synchronous additional denial; implementors must read only stable authoritative facts. */
@FunctionalInterface
public interface RbacStrictIssuerVeto {
    void check(RbacStrictIssuerView view);
}
