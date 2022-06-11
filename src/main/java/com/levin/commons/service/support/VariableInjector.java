package com.levin.commons.service.support;

import com.levin.commons.service.domain.InjectVar;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 变量注入器
 */
@FunctionalInterface
public interface VariableInjector {

    /**
     * 获取注入域
     * <p>
     * 默认
     *
     * @return
     * @see InjectVar#domain()
     */
    default String getInjectDomain() {
        return "default";
    }

    /**
     * 获取注入值
     *
     * @param targetBean
     * @param field
     * @param variableResolvers
     * @return
     */
    default ValueHolder<Object> getInjectValue(Object targetBean, Field field, VariableResolver... variableResolvers) {
        return getInjectValue(targetBean, Arrays.asList(variableResolvers), field);
    }

    /**
     * 获取注入值
     *
     * @param targetBean
     * @param variableResolvers
     * @param field
     * @return
     */
    default ValueHolder<Object> getInjectValue(Object targetBean, List<VariableResolver> variableResolvers, Field field) {
        throw new UnsupportedOperationException("not implement");
    }

    /**
     * 获取注入值列表
     *
     * @param targetBean
     * @param variableResolvers
     * @return
     */
    default List<ValueHolder<Object>> getInjectValues(Object targetBean, List<VariableResolver> variableResolvers) {
        throw new UnsupportedOperationException("not implement");
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean        被注入对象
     * @param variableResolvers 变量解析器列表
     * @return
     */
    default List<String> injectByVariableResolvers(Object targetBean, VariableResolver... variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return injectByVariableResolvers(targetBean, Arrays.asList(variableResolvers));
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean        被注入对象
     * @param variableResolvers 变量解析器列表
     * @return
     */
    default List<String> injectByVariableResolvers(Object targetBean, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return injectByVariableResolvers(targetBean, () -> variableResolvers);
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean 被注入对象
     * @param suppliers  变量解析器支持列表
     * @return
     */
    List<String> injectByVariableResolvers(Object targetBean, Supplier<List<VariableResolver>>... suppliers) throws VariableInjectException, VariableNotFoundException;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 为目标对象注入变量
     * <p>
     * 注意：不支持groovy 和 spel 表达式
     *
     * @param targetBean 被注入对象
     * @param beans      上下文环境变量支持列表
     * @return
     */
    default List<String> inject(Object targetBean, Object... beans) throws VariableInjectException, VariableNotFoundException {
        return inject(targetBean, Arrays.asList(beans));
    }

    /**
     * 为目标对象注入变量
     * <p>
     * 注意：不支持groovy 和 spel 表达式
     *
     * @param targetBean 被注入对象
     * @param beans      上下文环境变量支持列表
     * @return
     */
    default List<String> inject(Object targetBean, List<?> beans) throws VariableInjectException, VariableNotFoundException {
        return injectByVariableResolvers(targetBean, newDefaultResolver().addBeanContexts(() -> beans));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 为目标对象注入变量
     * <p>
     * 支持groovy 和 spel 表达式
     *
     * @param targetBean 被注入对象
     * @param contexts   上下文环境变量支持列表
     * @return
     */
    default List<String> injectByMap(Object targetBean, Map<String, ?>... contexts) throws VariableInjectException, VariableNotFoundException {
        return injectByMap(targetBean, Arrays.asList(contexts));
    }

    /**
     * 为目标对象注入变量
     * <p>
     * 支持groovy 和 spel 表达式
     *
     * @param targetBean 被注入对象
     * @param contexts   上下文环境变量支持列表
     * @return
     */
    default List<String> injectByMap(Object targetBean, List<Map<String, ?>> contexts) throws VariableInjectException, VariableNotFoundException {
        return injectByVariableResolvers(targetBean, newDefaultResolver().addMapContexts(() -> contexts));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////


    static VariableResolver.DefaultDelegateVariableResolver newDefaultResolver() {
        return new VariableResolver.DefaultDelegateVariableResolver();
    }

    static VariableResolver.DefaultDelegateVariableResolver newResolverByMap(Supplier<List<Map<String, ?>>>... suppliers) {
        return newDefaultResolver().addMapContexts(suppliers);
    }

    static VariableResolver.DefaultDelegateVariableResolver newResolverByMap(Map<String, ?>... contexts) {
        return newDefaultResolver().addMapContexts(contexts);
    }

    static VariableResolver.DefaultDelegateVariableResolver newResolverByBean(Supplier<List<?>>... suppliers) {
        return newDefaultResolver().addBeanContexts(suppliers);
    }

    static <T extends Object> VariableResolver.DefaultDelegateVariableResolver newResolverByBean(T... benas) {
        return newDefaultResolver().addBeanContexts(benas);
    }

}
