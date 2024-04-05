package com.levin.commons.service.domain;


import com.levin.commons.dao.domain.EnableObject;
import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.dao.domain.SortableObject;

/**
 * 可标识的对象
 *
 * @author levin
 */
public interface SimpleIdentifiable extends Identifiable, EnableObject, NamedObject, SortableObject {

    /**
     * 名称
     *
     * @return
     */
    @Override
    String getName();

    /**
     * 是否允许
     *
     * @return
     */
    @Override
    default boolean isEnable() {
        return true;
    }

    /**
     * 排序码
     *
     * @return
     */
    @Override
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
