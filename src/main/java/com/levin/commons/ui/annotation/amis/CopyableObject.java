package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * CopyableObject
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "CopyableObject")
public @interface CopyableObject {

    /**
     * 可以配置图标
     *
     * 参考定义: "#/definitions/SchemaIcon"
     *
     * @see String
     */
    @Schema(description = "可以配置图标")
    String icon() default "";

    /**
     * 配置复制时的内容模板。
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "配置复制时的内容模板。")
    String content() default "";


}
