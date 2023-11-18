package com.levin.commons.service.support;

import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.domain.InjectVar;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 变量注入器
 */
@FunctionalInterface
public interface VariableInjector {

    /**
     * 线程安全转换服务
     */
    ConversionService defaultConversionService = new DefaultFormattingConversionService();

    enum Mode {
        //把字段的当前值，反向转换
        FieldConvertOutput,

        //仅仅获取注入值，但不注入到目标对象的字段
        OnlyGetInjectValue,

        //获取注入值，并且注入到目标对象的字段
        InjectToField,
    }

    /**
     * 获取注入域
     * <p>
     * 默认为 default
     *
     * @return
     * @see InjectVar#domain()
     */
    default String getInjectDomain() {
        return "default";
    }

    /**
     * 获取默认变量解析器
     *
     * @return
     */
    default List<VariableResolver> getDefaultVariableResolvers() {
        return Collections.emptyList();
    }

    /**
     * 域是否匹配
     *
     * @param field
     * @return
     */
    default boolean isDomainMatch(Field field) {
        return field != null
                && field.isAnnotationPresent(InjectVar.class)
                && isDomainMatch(field.getAnnotation(InjectVar.class));
    }

    /**
     * 域是否匹配
     *
     * @param injectVar
     * @return
     */
    default boolean isDomainMatch(InjectVar injectVar) {
        return injectVar != null
                && isDomainMatch(injectVar.domain());
    }

    /**
     * 域是否匹配
     *
     * @param domainList
     * @return
     */
    default boolean isDomainMatch(String... domainList) {

        return domainList == null
                || domainList.length == 0
                || Stream.of(domainList).noneMatch(StringUtils::hasText)
                //如果匹配
                || PatternMatchUtils.simpleMatch(domainList, getInjectDomain());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    default ValueHolder<Object> getOutputValueByBean(Object targetBean, Field field, Object... beans) {
        return getOutputValueByBean(targetBean, field, Arrays.asList(beans));
    }

    default ValueHolder<Object> getOutputValueByBean(Object targetBean, Field field, List<?> beans) {
        return getOutputValue(targetBean, field, newResolverByBean(beans));
    }

    default ValueHolder<Object> getOutputValue(Object targetBean, Field field, Map<String, ?>... contexts) {
        return getOutputValue(targetBean, field, newResolverByMap(contexts));
    }

    default ValueHolder<Object> getOutputValue(Object targetBean, Field field, VariableResolver... variableResolvers) {
        return getOutputValue(targetBean, field, Arrays.asList(variableResolvers));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    default ValueHolder<Object> getInjectValueByBean(Object targetBean, Field field, Object... beans) {
        return getInjectValueByBean(targetBean, field, Arrays.asList(beans));
    }

    default ValueHolder<Object> getInjectValueByBean(Object targetBean, Field field, List<?> beans) {
        return getInjectValue(targetBean, field, newResolverByBean(beans));
    }

    default ValueHolder<Object> getInjectValue(Object targetBean, Field field, Map<String, ?>... contexts) {
        return getInjectValue(targetBean, field, newResolverByMap(contexts));
    }

    default ValueHolder<Object> getInjectValue(Object targetBean, Field field, VariableResolver... variableResolvers) {
        return getInjectValue(targetBean, field, Arrays.asList(variableResolvers));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    default ValueHolder<Object> injectValueByBean(Object targetBean, Field field, Object... beans) {
        return injectValueByBean(targetBean, field, Arrays.asList(beans));
    }

    default ValueHolder<Object> injectValueByBean(Object targetBean, Field field, List<?> beans) {
        return injectValue(targetBean, field, newResolverByBean(beans));
    }

    default ValueHolder<Object> injectValue(Object targetBean, Field field, Map<String, ?>... contexts) {
        return injectValue(targetBean, field, newResolverByMap(contexts));
    }

    default ValueHolder<Object> injectValue(Object targetBean, Field field, VariableResolver... variableResolvers) {
        return injectValue(targetBean, field, Arrays.asList(variableResolvers));
    }

    ////////////////////////////////////////// 注入 ///////////////////////////////////////////////////////////////////

    /**
     * 为目标对象注入变量
     *
     * @param targetBean 被注入对象
     * @param beans      上下文环境变量支持列表
     * @return
     */
    default List<ValueHolder<Object>> injectByBean(Object targetBean, Object... beans) throws VariableInjectException, VariableNotFoundException {
        return injectByBean(targetBean, Arrays.asList(beans));
    }

    /**
     * 为目标对象注入变量
     * <p>
     * 注意：不支持groovy 和 spel 表达式
     *
     * @param targetBean 被注入对象
     * @param beans      上下文环境变量支持列表
     * @return
     */
    default List<ValueHolder<Object>> injectByBean(Object targetBean, List<?> beans) throws VariableInjectException, VariableNotFoundException {
        return injectByBean(targetBean, null, beans);
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param ignoreFieldPredicate
     * @param beans
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<ValueHolder<Object>> injectByBean(Object targetBean, Predicate<Field> ignoreFieldPredicate, Object... beans) throws VariableInjectException, VariableNotFoundException {
        return injectByBean(targetBean, ignoreFieldPredicate, Arrays.asList(beans));
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param ignoreFieldPredicate
     * @param beans
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<ValueHolder<Object>> injectByBean(Object targetBean, Predicate<Field> ignoreFieldPredicate, List<?> beans) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, ignoreFieldPredicate, newResolverByBean(() -> beans));
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 为目标对象注入变量
     * <p>
     * 支持groovy 和 spel 表达式
     *
     * @param targetBean 被注入对象
     * @param contexts   上下文环境变量支持列表
     * @return
     */
    default List<ValueHolder<Object>> injectByMap(Object targetBean, Map<String, ?>... contexts) throws VariableInjectException, VariableNotFoundException {
        return injectByMap(targetBean, Arrays.asList(contexts));
    }

    /**
     * 为目标对象注入变量
     * <p>
     * 支持groovy 和 spel 表达式
     *
     * @param targetBean 被注入对象
     * @param contexts   上下文环境变量支持列表
     * @return
     */
    default List<ValueHolder<Object>> injectByMap(Object targetBean, List<Map<String, ?>> contexts) throws VariableInjectException, VariableNotFoundException {
        return injectByMap(targetBean, null, contexts);
    }

    /**
     * 为目标对象注入变量
     * <p>
     * 支持groovy 和 spel 表达式
     *
     * @param targetBean 被注入对象
     * @param contexts   上下文环境变量支持列表
     * @return
     */
    default List<ValueHolder<Object>> injectByMap(Object targetBean, Predicate<Field> ignoreFieldPredicate, Map<String, ?>... contexts) throws VariableInjectException, VariableNotFoundException {
        return injectByMap(targetBean, ignoreFieldPredicate, Arrays.asList(contexts));
    }

    /**
     * 为目标对象注入变量
     * <p>
     * 支持groovy 和 spel 表达式
     *
     * @param targetBean 被注入对象
     * @param contexts   上下文环境变量支持列表
     * @return
     */
    default List<ValueHolder<Object>> injectByMap(Object targetBean, Predicate<Field> ignoreFieldPredicate, List<Map<String, ?>> contexts) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, ignoreFieldPredicate, newResolverByMap(() -> contexts));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 为目标对象注入变量
     *
     * @param targetBean
     * @param contexts
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<ValueHolder<Object>> injectValues(Object targetBean, Map<String, ?>... contexts) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, newResolverByMap(contexts));
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean        被注入对象
     * @param variableResolvers 变量解析器列表
     * @return
     */
    default List<ValueHolder<Object>> injectValues(Object targetBean, VariableResolver... variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, Arrays.asList(variableResolvers));
    }


    /**
     * 为目标对象注入变量
     *
     * @param targetBean        被注入对象
     * @param variableResolvers 变量解析器列表
     * @return
     */
    default List<ValueHolder<Object>> injectValues(Object targetBean, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, null, variableResolvers);
    }

