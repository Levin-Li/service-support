package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Cards
 *
 * Cards 卡片集合渲染器。 文档：https://baidu.gitee.io/amis/docs/components/card
 *
 * @author auto gen by service-support at 2022-2-7 23:06:29
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Cards 卡片集合渲染器。 文档：https://baidu.gitee.io/amis/docs/components/card")
public @interface Cards {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "";

    /**
     * 指定为 cards 类型
     *
     * 参考定义: {"type":"string","const":"cards","description":"指定为 cards 类型"}
     *
     * @see 
     */
    @Schema(description = "指定为 cards 类型")
    String type() default "cards";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
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
     * @see 
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
     * @see 
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
     * @see 
     */
    @Schema(description = "是否显示表达式")
    String visibleOn() default "";

    /**
     * Construct a type with the properties of T except for those in type K.
     *
     * 参考定义: {"type":"object","properties":{"header":{"type":"object","properties":{"className":{"$ref":"#/definitions/SchemaClassName"},"title":{"$ref":"#/definitions/SchemaTpl","description":"标题"},"titleClassName":{"$ref":"#/definitions/SchemaClassName"},"subTitle":{"$ref":"#/definitions/SchemaTpl","description":"副标题"},"subTitleClassName":{"$ref":"#/definitions/SchemaClassName"},"subTitlePlaceholder":{"type":"string"},"description":{"$ref":"#/definitions/SchemaTpl","description":"描述"},"descriptionPlaceholder":{"type":"string","description":"描述占位内容"},"descriptionClassName":{"$ref":"#/definitions/SchemaClassName","description":"描述占位类名"},"desc":{"$ref":"#/definitions/SchemaTpl"},"descPlaceholder":{"$ref":"#/definitions/SchemaTpl"},"descClassName":{"$ref":"#/definitions/SchemaClassName"},"avatar":{"$ref":"#/definitions/SchemaUrlPath","description":"图片地址"},"avatarText":{"$ref":"#/definitions/SchemaTpl"},"avatarTextBackground":{"type":"array","items":{"type":"object","properties":{"length":{"type":"number","description":"Returns the length of a String object."}},"required":["length"],"additionalProperties":{"type":"string"},"description":"Allows manipulation and formatting of text strings and determination and location of substrings within strings."}},"avatarTextClassName":{"$ref":"#/definitions/SchemaClassName"},"avatarClassName":{"$ref":"#/definitions/SchemaClassName","description":"图片包括层类名"},"imageClassName":{"$ref":"#/definitions/SchemaClassName","description":"图片类名。"},"highlight":{"$ref":"#/definitions/SchemaExpression","description":"是否点亮"},"highlightClassName":{"$ref":"#/definitions/SchemaClassName"},"href":{"$ref":"#/definitions/SchemaTpl","description":"链接地址"},"blank":{"type":"boolean","description":"是否新窗口打开"}},"additionalProperties":false,"description":"头部配置"},"body":{"type":"array","items":{"$ref":"#/definitions/CardBodyField"},"description":"内容区域"},"media":{"type":"object","properties":{"className":{"$ref":"#/definitions/SchemaClassName"},"type":{"type":"string","enum":["image","video"],"description":"多媒体类型"},"url":{"$ref":"#/definitions/SchemaUrlPath","description":"多媒体链接地址"},"position":{"type":"string","enum":["top","left","right","bottom"],"description":"多媒体区域位置"},"autoPlay":{"type":"boolean","description":"类型为video时是否自动播放"},"isLive":{"type":"boolean","description":"类型为video时是否是直播"},"poster":{"$ref":"#/definitions/SchemaUrlPath","description":"类型为video时视频封面"}},"additionalProperties":false,"description":"多媒体区域"},"actions":{"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"底部按钮集合。"},"toolbar":{"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"工具栏按钮"},"secondary":{"$ref":"#/definitions/SchemaTpl","description":"次要说明"},"className":{"$ref":"#/definitions/SchemaClassName","description":"容器 css 类名"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"}},"additionalProperties":false,"description":"Construct a type with the properties of T except for those in type K."}
     *
     * @see 
     */
    @Schema(description = "Construct a type with the properties of T except for those in type K.")
    String card() default "";

    /**
     * 头部 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "头部 CSS 类名")
    String headerClassName() default "";

    /**
     * 底部 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "底部 CSS 类名")
    String footerClassName() default "";

    /**
     * 卡片 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "卡片 CSS 类名")
    String itemClassName() default "";

    /**
     * 无数据提示
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(description = "无数据提示")
    Tpl placeholder() ;

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
     * 数据源: 绑定当前环境变量
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * @see 
     */
    @Schema(description = "数据源: 绑定当前环境变量")
    String source() default "";

    /**
     * 标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(description = "标题")
    Tpl title() ;

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
     * 顶部区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "顶部区域")
    String header() default "";

    /**
     * 底部区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "底部区域")
    String footer() default "";

    /**
     * 配置某项是否可以点选
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see 
     */
    @Schema(description = "配置某项是否可以点选")
    String itemCheckableOn() default "";

    /**
     * 配置某项是否可拖拽排序，前提是要开启拖拽功能
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see 
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
     * 是否为瀑布流布局？
     *
     * 参考定义: {"type":"boolean","description":"是否为瀑布流布局？"}
     *
     * @see 
     */
    @Schema(description = "是否为瀑布流布局？")
    boolean masonryLayout() default false;

    /**
     * 可以用来作为值的字段
     *
     * 参考定义: {"type":"string","description":"可以用来作为值的字段"}
     *
     * @see 
     */
    @Schema(description = "可以用来作为值的字段")
    String valueField() default "";

}
