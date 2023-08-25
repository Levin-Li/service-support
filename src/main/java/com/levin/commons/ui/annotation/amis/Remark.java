package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Remark
 *
 * 提示渲染器，默认会显示个小图标，鼠标放上来的时候显示配置的内容。
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:01
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "提示渲染器，默认会显示个小图标，鼠标放上来的时候显示配置的内容。")
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

	//icon的形状
	enum Shape{
		circle,
		square,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////

  /**
   * Any Of
   *
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * 指定为提示类型
     *
     * 参考定义: {"type":"string","const":"remark","description":"指定为提示类型"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "指定为提示类型")
    String type() default "remark";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "容器 css 类名")
    String className() default "	";

    /**
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用"}
     *
     *
     *
     *
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
     *
     *
     *
     *
     * @see Expression
     */

    @Schema(title = "是否禁用表达式")
    String disabledOn() default "	";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
     *
     *
     *
     *
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
     *
     *
     *
     *
     * @see Expression
     */

    @Schema(title = "是否隐藏表达式")
    String hiddenOn() default "	";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
     *
     *
     *
     *
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
     *
     *
     *
     *
     * @see Expression
     */

    @Schema(title = "是否显示表达式")
    String visibleOn() default "	";

    /**
     * 组件唯一 id，主要用于日志采集
     *
     * 参考定义: {"type":"string","description":"组件唯一 id，主要用于日志采集"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "组件唯一 id，主要用于日志采集")
    String id() default "	";

    /**
     * 事件动作配置
     *
     * 参考定义: {"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "事件动作配置")
    String onEvent() default "	";

    /**
     * label
     *
     * 参考定义: {"type":"string"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "label")
    String label() default "	";

    /**
     * icon
     *
     * 参考定义: "#/definitions/SchemaIcon"
     *
     *
     *
     *
     *
     * @see Icon
     */

    @Schema(title = "icon")
    Icon icon() ;

    /**
     * tooltipClassName
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "tooltipClassName")
    String tooltipClassName() default "	";

    /**
     * 触发规则
     *
     * 参考定义: {"type":"array","items":{"type":"string","enum":["click","hover","focus"]},"description":"触发规则"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "触发规则")
    Trigger[] trigger() ;

    /**
     * 提示标题
     *
     * 参考定义: {"type":"string","description":"提示标题"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "提示标题")
    String title() default "	";

    /**
     * 提示内容
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     *
     *
     *
     *
     * @see Tpl
     */

    @Schema(title = "提示内容")
    Tpl content() ;

    /**
     * 显示位置
     *
     * 参考定义: {"type":"string","enum":["top","right","bottom","left"],"description":"显示位置"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "显示位置")
    Placement placement() ;

    /**
     * 点击其他内容时是否关闭弹框信息
     *
     * 参考定义: {"type":"boolean","description":"点击其他内容时是否关闭弹框信息"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "点击其他内容时是否关闭弹框信息")
    boolean rootClose() default false;

    /**
     * icon的形状
     *
     * 参考定义: {"type":"string","enum":["circle","square"],"description":"icon的形状"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "icon的形状")
    Shape shape() ;

}
