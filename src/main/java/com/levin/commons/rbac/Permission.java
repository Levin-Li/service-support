package com.levin.commons.rbac;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.regex.Pattern;


/**
 * 资源许可
 */

public interface Permission {

    /**
     * 字段分隔符
     */
    String DELIMITER = ":";

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
