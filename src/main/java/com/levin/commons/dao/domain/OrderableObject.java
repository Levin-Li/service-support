package com.levin.commons.dao.domain;


/**
 * 可排序的对象
 *
 * @author llw
 */
public interface OrderableObject extends Comparable<OrderableObject> {

    /**
     * 排序代码
     *
     * @return
     */
    <C extends Comparable<?>> C getOrderCode();

    /**
     * 比较大小
     *
     * @param o
     * @return
     */
    @Override
    default int compareTo(OrderableObject o) {

        if (o == null) {
            return 1;
        }

        Comparable<?> thisOrderCode = getOrderCode();

        if (o.getOrderCode() == null) {
            return (thisOrderCode == null) ? 0 : 1;
        }

        if (thisOrderCode == null) {
            return -1;
        }

        return thisOrderCode.compareTo(o.getOrderCode());
    }
}
