package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * HBoxColumnObject
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "HBoxColumnObject")
public @interface HBoxColumnObject {

    /**
     * 列上 CSS 类名
     *
     * 参考定义: {"type":"string","description":"列上 CSS 类名"}
     *
     * @see 
     */
    @Schema(description = "列上 CSS 类名")
    String columnClassName() default "";

    /**
     * 垂直对齐方式
     *
     * 参考定义: {"type":"string","enum":["top","middle","bottom","between"],"description":"垂直对齐方式"}
     *
     * @see 
     */
    @Schema(description = "垂直对齐方式")
    String valign() default "";

    /**
     * 宽度
     *
     * 参考定义: {"type":["number","string"],"description":"宽度"}
     *
     * @see 
     */
    @Schema(description = "宽度")
    String width() default "";

    /**
     * 高度
     *
     * 参考定义: {"type":["number","string"],"description":"高度"}
     *
     * @see 
     */
    @Schema(description = "高度")
    String height() default "";

    /**
     * 其他样式
     *
     * 参考定义: {"type":"object","description":"其他样式"}
     *
     * @see 
     */
    @Schema(description = "其他样式")
    String style() default "";

    /**
     * 配置子表单项默认的展示方式。
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置子表单项默认的展示方式。"}
     *
     * @see 
     */
    @Schema(description = "配置子表单项默认的展示方式。")
    String mode() default "";

    /**
     * 如果是水平排版，这个属性可以细化水平排版的左右宽度占比。
     *
     * 参考定义: "#/definitions/FormSchemaHorizontal"
     *
     * @see String
     */
    @Schema(description = "如果是水平排版，这个属性可以细化水平排版的左右宽度占比。")
    String horizontal() default "";

    /**
     * 内容区
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see String
     */
    @Schema(description = "内容区")
    String body() default "";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
     *
     * @see 
     */
    @Schema(description = "是否显示")
    boolean visible() default false;

    /**
     * 是否显示表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "是否显示表达式")
    String visibleOn() default "";


}
