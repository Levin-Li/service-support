package com.levin.commons.dao.domain;

import java.io.Serializable;
import java.util.Collection;

/**
 * 限定范围的组织对象
 *
 * @author llw
 */
public interface OrganizedScopeObject {

    /**
     * 获取限定范围的组织ID列表
     *
     * @param <ORG_ID>
     * @return
     */
    <ORG_ID extends Serializable> Collection<ORG_ID> getOrgIdList();
}
