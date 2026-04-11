package com.levin.commons.dao.domain;


import java.util.Collection;


/**
 * 可编辑的树节点
 *
 * @author llw
 */
public interface EditableTreeNode {

    /**
     * 设置节点路径
     *
     * @return
     */
    <T extends EditableTreeNode> T setNodePath(String nodePath);

    /**
     * 设置子节点
     *
     * @return
     */
    <T extends EditableTreeNode> T setChildren(Collection<T> children);

}
