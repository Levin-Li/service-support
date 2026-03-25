package com.levin.commons.rbac;


import com.levin.commons.dao.domain.MultiTenantObject;

import java.io.Serializable;
import java.util.Collection;

/**
 * 组织对象
 *
 * @author echo
 */
public interface RbacOrgInfo extends RbacCoreObject, MultiTenantObject {

    /**
     * 获取父级ID
     *
     * @return
     */
    default <ID extends Serializable> ID getParentId() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取父级对象
     *
     * @return
     */
    default <ORG extends RbacOrgInfo> ORG getParent() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取孩子列表
     *
     * @return
     */
    default <ORG extends RbacOrgInfo> Collection<ORG> getChildren() {
        throw new UnsupportedOperationException();
    }
}
