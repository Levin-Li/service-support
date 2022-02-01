package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * ListItem
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "ListItem")
public @interface ListItem {

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
     * actions
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/ActionSchema"}}
     *
     * @see 
     */
    @Schema(description = "actions")
    String[] actions() default {};

    /**
     * 操作位置，默认在右侧，可以设置成左侧。
     *
     * 参考定义: {"type":"string","enum":["left","right"],"description":"操作位置，默认在右侧，可以设置成左侧。"}
     *
     * @see 
     */
    @Schema(description = "操作位置，默认在右侧，可以设置成左侧。")
    String actionsPosition() default "";

    /**
     * 图片地址
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     * @see String
     */
    @Schema(description = "图片地址")
    String avatar() default "";

    /**
     * 内容区域
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"$ref":"#/definitions/ListBodyField"},{"$ref":"#/definitions/ListBodyFieldObject"}]},"description":"内容区域"}
     *
     * @see 
     */
    @Schema(description = "内容区域")
    String[] body() default {};

    /**
     * 描述
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "描述")
    String desc() default "";

    /**
     * tooltip 说明
     *
     * 参考定义: "#/definitions/SchemaRemark"
     *
     * @see String
     */
    @Schema(description = "tooltip 说明")
    String remark() default "";

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
     * 副标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "副标题")
    String subTitle() default "";


}
