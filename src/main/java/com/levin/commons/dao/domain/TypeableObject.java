package com.levin.commons.dao.domain;


import java.io.Serializable;


/**
 * 可类型化的对象
 */

@FunctionalInterface
public interface TypeableObject {

    /**
     * 获取类型
     *
     * @return
     */
    <T extends Serializable> T getType();

}
