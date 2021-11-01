package com.levin.commons.dao.domain;


/**
 * 可排序的对象
 */
public interface OrderableObject {

    /**
     * 排序代码
     *
     * @return
     */
    <C extends Comparable> C getOrderCode();

}
