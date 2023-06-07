package com.levin.commons.service.support;

import java.util.Arrays;
import java.util.List;

/**
 * 变量注入上下文管理器
 */
public interface VariableResolverManager extends VariableResolver {

    /**
     * 获取注入器
     *
     * @return
     */
    VariableInjector getVariableInjector();


    /**
     * 获取变量解析器
     *
     * @return
     */
    List<VariableResolver> getVariableResolvers();

    /**
     * 加入变量解析器
     *
     * @param variableResolvers
     */
    default VariableResolverManager add(VariableResolver... variableResolvers) {
        return add(Arrays.asList(variableResolvers));
    }

    /**
     * 加入变量解析器
     *
     * @param variableResolvers
     */
    VariableResolverManager add(List<VariableResolver> variableResolvers);

}
