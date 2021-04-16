package com.levin.commons.uiform.annotation;


import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * 主要参考 https://uformjs.org/
 *
 * <p>
 * 表单字段注解
 */

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Layout {

    enum Type {
        Default,
        Grid,
    }

    @Schema(description = "布局ID")
    String id() default "";

    @Schema(description = "布局名称")
    String name() default "";

    @Schema(description = "标题")
    String title() default "";

    @Schema(description = "图标-按模块资源公共路径规划")
    String logo() default "";

    @Schema(description = "样式扩展")
    String style() default "";

    @Schema(description = "在这个布局中的字段列表或是嵌套的布局ID")
    String[] fields();

    @Schema(description = "布局类型")
    Type type() default Type.Default;


}
