package com.levin.commons.rbac;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
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
     * 数据显示的权限要求
     * <p>
     * 根据当前的用户权限，动态处理
     * 默认需要登录
     *
     * @return
     */
    ResAuthorize showAuthorize() default @ResAuthorize(ignored = true);

    /**
     * 不脱敏需要的权限要求
     * <p>
     * 默认为超级管理员才不用脱敏数据
     *
     * @return
     */
    ResAuthorize noMaskingAuthorize() default @ResAuthorize(anyRoles = RbacRoleObject.SA_ROLE);

    /**
     * 固定的混淆信息
     *
     * @return
     */
    String fixedConfuseInfo() default "******";

    /**
     * 数据脱敏编解码器
     *
     * @return
     */
    Class<? extends DataMasker> dataMasker() default DefaultDataMasker.class;

    /**
     * 备注
     * 当变量找不到，应该抛出异常包含的备注信息
     *
     * @return
     */
    String remark() default "";
}
