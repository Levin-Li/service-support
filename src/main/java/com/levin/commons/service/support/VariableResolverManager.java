package com.levin.commons.service.support;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 变量解析器管理器
 */
@Tag(name = "变量解析器管理器", description = "管理器合并全局与局部解析器；提供者产生的解析器优先于全局解析器，局部解析器通常限于当前线程。添加局部解析器不得改变其他线程的解析顺序。")
public interface VariableResolverManager extends VariableResolver, Supplier<List<VariableResolver>> {

    /**
     * 获取注入器
     *
     * @return
     */
    @Override
    @Operation(summary = "获取解析器列表", description = "返回当前上下文生效的解析器列表，包含全局与局部解析器，并保持实现定义的优先级顺序。")
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
    @Operation(summary = "获取生效变量解析器", description = "提供者解析器优先于全局解析器；局部解析器仅在当前线程或上下文生效。")
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
    @Operation(summary = "添加变量解析器", description = "isOnlyForCurrentThread 为 true 时只添加到当前线程/上下文；否则添加为全局解析器。新增解析器的具体优先级由实现保持一致。")
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
    @Operation(summary = "添加变量解析器提供者", description = "提供者可按当前上下文生成解析器，且其结果优先于全局解析器；提供者为空或不产生解析器时不会回退改变既有解析器。")
    VariableResolverManager addSuppliers(List<Supplier<VariableResolver>> variableResolverSuppliers);

}
