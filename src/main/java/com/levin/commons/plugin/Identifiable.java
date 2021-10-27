package com.levin.commons.plugin;

import java.io.Serializable;

/**
 * 可标识的对象
 */
public interface Identifiable extends Serializable, Comparable<Identifiable> {

    /**
     * 资源 ID
     *
     * @return
     */
    String getId();

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
    default Boolean isEnable() {
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

    /**
     * 排序比较
     *
     * @param o
     * @return
     */
    @Override
    default int compareTo(Identifiable o) {

        int c1 = getOrderCode() != null ? getOrderCode() : 100;

        int c2 = (o != null && o.getOrderCode() != null) ? o.getOrderCode() : c1 - 1;

        return c1 - c2;
    }

}
