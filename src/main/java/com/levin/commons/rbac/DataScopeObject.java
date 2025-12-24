package com.levin.commons.rbac;


import com.levin.commons.dao.domain.ConfidentialObject;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 数据按2个维度进行过滤
 * 1.部门数据权限
 * 2.数据访问级别
 */
public interface DataScopeObject {

    /**
     * 获取数据权限范围
     *
     * @return
     */
    default OrgDataScope getOrgDataScope() {
        return OrgDataScope.OnlyShared;
    }

    /**
     * 获取已分配的部门ID列表
     *
     * @return
     */
    default <ORG_ID extends Serializable> List<ORG_ID> getAssignedOrgIdList() {
        if (getOrgDataScope() != OrgDataScope.Assigned) {
            throw new IllegalArgumentException("当前角色数据权限范围不是指定");
        }
        return Collections.emptyList();
    }

    /**
     * 获取数据访问级别
     * 数值越大，级别越高
     *
     * @return
     */
    default int getDataAccessLevel() {
        return ConfidentialObject.TENANT_SHARED;
    }

}
