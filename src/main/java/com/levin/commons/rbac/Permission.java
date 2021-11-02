package com.levin.commons.rbac;

import java.io.Serializable;


/**
 * 资源许可
 */

public interface Permission {

    /**
     * 资源域
     *
     * @param <D>
     */
    <D extends Serializable> D getDomain();

    /**
     * 资源类型
     *
     * @param < T>
     */
    <T extends Serializable> T getType();

    /**
     * 资源
     *
     * @param < T>
     */
    <R extends Serializable> R getRes();

    /**
     * 允许的操作
     *
     * @param < T>
     */
    <A extends Serializable> A getAction();

}