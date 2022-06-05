package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Tab
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Tab")
public @interface Tab {
///////////////////////////////////////////

	//null
	enum IconPosition{
		left,
		right,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//配置子表单项默认的展示方式。
	enum Mode{
		normal,
		inline,
		horizontal,
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
     * Tab 标题
     *
     * 参考定义: {"type":"string","description":"Tab 标题"}
     *
     * @see 
     */
    @Schema(description = "Tab 标题")
    String title() default "	";

    /**
     * 内容
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "内容")
    String tab() default "	";

    /**
     * 内容
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "内容")
    String body() default "	";

    /**
     * 徽标
     *
     * 参考定义: {"type":"number","description":"徽标"}
     *
     * @see 
     */
    @Schema(description = "徽标")
    double badge() default 0;

    /**
     * 设置以后将跟url的hash对应
     *
     * 参考定义: {"type":"string","description":"设置以后将跟url的hash对应"}
     *
     * @see 
     */
    @Schema(description = "设置以后将跟url的hash对应")
    String hash() default "	";

    /**
     * 按钮图标
     *
     * 参考定义: "#/definitions/SchemaIcon"
     *
     * @see 
     */
    @Schema(description = "按钮图标")
    Icon icon() ;

    /**
     * iconPosition
     *
     * 参考定义: {"type":"string","enum":["left","right"]}
     *
     * @see 
     */
    @Schema(description = "iconPosition")
    IconPosition iconPosition() ;

    /**
     * 设置以后内容每次都会重新渲染
     *
     * 参考定义: {"type":"boolean","description":"设置以后内容每次都会重新渲染"}
     *
     * @see 
     */
    @Schema(description = "设置以后内容每次都会重新渲染")
    boolean reload() default false;

    /**
     * 点开时才加载卡片内容
     *
     * 参考定义: {"type":"boolean","description":"点开时才加载卡片内容"}
     *
     * @see 
     */
    @Schema(description = "点开时才加载卡片内容")
    boolean mountOnEnter() default false;

    /**
     * 卡片隐藏就销毁卡片节点。
     *
     * 参考定义: {"type":"boolean","description":"卡片隐藏就销毁卡片节点。"}
     *
     * @see 
     */
    @Schema(description = "卡片隐藏就销毁卡片节点。")
    boolean unmountOnExit() default false;

    /**
     * 配置子表单项默认的展示方式。
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置子表单项默认的展示方式。"}
     *
     * @see 
     */
    @Schema(description = "配置子表单项默认的展示方式。")
    Mode mode() ;

    /**
     * 如果是水平排版，这个属性可以细化水平排版的左右宽度占比。
     *
     * 参考定义: "#/definitions/FormSchemaHorizontal"
     *
     * @see 
     */
    @Schema(description = "如果是水平排版，这个属性可以细化水平排版的左右宽度占比。")
    FormHorizontal horizontal() ;

    /**
     * 是否可关闭，优先级高于 tabs 的 closable
     *
     * 参考定义: {"type":"boolean","description":"是否可关闭，优先级高于 tabs 的 closable"}
     *
     * @see 
     */
    @Schema(description = "是否可关闭，优先级高于 tabs 的 closable")
    boolean closable() default false;

}
