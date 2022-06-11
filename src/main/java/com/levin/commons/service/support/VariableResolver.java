package com.levin.commons.service.support;


import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.utils.ClassUtils;
import com.levin.commons.utils.ExpressionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.util.Assert;
import org.springframework.util.TypeUtils;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 变量解析器
 *
 * @author llw
 */
@FunctionalInterface
public interface VariableResolver {

    /**
     * 是否是表达式
     * 表达式格式为：  #! + 脚本名称 + : + 表达式，例如： #!spel:aa
     *
     * @param name
     * @return
     */
    static boolean isEL(String name) {

        name = name.trim();

        int idx = name.indexOf(':');

//        #!spel:
        // 6 -2 = 4

        return idx > 2
                && name.startsWith("#!")
                && name.substring(2, idx).chars().allMatch(Character::isLetterOrDigit);
    }

    /**
     * 是否支持指定的变量
     *
     * @param name
     * @return
     */
    default boolean isSupported(String name) {
        return true;
    }

    /**
     * 标记没有值的特殊对象
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
     * @param isRequireNotNull    是否需要非空值
     * @param expectTypes         期望的类型
     * @return ValueHolder<T>
     * @throws VariableNotFoundException 如果变量无法获取将抛出异常
     */
    <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, boolean isRequireNotNull, Type... expectTypes) throws VariableNotFoundException;

    /**
     * 尝试获取变量值
     *
     * @param name
     * @param defaultValue
     * @param <T>
     * @return
     */
    default <T> T resolve(String name, boolean isRequireNotNull, T defaultValue) {
        return (T) resolve(name, null, false, isRequireNotNull).get(defaultValue);
    }

    /**
     * 是否是预期的类型
     * <p>
     * null 匹配所有类型
     *
     * @param actualType
     * @param expectTypes
     * @return
     */
    default boolean isExpectType(Type actualType, Type... expectTypes) {

        if (expectTypes == null
                || expectTypes.length == 0
                || actualType == null) {
            return true;
        }

        // List<?> isAssignable List<String>
        // List<?> aa = new ArrayList<String>();

        List<Type> typeList = Arrays.stream(expectTypes).filter(Objects::nonNull).collect(Collectors.toList());

        return typeList.isEmpty() || typeList.stream().anyMatch(expectType -> TypeUtils.isAssignableBound(expectType, actualType));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 聚合解析器
     */
    @Slf4j
    class DefaultDelegateVariableResolver implements VariableResolver {

        final List<VariableResolver> variableResolvers = new ArrayList<>(5);

        public DefaultDelegateVariableResolver() {
        }

        public DefaultDelegateVariableResolver addMapContexts(Supplier<List<Map<String, ?>>>... suppliers) {

            //强制类型转换
            variableResolvers.add(new BeanVariableResolver((Supplier[]) suppliers));
            variableResolvers.add(new SpelVariableResolver(suppliers));
            variableResolvers.add(new GroovyVariableResolver(suppliers));

            return this;
        }

        public DefaultDelegateVariableResolver addMapContexts(Map<String, ?>... contexts) {
            return addMapContexts(() -> Arrays.asList(contexts));
        }

        public <T extends Object> DefaultDelegateVariableResolver addBeanContexts(T... benas) {
            variableResolvers.add(new BeanVariableResolver(() -> Arrays.asList(benas)));
            return this;
        }

        public DefaultDelegateVariableResolver addBeanContexts(Supplier<List<?>>... suppliers) {
            variableResolvers.add(new BeanVariableResolver(suppliers));
            return this;
        }

        @Override
        public boolean isSupported(String name) {
            return variableResolvers.stream().anyMatch(vr -> vr.isSupported(name));
        }

        @Override
        public <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, boolean isRequireNotNull, Type... expectTypes) throws VariableNotFoundException {

            return variableResolvers.stream()
                    .filter(vr -> vr.isSupported(name))
                    .map(vr -> vr.resolve(name, originalValue, false, isRequireNotNull, expectTypes))
                    .filter(ValueHolder::hasValue)
                    .findFirst()
                    .orElse(ValueHolder.notValue(throwExWhenNotFound, name));

        }

    }


    /**
     * Bean 变量解析器
     */
    @Slf4j
    class BeanVariableResolver implements VariableResolver {

        final Supplier<List<?>>[] contextSuppliers;

        public BeanVariableResolver(Supplier<List<?>>... contextSuppliers) {
            Assert.notNull(contextSuppliers, "contextSuppliers is null");
            this.contextSuppliers = contextSuppliers;
        }

        @Override
        public boolean isSupported(String name) {
            return !isEL(name);
        }

        @Override
        public <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, boolean isRequireNotNull, Type... expectTypes) throws RuntimeException {

            if (isSupported(name)) {

                for (Supplier<List<?>> supplier : contextSuppliers) {

                    if (log.isTraceEnabled()) {
                        log.trace("resolve variable [{}] in Map Supplier {}", name, supplier);
                    }

                    for (Object context : supplier.get()) {

                        ValueHolder<?> holder = ClassUtils.getIndexValue(context, name);

                        if (holder.hasValue()
                                //是否允许空值
                                && (!isRequireNotNull || holder.isNotNull())
                                // 是否类型匹配
                                && isExpectType(holder.getType(), expectTypes)) {

                            if (log.isDebugEnabled()) {
                                log.debug("resolve variable [{}] in Map Supplier {} found.", name, supplier);
                            }

                            return (ValueHolder<T>) holder;
                        }

                    }
                }
            }

            return ValueHolder.notValue(throwExWhenNotFound, name);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Slf4j
    abstract class ScriptResolver implements VariableResolver {

        protected final Supplier<List<Map<String, ?>>>[] contextSuppliers;

        public ScriptResolver(Supplier<List<Map<String, ?>>>... contextSuppliers) {
            Assert.notNull(contextSuppliers, "contextSuppliers is null");
            this.contextSuppliers = contextSuppliers;
        }

        @Override
        public boolean isSupported(String name) {
            return name.trim().startsWith(getScriptPrefix());
        }

        abstract String getScriptPrefix();

        abstract Object eval(String scriptText, Object originalValue);

        /**
         * 初始化上下文
         *
         * @param mapConsumer
         */
        protected void initContext(Consumer<Map> mapConsumer) {
            Stream.of(contextSuppliers)
                    .filter(Objects::nonNull)
                    .map(Supplier::get)
                    .filter(Objects::nonNull)
                    .flatMap(List::stream)
                    .filter(Objects::nonNull)
                    .forEachOrdered(mapConsumer);
        }

        @Override
        public <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, boolean isRequireNotNull, Type... expectTypes) throws RuntimeException {

            if (isSupported(name)) {

                if (log.isTraceEnabled()) {
                    log.trace("resolve variable [{}] in {}({}) ...", name, getClass().getSimpleName(), this.hashCode());
                }

                if (name != null
                        && name.trim().toLowerCase().startsWith(getScriptPrefix().toLowerCase())) {

                    //截取前缀
                    name = name.substring(getScriptPrefix().length());

                    Object value = null;

                    try {
                        value = eval(name, originalValue);
                    } catch (Exception e) {

                        //脚本执行异常
                        if (log.isDebugEnabled()) {
                            log.debug("eval [" + name + "] error", e);
                        }

                        return ValueHolder.notValue(throwExWhenNotFound, name);
                    }

                    if ((!isRequireNotNull || value != null)
                            && isExpectType(value != null ? value.getClass() : null, expectTypes)) {

                        if (log.isDebugEnabled()) {
                            log.debug("resolve variable [{}] in {}({}) found.", name, getClass().getSimpleName(), this.hashCode());
                        }

                        return new ValueHolder<T>()
                                .setValue((T) value)
                                .setHasValue(true);
                    }
                }
            }

            return ValueHolder.notValue(throwExWhenNotFound, name);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * spel 解析器
     */
    @Slf4j
    class SpelVariableResolver extends ScriptResolver {

        public SpelVariableResolver(Supplier<List<Map<String, ?>>>... suppliers) {
            super(suppliers);
        }

        @Override
        String getScriptPrefix() {
            return InjectVar.SPEL_PREFIX;
        }

        @Override
        Object eval(String scriptText, Object originalValue) {
//
//            if (log.isDebugEnabled()) {
//                log.debug(" eval [ {} ] ...", scriptText);
//            }

            return ExpressionUtils.evalSpEL(null, scriptText, (ctx) -> {

                if (SpringContextHolder.getBeanFactory() != null) {
                    ctx.setBeanResolver(new BeanFactoryResolver(SpringContextHolder.getBeanFactory()));
                }

                initContext(ctx::setVariables);
            });

        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * groovy 解析器
     */
    @Slf4j
    class GroovyVariableResolver extends ScriptResolver {

        public GroovyVariableResolver(Supplier<List<Map<String, ?>>>... suppliers) {
            super(suppliers);
        }

        @Override
        String getScriptPrefix() {
            return InjectVar.GROOVY_PREFIX;
        }

        @Override
        Object eval(String scriptText, Object originalValue) {
//
//            if (log.isDebugEnabled()) {
//                log.debug(" eval [ {} ] ...", scriptText);
//            }

            return ExpressionUtils.evalGroovy(scriptText, (groovyScriptEvaluator, ctx) -> {

                //注入 Spring 上下文
                ctx.putIfAbsent("springContext", SpringContextHolder.getApplicationContext());

                initContext(ctx::putAll);

                //设置
                if (SpringContextHolder.getClassLoader() != null) {
                    groovyScriptEvaluator.setBeanClassLoader(SpringContextHolder.getClassLoader());
                }
            });
        }
    }

}
