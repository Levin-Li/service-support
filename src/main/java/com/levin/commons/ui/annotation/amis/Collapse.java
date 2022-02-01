package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Collapse
 *
 * \"Collapse 折叠渲染器，格式说明。 文档：https://baidu.gitee.io/amis/docs/components/collapse\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"Collapse 折叠渲染器，格式说明。 文档：https://baidu.gitee.io/amis/docs/components/collapse\"")
public @interface Collapse {

    /**
     * 指定为折叠器类型
     *
     * 参考定义: {"type":"string","const":"collapse","description":"指定为折叠器类型"}
     *
     * @see 
     */
    @Schema(description = "指定为折叠器类型")
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
     * 标识
     *
     * 参考定义: {"type":"string","description":"标识"}
     *
     * @see 
     */
    @Schema(description = "标识")
    String key() default "";

    /**
     * 标题展示位置
     *
     * 参考定义: {"type":"string","enum":["top","bottom"],"description":"标题展示位置"}
     *
     * @see 
     */
    @Schema(description = "标题展示位置")
    String headerPosition() default "";

    /**
     * 标题
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/SchemaCollection"}],"description":"标题"}
     *
     * @see 
     */
    @Schema(description = "标题")
    String header() default "";

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
     * 配置 Body 容器 className
     *
     * 参考定义: {"type":"string","description":"配置 Body 容器 className"}
     *
     * @see 
     */
    @Schema(description = "配置 Body 容器 className")
    String bodyClassName() default "";

    /**
     * 是否可折叠
     *
     * 参考定义: {"type":"boolean","description":"是否可折叠"}
     *
     * @see 
     */
    @Schema(description = "是否可折叠")
    boolean collapsable() default false;

    /**
     * 默认是否折叠
     *
     * 参考定义: {"type":"boolean","description":"默认是否折叠"}
     *
     * @see 
     */
    @Schema(description = "默认是否折叠")
    boolean collapsed() default false;

    /**
     * 图标是否展示
     *
     * 参考定义: {"type":"boolean","description":"图标是否展示"}
     *
     * @see 
     */
    @Schema(description = "图标是否展示")
    boolean showArrow() default false;

    /**
     * 自定义切换图标
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * @see String
     */
    @Schema(description = "自定义切换图标")
    String expandIcon() default "";

    /**
     * 标题 CSS 类名
     *
     * 参考定义: {"type":"string","description":"标题 CSS 类名"}
     *
     * @see 
     */
    @Schema(description = "标题 CSS 类名")
    String headingClassName() default "";

    /**
     * 收起的标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "收起的标题")
    String collapseHeader() default "";

    /**
     * 控件大小
     *
     * 参考定义: {"type":"string","enum":["xs","sm","md","lg","base"],"description":"控件大小"}
     *
     * @see 
     */
    @Schema(description = "控件大小")
    String size() default "";

    /**
     * 点开时才加载内容
     *
     * 参考定义: {"type":"boolean","description":"点开时才加载内容"}
     *
     * @see 
     */
    @Schema(description = "点开时才加载内容")
    boolean mountOnEnter() default false;

    /**
     * 卡片隐藏就销毁内容。
     *
     * 参考定义: {"type":"boolean","description":"卡片隐藏就销毁内容。"}
     *
     * @see 
     */
    @Schema(description = "卡片隐藏就销毁内容。")
    boolean unmountOnExit() default false;


}
