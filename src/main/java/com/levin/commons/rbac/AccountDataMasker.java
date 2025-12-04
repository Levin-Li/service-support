package com.levin.commons.rbac;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.utils.DataMaskingUtils;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * 账号数据脱敏
 * 把3分1的中间数据用*号代替
 */
public class AccountDataMasker implements DataMasker {

    private String null2Empty(String str, String defaultValue) {
        return StrUtil.isBlank(str) ? defaultValue : str;
    }

    protected String getConfuseInfo(DataMasking masking, AnnotatedElement annotatedElement, Supplier<String> dynamicConfuseInfoSupplier) {

        String confuseInfo = null2Empty(masking.fixedConfuseInfo(), "XXX")
                + null2Empty(dynamicConfuseInfoSupplier != null ? dynamicConfuseInfoSupplier.get() : null, "xxx");

        if (annotatedElement instanceof Field) {
            confuseInfo += ((Field) annotatedElement).getName() + "@" + ((Field) annotatedElement).getDeclaringClass().getName();
        } else if (annotatedElement instanceof Method) {
            confuseInfo += ((Method) annotatedElement).getName() + "@" + ((Method) annotatedElement).getDeclaringClass().getName();
        }

        //只取 hashcode 进行混淆, 避免过长的混淆信息导致性能问题

        return confuseInfo.hashCode() + "";
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

        return (T) encode(getConfuseInfo(masking, annotatedElement, dynamicConfuseInfoSupplier), rawData.toString());
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

        if (!masking.isRestoreData()) {
            return (T) markingData;
        }

//        Assert.isTrue(markingData instanceof CharSequence, "markingData must be CharSequence");

        //默认不处理
        return markingData;
    }

    protected String encode(String confuseInfo, String rawData) {
        return DataMaskingUtils.simpleStrEncode(confuseInfo, rawData);
    }

}
