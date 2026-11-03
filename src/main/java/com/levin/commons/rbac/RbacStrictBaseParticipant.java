package com.levin.commons.rbac;
/** Explicit audited opt-in that replacing this exact base implementation's authorization methods by
 * strict-view defaults cannot bypass a veto. Unknown subclasses must not inherit this trust. */
public interface RbacStrictBaseParticipant { }
