package com.levin.commons.service.domain;

/**
 * 变量解析器
 *
 * @author llw
 */
@FunctionalInterface
public interface VariableResolver {
    /**
     * 获取变量
     *
     * @param name                变量名
     * @param throwExWhenNotFound 当变量无法解析时是否抛出异常
     * @param expectTypes         期望的类型
     * @return T
     * @throws RuntimeException 如果变量无法获取将抛出异常
     */
    <T> T resolve(String name, boolean throwExWhenNotFound, Class<?>... expectTypes) throws RuntimeException;

}
