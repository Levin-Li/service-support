package com.levin.commons.service;

/**
 * 变量解析器
 */
public interface VariableResolver {

    /**
     * 获取变量
     *
     * @param domain      变量域
     * @param varName     变量名
     * @param expectTypes 期望的类型
     * @param <T>
     * @return
     */
    <T> T resolve(String domain, String varName, Class... expectTypes);

}
