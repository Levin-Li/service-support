package com.levin.commons.dao.domain;

import com.levin.commons.service.domain.Identifiable;

import java.io.Serializable;


/**
 * 树对象
 * 要注意检查，是否会死锁
 *
 * @author llw
 */
public interface TreeObject<P, C>
        extends BaseTreeObject<P, C>, Identifiable {

    /**
     * 获取父结点id
     *
     * @return parentId
     */
    <ID extends Serializable> ID getParentId();

}
