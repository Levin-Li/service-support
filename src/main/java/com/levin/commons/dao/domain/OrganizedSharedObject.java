package com.levin.commons.dao.domain;

/**
 * 可共享的对象
 *
 * @author llw
 */
public interface OrganizedSharedObject extends OrganizedObject {

    /**
     * 是否可共享
     *
     * @return
     */
    boolean isOrgShared();
}
