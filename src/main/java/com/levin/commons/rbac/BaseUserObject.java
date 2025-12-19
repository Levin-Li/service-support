package com.levin.commons.rbac;


import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.service.domain.Identifiable;

import java.io.Serializable;


/**
 * 用户基本信息
 */
public interface BaseUserObject
        extends Serializable, MultiTenantObject, Identifiable, NamedObject {

    /**
     * 获取用户类型
     * 如 管理后台用户
     * 客户
     *
     * @return
     */
    default String getType() {
        return "";
    }

}
