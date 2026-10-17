package com.levin.commons.rbac;

import com.levin.commons.annotation.GenNameConstant;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * 定义实体数据属性的权限分组。同一实体内名称相同的声明合并为一个分组。
 *
 * @author lilw
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@GenNameConstant
@Repeatable(RbacDataAttrGroup.RbacDataAttrGroups.class)
@Schema(title = "数据属性权限分组", description = "可重复标注在类、字段或JavaBean属性方法上；同一实体内同名分组的属性合并。字段和属性方法自动加入其对应属性，类级声明通过attrList指定属性；未标注属性不增加本分组限制")
public @interface RbacDataAttrGroup {

    @Schema(title = "分组名称", description = "同一实体内的分组标识；字段或JavaBean getter未填写时取对应字段的@Schema.title，字段未提供标题时取getter的title；仍为空则拒绝配置。类级声明必须填写。该名称也是授权标识，改名需要迁移授权")
    String name() default "";

    @Schema(title = "属性列表", description = "使用字段名或JavaBean属性名；类级声明必须指定至少一个存在的属性；字段或getter自动包含对应属性，列表中的其它属性一并加入。同名组合并去重，一个属性可属于多个组且须全部授权。静态字段和非getter方法不支持")
    String[] attrList() default {};

    @Schema(title = "最低机密访问级别", description = "用户的有效机密数据访问级别须不低于此值；同名声明取最高等级。默认平台公开，仅表示不增加机密等级门槛，不授予字段分组操作权限")
    int confidentialLevel() default ConfidentialLevel.PLATFORM_PUBLIC_CODE;

    @Schema(title = "备注", description = "补充分组用途和授权边界，不作为分组标识")
    String remark() default "";

    /**
     * 同一注解位置上重复声明的数据属性权限分组。
     */
    @Inherited
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Schema(title = "数据属性权限分组集合", description = "承载同一类、字段或属性方法上的多个RbacDataAttrGroup声明；各声明按分组名称合并属性")
    @interface RbacDataAttrGroups {

        @Schema(title = "权限分组列表", description = "同一注解位置上重复声明的数据属性权限分组")
        RbacDataAttrGroup[] value();
    }
}
