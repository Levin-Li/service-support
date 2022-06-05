package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Spinner
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Spinner")
public @interface Spinner {
///////////////////////////////////////////

	//spinner Icon 大小
	enum Size{
		sm,
		lg,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//spinner文案位置
	enum TipPlacement{
		top,
		right,
		bottom,
		left,
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
     * 组件类型
     *
     * 参考定义: {"type":"string","const":"spinner","description":"组件类型"}
     *
     * @see 
     */
    @Schema(description = "组件类型")
    String type() default "spinner";

    /**
     * 自定义spinner的class
     *
     * 参考定义: {"type":"string","description":"自定义spinner的class"}
     *
     * @see 
     */
    @Schema(description = "自定义spinner的class")
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
     * 控制Spinner显示与隐藏
     *
     * 参考定义: {"type":"boolean","description":"控制Spinner显示与隐藏"}
     *
     * @see 
     */
    @Schema(description = "控制Spinner显示与隐藏")
    boolean show() default false;

    /**
     * spin图标位置包裹元素的自定义class
     *
     * 参考定义: {"type":"string","description":"spin图标位置包裹元素的自定义class"}
     *
     * @see 
     */
    @Schema(description = "spin图标位置包裹元素的自定义class")
    String spinnerClassName() default "	";

    /**
     * 作为容器使用时最外层元素的class
     *
     * 参考定义: {"type":"string","description":"作为容器使用时最外层元素的class"}
     *
     * @see 
     */
    @Schema(description = "作为容器使用时最外层元素的class")
    String spinnerWrapClassName() default "	";

    /**
     * mode
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "mode")
    String mode() default "	";

    /**
     * spinner Icon 大小
     *
     * 参考定义: {"type":"string","enum":["sm","lg",""],"description":"spinner Icon 大小"}
     *
     * @see 
     */
    @Schema(description = "spinner Icon 大小")
    Size size() ;

    /**
     * 自定义icon
     *
     * 参考定义: {"type":"string","description":"自定义icon"}
     *
     * @see 
     */
    @Schema(description = "自定义icon")
    String icon() default "	";

    /**
     * spinner文案
     *
     * 参考定义: {"type":"string","description":"spinner文案"}
     *
     * @see 
     */
    @Schema(description = "spinner文案")
    String tip() default "	";

    /**
     * spinner文案位置
     *
     * 参考定义: {"type":"string","enum":["top","right","bottom","left"],"description":"spinner文案位置"}
     *
     * @see 
     */
    @Schema(description = "spinner文案位置")
    TipPlacement tipPlacement() ;

    /**
     * 延迟显示
     *
     * 参考定义: {"type":"number","description":"延迟显示"}
     *
     * @see 
     */
    @Schema(description = "延迟显示")
    double delay() default 0;

    /**
     * 是否显示遮罩层
     *
     * 参考定义: {"type":"boolean","description":"是否显示遮罩层"}
     *
     * @see 
     */
    @Schema(description = "是否显示遮罩层")
    boolean overlay() default false;

    /**
     * 作为容器使用时内容
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "作为容器使用时内容")
    String body() default "	";

}
