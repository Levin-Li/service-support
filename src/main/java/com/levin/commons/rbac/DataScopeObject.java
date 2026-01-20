package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;

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

        Assert.isTrue(getOrgDataScope() == OrgDataScope.Assigned, "数据权限错误-{}", getOrgDataScope());

        return Collections.emptyList();
    }

    /**
     * 获取机密数据的访问级别
     * 数值越大，级别越高
     *
     * @return
     */
    default Integer getConfidentialDataAccessLevel() {
        return null;
    }

}
