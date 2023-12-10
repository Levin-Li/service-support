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
import java.util.concurrent.atomic.AtomicReference;
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
    static boolean isExpectType(Type actualType, Type... expectTypes) {

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

        public DefaultDelegateVariableResolver(List<VariableResolver> variableResolvers) {
            this.variableResolvers.addAll(variableResolvers);
        }

        public static DefaultDelegateVariableResolver of(VariableResolver... variableResolvers) {
            return of(Arrays.asList(variableResolvers));
        }

        public static DefaultDelegateVariableResolver of(List<VariableResolver> variableResolvers) {
            return new DefaultDelegateVariableResolver(variableResolvers);
        }

        public DefaultDelegateVariableResolver addMapContexts(Object rootObject, Supplier<List<Map<String, ?>>>... suppliers) {

            //强制类型转换
            variableResolvers.add(new BeanVariableResolver((Supplier[]) suppliers));
            variableResolvers.add(new SpelVariableResolver(rootObject, suppliers));
            variableResolvers.add(new GroovyVariableResolver(rootObject, suppliers));

            return this;
        }

        public DefaultDelegateVariableResolver addMapContexts(Object rootObject, Map<String, ?>... contexts) {
            return addMapContexts(rootObject, () -> Arrays.asList(contexts));
        }

        public DefaultDelegateVariableResolver addBeanContexts(List<?> benas) {
            variableResolvers.add(new BeanVariableResolver(() -> benas));
            return this;
        }

        public DefaultDelegateVariableResolver addBeanContexts(Supplier<List<?>>... suppliers) {
            variableResolvers.add(new BeanVariableResolver(suppliers));
            return this;
        }

        public DefaultDelegateVariableResolver addVariableResolvers(VariableResolver... variableResolvers) {

            if (variableResolvers != null) {
                for (VariableResolver variableResolver : variableResolvers) {
                    this.variableResolvers.add(variableResolver);
                }
            }

            return this;
        }

        @Override
        public boolean isSupported(String name) {
            return variableResolvers.stream().anyMatch(vr -> vr.isSupported(name));
        }

        @Override
        public <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, boolean isRequireNotNull, Type... expectTypes) throws VariableNotFoundException {

            //保留最后一个异常，做为异常
            AtomicReference<Throwable> exRef = new AtomicReference();

            return variableResolvers.stream()
                    .filter(vr -> vr.isSupported(name))
                    .map(vr -> vr.resolve(name, originalValue, false, isRequireNotNull, expectTypes))
                    .map(vh -> {
                        if (vh.getValueNotFoundCause() != null) {
                            exRef.set(vh.getValueNotFoundCause());
                        }
                        return vh;
                    })
                    .filter(ValueHolder::hasValue)
                    .findFirst()
                    .orElse(ValueHolder.notValue(throwExWhenNotFound, name, exRef.get()));

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

        protected final Object rootObject;

        public ScriptResolver(Object rootObject, Supplier<List<Map<String, ?>>>... contextSuppliers) {
            Assert.notNull(contextSuppliers, "contextSuppliers is null");
            //倒序排列
            this.rootObject = rootObject;
            this.contextSuppliers = reverse(contextSuppliers);
        }

        /**
         * 数组倒序
         *
         * @param array
         * @param <T>
         * @return
         */
        private <T> T[] reverse(T[] array) {

            // 1,2,3,4,5
            // 5/2 = 2

            // 1,2,3,4,5,6
            // 6/2 = 3

            for (int i = 0; i < array.length / 2; i++) {
                T temp = array[i];
                array[i] = array[array.length - 1 - i];
                array[array.length - 1 - i] = temp;
            }

            return array;
        }

        /**
         * @param source
         * @param <T>
         * @return
         */
        <T> List<T> reverse(List<T> source) {
            //复制
            ArrayList<T> temp = new ArrayList<>(source);
            //倒序
            Collections.reverse(temp);
            return temp;
        }

        @Override
        public boolean isSupported(String name) {
            return name.trim().startsWith(getScriptPrefix());
        }

        abstract String getScriptPrefix();

        abstract Object eval(String scriptText, Object originalValue);

        /**
         * 初始化上下文
         * <p>
         * 需要倒序处理，因为参数是覆盖
         *
         * @param mapConsumer
         */
        protected void initContext(Consumer<Map> mapConsumer) {
            Stream.of(contextSuppliers)
                    .filter(Objects::nonNull)
                    .map(Supplier::get)
                    .map(this::reverse)
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
                        if (log.isTraceEnabled()) {
                            log.trace("eval [" + name + "] error", e);
                        }
                        return ValueHolder.notValue(throwExWhenNotFound, name, e);
                    }

                    if ((!isRequireNotNull || value != null)
                            && isExpectType(value != null ? value.getClass() : null, expectTypes)) {

                        if (log.isTraceEnabled()) {
                            log.trace("resolve variable [{}] in {}({}) found.", name, getClass().getSimpleName(), this.hashCode());
                        }

                        return new ValueHolder<T>()
                                .setValue((T) value)
                                .setName(name)
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

        public SpelVariableResolver(Object rootObject, Supplier<List<Map<String, ?>>>... suppliers) {
            super(rootObject, suppliers);
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

            return ExpressionUtils.evalSpEL(rootObject, scriptText, (ctx) -> {

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

        public GroovyVariableResolver(Object rootObject, Supplier<List<Map<String, ?>>>... suppliers) {
            super(rootObject, suppliers);
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
