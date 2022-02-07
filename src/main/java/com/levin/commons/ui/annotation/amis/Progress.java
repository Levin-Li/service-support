package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Progress
 *
 * 进度展示控件。 文档：https://baidu.gitee.io/amis/docs/components/progress
 *
 * @author auto gen by service-support at 2022-2-7 23:06:29
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "进度展示控件。 文档：https://baidu.gitee.io/amis/docs/components/progress")
public @interface Progress {
///////////////////////////////////////////

	//进度条类型。
	enum Mode{
		line,
		circle,
		dashboard,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//仪表盘进度条缺口位置
	enum GapPosition{
		top,
		bottom,
		left,
		right,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////
   /**
    *
    */
   String value() default "";

    /**
     * type
     *
     * 参考定义: {"type":"string","const":"progress"}
     *
     * @see 
     */
    @Schema(description = "type")
    String type() default "progress";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "容器 css 类名")
    String className() default "";

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
    String disabledOn() default "";

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
    String hiddenOn() default "";

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
    String visibleOn() default "";

    /**
     * 关联字段名。
     *
     * 参考定义: {"type":"string","description":"关联字段名。"}
     *
     * @see 
     */
    @Schema(description = "关联字段名。")
    String name() default "";

    /**
     * 进度条类型。
     *
     * 参考定义: {"type":"string","enum":["line","circle","dashboard"],"description":"进度条类型。"}
     *
     * @see 
     */
    @Schema(description = "进度条类型。")
    Mode mode() ;

    /**
     * 进度条 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "进度条 CSS 类名")
    String progressClassName() default "";

    /**
     * 进度外层 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "进度外层 CSS 类名")
    String progressBarClassName() default "";

    /**
     * 配置不通的值段，用不通的样式提示用户
     *
     * 参考定义: {"type":"array","items":{"type":"string"},"description":"配置不通的值段，用不通的样式提示用户"}
     *
     * @see 
     */
    @Schema(description = "配置不通的值段，用不通的样式提示用户")
    String[] map() default "";

    /**
     * 是否显示值
     *
     * 参考定义: {"type":"boolean","description":"是否显示值"}
     *
     * @see 
     */
    @Schema(description = "是否显示值")
    boolean showLabel() default false;

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * @see 
     */
    @Schema(description = "占位符")
    String placeholder() default "";

    /**
     * 是否显示背景间隔
     *
     * 参考定义: {"type":"boolean","description":"是否显示背景间隔"}
     *
     * @see 
     */
    @Schema(description = "是否显示背景间隔")
    boolean stripe() default false;

    /**
     * 是否显示动画（只有在开启的时候才能看出来）
     *
     * 参考定义: {"type":"boolean","description":"是否显示动画（只有在开启的时候才能看出来）"}
     *
     * @see 
     */
    @Schema(description = "是否显示动画（只有在开启的时候才能看出来）")
    boolean animate() default false;

    /**
     * 圆形进度条线的宽度
     *
     * 参考定义: {"type":"number","description":"圆形进度条线的宽度"}
     *
     * @see 
     */
    @Schema(description = "圆形进度条线的宽度")
    double strokeWidth() default 0;

    /**
     * 仪表盘进度条缺口角度，可取值 0 ~ 295
     *
     * 参考定义: {"type":"number","description":"仪表盘进度条缺口角度，可取值 0 ~ 295"}
     *
     * @see 
     */
    @Schema(description = "仪表盘进度条缺口角度，可取值 0 ~ 295")
    double gapDegree() default 0;

    /**
     * 仪表盘进度条缺口位置
     *
     * 参考定义: {"type":"string","enum":["top","bottom","left","right"],"description":"仪表盘进度条缺口位置"}
     *
     * @see 
     */
    @Schema(description = "仪表盘进度条缺口位置")
    GapPosition gapPosition() ;

    /**
     * 内容的模板函数
     *
     * 参考定义: {"type":"string","description":"内容的模板函数"}
     *
     * @see 
     */
    @Schema(description = "内容的模板函数")
    String valueTpl() default "";

}
