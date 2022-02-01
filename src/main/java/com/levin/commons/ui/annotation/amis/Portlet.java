package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Portlet
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Portlet")
public @interface Portlet {

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
     * 指定为 portlet 类型
     *
     * 参考定义: {"type":"string","const":"portlet","description":"指定为 portlet 类型"}
     *
     * @see 
     */
    @Schema(description = "指定为 portlet 类型")
    String type() default "";

    /**
     * tabs
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/PortletTabSchema"}}
     *
     * @see 
     */
    @Schema(description = "tabs")
    String[] tabs() default {};

    /**
     * 关联已有数据，选项卡直接根据目标数据重复。
     *
     * 参考定义: {"type":"string","description":"关联已有数据，选项卡直接根据目标数据重复。"}
     *
     * @see 
     */
    @Schema(description = "关联已有数据，选项卡直接根据目标数据重复。")
    String source() default "";

    /**
     * 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "类名")
    String tabsClassName() default "";

    /**
     * 展示形式
     *
     * 参考定义: {"type":"string","enum":["","line","card","radio","vertical","tiled"],"description":"展示形式"}
     *
     * @see 
     */
    @Schema(description = "展示形式")
    String tabsMode() default "";

    /**
     * 内容类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "内容类名")
    String contentClassName() default "";

    /**
     * 链接外层类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "链接外层类名")
    String linksClassName() default "";

    /**
     * 卡片是否只有在点开的时候加载？
     *
     * 参考定义: {"type":"boolean","description":"卡片是否只有在点开的时候加载？"}
     *
     * @see 
     */
    @Schema(description = "卡片是否只有在点开的时候加载？")
    boolean mountOnEnter() default false;

    /**
     * 卡片隐藏的时候是否销毁卡片内容
     *
     * 参考定义: {"type":"boolean","description":"卡片隐藏的时候是否销毁卡片内容"}
     *
     * @see 
     */
    @Schema(description = "卡片隐藏的时候是否销毁卡片内容")
    boolean unmountOnExit() default false;

    /**
     * 可以在右侧配置点其他功能按钮。不会随着tab切换
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"可以在右侧配置点其他功能按钮。不会随着tab切换"}
     *
     * @see 
     */
    @Schema(description = "可以在右侧配置点其他功能按钮。不会随着tab切换")
    String[] toolbar() default {};

    /**
     * 是否支持溢出滚动
     *
     * 参考定义: {"type":"boolean","description":"是否支持溢出滚动"}
     *
     * @see 
     */
    @Schema(description = "是否支持溢出滚动")
    boolean scrollable() default false;

    /**
     * header和内容是否展示分割线
     *
     * 参考定义: {"type":"boolean","description":"header和内容是否展示分割线"}
     *
     * @see 
     */
    @Schema(description = "header和内容是否展示分割线")
    boolean divider() default false;

    /**
     * 标题右侧的描述
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "标题右侧的描述")
    String description() default "";

    /**
     * 隐藏头部
     *
     * 参考定义: {"type":"boolean","description":"隐藏头部"}
     *
     * @see 
     */
    @Schema(description = "隐藏头部")
    boolean hideHeader() default false;

    /**
     * 自定义样式
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"object"}],"description":"自定义样式"}
     *
     * @see 
     */
    @Schema(description = "自定义样式")
    String style() default "";


}
