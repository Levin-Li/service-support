package com.levin.commons.dao.domain;

import java.io.Serializable;

/**
 * 多租户对象
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
