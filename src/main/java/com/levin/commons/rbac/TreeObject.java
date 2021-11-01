package com.levin.commons.rbac;

import java.util.Collection;
import java.util.Collections;


/**
 * 树形结构
 *
 * @param <PARENT>
 * @param <CHILD>
 * @author llw
 */
public interface TreeObject<PARENT, CHILD> {

    /**
     * 获取父节点
     *
     * @return
     */
    default <P extends PARENT> P getParent() {
        return null;
    }

    /**
     * 是否有孩子
     *
     * @return 返回 null，表示未知，true 表示有孩子节点，false 表示没有
     */
    default Boolean hasChildren() {
        return null;
    }

    /**
     * 获取子节点
     *
     * @return
     */
    default <C extends CHILD> Collection<C> getChildren() {
        return Collections.EMPTY_LIST;
    }

}
