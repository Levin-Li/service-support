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

/**
 * 字符串 like 处理
 * <p>
 * 总数生成字符串
 *
 * <p>
 * 用于查询条件
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

//        ResolvableType rt = targetType.getResolvableType();
//        Assert.isTrue(!rt.hasUnresolvableGenerics(), "目标类型中有未识别的泛型：" + rt);

        //总是转换成字符串或是字符串集合
        //目标类型
//        Type requireType = rt.hasGenerics() ? rt.getType() : rt.resolve();

        //如果目标是集合
        //String  -->  Json 放序列 对象
        //如果是集合
        if (TypeUtils.isAssignable(Collection.class, source.getClass())) {
            //用于查询
            return JsonStrArrayUtils.getLikeQueryStrList((Collection<?>) source);
        } else if (source.getClass().isArray()) {
            //用于查询
            return JsonStrArrayUtils.getLikeQueryStrList((Object[]) source);
        } else {
            return JsonStrArrayUtils.getLikeQueryStr(source);
        }

    }

}
