package com.levin.commons.dao.domain;

/**
 * 唯一列对象
 * @author lilw
 */
@FunctionalInterface
public interface UniqueConstraintObject {

    /**
     * 获取对象的唯一组合列名
     *
     * @return
     */
    String[] getUniqueColumnNames();

}
