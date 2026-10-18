package com.levin.commons.rbac;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 声明按枚举字段值限制记录访问，不决定属性是否可见。 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Schema(title = "枚举值数据范围", description = "仅用于枚举持久字段；JPA实体仅支持FIELD访问且字段须直接声明Enumerated(STRING)，拒绝PROPERTY/混合访问与启用Convert映射；属性标识固定为Java字段名，不受显示名称影响。框架结合可信权限上下文追加记录范围，字段本身的可见性由RbacDataAttrGroup等机制控制；本注解不直接授予访问权限")
public @interface RbacDataAttr {
    @Schema(title = "显示名称", description = "默认取字段Schema.title，仍为空则取Java字段名；不作为授权标识")
    String name() default "";

    @Schema(title = "备注", description = "补充枚举范围的用途，不作为授权标识或条件表达式")
    String remark() default "";
}
