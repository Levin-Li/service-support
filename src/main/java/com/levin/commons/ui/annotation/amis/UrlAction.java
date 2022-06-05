package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * UrlAction
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:11
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "UrlAction")
public @interface UrlAction {
///////////////////////////////////////////

	//指定按钮类型，支持 button、submit或者reset三种类型。
	enum Type{
		button,
		submit,
		reset,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//按钮样式
	enum Level{
		info,
		success,
		warning,
		danger,
		link,
		primary,
		dark,
		light,
		secondary,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//按钮大小
	enum Size{
		xs,
		sm,
		md,
		lg,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//null
	enum TooltipPlacement{
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
     * 指定按钮类型，支持 button、submit或者reset三种类型。
     *
     * 参考定义: {"type":"string","enum":["button","submit","reset"],"description":"指定按钮类型，支持 button、submit或者reset三种类型。"}
     *
     * @see 
     */
    @Schema(description = "指定按钮类型，支持 button、submit或者reset三种类型。")
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
     * 主要用于用户行为跟踪里区分是哪个按钮
     *
     * 参考定义: {"type":"string","description":"主要用于用户行为跟踪里区分是哪个按钮"}
     *
     * @see 
     */
    @Schema(description = "主要用于用户行为跟踪里区分是哪个按钮")
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
     * 是否为块状展示，默认为内联。
     *
     * 参考定义: {"type":"boolean","description":"是否为块状展示，默认为内联。"}
     *
     * @see 
     */
    @Schema(description = "是否为块状展示，默认为内联。")
    boolean block() default false;

    /**
     * 禁用时的文案提示。
     *
     * 参考定义: {"type":"string","description":"禁用时的文案提示。"}
     *
     * @see 
     */
    @Schema(description = "禁用时的文案提示。")
    String disabledTip() default "	";

    /**
     * 按钮图标， iconfont 的类名
     *
     * 参考定义: "#/definitions/SchemaIcon"
     *
     * @see 
     */
    @Schema(description = "按钮图标， iconfont 的类名")
    Icon icon() ;

    /**
     * icon 上的css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "icon 上的css 类名")
    String iconClassName() default "	";

    /**
     * 右侧按钮图标， iconfont 的类名
     *
     * 参考定义: "#/definitions/SchemaIcon"
     *
     * @see 
     */
    @Schema(description = "右侧按钮图标， iconfont 的类名")
    Icon rightIcon() ;

    /**
     * 右侧 icon 上的 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "右侧 icon 上的 css 类名")
    String rightIconClassName() default "	";

    /**
     * loading 上的css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "loading 上的css 类名")
    String loadingClassName() default "	";

    /**
     * 按钮文字
     *
     * 参考定义: {"type":"string","description":"按钮文字"}
     *
     * @see 
     */
    @Schema(description = "按钮文字")
    String label() default "	";

    /**
     * 按钮样式
     *
     * 参考定义: {"type":"string","enum":["info","success","warning","danger","link","primary","dark","light","secondary"],"description":"按钮样式"}
     *
     * @see 
     */
    @Schema(description = "按钮样式")
    Level level() ;

    /**
     * primary
     *
     * 参考定义: {"type":"boolean"}
     *
     * @see 
     */
    @Schema(description = "primary")
    boolean primary() default false;

    /**
     * 按钮大小
     *
     * 参考定义: {"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"}
     *
     * @see 
     */
    @Schema(description = "按钮大小")
    Size size() ;

    /**
     * tooltip
     *
     * 参考定义: "#/definitions/SchemaTooltip"
     *
     * @see 
     */
    @Schema(description = "tooltip")
    String tooltip() default "	";

    /**
     * tooltipPlacement
     *
     * 参考定义: {"type":"string","enum":["top","right","bottom","left"]}
     *
     * @see 
     */
    @Schema(description = "tooltipPlacement")
    TooltipPlacement tooltipPlacement() ;

    /**
     * 提示文字，配置了操作前会要求用户确认。
     *
     * 参考定义: {"type":"string","description":"提示文字，配置了操作前会要求用户确认。"}
     *
     * @see 
     */
    @Schema(description = "提示文字，配置了操作前会要求用户确认。")
    String confirmText() default "	";

