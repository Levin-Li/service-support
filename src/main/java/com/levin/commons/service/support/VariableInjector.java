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

    /**
     * 为目标对象注入变量
     *
     * @param targetBean 被注入对象
     * @param suppliers  变量解析器支持列表
     * @return
     */
    List<String> injectByVariableResolver(Object targetBean, Supplier<List<VariableResolver>>... suppliers) throws VariableInjectException, VariableNotFoundException;

    /**
     * 获取变量解析器列表
     *
     * @param suppliers
     * @return
     */
    default List<VariableResolver> getVariableResolvers(Supplier<List<Map<String, Object>>>... suppliers) {
        return Arrays.asList(new VariableResolver.MapVariableResolver(suppliers));
    }

    /**
     * 获取变量解析器列表
     *
     * @param beans
     * @return
     */
    default VariableResolver getVariableResolver(Object... beans) {
        return new VariableResolver.BeanVariableResolver(beans);
    }

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
     * @param targetBean
     * @param suppliers  上下文环境变量支持列表
     * @return
     */
    default List<String> inject(Object targetBean, Supplier<List<Map<String, Object>>>... suppliers) throws VariableInjectException, VariableNotFoundException {
        return injectByVariableResolver(targetBean, getVariableResolvers(suppliers));
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param contexts   上下文环境
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<String> inject(Object targetBean, Map<String, Object>... contexts) throws VariableInjectException, VariableNotFoundException {
        return inject(targetBean, Arrays.asList(contexts));
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param contexts   上下文环境
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<String> inject(Object targetBean, List<Map<String, Object>> contexts) throws VariableInjectException, VariableNotFoundException {
        return inject(targetBean, () -> contexts);
    }


}
