package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Link
 *
 * \"Link 链接展示控件。 文档：https://baidu.gitee.io/amis/docs/components/link\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"Link 链接展示控件。 文档：https://baidu.gitee.io/amis/docs/components/link\"")
public @interface Link {

    /**
     * 指定为 link 链接展示控件
     *
     * 参考定义: {"type":"string","const":"link","description":"指定为 link 链接展示控件"}
     *
     * @see 
     */
    @Schema(description = "指定为 link 链接展示控件")
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
     * 是否新窗口打开。
     *
     * 参考定义: {"type":"boolean","description":"是否新窗口打开。"}
     *
     * @see 
     */
    @Schema(description = "是否新窗口打开。")
    boolean blank() default false;

    /**
     * 链接内容，如果不配置将显示链接地址。
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "链接内容，如果不配置将显示链接地址。")
    String body() default "";

    /**
     * 角标
     *
     * 参考定义: "#/definitions/BadgeSchema"
     *
     * @see String
     */
    @Schema(description = "角标")
    String badge() default "";

    /**
     * a标签原生target属性
     *
     * 参考定义: {"type":"string","description":"a标签原生target属性"}
     *
     * @see 
     */
    @Schema(description = "a标签原生target属性")
    String htmlTarget() default "";

    /**
     * 图标
     *
     * 参考定义: {"type":"string","description":"图标"}
     *
     * @see 
     */
    @Schema(description = "图标")
    String icon() default "";

    /**
     * 右侧图标
     *
     * 参考定义: {"type":"string","description":"右侧图标"}
     *
     * @see 
     */
    @Schema(description = "右侧图标")
    String rightIcon() default "";


}
