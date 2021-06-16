package com.levin.commons.service.support;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 变量注入上下文管理器
 */
public interface VariableResolverManager {

    /**
     * @return
     */
    List<VariableResolver> getVariableResolvers(boolean isThreadLevel);

    /**
     * @param variableResolvers
     */
    VariableResolverManager addVariableResolvers(boolean isThreadLevel, VariableResolver... variableResolvers);

    /**
     * @param ctxs
     */
    VariableResolverManager addVariableResolverByCtx(boolean isThreadLevel, Map<String, Object>... ctxs);

    /**
     * @param suppliers
     */
    VariableResolverManager addVariableResolversByCtx(boolean isThreadLevel, Supplier<List<Map<String, Object>>>... suppliers);

}
