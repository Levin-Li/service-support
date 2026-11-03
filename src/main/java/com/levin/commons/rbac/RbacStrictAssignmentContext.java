package com.levin.commons.rbac;

/** Internal thread binding; never propagate to asynchronous execution. */
final class RbacStrictAssignmentContext {
    static final ThreadLocal<RbacBaseService> VIEW = new ThreadLocal<>();
    private RbacStrictAssignmentContext() { }
}
