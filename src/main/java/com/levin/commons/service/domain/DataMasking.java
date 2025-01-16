package com.levin.commons.service.domain;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.support.VariableInjector;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.util.PatternMatchUtils;

import java.lang.annotation.*;

/**
 * @author lilw
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@JacksonAnnotationsInside

/**
 * 数据脱敏注解
 */
public @interface DataMasking {

    /**
     * 数据显示授权要求
     * <p>
     * 根据当前的用户权限，动态处理
     * 默认不需要任何权限
     *
     * @return
     */
    ResAuthorize showAuthorize() default @ResAuthorize(ignored = true);

    /**
     * 原始数据脱敏编码器
     *
     * @return
     */
    Class<? extends GenericConverter> maskingEncoder() default GenericConverter.class;

    /**
     * 数据数据解码器
     *
     * @return
     */
    Class<? extends GenericConverter> maskingDecoder() default GenericConverter.class;

    /**
     * 备注
     * 当变量找不到，应该抛出异常包含的备注信息
     *
     * @return
     */
    String remark() default "";

}
