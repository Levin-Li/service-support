package com.levin.commons.rbac;


import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.service.domain.Identifiable;

import java.util.Collection;
import java.util.Collections;


/**
 * 用户基本信息
 */
public interface RbacUserObject<ROLE> extends MultiTenantObject, Identifiable, NamedObject {

    /**
     * 是否超级用户
     *
     * @return
     */
    boolean isSuperAdmin();

    /**
     * 获取角色列表
     *
     * @return
     */
    default Collection<ROLE> getRoleList() {
        return Collections.emptyList();
    }

}
