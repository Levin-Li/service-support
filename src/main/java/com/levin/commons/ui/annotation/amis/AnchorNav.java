package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * AnchorNav
 *
 * \"AnchorNav 锚点导航渲染器 文档：https://baidu.gitee.io/amis/docs/components/anchor-nav\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"AnchorNav 锚点导航渲染器 文档：https://baidu.gitee.io/amis/docs/components/anchor-nav\"")
public @interface AnchorNav {

    /**
     * 指定为 AnchorNav 锚点导航渲染器
     *
     * 参考定义: {"type":"string","const":"anchor-nav","description":"指定为 AnchorNav 锚点导航渲染器"}
     *
     * @see 
     */
    @Schema(description = "指定为 AnchorNav 锚点导航渲染器")
    String type() default "";

    /**
     * 样式名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "样式名")
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
     * 楼层集合
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/AnchorNavSectionSchema"},"description":"楼层集合"}
     *
     * @see 
     */
    @Schema(description = "楼层集合")
    String[] links() default {};

    /**
     * 被激活（定位）的楼层
     *
     * 参考定义: {"type":["string","number"],"description":"被激活（定位）的楼层"}
     *
     * @see 
     */
    @Schema(description = "被激活（定位）的楼层")
    String active() default "";

    /**
     * 导航样式名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "导航样式名")
    String linkClassName() default "";

    /**
     * 楼层样式名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "楼层样式名")
    String sectionClassName() default "";

    /**
     * direction
     *
     * 参考定义: {"type":"string","enum":["vertical","horizontal"]}
     *
     * @see 
     */
    @Schema(description = "direction")
    String direction() default "";


}
