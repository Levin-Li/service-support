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

    default ValueHolder<Object> getInjectValue(Object targetBean, Field field, VariableResolver... variableResolvers) {
        return getInjectValue(targetBean, Arrays.asList(variableResolvers), field);
    }

    /**
     * 默认未实现
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
//
//    static VariableResolver newVariableResolver(Supplier<List<?>>... suppliers) {
//        return new VariableResolver.BeanVariableResolver(suppliers);
//    }
//
//    static VariableResolver newVariableResolver(List<?> beans) {
//        return newVariableResolver(() -> beans);
//    }
//
//    static VariableResolver newMapVariableResolver(Supplier<List<Map<String, ?>>>... suppliers) {
//        //强转
//        return newVariableResolver((Supplier<List<?>>[]) Object.class.cast(suppliers));
//    }
//
//    static VariableResolver newMapVariableResolver(List<Map<String, ?>> contexts) {
//        //强转
//        return newVariableResolver((List<?>) Object.class.cast(contexts));
//    }
//
//    static VariableResolver newMapVariableResolver(Map<String, ?>... contexts) {
//        return newVariableResolver(Arrays.asList(contexts));
//    }
//
//    /**
//     * 获取 Spel 变量解析器
//     *
//     * @param suppliers 上下文列表
//     * @return
//     */
//    static VariableResolver newSpelVariableResolver(Supplier<List<Map<String, ?>>>... suppliers) {
//        return new VariableResolver.SpelVariableResolver(suppliers);
//    }
//
//    /**
//     * groovy 变量解析器 上下文列表
//     *
//     * @param suppliers
//     * @return
//     */
//    static VariableResolver newGroovyVariableResolver(Supplier<List<Map<String, ?>>>... suppliers) {
//        return new VariableResolver.GroovyVariableResolver(suppliers);
//    }
//
//    /**
//     * 创建支持 spel 和 groovy 的解析器
//     *
//     * @param suppliers
//     * @return
//     */
//    static List<VariableResolver> newSupportSpelAndGroovyResolvers(Supplier<List<Map<String, ?>>>... suppliers) {
//        return newResolverBuilder().addSupportSpelAndGroovy(suppliers).build();
//    }

    static VariableResolver.DefaultDelegateVariableResolver newDefaultResolver() {
        return new VariableResolver.DefaultDelegateVariableResolver();
    }

}
