package com.levin.commons.service.support;

import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.utils.ClassUtils;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 变量注入器
 */
public interface SimpleVariableInjector extends VariableInjector {

    /**
     * 默认注入器
     */
    SimpleVariableInjector defaultSimpleVariableInjector = new SimpleVariableInjector() {
    };

    /**
     * 线程安全转换服务
     */
    ConfigurableConversionService defaultConversionService = new DefaultFormattingConversionService();

    /**
     * 获取转换服务
     *
     * @return
     */
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
    default List<String> injectByVariableResolvers(Object targetBean, Supplier<List<VariableResolver>>... suppliers) throws VariableInjectException, VariableNotFoundException {

        List<String> injectFields = new LinkedList<>();

        ResolvableType resolvableTypeRoot = ResolvableType.forClass(targetBean.getClass());

        //提取所有的变量解析器
        List<VariableResolver> variableResolvers = Stream.of(suppliers)
                .filter(Objects::nonNull)
                .map(Supplier::get)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        //

        if (variableResolvers.isEmpty()) {
            //增加默认的脚本表达式支持
            variableResolvers.add(VariableInjector.newDefaultResolver().addMapContexts(Collections.emptyMap()));
        }

        for (Field field : ClassUtils.getFields(targetBean.getClass(), InjectVar.class)) {

            getInjectValue(targetBean, resolvableTypeRoot, variableResolvers, field, null, true);

            injectFields.add(field.getName());
        }

        return injectFields;
    }

    /**
     * @param targetBean
     * @param variableResolvers
     * @param field
     * @return
     */
    @Override
    default ValueHolder<Object> getInjectValue(Object targetBean, List<VariableResolver> variableResolvers, Field field) {
        return getInjectValue(targetBean, null, variableResolvers, field, null, false);
    }

    /**
     * @param targetBean
     * @param variableResolvers
     * @return
     */
    @Override
    default List<ValueHolder<Object>> getInjectValues(Object targetBean, List<VariableResolver> variableResolvers) {

        List<ValueHolder<Object>> injectFields = new LinkedList<>();

        ResolvableType resolvableTypeRoot = ResolvableType.forClass(targetBean.getClass());

        if (variableResolvers.isEmpty()) {
            //增加默认的脚本表达式支持
            variableResolvers.add(VariableInjector.newDefaultResolver().addMapContexts(Collections.emptyMap()));
        }

        for (Field field : ClassUtils.getFields(targetBean.getClass(), InjectVar.class)) {
            injectFields.add(getInjectValue(targetBean, resolvableTypeRoot, variableResolvers, field, null, false));
        }

        return injectFields;
    }


    /**
     * 获取注入值
     * 关键方法
     *
     * @param targetBean
     * @param resolvableTypeRoot
     * @param variableResolvers
     * @param field
     * @param injectVar
     * @param isInject
     * @return
     */
    default ValueHolder<Object> getInjectValue(Object targetBean, ResolvableType resolvableTypeRoot, List<VariableResolver> variableResolvers, Field field, InjectVar injectVar, boolean isInject) {

        if (injectVar == null) {
            injectVar = field.getAnnotation(InjectVar.class);
        }

        if (injectVar == null) {
            return null;
        }

        //如果有指定注入域，只处理指定的域
        if (StringUtils.hasText(getInjectDomain())
                && !getInjectDomain().equals(injectVar.domain())) {
            return null;
        }

        if (resolvableTypeRoot == null) {
            resolvableTypeRoot = ResolvableType.forClass(targetBean.getClass());
        }

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
            return ValueHolder.notValue();
        }

        /////////////////////////////////////////////////////

        // 2、获取注入值
        String varName = injectVar.value();

        //默认等于变量名称
        if (!StringUtils.hasText(varName)) {
            varName = field.getName();
        }

        //获取类型
        Class<?> expectBaseType = injectVar.expectBaseType();
        Class<?>[] expectGenericTypes = injectVar.expectGenericTypes();

        boolean hasSubTypes = expectGenericTypes != null && expectGenericTypes.length > 0;

        Type expectType = (expectBaseType == null || expectBaseType == Void.class) ? null
                : (hasSubTypes ? ResolvableType.forClassWithGenerics(expectBaseType, expectGenericTypes).getType() : ResolvableType.forClass(expectBaseType).getType());

        //求值
        ValueHolder<Object> valueHolder = eval(varName, originalValue, expectType, isRequired.get(), variableResolvers);

        if (valueHolder.hasValue()) {
            //4、如果变量获取成功
            try {

                Object newValue = valueHolder.get();

                if (injectVar.converter() == null
                        || injectVar.converter() == GenericConverter.class) {
                    //默认的转换方式
                    //转换并且注入变量
                    ConversionService conversionService = getConversionService();

                    newValue = conversionService == null ? newValue : conversionService.convert(newValue, fieldType);

                } else {
                    //临时创建转化器
                    newValue = injectVar.converter().newInstance().convert(newValue,
                            newValue == null ? null : new TypeDescriptor(ResolvableType.forClass(newValue.getClass()), newValue.getClass(), new Annotation[0]),
                            new TypeDescriptor(forField, fieldType, field.getAnnotations()));
                }

                if (isInject) {
                    field.set(targetBean, newValue);
                }

                //放入转换后的值
                valueHolder.setValue(newValue);

            } catch (Exception e) {
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

        //输出的名字
        valueHolder.setName(StringUtils.hasText(injectVar.outputVarName()) ? injectVar.outputVarName() : field.getName());

        return valueHolder;

    }

    /**
     * @param variableResolvers
     * @param expr
     * @return
     */
    default ValueHolder<Boolean> getBooleanValueHolder(List<VariableResolver> variableResolvers, String expr) {

        //特别处理
        //如果是 true 或是 空值，都任务是 true
        boolean isTrue = !StringUtils.hasText(expr) || Boolean.TRUE.toString().equalsIgnoreCase(expr.trim());

        boolean isFalse = !isTrue && Boolean.FALSE.toString().equalsIgnoreCase(expr.trim());

        ValueHolder<Boolean> booleanValueHolder = new ValueHolder<Boolean>().setHasValue(isTrue || isFalse).setValue(isTrue);

        //如果没有值
        if (!booleanValueHolder.hasValue()) {
            booleanValueHolder = eval(expr, VariableResolver.NOT_VALUE, Boolean.class, true, variableResolvers);
        }

        return booleanValueHolder;
    }

    /**
     * @param expr
     * @param originalValue
     * @param expectType
     * @param variableResolvers
     * @return
     */
    static ValueHolder<Object> eval(String expr, Object originalValue, Type expectType, boolean isRequireNotNull, VariableResolver... variableResolvers) {
        return eval(expr, originalValue, expectType, isRequireNotNull, Arrays.asList(variableResolvers));
    }

    /**
     * 求值函数
     *
     * @param expr
     * @param originalValue
     * @param expectType
     * @param variableResolvers
     * @return
     */
    static <T> ValueHolder<T> eval(String expr, Object originalValue, Type expectType, boolean isRequireNotNull, List<VariableResolver> variableResolvers) {

        return (ValueHolder<T>) variableResolvers.stream()
                .filter(Objects::nonNull)
                .filter(vr -> vr.isSupported(expr))
                .map(variableResolver -> variableResolver.resolve(expr, originalValue, false, isRequireNotNull, expectType))
                .filter(Objects::nonNull)
                .filter(ValueHolder::hasValue)
                .findFirst()
                .orElse(ValueHolder.notValue());
    }

}
