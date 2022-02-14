package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Remark
 *
 * 提示渲染器，默认会显示个小图标，鼠标放上来的时候显示配置的内容。
 *
 * @author auto gen by service-support at 2022-2-10 12:04:41
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "提示渲染器，默认会显示个小图标，鼠标放上来的时候显示配置的内容。")
public @interface Remark {
///////////////////////////////////////////

	//触发规则
	enum Trigger{
		click,
		hover,
		focus,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//显示位置
	enum Placement{
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
    *
    */
   String value() default "	";

    /**
     * 指定为提示类型
     *
     * 参考定义: {"type":"string","const":"remark","description":"指定为提示类型"}
     *
     * @see 
     */
    @Schema(description = "指定为提示类型")
    String type() default "remark";

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
     * label
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "label")
    String label() default "	";

    /**
     * icon
     *
     * 参考定义: "#/definitions/SchemaIcon"
     *
     * @see 
     */
    @Schema(description = "icon")
    Icon icon() ;

    /**
     * tooltipClassName
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "tooltipClassName")
    String tooltipClassName() default "	";

    /**
     * 触发规则
     *
     * 参考定义: {"type":"array","items":{"type":"string","enum":["click","hover","focus"]},"description":"触发规则"}
     *
     * @see 
     */
    @Schema(description = "触发规则")
    Trigger[] trigger() ;

    /**
     * 提示标题
     *
     * 参考定义: {"type":"string","description":"提示标题"}
     *
     * @see 
     */
    @Schema(description = "提示标题")
    String title() default "	";

    /**
     * 提示内容
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(description = "提示内容")
    Tpl content() ;

    /**
     * 显示位置
     *
     * 参考定义: {"type":"string","enum":["top","right","bottom","left"],"description":"显示位置"}
     *
     * @see 
     */
    @Schema(description = "显示位置")
    Placement placement() ;

    /**
     * 点击其他内容时是否关闭弹框信息
     *
     * 参考定义: {"type":"boolean","description":"点击其他内容时是否关闭弹框信息"}
     *
     * @see 
     */
    @Schema(description = "点击其他内容时是否关闭弹框信息")
    boolean rootClose() default false;

}
