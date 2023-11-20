package com.levin.commons.dao.domain;


/**
 * 可逻辑删除的对象
 * <p/>
 *
 * @author llw
 */
@Deprecated
public interface DeletableObject {

    /**
     * 是否已经删除
     *
     * @return isDisable
     */
    boolean isDeleted();

}
