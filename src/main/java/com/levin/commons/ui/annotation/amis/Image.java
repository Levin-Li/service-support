package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Image
 *
 * 图片展示控件。 文档：https://baidu.gitee.io/amis/docs/components/image
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "图片展示控件。 文档：https://baidu.gitee.io/amis/docs/components/image")
public @interface Image {
///////////////////////////////////////////

	//指定为图片展示类型
	enum Type{
		image,
		static_image,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//图片展示模式，默认为缩略图模式、可以配置成原图模式
	enum ImageMode{
		thumb,
		original,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//预览图模式
	enum ThumbMode{
		w_full,
		h_full,
		contain,
		cover,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//预览图比率
	enum ThumbRatio{
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////

  /**
   * Any Of
   * 
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * 指定为图片展示类型
     *
     * 参考定义: {"type":"string","enum":["image","static-image"],"description":"指定为图片展示类型"}
     *
     * @see 
     */
    @Schema(description = "指定为图片展示类型")
    Type type() ;

    /**
     * 外层 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "外层 css 类名")
    String className() default "	";

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
    String disabledOn() default "	";

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
    String hiddenOn() default "	";

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
    String visibleOn() default "	";

    /**
     * 组件唯一 id，主要用于日志采集
     *
     * 参考定义: {"type":"string","description":"组件唯一 id，主要用于日志采集"}
     *
     * @see 
     */
    @Schema(description = "组件唯一 id，主要用于日志采集")
    String id() default "	";

    /**
     * 事件动作配置
     *
     * 参考定义: {"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"}
     *
     * @see 
     */
    @Schema(description = "事件动作配置")
    String onEvent() default "	";

    /**
     * 默认图片地址
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     * @see 
     */
    @Schema(description = "默认图片地址")
    String defaultImage() default "	";

    /**
     * 图片标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(description = "图片标题")
    Tpl title() ;

    /**
     * 关联字段名，也可以直接配置 src
     *
     * 参考定义: {"type":"string","description":"关联字段名，也可以直接配置 src"}
     *
     * @see 
     */
    @Schema(description = "关联字段名，也可以直接配置 src")
    String name() default "	";

    /**
     * 图片描述信息
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(description = "图片描述信息")
    Tpl imageCaption() ;

    /**
     * 图片地址，如果配置了 name，这个属性不用配置。
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     * @see 
     */
    @Schema(description = "图片地址，如果配置了 name，这个属性不用配置。")
    String src() default "	";

    /**
     * 大图地址，不设置用 src
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     * @see 
     */
    @Schema(description = "大图地址，不设置用 src")
    String originalSrc() default "	";

    /**
     * 是否启动放大功能。
     *
     * 参考定义: {"type":"boolean","description":"是否启动放大功能。"}
     *
     * @see 
     */
    @Schema(description = "是否启动放大功能。")
    boolean enlargeAble() default false;

    /**
     * 图片无法显示时的替换文本
     *
     * 参考定义: {"type":"string","description":"图片无法显示时的替换文本"}
     *
     * @see 
     */
    @Schema(description = "图片无法显示时的替换文本")
    String alt() default "	";

    /**
     * 高度
     *
     * 参考定义: {"type":"number","description":"高度"}
     *
     * @see 
     */
    @Schema(description = "高度")
    double height() default 0;

    /**
     * 宽度
     *
     * 参考定义: {"type":"number","description":"宽度"}
     *
     * @see 
     */
    @Schema(description = "宽度")
    double width() default 0;

    /**
     * 图片 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "图片 css 类名")
    String imageClassName() default "	";

    /**
     * 图片缩率图外层 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "图片缩率图外层 css 类名")
    String thumbClassName() default "	";

    /**
     * caption
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(description = "caption")
    Tpl caption() ;

    /**
     * 图片展示模式，默认为缩略图模式、可以配置成原图模式
     *
     * 参考定义: {"type":"string","enum":["thumb","original"],"description":"图片展示模式，默认为缩略图模式、可以配置成原图模式"}
     *
     * @see 
     */
    @Schema(description = "图片展示模式，默认为缩略图模式、可以配置成原图模式")
    ImageMode imageMode() ;

    /**
     * 预览图模式
     *
     * 参考定义: {"type":"string","enum":["w-full","h-full","contain","cover"],"description":"预览图模式"}
     *
     * @see 
     */
    @Schema(description = "预览图模式")
    ThumbMode thumbMode() ;

    /**
     * 预览图比率
     *
     * 参考定义: {"type":"string","enum":["1:1","4:3","16:9"],"description":"预览图比率"}
     *
     * @see 
     */
    @Schema(description = "预览图比率")
    ThumbRatio thumbRatio() ;

    /**
     * 链接地址
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(description = "链接地址")
    Tpl href() ;

    /**
     * 是否新窗口打开
     *
     * 参考定义: {"type":"boolean","description":"是否新窗口打开"}
     *
     * @see 
     */
    @Schema(description = "是否新窗口打开")
    boolean blank() default false;

    /**
     * 链接的 target
     *
     * 参考定义: {"type":"string","description":"链接的 target"}
     *
     * @see 
     */
    @Schema(description = "链接的 target")
    String htmlTarget() default "	";

}
