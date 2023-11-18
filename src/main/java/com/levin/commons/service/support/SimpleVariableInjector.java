package com.levin.commons.service.support;

import cn.hutool.core.util.TypeUtil;
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.utils.ClassUtils;
import lombok.SneakyThrows;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * 变量注入器
 */
public interface SimpleVariableInjector extends VariableInjector {

    /**
     * 缓存
     * <p>
     * 允许中途释放
     */
    Map<Class<? extends GenericConverter>, GenericConverter> genericConverterInstanceCache = new ConcurrentReferenceHashMap<>();

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
///////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 执行注入处理
     *
     * @param targetBean
     * @param field
     * @param mode
     * @param variableResolvers
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    @Override
    default ValueHolder<Object> doInject(Object targetBean, Field field, Mode mode, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return doInject(targetBean, null, field, null, !mode.equals(Mode.FieldConvertOutput), mode.equals(Mode.InjectToField), variableResolvers);
    }

    /**
     * 执行注入
     *
     * @param targetBean
     * @param ignoreFieldPredicate
     * @param mode                 注入模式，1 = 输出转换值，2 = 获取注入值，3 = 注入targetBean
     * @param variableResolvers
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    @Override
    default List<ValueHolder<Object>> doInject(Object targetBean, Predicate<Field> ignoreFieldPredicate, Mode mode, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        List<ValueHolder<Object>> injectFields = new LinkedList<>();
        doInject(targetBean, ignoreFieldPredicate, mode, variableResolvers, (field, objectValueHolder) -> injectFields.add(objectValueHolder));
        return injectFields;
    }

    /**
     * 执行注入
     *
     * @param targetBean
     * @param ignoreFieldPredicate
     * @param mode                 注入模式，1 = 输出转换值，2 = 获取注入值，3 = 注入targetBean
     * @param variableResolvers
     * @param callback
     * @return
     */
    default boolean doInject(Object targetBean, Predicate<Field> ignoreFieldPredicate, Mode mode
            , List<VariableResolver> variableResolvers, BiFunction<Field, ValueHolder<Object>, Boolean> callback) {

        Assert.notNull(mode, "mode is require");

        ResolvableType resolvableTypeRoot = ResolvableType.forClass(targetBean.getClass());

        for (Field field : ClassUtils.getFields(targetBean.getClass(), InjectVar.class)) {

            if (ignoreFieldPredicate != null
                    && ignoreFieldPredicate.test(field)) {
                continue;
            }

            ValueHolder<Object> valueHolder = doInject(targetBean, resolvableTypeRoot, field,
                    null, !mode.equals(Mode.FieldConvertOutput), mode.equals(Mode.InjectToField), variableResolvers);

            //中断执行
            if (Boolean.FALSE.equals(callback.apply(field, valueHolder))) {
                return false;
            }

        }

        return true;
    }


    /**
     * 执行注入
     * 关键方法 核心方法
     * <p>
     * 实现根据InjectVar注解注入到目标bean的字段
     * 或是只是获取注入值然后返回
     *
     * @param targetBean
     * @param resolvableTypeRoot
     * @param field
     * @param injectVar
     * @param isInput            是否是注入到字段中，否则是反向输出
     * @param isInjectToBean     是否注入值到targetBean
     * @param variableResolvers
     * @return
     */
    default ValueHolder<Object> doInject(Object targetBean, ResolvableType resolvableTypeRoot
            , Field field, InjectVar injectVar, boolean isInput, boolean isInjectToBean, List<VariableResolver> variableResolvers) {

        if (injectVar == null) {
            injectVar = field.getAnnotation(InjectVar.class);
        }

        if (injectVar == null) {
            return null;
        }

        //如果注入域不匹配
        if (!isDomainMatch(injectVar.domain())) {
            return null;
        }

        //1、获取字段类型
        //  final Class<?> fieldType = forField.resolve(field.getType());

        field.setAccessible(true);

        // 1、获取原值
        Object originalValue = null;//  field.get(targetBean);

        try {
            //2、获取原字段值
            originalValue = field.get(targetBean);
        } catch (IllegalAccessException e) {
            throw new VariableInjectException(field.getDeclaringClass().getName()
                    + "." + field.getName() + " can't get originalValue ," + injectVar.remark(), e);
        }

        /////////////////////////////////解析器列表/////////////////////////////////////////
        //加上默认的变量解析器
        List<VariableResolver> defaultVariableResolvers = getDefaultVariableResolvers();

        if (defaultVariableResolvers != null && !defaultVariableResolvers.isEmpty()) {

            //复制自己
            variableResolvers = new ArrayList<>(variableResolvers != null ? variableResolvers : Collections.emptyList());

            //添加默认的变量解析器
            variableResolvers.addAll(defaultVariableResolvers);
        }

        //确保有解析器
        if (variableResolvers == null || variableResolvers.isEmpty()) {
            variableResolvers = Arrays.asList(VariableInjector.newResolverByMap(Collections.emptyMap()));
        }
        /////////////////////////////////解析器列表/////////////////////////////////////////


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
        //关键逻辑
        // isInput 为 false ，则是方向输出当前字段值，然后转换输出，
        final ValueHolder<Object> valueHolder = isInput
                ? eval(varName, originalValue, Optional.ofNullable(expectResolvableType).map(ResolvableType::getType).orElse(null), isRequired.get(), variableResolvers)
                : new ValueHolder<>(originalValue).setHasValue(true);

        //如果没有指定类型
        if (!isInput && expectResolvableType == null) {
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

        final ResolvableType targetResolvableType = isInput ? forField : expectResolvableType;

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

                TypeDescriptor targetTypeDescriptor = new TypeDescriptor(targetResolvableType, null, isInput ? field.getAnnotations() : emptyArray);

                if (injectVar.converter() == null
                        || injectVar.converter() == GenericConverter.class) {
                    //默认的转换方式
                    //转换并且注入变量
                    //转换数据类型
                    newValue = VariableInjector.convert(newValue, sourceTypeDescriptor, targetTypeDescriptor);
                } else {
                    //临时创建转化器
                    newValue = getConverter(injectVar).convert(newValue, sourceTypeDescriptor, targetTypeDescriptor);
                }

                //如果输入
                if (isInput && isInjectToBean) {
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
        valueHolder.setName(StringUtils.hasText(injectVar.outputVarName()) ? injectVar.outputVarName() : varName);

        return valueHolder;
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
