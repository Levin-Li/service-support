package com.levin.commons.rbac;

import java.util.Set;

/** 用户覆盖和角色继承后的不可变范围快照，不携带任何授权查询缓存。 */
record EffectiveDataScope(Set<String> tenantScopeList,
                          Set<String> deniedTenantScopeList,
                          Set<String> domainScopeList,
                          Set<String> deniedDomainScopeList,
                          Set<String> orgScopeList,
                          Set<String> deniedOrgScopeList,
                          Integer confidentialDataAccessLevel) implements DataScope {
    @Override public Set<String> getTenantScopeList() { return tenantScopeList; }
    @Override public Set<String> getDeniedTenantScopeList() { return deniedTenantScopeList; }
    @Override public Set<String> getDomainScopeList() { return domainScopeList; }
    @Override public Set<String> getDeniedDomainScopeList() { return deniedDomainScopeList; }
    @Override public Set<String> getOrgScopeList() { return orgScopeList; }
    @Override public Set<String> getDeniedOrgScopeList() { return deniedOrgScopeList; }
    @Override public Integer getConfidentialDataAccessLevel() { return confidentialDataAccessLevel; }
}
