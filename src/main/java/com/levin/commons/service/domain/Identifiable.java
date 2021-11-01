package com.levin.commons.service.domain;

import java.io.Serializable;

/**
 * 可标识的对象
 */
public interface Identifiable extends Serializable {

    /**
     * ID
     *
     * @return
     */
    <ID extends Serializable> ID getId();

    /**
     * 名称
     *
     * @return
     */
    String getName();

    /**
     * 是否允许
     *
     * @return
     */
    default boolean isEnable() {
        return true;
    }

    /**
     * 排序码
     *
     * @return
     */
    default Integer getOrderCode() {
        return 100;
    }

    /**
     * 获取描述
     *
     * @return
     */
    default String getRemark() {
        return getName() + "[" + getId() + "]";
    }


}
