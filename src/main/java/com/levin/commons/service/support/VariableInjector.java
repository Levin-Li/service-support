package com.levin.commons.service.support;

import com.levin.commons.service.domain.InjectVar;

import java.util.ArrayList;
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
        return injectByVariableResolvers(targetBean, newVariableResolver(beans));
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
        return injectByVariableResolvers(targetBean, newBuilder().addSupportSpelAndGroovy(() -> contexts).build());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static VariableResolver newVariableResolver(Supplier<List<?>>... suppliers) {
        return new VariableResolver.BeanVariableResolver(suppliers);
    }

    static VariableResolver newVariableResolver(List<?> beans) {
        return newVariableResolver(() -> beans);
    }

    static VariableResolver newMapVariableResolver(Supplier<List<Map<String, ?>>>... suppliers) {
        //强转
        return newVariableResolver((Supplier<List<?>>[]) Object.class.cast(suppliers));
    }

    static VariableResolver newMapVariableResolver(List<Map<String, ?>> contexts) {
        //强转
        return newVariableResolver((List<?>) Object.class.cast(contexts));
    }

    static VariableResolver newMapVariableResolver(Map<String, ?>... contexts) {
        return newVariableResolver(Arrays.asList(contexts));
    }

    /**
     * 获取 Spel 变量解析器
     *
     * @param suppliers 上下文列表
     * @return
     */
    static VariableResolver newSpelVariableResolver(Supplier<List<Map<String, ?>>>... suppliers) {
        return new VariableResolver.SpelVariableResolver(suppliers);
    }

    /**
     * groovy 变量解析器 上下文列表
     *
     * @param suppliers
     * @return
     */
    static VariableResolver newGroovyVariableResolver(Supplier<List<Map<String, ?>>>... suppliers) {
        return new VariableResolver.GroovyVariableResolver(suppliers);
    }

    static VariableResolverListBuilder newBuilder() {
        return VariableResolverListBuilder.newBuilder();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    class VariableResolverListBuilder {

        private VariableResolverListBuilder() {
        }

        public static VariableResolverListBuilder newBuilder() {
            return new VariableResolverListBuilder();
        }

        List<VariableResolver> variableResolverList = new ArrayList<>(7);

        public VariableResolverListBuilder add(VariableResolver... variableResolvers) {
            variableResolverList.addAll(Arrays.asList(variableResolvers));
            return this;
        }

        public VariableResolverListBuilder add(List<?>... beans) {
            variableResolverList.add(newVariableResolver(Arrays.asList(beans)));
            return this;
        }

        public VariableResolverListBuilder add(Map<String, ?>... contexts) {
            variableResolverList.add(newMapVariableResolver(contexts));
            return this;
        }

        public VariableResolverListBuilder addSupportSpelAndGroovy(Map<String, ?>... contexts) {
            return addSupportSpelAndGroovy(() -> Arrays.asList(contexts));
        }

        public VariableResolverListBuilder addSupportSpelAndGroovy(Supplier<List<Map<String, ?>>>... suppliers) {
            variableResolverList.add(newMapVariableResolver(suppliers));
            variableResolverList.add(newSpelVariableResolver(suppliers));
            variableResolverList.add(newGroovyVariableResolver(suppliers));
            return this;
        }

        public List<VariableResolver> build() {
            return variableResolverList;
        }

    }

}
