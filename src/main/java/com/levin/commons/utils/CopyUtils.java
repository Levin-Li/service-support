package com.levin.commons.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.levin.commons.service.support.SimpleVariableInjector;
import com.levin.commons.service.support.VariableInjector;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class CopyUtils {

    public static <T> T convert(Object source, Type targetType) {
        return VariableInjector.convert(source, targetType);
    }

    /**
     * 拷贝属性
     *
     * @param source
     * @param target
     * @param ignoreNullValue
     * @param ignoreProperties
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, T target, boolean ignoreNullValue, String... ignoreProperties) {
        return copy(source, target, ignoreNullValue, null, ignoreProperties);
    }

    /**
     * 拷贝属性
     *
     * @param source
     * @param target
     * @param ignoreNullValue
     * @param convertor
     * @param ignorePropertyPredicate
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, T target, boolean ignoreNullValue, Function<Object, T> convertor, Predicate<Field> ignorePropertyPredicate) {

        if (source == null || target == null) {
            return target;
        }

        final List<String> attrs = new ArrayList<>();

        if (ignorePropertyPredicate != null) {
            ReflectionUtils.doWithFields(target.getClass(), field -> {
                if (ignorePropertyPredicate.test(field)) {
                    attrs.add(field.getName());
                }
            });
        }

        return copy(source, target, ignoreNullValue, convertor, attrs.toArray(new String[attrs.size()]));
    }

    /**
     * 拷贝属性
     *
     * @param source
     * @param target
     * @param ignoreNullValue
     * @param convertor
     * @param ignoreProperties
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, T target, boolean ignoreNullValue, Function<Object, T> convertor, String... ignoreProperties) {

        if (source == null || target == null) {
            return target;
        }

        CopyOptions copyOptions = CopyOptions.create()
                .setIgnoreProperties(ignoreProperties)
                .setConverter(convertor == null ? null : (type, sourceObj) -> convertor.apply(sourceObj))
                .setIgnoreNullValue(ignoreNullValue);

        BeanUtil.copyProperties(source, target, copyOptions);

        return target;
    }


}
