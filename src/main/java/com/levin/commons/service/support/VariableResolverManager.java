package com.levin.commons.service.support;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 变量解析器管理器
 */
public interface VariableResolverManager extends VariableResolver, Supplier<List<VariableResolver>> {

    /**
     * 获取注入器
     *
     * @return
     */
    @Override
    default List<VariableResolver> get() {
        return getVariableResolvers();
    }

    /**
     * 获取变量解析器，包括全局的和局部的解析器
     * <p>
     * 提供者Supplier提供的变量解析器优先级高于全局的变量解析器。
     * <p>
     * 局部的解析器通常是线程级别的。
     *
     * @return
     */
    List<VariableResolver> getVariableResolvers();

    /**
     * 增加全局变量解析器
     *
     * @param variableResolvers
     */
    default VariableResolverManager add(VariableResolver... variableResolvers) {
        return add(false, variableResolvers);
    }

    /**
     * 增加全局变量解析器
     *
     * @param variableResolvers
     */
    default VariableResolverManager add(boolean isOnlyForCurrentThread, VariableResolver... variableResolvers) {
        return add(isOnlyForCurrentThread, Arrays.asList(variableResolvers));
    }

    /**
     * 增加全局变量解析器
     *
     * @param variableResolvers
     */
    default VariableResolverManager add(List<VariableResolver> variableResolvers) {
        return add(false, variableResolvers);
    }

    /**
     * 增加全局变量解析器
     *
     * @param variableResolvers
     */
    VariableResolverManager add(boolean isOnlyForCurrentThread, List<VariableResolver> variableResolvers);

    /**
     * 增加变量解析器提供者
     * <p>
     * 可以用于提供线程上下文的变量解析器。
     *
     * @param variableResolverSuppliers
     */
    default VariableResolverManager addSuppliers(Supplier<VariableResolver>... variableResolverSuppliers) {
        return addSuppliers(Arrays.asList(variableResolverSuppliers));
    }

    /**
     * 增加变量解析器提供者
     * <p>
     * 可以用于提供线程上下文的变量解析器。
     *
     * @param variableResolverSuppliers
     */
    VariableResolverManager addSuppliers(List<Supplier<VariableResolver>> variableResolverSuppliers);

}
