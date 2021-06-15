package com.levin.commons.service.support;


import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
     * Map 变量解析器
     */
    @AllArgsConstructor
    class MapVariableResolver implements VariableResolver {

        Supplier<List<Map<String, Object>>>[] suppliers;

        @Override
        public <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, Class<?>... expectTypes) throws RuntimeException {

            for (Supplier<List<Map<String, Object>>> supplier : suppliers) {

                for (Map<String, Object> context : supplier.get()) {

                    if (context.containsKey(name)) {

                        Object value = context.get(name);

                        Class type = value != null ? value.getClass() : null;

                        if (expectTypes == null
                                || expectTypes.length < 1
                                || (type != null && Stream.of(expectTypes).anyMatch(c -> c.isAssignableFrom(type)))) {

//                            System.out.println(name + " --> " + value);

                            return new ValueHolder<T>()
                                    .setRoot(context)
                                    .setName(name)
                                    .setValue((T) value)
                                    .setHasValue(true);
                        }
                    }
                }

            }

            if (throwExWhenNotFound) {
                throw new VariableNotFoundException("variable " + name + " not found");
            }

            return ValueHolder.notValue();
        }

    }

}
