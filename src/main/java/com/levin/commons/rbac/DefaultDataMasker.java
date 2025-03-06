package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import com.levin.commons.utils.DataMaskingUtils;

import java.lang.reflect.AnnotatedElement;
import java.util.function.Supplier;

public class DefaultDataMasker implements DataMasker {

    private String null2Empty(String str) {
        return str == null ? "" : str;
    }

    /**
     * 脱敏编码
     *
     * @param masking
     * @param annotatedElement
     * @param rawData
     * @param dynamicConfuseInfoSupplier 动态的混淆信息，一般建议根据当前登录用户的信息来动态的生成混淆信息
     */
    @Override
    public <T> T encode(DataMasking masking, AnnotatedElement annotatedElement, T rawData, Supplier<String> dynamicConfuseInfoSupplier) {

        if (rawData == null) {
            return null;
        }

        Assert.notNull(masking, "masking is required");

        Assert.isTrue(rawData instanceof CharSequence, "rawData must be CharSequence");

        String confuseInfo = null2Empty(masking.fixedConfuseInfo()) + null2Empty(dynamicConfuseInfoSupplier != null ? dynamicConfuseInfoSupplier.get() : null);

        return (T) DataMaskingUtils.simpleMergeEncode(confuseInfo, rawData.toString());
    }

    /**
     * 脱敏解码
     *
     * @param masking
     * @param annotatedElement
     * @param markingData
     * @param dynamicConfuseInfoSupplier 动态的混淆信息，一般建议根据当前登录用户的信息来动态的生成混淆信息
     */
    @Override
    public <T> T decode(DataMasking masking, AnnotatedElement annotatedElement, T markingData, Supplier<String> dynamicConfuseInfoSupplier) {

        if (markingData == null) {
            return null;
        }

        Assert.notNull(masking, "masking is required");

        Assert.isTrue(markingData instanceof CharSequence, "markingData must be CharSequence");

        String confuseInfo = null2Empty(masking.fixedConfuseInfo()) + null2Empty(dynamicConfuseInfoSupplier != null ? dynamicConfuseInfoSupplier.get() : null);

        return (T) DataMaskingUtils.simpleMergeDecode(confuseInfo, markingData.toString());
    }

}
