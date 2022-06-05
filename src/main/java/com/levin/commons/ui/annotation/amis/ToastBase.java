package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * ToastBase
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:11
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "ToastBase")
public @interface ToastBase {
///////////////////////////////////////////

	//弹出位置
	enum Position{
		top_right,
		top_center,
		top_left,
		bottom_center,
		bottom_left,
		bottom_right,
		center,
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
     * type
     *
     * 参考定义: "#/definitions/SchemaType"
     *
     * @see 
     */
    @Schema(description = "type")
    Type type() ;

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
     * 轻提示内容
     *
     * 参考定义: {"type":"array","items":{"type":"object","properties":{"title":{"$ref":"#/definitions/SchemaCollection"},"body":{"$ref":"#/definitions/SchemaCollection"},"level":{"type":"string","enum":["info","success","error","warning"]},"id":{"type":"string"},"position":{"type":"string","enum":["top-right","top-center","top-left","bottom-center","bottom-left","bottom-right","center"]},"closeButton":{"type":"boolean"},"showIcon":{"type":"boolean"},"timeout":{"type":"number"}},"required":["body","level","id"],"additionalProperties":false},"description":"轻提示内容"}
     *
     * @see 
     */
    @Schema(description = "轻提示内容")
    String[] items() default "	";

    /**
     * 弹出位置
     *
     * 参考定义: {"type":"string","enum":["top-right","top-center","top-left","bottom-center","bottom-left","bottom-right","center"],"description":"弹出位置"}
     *
     * @see 
     */
    @Schema(description = "弹出位置")
    Position position() ;

    /**
     * 是否展示关闭按钮
     *
     * 参考定义: {"type":"boolean","description":"是否展示关闭按钮"}
     *
     * @see 
     */
    @Schema(description = "是否展示关闭按钮")
    boolean closeButton() default false;

    /**
     * 是否展示图标
     *
     * 参考定义: {"type":"boolean","description":"是否展示图标"}
     *
     * @see 
     */
    @Schema(description = "是否展示图标")
    boolean showIcon() default false;

    /**
     * 持续时间
     *
     * 参考定义: {"type":"number","description":"持续时间"}
     *
     * @see 
     */
    @Schema(description = "持续时间")
    double timeout() default 0;

}
