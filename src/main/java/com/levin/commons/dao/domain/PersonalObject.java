package com.levin.commons.dao.domain;

import java.io.Serializable;

/**
 * 个性化的对象
 *
 * @author llw
 */
public interface PersonalObject {

    /**
     * 获取对象的所有者
     *
     * @return
     */
    <UID extends Serializable> UID getOwnerId();

}