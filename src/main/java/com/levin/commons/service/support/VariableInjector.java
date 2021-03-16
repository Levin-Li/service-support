package com.levin.commons.service.support;

import java.util.*;
import java.util.function.Supplier;

/**
 * 变量注入器
 */
//@FunctionalInterface
public interface VariableInjector {

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param suppliers  上下文环境变量支持列表
     */
    void inject(Object targetBean, Supplier<List<Map<String, Object>>>... suppliers) throws VariableInjectException, VariableNotFoundException;

    /**
     * 为目标对象注入变量
     *
     * @param targetBean 被注入对象
     * @param suppliers  变量解析器支持列表
     */
    void injectByVariableResolver(Object targetBean, Supplier<List<VariableResolver>>... suppliers) throws VariableInjectException, VariableNotFoundException;

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param variableResolvers 变量解析器列表
     */
    default void injectByVariableResolver(Object targetBean, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        injectByVariableResolver(targetBean, () -> variableResolvers);
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param variableResolvers 变量解析器列表
     */
    default void injectByVariableResolver(Object targetBean, VariableResolver... variableResolvers) throws VariableInjectException, VariableNotFoundException {
        injectByVariableResolver(targetBean, Arrays.asList(variableResolvers));
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param contexts   上下文环境
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default void inject(Object targetBean, Map<String, Object>... contexts) throws VariableInjectException, VariableNotFoundException {
        inject(targetBean, Arrays.asList(contexts));
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param contexts   上下文环境
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default void inject(Object targetBean, List<Map<String, Object>> contexts) throws VariableInjectException, VariableNotFoundException {
        inject(targetBean, () -> contexts);
    }

}
