package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * List
 *
 * \"List 列表展示控件。 文档：https://baidu.gitee.io/amis/docs/components/card\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"List 列表展示控件。 文档：https://baidu.gitee.io/amis/docs/components/card\"")
public @interface List {

    /**
     * 指定为 List 列表展示控件。
     *
     * 参考定义: {"type":"string","enum":["list","static-list"],"description":"指定为 List 列表展示控件。"}
     *
     * @see 
     */
    @Schema(description = "指定为 List 列表展示控件。")
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
     * 标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "标题")
    String title() default "";

    /**
     * 底部区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see String
     */
    @Schema(description = "底部区域")
    String footer() default "";

    /**
     * 底部区域类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "底部区域类名")
    String footerClassName() default "";

    /**
     * 顶部区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see String
     */
    @Schema(description = "顶部区域")
    String header() default "";

    /**
     * 顶部区域类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "顶部区域类名")
    String headerClassName() default "";

    /**
     * 单条数据展示内容配置
     *
     * 参考定义: "#/definitions/ListItemSchema"
     *
     * @see String
     */
    @Schema(description = "单条数据展示内容配置")
    String listItem() default "";

    /**
     * 数据源: 绑定当前环境变量
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * @see String
     */
    @Schema(description = "数据源: 绑定当前环境变量")
    String source() default "";

    /**
     * 是否显示底部
     *
     * 参考定义: {"type":"boolean","description":"是否显示底部"}
     *
     * @see 
     */
    @Schema(description = "是否显示底部")
    boolean showFooter() default false;

    /**
     * 是否显示头部
     *
     * 参考定义: {"type":"boolean","description":"是否显示头部"}
     *
     * @see 
     */
    @Schema(description = "是否显示头部")
    boolean showHeader() default false;

    /**
     * 无数据提示
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "无数据提示")
    String placeholder() default "";

    /**
     * 是否隐藏勾选框
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏勾选框"}
     *
     * @see 
     */
    @Schema(description = "是否隐藏勾选框")
    boolean hideCheckToggler() default false;

    /**
     * 是否固顶
     *
     * 参考定义: {"type":"boolean","description":"是否固顶"}
     *
     * @see 
     */
    @Schema(description = "是否固顶")
    boolean affixHeader() default false;

    /**
     * 配置某项是否可以点选
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "配置某项是否可以点选")
    String itemCheckableOn() default "";

    /**
     * 配置某项是否可拖拽排序，前提是要开启拖拽功能
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "配置某项是否可拖拽排序，前提是要开启拖拽功能")
    String itemDraggableOn() default "";

    /**
     * 点击卡片的时候是否勾选卡片。
     *
     * 参考定义: {"type":"boolean","description":"点击卡片的时候是否勾选卡片。"}
     *
     * @see 
     */
    @Schema(description = "点击卡片的时候是否勾选卡片。")
    boolean checkOnItemClick() default false;

    /**
     * 可以用来作为值的字段
     *
     * 参考定义: {"type":"string","description":"可以用来作为值的字段"}
     *
     * @see 
     */
    @Schema(description = "可以用来作为值的字段")
    String valueField() default "";

    /**
     * 大小
     *
     * 参考定义: {"type":"string","enum":["sm","base"],"description":"大小"}
     *
     * @see 
     */
    @Schema(description = "大小")
    String size() default "";

    /**
     * 点击列表项的行为
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * @see String
     */
    @Schema(description = "点击列表项的行为")
    String itemAction() default "";


}
