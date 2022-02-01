package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * ButtonGroup
 *
 * \"Button Group 渲染器。 文档：https://baidu.gitee.io/amis/docs/components/button-group\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"Button Group 渲染器。 文档：https://baidu.gitee.io/amis/docs/components/button-group\"")
public @interface ButtonGroup {

    /**
     * 指定为提交按钮类型
     *
     * 参考定义: {"type":"string","const":"button-group","description":"指定为提交按钮类型"}
     *
     * @see 
     */
    @Schema(description = "指定为提交按钮类型")
    String type() default "";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "容器 css 类名")
    String className() default "";

    /**
     * 是否为禁用状态。
     *
     * 参考定义: {"type":"boolean","description":"是否为禁用状态。"}
     *
     * @see 
     */
    @Schema(description = "是否为禁用状态。")
    boolean disabled() default false;

    /**
     * 通过 JS 表达式来配置当前表单项的禁用状态。
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "通过 JS 表达式来配置当前表单项的禁用状态。")
    String disabledOn() default "";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
     *
     * @see 
     */
    @Schema(description = "是否隐藏")
    boolean hidden() default false;

    /**
     * 是否隐藏表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "是否隐藏表达式")
    String hiddenOn() default "";

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
     * 通过 JS 表达式来配置当前表单项是否显示
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "通过 JS 表达式来配置当前表单项是否显示")
    String visibleOn() default "";

    /**
     * 给 Button 配置 className。
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "给 Button 配置 className。")
    String btnClassName() default "";

    /**
     * 给选中态 Button 配置 className。
     *
     * 参考定义: {"type":"string","description":"给选中态 Button 配置 className。"}
     *
     * @see 
     */
    @Schema(description = "给选中态 Button 配置 className。")
    String btnActiveClassName() default "";

    /**
     * 按钮集合
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"按钮集合"}
     *
     * @see 
     */
    @Schema(description = "按钮集合")
    String[] buttons() default {};

    /**
     * 按钮样式级别
     *
     * 参考定义: {"type":"string","description":"按钮样式级别"}
     *
     * @see 
     */
    @Schema(description = "按钮样式级别")
    String btnLevel() default "";

    /**
     * 按钮选中的样式级别
     *
     * 参考定义: {"type":"string","description":"按钮选中的样式级别"}
     *
     * @see 
     */
    @Schema(description = "按钮选中的样式级别")
    String btnActiveLevel() default "";

    /**
     * 垂直展示？
     *
     * 参考定义: {"type":"boolean","description":"垂直展示？"}
     *
     * @see 
     */
    @Schema(description = "垂直展示？")
    boolean vertical() default false;

    /**
     * 平铺展示？
     *
     * 参考定义: {"type":"boolean","description":"平铺展示？"}
     *
     * @see 
     */
    @Schema(description = "平铺展示？")
    boolean tiled() default false;

    /**
     * 按钮大小
     *
     * 参考定义: {"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"}
     *
     * @see 
     */
    @Schema(description = "按钮大小")
    String size() default "";


}
