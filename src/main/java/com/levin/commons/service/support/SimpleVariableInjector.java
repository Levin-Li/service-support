package com.levin.commons.service.support;

import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.utils.ClassUtils;
import lombok.SneakyThrows;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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
     * 缓存
     * <p>
     * 允许中途释放
     */
    Map<Class<? extends GenericConverter>, GenericConverter> genericConverterInstanceCache = new ConcurrentReferenceHashMap<>();

    /**
     * 转换数据类型
     *
     * @param source
     * @param <T>
     * @return
     */
    default <T> T convert(@Nullable Object source, TypeDescriptor sourceTypeDescriptor, TypeDescriptor targetTypeDescriptor) {

        ResolvableType rt = targetTypeDescriptor.getResolvableType();

        cn.hutool.core.lang.Assert.isTrue(!rt.hasUnresolvableGenerics(), "目标类型中有未识别的泛型：" + rt);

        ValueHolder<T> holder = EnumDesc.convert(source, rt.hasGenerics() ? rt.getType() : rt.resolve());

        if (holder.hasValue()) {
            return holder.get();
        }

        //对特别的枚举类型进行转换
//        if (source instanceof EnumDesc) {
//            //如果是数值，并且源是枚举
//            if (Number.class.isAssignableFrom(targetType))
//                source = ((EnumDesc) source).code();
//            else if (CharSequence.class.isAssignableFrom(targetType))
//                source = ((EnumDesc) source).name();
//
//        } else if (targetType.isEnum() && EnumDesc.class.isAssignableFrom(targetType)) {
//            if (source instanceof Number)
//                return (T) EnumDesc.parse((Class<? extends Enum>) targetType, ((Number) source).intValue());
//            else if (source instanceof CharSequence)
//                return (T) EnumDesc.parse((Class<? extends Enum>) targetType, source.toString());
//        }

        return (T) defaultConversionService.convert(source, sourceTypeDescriptor, targetTypeDescriptor);
    }

    /**
     * 获取转换器
     * <p>
     * 可以考虑缓存
     *
     * @param injectVar
     * @return
     */
    @SneakyThrows
    default GenericConverter getConverter(InjectVar injectVar) {

        Class<? extends GenericConverter> type = injectVar.converter();

        Assert.notNull(type, "InjectVar.converter is require");

        GenericConverter converter = genericConverterInstanceCache.get(type);

        if (converter == null) {
            genericConverterInstanceCache.put(type, converter = type.newInstance());
        }

        return converter;
    }

    /**
     * 按字段注解注入变量
     * <p>
     * 功能实现的核心方法
     *
     * @param targetBean
     * @param variableResolvers 变量解析器列表
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    @Override
    default List<String> injectByVariableResolvers(Object targetBean, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {

        List<String> injectFields = new LinkedList<>();

        ResolvableType resolvableTypeRoot = ResolvableType.forClass(targetBean.getClass());
        //
        if (variableResolvers.isEmpty()) {
            //增加默认的脚本表达式支持
            variableResolvers = Arrays.asList(VariableInjector.newResolverByMap(Collections.emptyMap()));
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
            variableResolvers.add(VariableInjector.newResolverByMap(Collections.emptyMap()));
        }

        for (Field field : ClassUtils.getFields(targetBean.getClass(), InjectVar.class)) {
            injectFields.add(getInjectValue(targetBean, resolvableTypeRoot, variableResolvers, field, null, false));
        }

        return injectFields;
    }

    /**
     * 获取注解上声明的类型
     *
     * @param injectVar
     * @return
     */
    default ResolvableType getExpectResolvableType(InjectVar injectVar) {

        //获取类型
        Class<?> expectBaseType = injectVar.expectBaseType();

        if (expectBaseType == null || expectBaseType == Void.class) {
            return null;
        }

        Class<?>[] expectGenericTypes = injectVar.expectGenericTypes();

        //目标预期类型
        return (expectGenericTypes != null && expectGenericTypes.length > 0) ?
                ResolvableType.forClassWithGenerics(expectBaseType, expectGenericTypes)
                : ResolvableType.forClass(expectBaseType);
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
     * @param isInject           是否是注入到字段中，否则是输出
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


        //1、获取字段类型
        //  final Class<?> fieldType = forField.resolve(field.getType());

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
                    + "." + field.getName() + " annotation  InjectVar.isOverride [" + injectVar.isOverride() + "] can't eval", isOverride.getValueNotFoundCause());
        }

        //如果没有值或是 true，都认为是 true
        ValueHolder<Boolean> isRequired = getBooleanValueHolder(variableResolvers, injectVar.isRequired());

        if (!isRequired.hasValue()) {
            throw new VariableInjectException(field.getDeclaringClass().getName()
                    + "." + field.getName() + " annotation  InjectVar.isRequired [" + injectVar.isRequired() + "] can't eval", isRequired.getValueNotFoundCause());
        }

        if (!isOverride.get()
                && (originalValue != null || !isRequired.get())) {
            //如果不要求覆盖原值，并且 存在原值 或是 值不是必须的
            //跳过这个字段
            return ValueHolder.notValue(field.getName());
        }

        /////////////////////////////////////////////////////

        // 2、获取注入值
        String varName = injectVar.value();

        //默认等于变量名称
        if (!StringUtils.hasText(varName)) {
            varName = field.getName();
        }

        //获取类型

        //目标预期类型
        ResolvableType expectResolvableType = getExpectResolvableType(injectVar);

        //求值，对于注入是求值
        final ValueHolder<Object> valueHolder = isInject
                ? eval(varName, originalValue, Optional.ofNullable(expectResolvableType).map(ResolvableType::getType).orElse(null), isRequired.get(), variableResolvers)
                : new ValueHolder<>(originalValue).setHasValue(true);

        //如果没有指定类型
        if (!isInject && expectResolvableType == null) {
            if (valueHolder.getType() != null) {
                expectResolvableType = ResolvableType.forType(valueHolder.getType());
            } else if (valueHolder.getValue() != null) {
                expectResolvableType = ResolvableType.forType(valueHolder.getValue().getClass());
            }
        }

        if (expectResolvableType == null) {
            throw new VariableInjectException(injectVar.remark() + "," + field.getDeclaringClass().getName()
                    + "." + field.getName() + " inject annotation expectBaseType attribute is miss " + injectVar.expectBaseType());
        }

        if (resolvableTypeRoot == null) {
            resolvableTypeRoot = ResolvableType.forClass(targetBean.getClass());
        }

        final ResolvableType forField = ResolvableType.forField(field, resolvableTypeRoot);

        final ResolvableType targetResolvableType = isInject ? forField : expectResolvableType;

        //源目标类型
        Type sourceType = valueHolder.getType();

        //变更为目标类型
        valueHolder.setType(targetResolvableType.getType());

        if (valueHolder.hasValue()) {
            //4、如果变量获取成功
            try {

                Object newValue = valueHolder.get();

                Annotation[] emptyArray = new Annotation[0];

                if (sourceType == null && newValue != null) {
                    sourceType = newValue.getClass();
                }

                //创建描述
                //@todo 优化性能，考虑缓存
                TypeDescriptor sourceTypeDescriptor = sourceType == null ? null
                        : new TypeDescriptor(ResolvableType.forType(sourceType), null, emptyArray);

                TypeDescriptor targetTypeDescriptor = new TypeDescriptor(targetResolvableType, null, isInject ? field.getAnnotations() : emptyArray);

                if (injectVar.converter() == null
                        || injectVar.converter() == GenericConverter.class) {
                    //默认的转换方式
                    //转换并且注入变量
                    //转换数据类型
                    newValue = convert(newValue, sourceTypeDescriptor, targetTypeDescriptor);
                } else {
                    //临时创建转化器
                    newValue = getConverter(injectVar).convert(newValue, sourceTypeDescriptor, targetTypeDescriptor);
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
                    + "." + field.getName() + " inject var [" + varName + "] is required , but can't resolve", valueHolder.getValueNotFoundCause());
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

        //保留最后一个异常，做为异常
        AtomicReference<Throwable> exRef = new AtomicReference();

        return (ValueHolder<T>) variableResolvers.stream()
                .filter(Objects::nonNull)
                .filter(vr -> vr.isSupported(expr))
                .map(variableResolver -> variableResolver.resolve(expr, originalValue, false, isRequireNotNull, expectType))
                .filter(Objects::nonNull)
                .map(vh -> {
                    if (vh.getValueNotFoundCause() != null) {
                        exRef.set(vh.getValueNotFoundCause());
                    }
                    return vh;
                })
                .filter(ValueHolder::hasValue)
                .findFirst()
                .orElse(ValueHolder.notValue(false, expr, exRef.get()));
    }

}
