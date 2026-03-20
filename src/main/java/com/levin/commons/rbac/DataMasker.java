package com.levin.commons.rbac;


import java.lang.reflect.AnnotatedElement;
import java.util.function.Supplier;

/**
 * @author echo
 */

/**
 * 数据脱敏编解码器
 *
 * @author echo
 */
public interface DataMasker {

    /**
     * 脱敏编码
     *
     * @param masking
     * @param annotatedElement
     * @param rawData
     * @param dynamicConfuseInfoSupplier 动态的混淆信息，一般建议根据当前登录用户的信息来动态的生成混淆信息
     * @param <T>
     */

    <T> T encode(DataMasking masking, AnnotatedElement annotatedElement, T rawData, Supplier<String> dynamicConfuseInfoSupplier);

    /**
     * 脱敏解码
     *
     * @param masking
     * @param annotatedElement
     * @param markingData
     * @param dynamicConfuseInfoSupplier 动态的混淆信息，一般建议根据当前登录用户的信息来动态的生成混淆信息
     * @param <T>
     */
    <T> T decode(DataMasking masking, AnnotatedElement annotatedElement, T markingData, Supplier<String> dynamicConfuseInfoSupplier);

}
