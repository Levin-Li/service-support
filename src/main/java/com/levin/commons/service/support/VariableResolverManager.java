package com.levin.commons.service.support;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 变量注入上下文管理器
 */
public interface VariableResolverManager {

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

    /**
     * 加入变量解析器
     *
     * @param ctxs
     */
    default VariableResolverManager add(Map<String, Object>... ctxs) {
        return add(() -> Arrays.asList(ctxs));
    }

    /**
     * 加入变量解析器
     *
     * @param suppliers
     */
    VariableResolverManager add(Supplier<List<Map<String, Object>>>... suppliers);

}
