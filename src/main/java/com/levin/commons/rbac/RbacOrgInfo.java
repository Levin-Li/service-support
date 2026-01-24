package com.levin.commons.rbac;


import com.levin.commons.dao.domain.MultiTenantObject;

import java.io.Serializable;

/**
 * 组织对象
 *
 * @author lilw
 */
public interface RbacOrgInfo extends RbacCoreObject, MultiTenantObject {

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
//
//    /**
//     * 获取父级对象
//     *
//     * @return
//     */
//    default <ORG extends RbacOrgInfo> ORG getParent() {
//        throw new UnsupportedOperationException();
//    }

}
