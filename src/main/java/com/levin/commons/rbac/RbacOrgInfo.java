package com.levin.commons.rbac;


import com.levin.commons.dao.domain.MultiTenantObject;

import java.io.Serializable;

/**
 * 组织对象
 *
 * @author lilw
 */
public interface RbacOrgInfo extends RbacCoreObject, MultiTenantObject, DataScopeObject {

    /**
     * 获取租户 ID
     *
     * @return
     */
    @Override
    default <TID extends Serializable> TID getTenantId() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取父级ID
     *
     * @return
     */
    default <ID extends Serializable> ID getParentId() {
        return null;
    }

}
