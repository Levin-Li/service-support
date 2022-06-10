package com.levin.commons.service.support;

@FunctionalInterface
public interface VariableResolverConfigurer {
    /**
     * 配置变量解析器
     *
     * @param manager
     */
    void config(VariableResolverManager manager);

}
