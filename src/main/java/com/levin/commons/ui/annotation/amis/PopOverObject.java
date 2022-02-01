package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * PopOverObject
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "PopOverObject")
public @interface PopOverObject {

    /**
     * 类名
     *
     * 参考定义: {"type":"string","description":"类名"}
     *
     * @see 
     */
    @Schema(description = "类名")
    String className() default "";

    /**
     * 弹框外层类名
     *
     * 参考定义: {"type":"string","description":"弹框外层类名"}
     *
     * @see 
     */
    @Schema(description = "弹框外层类名")
    String popOverClassName() default "";

    /**
     * 配置当前行是否启动，要用表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "配置当前行是否启动，要用表达式")
    String popOverEnableOn() default "";

    /**
     * 弹出模式
     *
     * 参考定义: {"type":"string","enum":["dialog","drawer","popOver"],"description":"弹出模式"}
     *
     * @see 
     */
    @Schema(description = "弹出模式")
    String mode() default "";

    /**
     * 是弹窗形式的时候有用。
     *
     * 参考定义: {"type":"string","enum":["sm","md","lg","xl"],"description":"是弹窗形式的时候有用。"}
     *
     * @see 
     */
    @Schema(description = "是弹窗形式的时候有用。")
    String size() default "";

    /**
     * 弹出位置
     *
     * 参考定义: {"type":"string","enum":["center","left-top","left-top-left-top","left-top-left-center","left-top-left-bottom","left-top-center-top","left-top-center-center","left-top-center-bottom","left-top-right-top","left-top-right-center","left-top-right-bottom","right-top","right-top-left-top","right-top-left-center","right-top-left-bottom","right-top-center-top","right-top-center-center","right-top-center-bottom","right-top-right-top","right-top-right-center","right-top-right-bottom","left-bottom","left-bottom-left-top","left-bottom-left-center","left-bottom-left-bottom","left-bottom-center-top","left-bottom-center-center","left-bottom-center-bottom","left-bottom-right-top","left-bottom-right-center","left-bottom-right-bottom","right-bottom","right-bottom-left-top","right-bottom-left-center","right-bottom-left-bottom","right-bottom-center-top","right-bottom-center-center","right-bottom-center-bottom","right-bottom-right-top","right-bottom-right-center","right-bottom-right-bottom","fixed-center","fixed-left-top","fixed-right-top","fixed-left-bottom","fixed-right-bottom"],"description":"弹出位置"}
     *
     * @see 
     */
    @Schema(description = "弹出位置")
    String position() default "";

    /**
     * 触发条件，默认是 click
     *
     * 参考定义: {"type":"string","enum":["click","hover"],"description":"触发条件，默认是 click"}
     *
     * @see 
     */
    @Schema(description = "触发条件，默认是 click")
    String trigger() default "";

    /**
     * 是否显示查看更多的 icon，通常是放大图标。
     *
     * 参考定义: {"type":"boolean","description":"是否显示查看更多的 icon，通常是放大图标。"}
     *
     * @see 
     */
    @Schema(description = "是否显示查看更多的 icon，通常是放大图标。")
    boolean showIcon() default false;

    /**
     * 偏移量
     *
     * 参考定义: {"type":"object","properties":{"top":{"type":"number"},"left":{"type":"number"}},"additionalProperties":false,"description":"偏移量"}
     *
     * @see 
     */
    @Schema(description = "偏移量")
    String offset() default "";

    /**
     * 标题
     *
     * 参考定义: {"type":"string","description":"标题"}
     *
     * @see 
     */
    @Schema(description = "标题")
    String title() default "";

    /**
     * body
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see String
     */
    @Schema(description = "body")
    String body() default "";


}
