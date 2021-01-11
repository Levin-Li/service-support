package com.levin.commons.plugin;

import java.io.Serializable;

/**
 * 可标识的对象
 */
public interface Identifiable<T> extends Serializable {

    /**
     * 归属域
     * <p>
     * 通常是子系统，子模块等
     *
     * @return
     */
    default T getDomain() {
        return null;
    }

    /**
     * 全局唯一ID
     *
     * @return
     */
    String getId();


    /**
     * 名称
     *
     * @return
     */
    String getName();


    /**
     * 获取描述
     *
     * @return
     */
    default String getDescription() {
        return getName() + "[" + getId() + "]";
    }

}
