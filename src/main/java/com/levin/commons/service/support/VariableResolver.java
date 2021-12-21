package com.levin.commons.service.support;


import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 变量解析器
 *
 * @author llw
 */
@FunctionalInterface
public interface VariableResolver {

    /**
     *
     */
    Object NOT_VALUE = new Object();

    /**
     * 尝试获取变量值
     *
     * @param name
     * @param defaultValue
     * @param <T>
     * @return
     */
    default <T> T resolve(String name, T defaultValue) {
        return (T) resolve(name, null, false).get(defaultValue);
    }

    /**
     * 获取变量
     * <p>
     * 方法必须永远返回一个ValueHolder对象
     *
     * @param name                变量名
     * @param originalValue       原值
     * @param throwExWhenNotFound 当变量无法解析时是否抛出异常
     * @param expectTypes         期望的类型
     * @return ValueHolder<T>
     * @throws VariableNotFoundException 如果变量无法获取将抛出异常
     */
    <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, Class<?>... expectTypes) throws VariableNotFoundException;

    /**
     * 是否是预期的类型
     * <p>
     * null 匹配所有类型
     *
     * @param actualType
     * @param expectTypes
     * @return
     */
    default boolean isExpectType(Class actualType, Class<?>... expectTypes) {

        if (expectTypes == null
                || expectTypes.length == 0
                || actualType == null) {
            return true;
        }

        List<Class<?>> classList = Arrays.stream(expectTypes).filter(Objects::nonNull).collect(Collectors.toList());

        return classList.isEmpty() || classList.stream().anyMatch(c -> c.isAssignableFrom(actualType));
    }

    /**
     * Map 变量解析器
     */
    @Slf4j
    class MapVariableResolver implements VariableResolver {

        final Supplier<List<Map<String, Object>>>[] suppliers;

        public MapVariableResolver(Supplier<List<Map<String, Object>>>... suppliers) {
            this.suppliers = suppliers;
        }

        @Override
        public <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, Class<?>... expectTypes) throws RuntimeException {

            for (Supplier<List<Map<String, Object>>> supplier : suppliers) {

                if (log.isDebugEnabled()) {
                    log.debug("resolve variable [{}] in Map Supplier {}", name, supplier);
                }

                for (Map<String, Object> context : supplier.get()) {

                    if (context.containsKey(name)) {

                        Object value = context.get(name);

                        if (isExpectType(value != null ? value.getClass() : null, expectTypes)) {

                            return new ValueHolder<T>()
                                    .setRoot(context)
                                    .setName(name)
                                    .setValue((T) value)
                                    .setHasValue(true);
                        }
                    }
                }

            }

            return ValueHolder.notValue(throwExWhenNotFound, name);
        }

    }

}
