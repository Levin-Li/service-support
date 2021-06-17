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
     * 获取变量解析器列表
     *
     * @param isThreadLevel 是否是线程级别，false 则返回全局的变量解析器
     * @return
     */
    List<VariableResolver> getVariableResolvers(boolean isThreadLevel);

    /**
     * @param variableResolvers
     */
    default VariableResolverManager addVariableResolvers(boolean isThreadLevel, VariableResolver... variableResolvers) {
        return addVariableResolvers(isThreadLevel, Arrays.asList(variableResolvers));
    }

    /**
     * @param variableResolvers
     */
    VariableResolverManager addVariableResolvers(boolean isThreadLevel, List<VariableResolver> variableResolvers);

    /**
     * @param ctxs
     */
    default VariableResolverManager addVariableResolverByCtx(boolean isThreadLevel, Map<String, Object>... ctxs) {
        return addVariableResolversByCtx(isThreadLevel, () -> Arrays.asList(ctxs));
    }

    /**
     * @param suppliers
     */
    VariableResolverManager addVariableResolversByCtx(boolean isThreadLevel, Supplier<List<Map<String, Object>>>... suppliers);

}
