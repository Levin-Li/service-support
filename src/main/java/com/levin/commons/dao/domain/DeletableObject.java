package com.levin.commons.dao.domain;


/**
 * 可逻辑删除对象
 * <p/>
 * @author llw
 */
public interface DeletableObject {

    /**
     * 是否已经删除
     *
     * @return isDisable
     */
    boolean isDeleted();

}
