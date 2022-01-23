package com.levin.commons.service.support;

import com.levin.commons.service.domain.InjectVar;

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

    ////////////////////////////// 默认构造器 ///////////////////////////////////////////////////

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param variableResolvers 变量解析器列表
     * @return
     */
    default List<String> injectByVariableResolver(Object targetBean, VariableResolver... variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return injectByVariableResolver(targetBean, Arrays.asList(variableResolvers));
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param variableResolvers 变量解析器列表
     * @return
     */
    default List<String> injectByVariableResolver(Object targetBean, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return injectByVariableResolver(targetBean, () -> variableResolvers);
    }


    /**
     * 为目标对象注入变量
     *
     * @param targetBean 被注入对象
     * @param suppliers  变量解析器支持列表
     * @return
     */
    List<String> injectByVariableResolver(Object targetBean, Supplier<List<VariableResolver>>... suppliers) throws VariableInjectException, VariableNotFoundException;


    ////////////////////////////// 默认构造器 ///////////////////////////////////////////////////

    default VariableResolver newVariableResolver(Supplier<List<?>>... suppliers) {
        return new VariableResolver.BeanVariableResolver(suppliers);
    }

    default VariableResolver newVariableResolver(List<?> beans) {
        return newVariableResolver(() -> beans);
    }

    default VariableResolver newMapVariableResolver(Supplier<List<Map<String, ?>>>... suppliers) {
        //强转
        return newVariableResolver((Supplier<List<?>>[]) Object.class.cast(suppliers));
    }

    default VariableResolver newMapVariableResolver(List<Map<String, ?>> contexts) {
        //强转
        return newVariableResolver((List<?>) Object.class.cast(contexts));
    }

    default VariableResolver newMapVariableResolver(Map<String, ?>... contexts) {
        return newVariableResolver(Arrays.asList(contexts));
    }


    /**
     * 获取 Spel 变量解析器
     *
     * @param suppliers 上下文列表
     * @return
     */
    default VariableResolver newSpelVariableResolver(Supplier<List<Map<String, ?>>>... suppliers) {
        return new VariableResolver.SpelVariableResolver(suppliers);
    }

    /**
     * groovy 变量解析器 上下文列表
     *
     * @param suppliers
     * @return
     */
    default VariableResolver newGroovyVariableResolver(Supplier<List<Map<String, ?>>>... suppliers) {
        return new VariableResolver.GroovyVariableResolver(suppliers);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取变量解析器列表
     *
     * @param suppliers 上下文列表
     * @return
     */
    //@Override
    default List<VariableResolver> getVariableResolvers(Supplier<List<Map<String, ?>>>... suppliers) {
        return Arrays.asList(newSpelVariableResolver(suppliers)
                , newGroovyVariableResolver(suppliers)
                , newMapVariableResolver(suppliers));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 为目标对象注入变量
     * <p>
     * 不支持groovy 和 spel 表达式
     *
     * @param targetBean
     * @param beans      上下文环境变量支持列表
     * @return
     */
    default List<String> inject(Object targetBean, Object... beans) throws VariableInjectException, VariableNotFoundException {
        return inject(targetBean, Arrays.asList(beans));
    }

    /**
     * 为目标对象注入变量
     * <p>
     * 不支持groovy 和 spel 表达式
     *
     * @param targetBean
     * @param beans      上下文环境变量支持列表
     * @return
     */
    default List<String> inject(Object targetBean, List<?> beans) throws VariableInjectException, VariableNotFoundException {
        return injectByVariableResolver(targetBean, newVariableResolver(beans));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 为目标对象注入变量
     * <p>
     * 支持groovy 和 spel 表达式
     *
     * @param targetBean
     * @param contexts   上下文环境
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<String> injectByMap(Object targetBean, Map<String, ?>... contexts) throws VariableInjectException, VariableNotFoundException {
        return injectByMap(targetBean, Arrays.asList(contexts));
    }

    /**
     * 为目标对象注入变量
     * <p>
     * 支持groovy 和 spel 表达式
     *
     * @param targetBean
     * @param contexts   上下文环境
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<String> injectByMap(Object targetBean, List<Map<String, ?>> contexts) throws VariableInjectException, VariableNotFoundException {
        return injectByVariableResolver(targetBean, getVariableResolvers(() -> contexts));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
