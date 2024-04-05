package com.levin.commons.dao.domain;


/**
 * 可排序的对象
 *
 * @author llw
 */
public interface SortableObject extends Comparable<SortableObject> {

    /**
     * 排序代码
     *
     * @return
     */
    <C extends Comparable<?>> C getOrderCode();

    /**
     * 比较大小
     *
     * @param obj
     * @return
     */
    @Override
    default int compareTo(SortableObject obj) {

        if (obj == null) {
            return 1;
        }

        Comparable<?> thisOrderCode = getOrderCode();

        if (obj.getOrderCode() == null) {
            return (thisOrderCode == null) ? 0 : 1;
        }

        if (thisOrderCode == null) {
            return -1;
        }

        return thisOrderCode.compareTo(obj.getOrderCode());
    }
}
