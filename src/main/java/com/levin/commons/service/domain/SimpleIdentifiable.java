package com.levin.commons.service.domain;


/**
 * 可标识的对象
 */
public interface SimpleIdentifiable extends Identifiable {

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
