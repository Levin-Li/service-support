package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Carousel
 *
 * Carousel 轮播图渲染器。 文档：https://baidu.gitee.io/amis/docs/components/carousel
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Carousel 轮播图渲染器。 文档：https://baidu.gitee.io/amis/docs/components/carousel")
public @interface Carousel {
///////////////////////////////////////////

	//null
	enum ControlsTheme{
		light,
		dark,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//配置控件内容
	enum Control{
		dots,
		arrows,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//动画类型
	enum Animation{
		fade,
		slide,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//预览图模式
	enum ThumbMode{
		contain,
		cover,
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
     * 指定为轮播图类型
     *
     * 参考定义: {"type":"string","const":"carousel","description":"指定为轮播图类型"}
     *
     * @see 
     */
    @Schema(description = "指定为轮播图类型")
    String type() default "carousel";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "容器 css 类名")
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
     * 是否自动播放
     *
     * 参考定义: {"type":"boolean","description":"是否自动播放"}
     *
     * @see 
     */
    @Schema(description = "是否自动播放")
    boolean auto() default false;

    /**
     * 轮播间隔时间
     *
     * 参考定义: {"type":"number","description":"轮播间隔时间"}
     *
     * @see 
     */
    @Schema(description = "轮播间隔时间")
    double interval() default 0;

    /**
     * 动画时长
     *
     * 参考定义: {"type":"number","description":"动画时长"}
     *
     * @see 
     */
    @Schema(description = "动画时长")
    double duration() default 0;

    /**
     * 设置宽度
     *
     * 参考定义: {"type":"number","description":"设置宽度"}
     *
     * @see 
     */
    @Schema(description = "设置宽度")
    double width() default 0;

    /**
     * 设置高度
     *
     * 参考定义: {"type":"number","description":"设置高度"}
     *
     * @see 
     */
    @Schema(description = "设置高度")
    double height() default 0;

    /**
     * controlsTheme
     *
     * 参考定义: {"type":"string","enum":["light","dark"]}
     *
     * @see 
     */
    @Schema(description = "controlsTheme")
    ControlsTheme controlsTheme() ;

    /**
     * 占位
     *
     * 参考定义: {"type":"string","description":"占位"}
     *
     * @see 
     */
    @Schema(description = "占位")
    String placeholder() default "	";

    /**
     * 配置控件内容
     *
     * 参考定义: {"type":"array","items":{"type":"string","enum":["dots","arrows"]},"description":"配置控件内容"}
     *
     * @see 
     */
    @Schema(description = "配置控件内容")
    Control[] controls() ;

    /**
     * 动画类型
     *
     * 参考定义: {"type":"string","enum":["fade","slide"],"description":"动画类型"}
     *
     * @see 
     */
    @Schema(description = "动画类型")
    Animation animation() ;

    /**
     * item
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "item")
    String item() default "	";

    /**
     * name
     *
     * 参考定义: "#/definitions/SchemaName"
     *
     * @see 
     */
    @Schema(description = "name")
    String name() default "	";

    /**
     * 预览图模式
     *
     * 参考定义: {"type":"string","enum":["contain","cover"],"description":"预览图模式"}
     *
     * @see 
     */
    @Schema(description = "预览图模式")
    ThumbMode thumbMode() ;

    /**
     * 配置固定值
     *
     * 参考定义: {"type":"array","items":{},"description":"配置固定值"}
     *
     * @see 
     */
    @Schema(description = "配置固定值")
    String[] options() default "	";

}
