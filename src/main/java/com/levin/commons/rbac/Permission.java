package com.levin.commons.rbac;

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

    /**
     * @param requirePermission
     * @param currentPermission
     * @return
     */
    default boolean isMatch(String requirePermission, String currentPermission) {

        // 如果表达式不带有*号，则只需简单equals即可 (速度提升200倍)
        if (!currentPermission.contains("*")) {
            return currentPermission.equals(requirePermission);
        }

        //正则表达式
        return Pattern.matches(currentPermission.replaceAll("\\*", ".*"), requirePermission);
    }

}
