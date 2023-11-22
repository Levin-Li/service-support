package com.levin.commons.dao.domain;


/**
 * 可分享的多租户对象
 *
 * 租户ID不允许为空
 *
 * @Author levin li
 * @Since 2.2.23
 */
public interface MultiTenantShareableObject extends MultiTenantObject {

    /**
     * 是否可共享
     *
     * @return
     */
    boolean isShareable();
}
