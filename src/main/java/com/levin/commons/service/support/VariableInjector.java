package com.levin.commons.service.support;

import com.levin.commons.service.domain.InjectVar;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    default ValueHolder<Object> getOutputValue(Object targetBean, Field field, VariableResolver... variableResolvers) {
        return getOutputValue(targetBean, field, Arrays.asList(variableResolvers));
    }

    default ValueHolder<Object> getInjectValue(Object targetBean, Field field, VariableResolver... variableResolvers) {
        return getInjectValue(targetBean, field, Arrays.asList(variableResolvers));
    }

    default ValueHolder<Object> injectValue(Object targetBean, Field field, VariableResolver... variableResolvers) {
        return injectValue(targetBean, field, Arrays.asList(variableResolvers));
    }

    ////////////////////////////////////////// 注入 ///////////////////////////////////////////////////////////////////

    /**
     * 为目标对象注入变量
     *
     * @param targetBean 被注入对象
     * @param beans      上下文环境变量支持列表
     * @return
     */
    default List<ValueHolder<Object>> injectByBean(Object targetBean, Object... beans) throws VariableInjectException, VariableNotFoundException {
        return injectByBean(targetBean, Arrays.asList(beans));
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
    default List<ValueHolder<Object>> injectByBean(Object targetBean, List<?> beans) throws VariableInjectException, VariableNotFoundException {
        return injectByBean(targetBean, null, beans);
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param ignoreFieldPredicate
     * @param beans
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<ValueHolder<Object>> injectByBean(Object targetBean, Predicate<Field> ignoreFieldPredicate, Object... beans) throws VariableInjectException, VariableNotFoundException {
        return injectByBean(targetBean, ignoreFieldPredicate, Arrays.asList(beans));
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param ignoreFieldPredicate
     * @param beans
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<ValueHolder<Object>> injectByBean(Object targetBean, Predicate<Field> ignoreFieldPredicate, List<?> beans) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, ignoreFieldPredicate, newResolverByBean(() -> beans));
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
    default List<ValueHolder<Object>> injectByMap(Object targetBean, Map<String, ?>... contexts) throws VariableInjectException, VariableNotFoundException {
        return injectByMap(targetBean, null, contexts);
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
    default List<ValueHolder<Object>> injectByMap(Object targetBean, Predicate<Field> ignoreFieldPredicate, Map<String, ?>... contexts) throws VariableInjectException, VariableNotFoundException {
        return injectByMap(targetBean, ignoreFieldPredicate, Arrays.asList(contexts));
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
    default List<ValueHolder<Object>> injectByMap(Object targetBean, Predicate<Field> ignoreFieldPredicate, List<Map<String, ?>> contexts) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, ignoreFieldPredicate, newResolverByMap(() -> contexts));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * 为目标对象注入变量
     *
     * @param targetBean        被注入对象
     * @param variableResolvers 变量解析器列表
     * @return
     */
    default List<ValueHolder<Object>> injectValues(Object targetBean, VariableResolver... variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, Arrays.asList(variableResolvers));
    }


    /**
     * 为目标对象注入变量
     *
     * @param targetBean        被注入对象
     * @param variableResolvers 变量解析器列表
     * @return
     */
    default List<ValueHolder<Object>> injectValues(Object targetBean, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, null, variableResolvers);
    }

    /**
     * @param targetBean
     * @param ignoreFieldPredicate
     * @param variableResolvers
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<ValueHolder<Object>> injectValues(Object targetBean, Predicate<Field> ignoreFieldPredicate, VariableResolver... variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, ignoreFieldPredicate, Arrays.asList(variableResolvers));
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean 被注入对象
     * @param suppliers  变量解析器支持列表
     * @return
     */
    default List<ValueHolder<Object>> injectValues(Object targetBean, Predicate<Field> ignoreFieldPredicate, Supplier<List<VariableResolver>>... suppliers) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean,
                ignoreFieldPredicate,
                Stream.of(suppliers)
                        .filter(Objects::nonNull)
                        .map(Supplier::get)
                        .flatMap(Collection::stream)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );
    }

    /**
     * 获取当前字段输出值
     *
     * @param targetBean
     * @param variableResolvers
     * @param field
     * @return
     */

    default ValueHolder<Object> getOutputValue(Object targetBean, Field field, List<VariableResolver> variableResolvers) {
        return doInject(targetBean, field, 1, variableResolvers);
    }

    /**
     * 获取当前字段注入值，但不注入targetBean
     *
     * @param targetBean
     * @param field
     * @param variableResolvers
     * @return
     */

    default ValueHolder<Object> getInjectValue(Object targetBean, Field field, List<VariableResolver> variableResolvers) {
        return doInject(targetBean, field, 2, variableResolvers);
    }

    /**
     * 获取当前字段注入值，但不注入targetBean
     *
     * @param targetBean
     * @param field
     * @param variableResolvers
     * @return
     */

    default ValueHolder<Object> injectValue(Object targetBean, Field field, List<VariableResolver> variableResolvers) {
        return doInject(targetBean, field, 3, variableResolvers);
    }

    /**
     * 按字段注解注入变量
     * <p>
     * 功能实现的核心方法
     *
     * @param targetBean
     * @param variableResolvers 变量解析器列表
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<ValueHolder<Object>> getOutputValues(Object targetBean, Predicate<Field> ignoreFieldPredicate, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return doInject(targetBean, ignoreFieldPredicate, 1, variableResolvers);
    }

    /**
     * 按字段注解注入变量
     * <p>
     * 功能实现的核心方法
     *
     * @param targetBean
     * @param variableResolvers 变量解析器列表
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */

    default List<ValueHolder<Object>> getInjectValues(Object targetBean, Predicate<Field> ignoreFieldPredicate, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return doInject(targetBean, ignoreFieldPredicate, 2, variableResolvers);
    }

    /**
     * 兼容源方法
     *
     * @param targetBean
     * @param variableResolvers
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    @Deprecated
    default List<ValueHolder<Object>> injectByVariableResolvers(Object targetBean, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, null, variableResolvers);
    }

    /**
     * 按字段注解注入变量
     * <p>
     * 功能实现的核心方法
     *
     * @param targetBean
     * @param variableResolvers 变量解析器列表
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<ValueHolder<Object>> injectValues(Object targetBean, Predicate<Field> ignoreFieldPredicate, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return doInject(targetBean, ignoreFieldPredicate, 3, variableResolvers);
    }

    /**
     * @param targetBean
     * @param field
     * @param mode              注入模式，1 = 输出转换值，2 = 获取注入值，3 = 注入targetBean
     * @param variableResolvers
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default ValueHolder<Object> doInject(Object targetBean, Field field, int mode, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        throw new UnsupportedOperationException("not impl");
    }

    /**
     * @param targetBean
     * @param ignoreFieldPredicate
     * @param mode                 注入模式，1 = 输出转换值，2 = 获取注入值，3 = 注入targetBean
     * @param variableResolvers
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    List<ValueHolder<Object>> doInject(Object targetBean, Predicate<Field> ignoreFieldPredicate, int mode, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
