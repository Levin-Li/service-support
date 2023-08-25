package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * PopOverObject
 *
 *
 *
 * @author auto gen by service-support at 2022-2-10 12:04:41
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "PopOverObject")
public @interface PopOverObject {
///////////////////////////////////////////

	//弹出模式
	enum Mode{
		dialog,
		drawer,
		popOver,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//是弹窗形式的时候有用。
	enum Size{
		sm,
		md,
		lg,
		xl,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//弹出位置
	enum Position{
		center,
		left_top,
		left_top_left_top,
		left_top_left_center,
		left_top_left_bottom,
		left_top_center_top,
		left_top_center_center,
		left_top_center_bottom,
		left_top_right_top,
		left_top_right_center,
		left_top_right_bottom,
		right_top,
		right_top_left_top,
		right_top_left_center,
		right_top_left_bottom,
		right_top_center_top,
		right_top_center_center,
		right_top_center_bottom,
		right_top_right_top,
		right_top_right_center,
		right_top_right_bottom,
		left_bottom,
		left_bottom_left_top,
		left_bottom_left_center,
		left_bottom_left_bottom,
		left_bottom_center_top,
		left_bottom_center_center,
		left_bottom_center_bottom,
		left_bottom_right_top,
		left_bottom_right_center,
		left_bottom_right_bottom,
		right_bottom,
		right_bottom_left_top,
		right_bottom_left_center,
		right_bottom_left_bottom,
		right_bottom_center_top,
		right_bottom_center_center,
		right_bottom_center_bottom,
		right_bottom_right_top,
		right_bottom_right_center,
		right_bottom_right_bottom,
		fixed_center,
		fixed_left_top,
		fixed_right_top,
		fixed_left_bottom,
		fixed_right_bottom,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//触发条件，默认是 click
	enum Trigger{
		click,
		hover,
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
     * 类名
     *
     * 参考定义: {"type":"string","description":"类名"}
     *
     * @see
     */
    @Schema(title = "类名")
    String className() default "	";

    /**
     * 弹框外层类名
     *
     * 参考定义: {"type":"string","description":"弹框外层类名"}
     *
     * @see
     */
    @Schema(title = "弹框外层类名")
    String popOverClassName() default "	";

    /**
     * 配置当前行是否启动，要用表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see
     */
    @Schema(title = "配置当前行是否启动，要用表达式")
    String popOverEnableOn() default "	";

    /**
     * 弹出模式
     *
     * 参考定义: {"type":"string","enum":["dialog","drawer","popOver"],"description":"弹出模式"}
     *
     * @see
     */
    @Schema(title = "弹出模式")
    Mode mode() ;

    /**
     * 是弹窗形式的时候有用。
     *
     * 参考定义: {"type":"string","enum":["sm","md","lg","xl"],"description":"是弹窗形式的时候有用。"}
     *
     * @see
     */
    @Schema(title = "是弹窗形式的时候有用。")
    Size size() ;

    /**
     * 弹出位置
     *
     * 参考定义: {"type":"string","enum":["center","left-top","left-top-left-top","left-top-left-center","left-top-left-bottom","left-top-center-top","left-top-center-center","left-top-center-bottom","left-top-right-top","left-top-right-center","left-top-right-bottom","right-top","right-top-left-top","right-top-left-center","right-top-left-bottom","right-top-center-top","right-top-center-center","right-top-center-bottom","right-top-right-top","right-top-right-center","right-top-right-bottom","left-bottom","left-bottom-left-top","left-bottom-left-center","left-bottom-left-bottom","left-bottom-center-top","left-bottom-center-center","left-bottom-center-bottom","left-bottom-right-top","left-bottom-right-center","left-bottom-right-bottom","right-bottom","right-bottom-left-top","right-bottom-left-center","right-bottom-left-bottom","right-bottom-center-top","right-bottom-center-center","right-bottom-center-bottom","right-bottom-right-top","right-bottom-right-center","right-bottom-right-bottom","fixed-center","fixed-left-top","fixed-right-top","fixed-left-bottom","fixed-right-bottom"],"description":"弹出位置"}
     *
     * @see
     */
    @Schema(title = "弹出位置")
    Position position() ;

    /**
     * 触发条件，默认是 click
     *
     * 参考定义: {"type":"string","enum":["click","hover"],"description":"触发条件，默认是 click"}
     *
     * @see
     */
    @Schema(title = "触发条件，默认是 click")
    Trigger trigger() ;

    /**
     * 是否显示查看更多的 icon，通常是放大图标。
     *
     * 参考定义: {"type":"boolean","description":"是否显示查看更多的 icon，通常是放大图标。"}
     *
     * @see
     */
    @Schema(title = "是否显示查看更多的 icon，通常是放大图标。")
    boolean showIcon() default false;

    /**
     * 偏移量
     *
     * 参考定义: {"type":"object","properties":{"top":{"type":"number"},"left":{"type":"number"}},"additionalProperties":false,"description":"偏移量"}
     *
     * @see
     */
    @Schema(title = "偏移量")
    String offset() default "	";

    /**
     * 标题
     *
     * 参考定义: {"type":"string","description":"标题"}
     *
     * @see
     */
    @Schema(title = "标题")
    String title() default "	";

    /**
     * body
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see
     */
    @Schema(title = "body")
    String body() default "	";

}
