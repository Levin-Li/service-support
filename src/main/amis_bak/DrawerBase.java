package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DrawerBase
 *
 * Construct a type with the properties of T except for those in type K.
 *
 * @author auto gen by service-support at 2022-2-10 12:04:41
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Construct a type with the properties of T except for those in type K.")
public @interface DrawerBase {
///////////////////////////////////////////

	//Dialog 大小
	enum Size{
		xs,
		sm,
		md,
		lg,
		full,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//从什么位置弹出
	enum Position{
		left,
		right,
		top,
		bottom,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////
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
    @Schema(title = "默认不用填写，自动会创建确认和取消按钮。")
    String[] actions() default "	";

    /**
     * 内容区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see
     */
    @Schema(title = "内容区域")
    String body() default "	";

    /**
     * 配置 Body 容器 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see
     */
    @Schema(title = "配置 Body 容器 className")
    String bodyClassName() default "	";

    /**
     * 是否支持按 ESC 关闭 Dialog
     *
     * 参考定义: {"type":"boolean","description":"是否支持按 ESC 关闭 Dialog"}
     *
     * @see
     */
    @Schema(title = "是否支持按 ESC 关闭 Dialog")
    boolean closeOnEsc() default false;

    /**
     * name
     *
     * 参考定义: "#/definitions/SchemaName"
     *
     * @see
     */
    @Schema(title = "name")
    String name() default "	";

    /**
     * Dialog 大小
     *
     * 参考定义: {"type":"string","enum":["xs","sm","md","lg","full"],"description":"Dialog 大小"}
     *
     * @see
     */
    @Schema(title = "Dialog 大小")
    Size size() ;

    /**
     * 请通过配置 title 设置标题
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see
     */
    @Schema(title = "请通过配置 title 设置标题")
    String title() default "	";

    /**
     * 从什么位置弹出
     *
     * 参考定义: {"type":"string","enum":["left","right","top","bottom"],"description":"从什么位置弹出"}
     *
     * @see
     */
    @Schema(title = "从什么位置弹出")
    Position position() ;

    /**
     * 头部
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see
     */
    @Schema(title = "头部")
    String header() default "	";

    /**
     * 底部
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see
     */
    @Schema(title = "底部")
    String footer() default "	";

    /**
     * 影响自动生成的按钮，如果自己配置了按钮这个配置无效。
     *
     * 参考定义: {"type":"boolean","description":"影响自动生成的按钮，如果自己配置了按钮这个配置无效。"}
     *
     * @see
     */
    @Schema(title = "影响自动生成的按钮，如果自己配置了按钮这个配置无效。")
    boolean confirm() default false;

    /**
     * 是否可以拖动弹窗大小
     *
     * 参考定义: {"type":"boolean","description":"是否可以拖动弹窗大小"}
     *
     * @see
     */
    @Schema(title = "是否可以拖动弹窗大小")
    boolean resizable() default false;

    /**
     * 是否显示蒙层
     *
     * 参考定义: {"type":"boolean","description":"是否显示蒙层"}
     *
     * @see
     */
    @Schema(title = "是否显示蒙层")
    boolean overlay() default false;

    /**
     * 点击外部的时候是否关闭弹框。
     *
     * 参考定义: {"type":"boolean","description":"点击外部的时候是否关闭弹框。"}
     *
     * @see
     */
    @Schema(title = "点击外部的时候是否关闭弹框。")
    boolean closeOnOutside() default false;

    /**
     * 是否显示错误信息
     *
     * 参考定义: {"type":"boolean","description":"是否显示错误信息"}
     *
     * @see
     */
    @Schema(title = "是否显示错误信息")
    boolean showErrorMsg() default false;

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see
     */
    @Schema(title = "容器 css 类名")
    String className() default "	";

    /**
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用"}
     *
     * @see
     */
    @Schema(title = "是否禁用")
    boolean disabled() default false;

    /**
     * 是否禁用表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see
     */
    @Schema(title = "是否禁用表达式")
    String disabledOn() default "	";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
     *
     * @see
     */
    @Schema(title = "是否隐藏")
    boolean hidden() default false;

    /**
     * 是否隐藏表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see
     */
    @Schema(title = "是否隐藏表达式")
    String hiddenOn() default "	";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
     *
     * @see
     */
    @Schema(title = "是否显示")
    boolean visible() default false;

    /**
     * 是否显示表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see
     */
    @Schema(title = "是否显示表达式")
    String visibleOn() default "	";

}
