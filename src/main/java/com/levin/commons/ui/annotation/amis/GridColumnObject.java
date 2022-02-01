package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * GridColumnObject
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "GridColumnObject")
public @interface GridColumnObject {

    /**
     * 极小屏（<768px）时宽度占比
     * <p>
     * 参考定义: {"anyOf":[{"type":"number"},{"type":"string","const":"auto"}],"description":"极小屏（<768px）时宽度占比"}
     *
     * @see
     */
    @Schema(description = "极小屏（<768px）时宽度占比")
    double xs() default 0;

    /**
     * 小屏时（>=768px）宽度占比
     * <p>
     * 参考定义: {"anyOf":[{"type":"number"},{"type":"string","const":"auto"}],"description":"小屏时（>=768px）宽度占比"}
     *
     * @see
     */
    @Schema(description = "小屏时（>=768px）宽度占比")
    double sm() default 0;

    /**
     * 中屏时(>=992px)宽度占比
     * <p>
     * 参考定义: {"anyOf":[{"type":"number"},{"type":"string","const":"auto"}],"description":"中屏时(>=992px)宽度占比"}
     *
     * @see
     */
    @Schema(description = "中屏时(>=992px)宽度占比")
    double md() default 0;

    /**
     * 大屏时(>=1200px)宽度占比
     * <p>
     * 参考定义: {"anyOf":[{"type":"number"},{"type":"string","const":"auto"}],"description":"大屏时(>=1200px)宽度占比"}
     *
     * @see
     */
    @Schema(description = "大屏时(>=1200px)宽度占比")
    double lg() default 0;

    /**
     * 垂直对齐方式
     * <p>
     * 参考定义: {"type":"string","enum":["top","middle","bottom","between"],"description":"垂直对齐方式"}
     *
     * @see
     */
    @Schema(description = "垂直对齐方式")
    String valign() default "";

    /**
     * 配置子表单项默认的展示方式。
     * <p>
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置子表单项默认的展示方式。"}
     *
     * @see
     */
    @Schema(description = "配置子表单项默认的展示方式。")
    String mode() default "";

    /**
     * 如果是水平排版，这个属性可以细化水平排版的左右宽度占比。
     * <p>
     * 参考定义: "#/definitions/FormSchemaHorizontal"
     *
     * @see
     */
    @Schema(description = "如果是水平排版，这个属性可以细化水平排版的左右宽度占比。")
    FormHorizontal horizontal();

    /**
     * body
     * <p>
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see String
     */
    @Schema(description = "body")
    String body() default "";

    /**
     * 列类名
     * <p>
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "列类名")
    String columnClassName() default "";


}
