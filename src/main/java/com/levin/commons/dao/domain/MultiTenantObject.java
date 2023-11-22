package com.levin.commons.dao.domain;

import java.io.Serializable;

/**
 * 多租户对象
 * <p>
 * 租户ID不允许为空
 *
 * @Author levin li
 * @Since 2.2.23
 */
public interface MultiTenantObject {

    /**
     * 获取租户 ID
     *
     * @return
     */
    <TID extends Serializable> TID getTenantId();

}
