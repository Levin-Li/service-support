package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * HBox
 *
 * \"Hbox 水平布局渲染器。 文档：https://baidu.gitee.io/amis/docs/components/hbox\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"Hbox 水平布局渲染器。 文档：https://baidu.gitee.io/amis/docs/components/hbox\"")
public @interface HBox {

    /**
     * 指定为each展示类型
     *
     * 参考定义: {"type":"string","const":"hbox","description":"指定为each展示类型"}
     *
     * @see 
     */
    @Schema(description = "指定为each展示类型")
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
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用"}
     *
     * @see 
     */
    @Schema(description = "是否禁用")
    boolean disabled() default false;

    /**
     * 是否禁用表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "是否禁用表达式")
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
     * 是否显示表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "是否显示表达式")
    String visibleOn() default "";

    /**
     * columns
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/HBoxColumn"}}
     *
     * @see 
     */
    @Schema(description = "columns")
    String[] columns() default {};

    /**
     * 配置子表单项默认的展示方式。
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置子表单项默认的展示方式。"}
     *
     * @see 
     */
    @Schema(description = "配置子表单项默认的展示方式。")
    String subFormMode() default "";

    /**
     * 如果是水平排版，这个属性可以细化水平排版的左右宽度占比。
     *
     * 参考定义: "#/definitions/FormSchemaHorizontal"
     *
     * @see String
     */
    @Schema(description = "如果是水平排版，这个属性可以细化水平排版的左右宽度占比。")
    String subFormHorizontal() default "";

    /**
     * 水平间距
     *
     * 参考定义: {"type":"string","enum":["xs","sm","base","none","md","lg"],"description":"水平间距"}
     *
     * @see 
     */
    @Schema(description = "水平间距")
    String gap() default "";

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
     * 水平对齐方式
     *
     * 参考定义: {"type":"string","enum":["left","right","between","center"],"description":"水平对齐方式"}
     *
     * @see 
     */
    @Schema(description = "水平对齐方式")
    String align() default "";


}
