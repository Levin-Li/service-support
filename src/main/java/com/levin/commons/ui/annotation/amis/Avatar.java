package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Avatar
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Avatar")
public @interface Avatar {

    /**
     * type
     *
     * 参考定义: {"type":"string","const":"avatar"}
     *
     * @see 
     */
    @Schema(description = "type")
    String type() default "";

    /**
     * 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "类名")
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
     * 自定义样式
     *
     * 参考定义: {"type":"object","description":"自定义样式"}
     *
     * @see 
     */
    @Schema(description = "自定义样式")
    String style() default "";

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
     * 图片地址
     *
     * 参考定义: {"type":"string","description":"图片地址"}
     *
     * @see 
     */
    @Schema(description = "图片地址")
    String src() default "";

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
     * 图片相对于容器的缩放方式
     *
     * 参考定义: {"type":"string","enum":["fill","contain","cover","none","scale-down"],"description":"图片相对于容器的缩放方式"}
     *
     * @see 
     */
    @Schema(description = "图片相对于容器的缩放方式")
    String fit() default "";

    /**
     * 形状
     *
     * 参考定义: {"type":"string","enum":["circle","square","rounded"],"description":"形状"}
     *
     * @see 
     */
    @Schema(description = "形状")
    String shape() default "";

    /**
     * 大小
     *
     * 参考定义: {"anyOf":[{"type":"number"},{"type":"string","const":"small"},{"type":"string","const":"default"},{"type":"string","const":"large"}],"description":"大小"}
     *
     * @see 
     */
    @Schema(description = "大小")
    double size() default 0;

    /**
     * 文本
     *
     * 参考定义: {"type":"string","description":"文本"}
     *
     * @see 
     */
    @Schema(description = "文本")
    String text() default "";

    /**
     * 字符类型距离左右两侧边界单位像素
     *
     * 参考定义: {"type":"number","description":"字符类型距离左右两侧边界单位像素"}
     *
     * @see 
     */
    @Schema(description = "字符类型距离左右两侧边界单位像素")
    double gap() default 0;

    /**
     * 图片无法显示时的替换文字地址
     *
     * 参考定义: {"type":"string","description":"图片无法显示时的替换文字地址"}
     *
     * @see 
     */
    @Schema(description = "图片无法显示时的替换文字地址")
    String alt() default "";

    /**
     * 图片是否允许拖动
     *
     * 参考定义: {"type":"boolean","description":"图片是否允许拖动"}
     *
     * @see 
     */
    @Schema(description = "图片是否允许拖动")
    boolean draggable() default false;

    /**
     * 图片CORS属性
     *
     * 参考定义: {"type":"string","enum":["anonymous","use-credentials",""],"description":"图片CORS属性"}
     *
     * @see 
     */
    @Schema(description = "图片CORS属性")
    String crossOrigin() default "";

    /**
     * 图片加载失败的是否默认处理，字符串函数
     *
     * 参考定义: {"type":"string","description":"图片加载失败的是否默认处理，字符串函数"}
     *
     * @see 
     */
    @Schema(description = "图片加载失败的是否默认处理，字符串函数")
    String onError() default "";


}
