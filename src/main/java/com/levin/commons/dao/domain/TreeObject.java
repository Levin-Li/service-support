package com.levin.commons.dao.domain;

import java.io.Serializable;
import java.util.Set;


/**
 * 树对象
 * 要注意检查，是否会死锁
 * @author llw
 */
public interface TreeObject<P, C>
        extends Identifiable {

    /**
     * 获取父结点id
     *
     * @return parentId
     */
    <ID extends Serializable> ID getParentId();

    /**
     * 获取父对象
     *
     * @return parent
     */
    P getParent();

    /**
     * 获取所有的孩子对象
     *
     * @return children
     */
    Set<C> getChildren();

}