    /**
     * 如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。
     *
     * 参考定义: {"type":"array","items":{"type":"string"},"description":"如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。"}
     *
     * @see 
     */
    @Schema(description = "如果按钮在form中，配置此属性会要求用户把指定的字段通过验证后才会触发行为。")
    String[] required() default "	";

    /**
     * 激活状态时的样式
     *
     * 参考定义: {"type":"string","description":"激活状态时的样式"}
     *
     * @see 
     */
    @Schema(description = "激活状态时的样式")
    String activeLevel() default "	";

    /**
     * 激活状态时的类名
     *
     * 参考定义: {"type":"string","description":"激活状态时的类名"}
     *
     * @see 
     */
    @Schema(description = "激活状态时的类名")
    String activeClassName() default "	";

    /**
     * 如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。
     *
     * 参考定义: {"type":["boolean","string"],"description":"如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。"}
     *
     * @see 
     */
    @Schema(description = "如果按钮在弹框中，可以配置这个动作完成后是否关闭弹窗，或者指定关闭目标弹框。")
    String close() default "	";

    /**
     * 当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。
     *
     * 参考定义: {"type":"boolean","description":"当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。"}
     *
     * @see 
     */
    @Schema(description = "当按钮时批量操作按钮时，默认必须有勾选元素才能可点击，如果此属性配置成 false，则没有点选成员也能点击。")
    boolean requireSelected() default false;

    /**
     * 是否将弹框中数据 merge 到父级作用域。
     *
     * 参考定义: {"type":"boolean","description":"是否将弹框中数据 merge 到父级作用域。"}
     *
     * @see 
     */
    @Schema(description = "是否将弹框中数据 merge 到父级作用域。")
    boolean mergeData() default false;

    /**
     * 可以指定让谁来触发这个动作。
     *
     * 参考定义: {"type":"string","description":"可以指定让谁来触发这个动作。"}
     *
     * @see 
     */
    @Schema(description = "可以指定让谁来触发这个动作。")
    String target() default "	";

    /**
     * 点击后的禁止倒计时（秒）
     *
     * 参考定义: {"type":"number","description":"点击后的禁止倒计时（秒）"}
     *
     * @see 
     */
    @Schema(description = "点击后的禁止倒计时（秒）")
    double countDown() default 0;

    /**
     * 倒计时文字自定义
     *
     * 参考定义: {"type":"string","description":"倒计时文字自定义"}
     *
     * @see 
     */
    @Schema(description = "倒计时文字自定义")
    String countDownTpl() default "	";

    /**
     * 角标
     *
     * 参考定义: "#/definitions/BadgeSchema"
     *
     * @see 
     */
    @Schema(description = "角标")
    Badge badge() ;

    /**
     * 键盘快捷键
     *
     * 参考定义: {"type":"string","description":"键盘快捷键"}
     *
     * @see 
     */
    @Schema(description = "键盘快捷键")
    String hotKey() default "	";

    /**
     * 是否显示loading效果
     *
     * 参考定义: {"type":"string","description":"是否显示loading效果"}
     *
     * @see 
     */
    @Schema(description = "是否显示loading效果")
    String loadingOn() default "	";

    /**
     * 自定义事件处理函数
     *
     * 参考定义: {"anyOf":[{"type":"string"},{}],"description":"自定义事件处理函数"}
     *
     * @see 
     */
    @Schema(description = "自定义事件处理函数")
    String onClick() default "	";

    /**
     * 子内容
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "子内容")
    String body() default "	";

    /**
     * 指定为打开链接
     *
     * 参考定义: {"type":"string","const":"url","description":"指定为打开链接"}
     *
     * @see 
     */
    @Schema(description = "指定为打开链接")
    String actionType() default "url";

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
     * 打开的目标地址
     *
     * 参考定义: {"type":"string","description":"打开的目标地址"}
     *
     * @see 
     */
    @Schema(description = "打开的目标地址")
    String url() default "	";

}
