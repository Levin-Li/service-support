package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Images
 *
 * 图片集展示控件。 文档：https://baidu.gitee.io/amis/docs/components/images
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "图片集展示控件。 文档：https://baidu.gitee.io/amis/docs/components/images")
public @interface Images {
///////////////////////////////////////////

	//指定为图片集渲染器
	enum Type{
		images,
		static_images,
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
     * 指定为图片集渲染器
     *
     * 参考定义: {"type":"string","enum":["images","static-images"],"description":"指定为图片集渲染器"}
     *
     * @see 
     */
    @Schema(description = "指定为图片集渲染器")
    Type type() ;

    /**
     * 外层 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "外层 CSS 类名")
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
     * 列表为空时显示
     *
     * 参考定义: {"type":"string","description":"列表为空时显示"}
     *
     * @see 
     */
    @Schema(description = "列表为空时显示")
    String placeholder() default "	";

    /**
     * 配置值的连接符
     *
     * 参考定义: {"type":"string","description":"配置值的连接符"}
     *
     * @see 
     */
    @Schema(description = "配置值的连接符")
    String delimiter() default "	";

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
     * 关联字段名，也可以直接配置 src
     *
     * 参考定义: {"type":"string","description":"关联字段名，也可以直接配置 src"}
     *
     * @see 
     */
    @Schema(description = "关联字段名，也可以直接配置 src")
    String name() default "	";

    /**
     * value
     *
     * 参考定义: {}
     *
     * @see 
     */
    @Schema(description = "value")
    String value() default "	";

    /**
     * source
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "source")
    String source() default "	";

    /**
     * options
     *
     * 参考定义: {"type":"array","items":{}}
     *
     * @see 
     */
    @Schema(description = "options")
    String[] options() default "	";

    /**
     * 图片地址，默认读取数据中的 image 属性，如果不是请配置 ,如  ${imageUrl}
     *
     * 参考定义: {"type":"string","description":"图片地址，默认读取数据中的 image 属性，如果不是请配置 ,如  ${imageUrl}"}
     *
     * @see 
     */
    @Schema(description = "图片地址，默认读取数据中的 image 属性，如果不是请配置 ,如  ${imageUrl}")
    String src() default "	";

    /**
     * 大图地址，不设置用 src 属性，如果不是请配置，如：${imageOriginUrl}
     *
     * 参考定义: {"type":"string","description":"大图地址，不设置用 src 属性，如果不是请配置，如：${imageOriginUrl}"}
     *
     * @see 
     */
    @Schema(description = "大图地址，不设置用 src 属性，如果不是请配置，如：${imageOriginUrl}")
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
     * 是否显示尺寸。
     *
     * 参考定义: {"type":"boolean","description":"是否显示尺寸。"}
     *
     * @see 
     */
    @Schema(description = "是否显示尺寸。")
    boolean showDimensions() default false;

    /**
     * 列表 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "列表 CSS 类名")
    String listClassName() default "	";

}
