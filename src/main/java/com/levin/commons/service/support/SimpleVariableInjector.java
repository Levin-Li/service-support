package com.levin.commons.service.support;

import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.utils.ClassUtils;
import com.levin.commons.utils.ExpressionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 变量注入器
 */
public interface SimpleVariableInjector extends VariableInjector {

    SimpleVariableInjector defaultSimpleVariableInjector = new SimpleVariableInjector() {
    };

    /**
     * 线程安全转换服务
     */
    GenericConversionService defaultConversionService = new DefaultFormattingConversionService();

    default ConversionService getConversionService() {
        return defaultConversionService;
    }

    /**
     * 按字段注解注入变量
     * <p>
     * 功能实现的核心方法
     *
     * @param targetBean
     * @param suppliers  变量解析器列表
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    @Override
    default List<String> injectByVariableResolver(Object targetBean, Supplier<List<VariableResolver>>... suppliers) throws VariableInjectException, VariableNotFoundException {

        List<String> injectFields = new LinkedList<>();

        ResolvableType resolvableTypeRoot = ResolvableType.forClass(targetBean.getClass());

        //提取所有的变量解析器
        List<VariableResolver> variableResolvers = Stream.of(suppliers)
                .filter(Objects::nonNull)
                .map(Supplier::get)
                .filter(Objects::nonNull)
                .reduce(new LinkedList<>(), (first, second) -> {
                    first.addAll(second);
                    return first;
                });

        for (Field field : ClassUtils.getFields(targetBean.getClass(), InjectVar.class)) {

            InjectVar injectVar = field.getAnnotation(InjectVar.class);

            ResolvableType forField = ResolvableType.forField(field, resolvableTypeRoot);

            //1、获取字段类型
            Class<?> fieldType = forField.resolve(field.getType());

            field.setAccessible(true);

//            System.out.println(" *** "+injectVar.value()+" " + field.getName() +" ...");

            // 1、获取原值
            Object originalValue = null;//  field.get(targetBean);
            try {
                //2、获取原字段值
                originalValue = field.get(targetBean);
            } catch (IllegalAccessException e) {
                throw new VariableInjectException(field.getDeclaringClass().getName()
                        + "." + field.getName() + " can't get originalValue ," + injectVar.remark(), e);
            }

            //如果没有值或是 true，都认为是 true
            ValueHolder<Boolean> isOverride = getBooleanValueHolder(variableResolvers, injectVar.isOverride());

            if (!isOverride.hasValue()) {
                throw new VariableInjectException(field.getDeclaringClass().getName()
                        + "." + field.getName() + " annotation  InjectVar.isOverride [" + injectVar.isOverride() + "] can't eval");
            }

            //如果没有值或是 true，都认为是 true
            ValueHolder<Boolean> isRequired = getBooleanValueHolder(variableResolvers, injectVar.isRequired());

            if (!isRequired.hasValue()) {
                throw new VariableInjectException(field.getDeclaringClass().getName()
                        + "." + field.getName() + " annotation  InjectVar.isRequired [" + injectVar.isRequired() + "] can't eval");
            }

            if (!isOverride.get()
                    && (originalValue != null || !isRequired.get())) {
                //如果不要求覆盖原值，并且 存在原值 或是 值不是必须的
                //跳过这个字段
                continue;
            }

            // 2、获取注入值
            String varName = injectVar.value();

            //默认等于变量名称
            if (!StringUtils.hasText(varName)) {
                varName = field.getName();
            }

            ValueHolder<Object> valueHolder = eval(varName, originalValue, null, variableResolvers);


            if (valueHolder.hasValue()) {
                //4、如果变量获取成功
                try {
                    //转换并且注入变量
                    ConversionService conversionService = getConversionService();

                    final Object newValue = valueHolder.get();

                    Object convertValue = conversionService != null ? conversionService.convert(valueHolder.get(), fieldType) : newValue;

                    field.set(targetBean, convertValue);

                } catch (IllegalAccessException e) {
                    throw new VariableInjectException(field.getDeclaringClass().getName()
                            + "." + field.getName() + " inject var [" + varName + "] can't inject," + injectVar.remark(), e);
                }
            }

            //如果不允许为 null 值，则抛出异常
            if (isRequired.get() && !valueHolder.hasValue()) {
                //如果变量是必须的，则抛出异常
                throw new VariableNotFoundException(injectVar.remark() + " --> " + field.getDeclaringClass().getName()
                        + "." + field.getName() + " inject var [" + varName + "] is required , but can't resolve");
            }

            injectFields.add(field.getName());
        }

        return injectFields;
    }

    /**
     * @param variableResolvers
     * @param expr
     * @return
     */
    default ValueHolder<Boolean> getBooleanValueHolder(List<VariableResolver> variableResolvers, String expr) {

        //如果是 true 或是 空值，都任务是 true
        boolean isTrue = !StringUtils.hasText(expr) || Boolean.TRUE.toString().equalsIgnoreCase(expr.trim());

        boolean isFalse = !isTrue && Boolean.FALSE.toString().equalsIgnoreCase(expr.trim());

        ValueHolder<Boolean> booleanValueHolder = new ValueHolder<Boolean>().setHasValue(isTrue || isFalse).setValue(isTrue);

        //如果没有值
        if (!booleanValueHolder.hasValue()) {
            //
            booleanValueHolder = eval(expr, VariableResolver.NOT_VALUE, Boolean.class,
                    (variableResolvers != null && variableResolvers.size() > 0) ?
                            variableResolvers : getVariableResolvers(() -> Arrays.asList(Collections.emptyMap())));
        }

        return booleanValueHolder;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取变量解析器列表
     *
     * @param suppliers 上下文列表
     * @return
     */
    @Override
    default List<VariableResolver> getVariableResolvers(Supplier<List<Map<String, Object>>>... suppliers) {
        return Arrays.asList(getSpelVariableResolver(suppliers)
                , getGroovyVariableResolver(suppliers)
                , getSimpleVariableResolver(suppliers));
    }

    /**
     * 获取简单变量解析器
     *
     * @param suppliers 上下文列表
     * @return
     */
    default VariableResolver getSimpleVariableResolver(Supplier<List<Map<String, Object>>>... suppliers) {
        return new VariableResolver.MapVariableResolver(suppliers);
    }

    /**
     * 获取 Spel 变量解析器
     *
     * @param suppliers 上下文列表
     * @return
     */
    default VariableResolver getSpelVariableResolver(Supplier<List<Map<String, Object>>>... suppliers) {
        return new SpelVariableResolver(suppliers);
    }

    /**
     * groovy 变量解析器 上下文列表
     *
     * @param suppliers
     * @return
     */
    default VariableResolver getGroovyVariableResolver(Supplier<List<Map<String, Object>>>... suppliers) {
        return new GroovyVariableResolver(suppliers);
    }

    /**
     * @param expr
     * @param originalValue
     * @param fieldType
     * @param variableResolvers
     * @return
     */
    static ValueHolder<Object> eval(String expr, Object originalValue, Class fieldType, VariableResolver... variableResolvers) {
        return eval(expr, originalValue, fieldType, Arrays.asList(variableResolvers));
    }

    /**
     * @param expr
     * @param originalValue
     * @param fieldType
     * @param variableResolvers
     * @return
     */
    static <T> ValueHolder<T> eval(String expr, Object originalValue, Class fieldType, List<VariableResolver> variableResolvers) {

        return (ValueHolder<T>) variableResolvers.stream()
                .filter(Objects::nonNull)
                .map(variableResolver -> variableResolver.resolve(expr, originalValue, false, fieldType))
                .filter(Objects::nonNull)
                .filter(ValueHolder::hasValue)
                .findFirst()
                .orElse(ValueHolder.notValue());

    }


    ///////////////////////////////////////////////////////////////////////
    @AllArgsConstructor
    @Slf4j
    abstract class ScriptResolver implements VariableResolver {

        protected Supplier<List<Map<String, Object>>>[] suppliers;

        abstract String getScriptPrefix();

        abstract Object eval(String scriptText, Object originalValue);

        @Override
        public <T> ValueHolder<T> resolve(String name, T originalValue, boolean throwExWhenNotFound, Class<?>... expectTypes) throws RuntimeException {

            if (log.isDebugEnabled()) {
                log.debug("resolve variable [{}] in {}({}) ...", name, getClass().getSimpleName(), this.hashCode());
            }

            if (name != null
                    && name.trim().toLowerCase().startsWith(getScriptPrefix().toLowerCase())) {

                //截取前缀
                name = name.substring(getScriptPrefix().length());

                Object value = eval(name, originalValue);

                // System.out.println(getScriptPrefix() + name + " --> " + value);

                Class type = value != null ? value.getClass() : null;

                if (expectTypes == null
                        || expectTypes.length < 1
                        //如果有要求类型
                        || (type != null && Stream.of(expectTypes).anyMatch(c -> c.isAssignableFrom(type)))) {

                    return new ValueHolder<T>()
                            .setValue((T) value)
                            .setHasValue(true);
                }
            }

            if (throwExWhenNotFound) {
                throw new VariableNotFoundException("variable " + name + " not found");
            }

            return ValueHolder.notValue();
        }

    }


    /**
     * spel 解析器
     */
    @Slf4j
    class SpelVariableResolver extends ScriptResolver {

        public SpelVariableResolver(Supplier<List<Map<String, Object>>>[] suppliers) {
            super(suppliers);
        }

        @Override
        String getScriptPrefix() {
            return InjectVar.SPEL_PREFIX;
        }

        @Override
        Object eval(String scriptText, Object originalValue) {

            if (log.isDebugEnabled()) {
                log.debug(" eval [ {} ] ...", scriptText);
            }

            return ExpressionUtils.evalSpEL(null, scriptText, (ctx) -> {

                if (SpringContextHolder.getBeanFactory() != null) {
                    ctx.setBeanResolver(new BeanFactoryResolver(SpringContextHolder.getBeanFactory()));
                }

                Stream.of(suppliers)
                        .filter(Objects::nonNull)
                        .map(Supplier::get)
                        .filter(Objects::nonNull)
                        .forEachOrdered(
                                //设置变量到 spel 的上下文
                                list -> list.stream()
                                        .filter(Objects::nonNull)
                                        .forEachOrdered(ctx::setVariables)
                        );
            });

        }
    }

    /**
     * groovy 解析器
     */
    @Slf4j
    class GroovyVariableResolver extends ScriptResolver {

        public GroovyVariableResolver(Supplier<List<Map<String, Object>>>... suppliers) {
            super(suppliers);
        }

        @Override
        String getScriptPrefix() {
            return InjectVar.GROOVY_PREFIX;
        }

        @Override
        Object eval(String scriptText, Object originalValue) {

            if (log.isDebugEnabled()) {
                log.debug(" eval [ {} ] ...", scriptText);
            }

            return ExpressionUtils.evalGroovy(scriptText, (groovyScriptEvaluator, ctx) -> {

                //注入 Spring 上下文
                ctx.putIfAbsent("springContext", SpringContextHolder.getApplicationContext());

                Stream.of(suppliers)
                        .filter(Objects::nonNull)
                        .map(Supplier::get)
                        .filter(Objects::nonNull)
                        .forEachOrdered(
                                //设置变量到 spel 的上下文
                                list -> list.stream()
                                        .filter(Objects::nonNull)
                                        .forEachOrdered(ctx::putAll)
                        );

                //设置
                if (SpringContextHolder.getClassLoader() != null) {
                    groovyScriptEvaluator.setBeanClassLoader(SpringContextHolder.getClassLoader());
                }
            });
        }

    }


}
