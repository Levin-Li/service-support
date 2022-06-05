package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * FeedbackDialog
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:11
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "FeedbackDialog")
public @interface FeedbackDialog {
///////////////////////////////////////////

	//Dialog 大小
	enum Size{
		xs,
		sm,
		md,
		lg,
		xl,
		full,
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
     * 默认不用填写，自动会创建确认和取消按钮。
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * @see 
     */
    @Schema(description = "默认不用填写，自动会创建确认和取消按钮。")
    String[] actions() default "	";

    /**
     * 内容区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "内容区域")
    String body() default "	";

    /**
     * 配置 Body 容器 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "配置 Body 容器 className")
    String bodyClassName() default "	";

    /**
     * 是否支持按 ESC 关闭 Dialog
     *
     * 参考定义: {"type":"boolean","description":"是否支持按 ESC 关闭 Dialog"}
     *
     * @see 
     */
    @Schema(description = "是否支持按 ESC 关闭 Dialog")
    boolean closeOnEsc() default false;

    /**
     * 是否支持点其它区域关闭 Dialog
     *
     * 参考定义: {"type":"boolean","description":"是否支持点其它区域关闭 Dialog"}
     *
     * @see 
     */
    @Schema(description = "是否支持点其它区域关闭 Dialog")
    boolean closeOnOutside() default false;

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
     * Dialog 大小
     *
     * 参考定义: {"type":"string","enum":["xs","sm","md","lg","xl","full"],"description":"Dialog 大小"}
     *
     * @see 
     */
    @Schema(description = "Dialog 大小")
    Size size() ;

    /**
     * 请通过配置 title 设置标题
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "请通过配置 title 设置标题")
    String title() default "	";

    /**
     * header
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "header")
    String header() default "	";

    /**
     * headerClassName
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "headerClassName")
    String headerClassName() default "	";

    /**
     * footer
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "footer")
    String footer() default "	";

    /**
     * 影响自动生成的按钮，如果自己配置了按钮这个配置无效。
     *
     * 参考定义: {"type":"boolean","description":"影响自动生成的按钮，如果自己配置了按钮这个配置无效。"}
     *
     * @see 
     */
    @Schema(description = "影响自动生成的按钮，如果自己配置了按钮这个配置无效。")
    boolean confirm() default false;

    /**
     * 是否显示关闭按钮
     *
     * 参考定义: {"type":"boolean","description":"是否显示关闭按钮"}
     *
     * @see 
     */
    @Schema(description = "是否显示关闭按钮")
    boolean showCloseButton() default false;

    /**
     * 是否显示错误信息
     *
     * 参考定义: {"type":"boolean","description":"是否显示错误信息"}
     *
     * @see 
     */
    @Schema(description = "是否显示错误信息")
    boolean showErrorMsg() default false;

    /**
     * 是否显示 spinner
     *
     * 参考定义: {"type":"boolean","description":"是否显示 spinner"}
     *
     * @see 
     */
    @Schema(description = "是否显示 spinner")
    boolean showLoading() default false;

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
     * 可以用来配置 feedback 的出现条件
     *
     * 参考定义: {"type":"string","description":"可以用来配置 feedback 的出现条件"}
     *
     * @see 
     */
    @Schema(description = "可以用来配置 feedback 的出现条件")
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
     * feedback 弹框取消是否中断后续操作
     *
     * 参考定义: {"type":"boolean","description":"feedback 弹框取消是否中断后续操作"}
     *
     * @see 
     */
    @Schema(description = "feedback 弹框取消是否中断后续操作")
    boolean skipRestOnCancel() default false;

    /**
     * feedback 弹框确认是否中断后续操作
     *
     * 参考定义: {"type":"boolean","description":"feedback 弹框确认是否中断后续操作"}
     *
     * @see 
     */
    @Schema(description = "feedback 弹框确认是否中断后续操作")
    boolean skipRestOnConfirm() default false;

}