    /**
     * @param targetBean
     * @param ignoreFieldPredicate
     * @param variableResolvers
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default List<ValueHolder<Object>> injectValues(Object targetBean, Predicate<Field> ignoreFieldPredicate, VariableResolver... variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, ignoreFieldPredicate, Arrays.asList(variableResolvers));
    }

    /**
     * 为目标对象注入变量
     *
     * @param targetBean 被注入对象
     * @param suppliers  变量解析器支持列表
     * @return
     */
    default List<ValueHolder<Object>> injectValues(Object targetBean, Predicate<Field> ignoreFieldPredicate, Supplier<List<VariableResolver>>... suppliers) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean,
                ignoreFieldPredicate,
                Stream.of(suppliers)
                        .filter(Objects::nonNull)
                        .map(Supplier::get)
                        .flatMap(Collection::stream)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );
    }

    /**
     * 获取当前字段输出值
     *
     * @param targetBean
     * @param variableResolvers
     * @param field
     * @return
     */

    default ValueHolder<Object> getOutputValue(Object targetBean, Field field, List<VariableResolver> variableResolvers) {
        return doInject(targetBean, field, Mode.FieldConvertOutput, variableResolvers);
    }

    /**
     * 获取当前字段注入值，但不注入targetBean
     *
     * @param targetBean
     * @param field
     * @param variableResolvers
     * @return
     */

    default ValueHolder<Object> getInjectValue(Object targetBean, Field field, List<VariableResolver> variableResolvers) {
        return doInject(targetBean, field, Mode.OnlyGetInjectValue, variableResolvers);
    }

    /**
     * 获取当前字段注入值，但不注入targetBean
     *
     * @param targetBean
     * @param field
     * @param variableResolvers
     * @return
     */

    default ValueHolder<Object> injectValue(Object targetBean, Field field, List<VariableResolver> variableResolvers) {
        return doInject(targetBean, field, Mode.InjectToField, variableResolvers);
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
    default List<ValueHolder<Object>> getOutputValues(Object targetBean, Predicate<Field> ignoreFieldPredicate, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return doInject(targetBean, ignoreFieldPredicate, Mode.FieldConvertOutput, variableResolvers);
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

    default List<ValueHolder<Object>> getInjectValues(Object targetBean, Predicate<Field> ignoreFieldPredicate, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return doInject(targetBean, ignoreFieldPredicate, Mode.OnlyGetInjectValue, variableResolvers);
    }

    /**
     * 兼容源方法
     *
     * @param targetBean
     * @param variableResolvers
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    @Deprecated
    default List<ValueHolder<Object>> injectByVariableResolvers(Object targetBean, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return injectValues(targetBean, null, variableResolvers);
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
    default List<ValueHolder<Object>> injectValues(Object targetBean, Predicate<Field> ignoreFieldPredicate, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        return doInject(targetBean, ignoreFieldPredicate, Mode.InjectToField, variableResolvers);
    }

    /**
     * @param targetBean
     * @param field
     * @param mode              注入模式，1 = 输出转换值，2 = 获取注入值，3 = 注入targetBean
     * @param variableResolvers
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    default ValueHolder<Object> doInject(Object targetBean, Field field, Mode mode, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException {
        throw new UnsupportedOperationException("not impl");
    }

    /**
     * @param targetBean
     * @param ignoreFieldPredicate
     * @param mode                 注入模式，1 = 输出转换值，2 = 获取注入值，3 = 注入targetBean
     * @param variableResolvers
     * @return
     * @throws VariableInjectException
     * @throws VariableNotFoundException
     */
    List<ValueHolder<Object>> doInject(Object targetBean, Predicate<Field> ignoreFieldPredicate, Mode mode, List<VariableResolver> variableResolvers) throws VariableInjectException, VariableNotFoundException;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * 转换值
     *
     * @param source
     * @param targetType
     * @param <T>
     * @return
     */
    static <T> T convert(@Nullable Object source, Type targetType) {

        if (targetType == null) {
            return (T) source;
        }

        ValueHolder<T> valueHolder = EnumDesc.convert(source, targetType);

        return valueHolder.hasValue() ? valueHolder.get() : (targetType instanceof Class) ?
                defaultConversionService.convert(source, (Class<T>) targetType)
                : (T) defaultConversionService.convert(source, new TypeDescriptor(ResolvableType.forInstance(source), null, new Annotation[0])
                , new TypeDescriptor(ResolvableType.forType(targetType), null, new Annotation[0]));

    }

    /**
     * 转换数据类型
     *
     * @param source
     * @param <T>
     * @return
     */
    static <T> T convert(@Nullable Object source, TypeDescriptor sourceTypeDescriptor, TypeDescriptor targetTypeDescriptor) {

        ResolvableType rt = targetTypeDescriptor.getResolvableType();

        cn.hutool.core.lang.Assert.isTrue(!rt.hasUnresolvableGenerics(), "目标类型中有未识别的泛型：" + rt);

        ValueHolder<T> holder = EnumDesc.convert(source, rt.hasGenerics() ? rt.getType() : rt.resolve());

        if (holder.hasValue()) {
            return holder.get();
        }

        return (T) defaultConversionService.convert(source, sourceTypeDescriptor, targetTypeDescriptor);
    }

    static VariableResolver.DefaultDelegateVariableResolver newDefaultResolver() {
        return new VariableResolver.DefaultDelegateVariableResolver();
    }

    static VariableResolver.DefaultDelegateVariableResolver newResolverByMap(Supplier<List<Map<String, ?>>>... suppliers) {
        return newDefaultResolver().addMapContexts(suppliers);
    }

    static VariableResolver.DefaultDelegateVariableResolver newResolverByMap(Map<String, ?>... contexts) {
        return newDefaultResolver().addMapContexts(contexts);
    }

    static VariableResolver.DefaultDelegateVariableResolver newResolverByBean(Supplier<List<?>>... suppliers) {
        return newDefaultResolver().addBeanContexts(suppliers);
    }

    static VariableResolver.DefaultDelegateVariableResolver newResolverByBean(List<?> benas) {
        return newDefaultResolver().addBeanContexts(benas);
    }
}
