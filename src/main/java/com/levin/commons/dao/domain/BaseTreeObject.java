package com.levin.commons.dao.domain;

import java.util.Collection;
import java.util.Collections;


/**
 * 树形结构
 *
 * @param <PARENT>
 * @param <CHILD>
 * @author llw
 */
public interface BaseTreeObject<PARENT, CHILD> {

    /**
     * 获取节点数据
     * 默认返回自己
     *
     * @return
     */
    default <D> D getNodeData() {
        return null;
    }

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
        return getChildren() != null && !getChildren().isEmpty();
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