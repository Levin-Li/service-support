package com.levin.commons.service.support;


import com.levin.commons.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.*;
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
     * Bean 变量解析器
     */
    @Slf4j
    class BeanVariableResolver implements VariableResolver {

        final List<Supplier<List<?>>> suppliers;

        public BeanVariableResolver(Object... beans) {
            this(Arrays.asList(() -> Arrays.asList(beans)));
        }

        public BeanVariableResolver(Supplier<List<?>>... suppliers) {
            this(Arrays.asList(suppliers));
        }

        public BeanVariableResolver(List<Supplier<List<?>>> suppliers) {
            Assert.notNull(suppliers, "suppliers is null");
            this.suppliers = suppliers;
        }

        @Override
        public <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, Class<?>... expectTypes) throws RuntimeException {

            for (Supplier<List<?>> supplier : suppliers) {

                if (log.isDebugEnabled()) {
                    log.debug("resolve variable [{}] in Map Supplier {}", name, supplier);
                }

                for (Object context : supplier.get()) {

                    ValueHolder<?> holder = ClassUtils.getIndexValue(context, name);

                    if (holder.hasValue()) {

                        Object value = holder.getValue();

                        if (isExpectType(value != null ? value.getClass() : null, expectTypes)) {
                            return (ValueHolder<T>) holder;
                        }

                    }
                }
            }

            return ValueHolder.notValue(throwExWhenNotFound, name);
        }
    }

    /**
     * Map 变量解析器
     */
    @Slf4j
    class MapVariableResolver extends BeanVariableResolver {

        public MapVariableResolver(Supplier<List<Map<String, Object>>>... suppliers) {
            super(c(suppliers));
        }

        private static List<Supplier<List<?>>> c(Supplier<List<Map<String, Object>>>... suppliers) {

            Assert.notNull(suppliers, "suppliers is null");

            List<Supplier<List<?>>> supplierList = new ArrayList<>(suppliers.length);

            for (Object supplier : suppliers) {
                supplierList.add((Supplier<List<?>>) supplier);
            }

            return supplierList;
        }
    }

}
