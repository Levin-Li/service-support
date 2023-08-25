package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ButtonGroup
 *
 * Button Group 渲染器。 文档：https://baidu.gitee.io/amis/docs/components/button-group
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Button Group 渲染器。 文档：https://baidu.gitee.io/amis/docs/components/button-group")
public @interface ButtonGroup {
///////////////////////////////////////////

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
     * 指定为提交按钮类型
     *
     * 参考定义: {"type":"string","const":"button-group","description":"指定为提交按钮类型"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "指定为提交按钮类型")
    String type() default "button-group";

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
     * 是否为禁用状态。
     *
     * 参考定义: {"type":"boolean","description":"是否为禁用状态。"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "是否为禁用状态。")
    boolean disabled() default false;

    /**
     * 通过 JS 表达式来配置当前表单项的禁用状态。
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     *
     *
     *
     *
     * @see Expression
     */

    @Schema(title = "通过 JS 表达式来配置当前表单项的禁用状态。")
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
     * 通过 JS 表达式来配置当前表单项是否显示
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     *
     *
     *
     *
     * @see Expression
     */

    @Schema(title = "通过 JS 表达式来配置当前表单项是否显示")
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
     * btnClassName
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "btnClassName")
    String btnClassName() default "	";

    /**
     * btnActiveClassName
     *
     * 参考定义: {"type":"string"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "btnActiveClassName")
    String btnActiveClassName() default "	";

    /**
     * 按钮集合
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     *
     *
     *
     *
     * @see Action
     */

    @Schema(title = "按钮集合")
    String[] buttons() default "	";

    /**
     * 按钮样式级别
     *
     * 参考定义: {"type":"string","description":"按钮样式级别"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "按钮样式级别")
    String btnLevel() default "	";

    /**
     * 按钮选中的样式级别
     *
     * 参考定义: {"type":"string","description":"按钮选中的样式级别"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "按钮选中的样式级别")
    String btnActiveLevel() default "	";

    /**
     * 垂直展示？
     *
     * 参考定义: {"type":"boolean","description":"垂直展示？"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "垂直展示？")
    boolean vertical() default false;

    /**
     * 平铺展示？
     *
     * 参考定义: {"type":"boolean","description":"平铺展示？"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "平铺展示？")
    boolean tiled() default false;

    /**
     * 按钮大小
     *
     * 参考定义: {"type":"string","enum":["xs","sm","md","lg"],"description":"按钮大小"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "按钮大小")
    Size size() ;

}
