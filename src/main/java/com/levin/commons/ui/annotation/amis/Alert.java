package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Alert
 *
 * \"Alert 提示渲染器。 文档：https://baidu.gitee.io/amis/docs/components/alert\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"Alert 提示渲染器。 文档：https://baidu.gitee.io/amis/docs/components/alert\"")
public @interface Alert {

    /**
     * 指定为提示框类型
     *
     * 参考定义: {"type":"string","const":"alert","description":"指定为提示框类型"}
     *
     * @see 
     */
    @Schema(description = "指定为提示框类型")
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
     * 提示框标题
     *
     * 参考定义: {"type":"string","description":"提示框标题"}
     *
     * @see 
     */
    @Schema(description = "提示框标题")
    String title() default "";

    /**
     * 内容区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see String
     */
    @Schema(description = "内容区域")
    String body() default "";

    /**
     * 提示类型
     *
     * 参考定义: {"type":"string","enum":["info","warning","success","error","danger"],"description":"提示类型"}
     *
     * @see 
     */
    @Schema(description = "提示类型")
    String level() default "";

    /**
     * 是否显示关闭按钮
     *
     * 参考定义: {"type":"boolean","description":"是否显示关闭按钮"}
     *
     * @see 
     */
    @Schema(description = "是否显示关闭按钮")
    boolean showCloseButton() default false;

    /**
     * 关闭按钮CSS类名
     *
     * 参考定义: {"type":"string","description":"关闭按钮CSS类名"}
     *
     * @see 
     */
    @Schema(description = "关闭按钮CSS类名")
    String closeButtonClassName() default "";

    /**
     * 是否显示ICON
     *
     * 参考定义: {"type":"boolean","description":"是否显示ICON"}
     *
     * @see 
     */
    @Schema(description = "是否显示ICON")
    boolean showIcon() default false;

    /**
     * 左侧图标
     *
     * 参考定义: "#/definitions/SchemaIcon"
     *
     * @see String
     */
    @Schema(description = "左侧图标")
    String icon() default "";

    /**
     * 图标CSS类名
     *
     * 参考定义: {"type":"string","description":"图标CSS类名"}
     *
     * @see 
     */
    @Schema(description = "图标CSS类名")
    String iconClassName() default "";


}
