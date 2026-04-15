package com.levin.commons.ui.annotation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * @author llw
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JsonSchemaEditor {

    @Schema(title = "标题", description = "默认为被注解字段的@Schema.title属性")
    String title() default "";

    /**
     * class: xx.xx.xx
     * attrName: 当前类的属性名
     * id: xx
     * 默认当前字段的类型
     *
     * @return
     */
    @Schema(description = "支持3种格式: 1类名, 2 Url, 3 被注解字段同个类的属性名. 如: 1 com.test.UserAddress  2 :attrName 3 /sys/jsonSchema/userAddr.json")
    String jsonSchema() default "";

    /**
     * 描述
     *
     * @return
     */
    String desc() default "";
}
