package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.levin.commons.utils.JsonStrArrayUtils;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.util.TypeUtils;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 字符串 like 处理
 * <p>
 * 用于
 */
public class JsonStrLikeConverter implements GenericConverter {

    /**
     * @return
     */
    @Override
    @Nullable
    public Set<ConvertiblePair> getConvertibleTypes() {
        return null;
    }

    @Override
    public Object convert(@Nullable Object source, @Nullable TypeDescriptor sourceType, @NonNull TypeDescriptor targetType) {

        if (source == null) {
            return null;
        }

        ResolvableType rt = targetType.getResolvableType();
        Assert.isTrue(!rt.hasUnresolvableGenerics(), "目标类型中有未识别的泛型：" + rt);

        Type requireType = rt.hasGenerics() ? rt.getType() : rt.resolve();

        if (TypeUtils.isAssignable(CharSequence.class, requireType) && (source instanceof CharSequence)) {

            if (!StringUtils.hasText((CharSequence) source)) {
                return null;
            }

            return JsonStrArrayUtils.getLikeQueryStr(source);

        } else if (sourceType.isCollection() && TypeUtils.isAssignable(Collection.class, requireType)) {

            Stream stream = ((Collection) source).stream().map(JsonStrArrayUtils::getLikeQueryStr);

            if (TypeUtils.isAssignable(Set.class, requireType)) {
                return stream.collect(Collectors.toSet());
            } else {
                return stream.collect(Collectors.toList());
            }

        } else {
            throw new IllegalArgumentException("source[" + source.getClass() + "] is not a Iterable or String");
        }

    }

}
