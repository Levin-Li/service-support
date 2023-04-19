package com.levin.commons.service.support;

import cn.hutool.core.lang.Assert;
import com.levin.commons.service.domain.EnumDesc;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Set;

/**
 * Json 和  对象 转换器
 * <p>
 * 基于Spring 转化器
 */
public class EnumDescConverter implements GenericConverter {

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

        cn.hutool.core.lang.Assert.isTrue(!rt.hasUnresolvableGenerics(), "目标类型中有未识别的泛型：" + rt);

        ValueHolder<Object> holder = EnumDesc.convert(source, rt.hasGenerics() ? rt.getType() : rt.resolve());

        Assert.isTrue(holder.hasValue(), "无法转换的类型:" + targetType);

        return holder.get();
    }

}
