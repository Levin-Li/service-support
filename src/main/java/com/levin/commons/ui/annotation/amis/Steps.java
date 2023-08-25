package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Steps
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Steps")
public @interface Steps {
///////////////////////////////////////////

	//展示模式
	enum Mode{
		horizontal,
		vertical,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//标签放置位置
	enum LabelPlacement{
		horizontal,
		vertical,
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
     * 指定为 Steps 步骤条渲染器
     *
     * 参考定义: {"type":"string","const":"steps","description":"指定为 Steps 步骤条渲染器"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "指定为 Steps 步骤条渲染器")
    String type() default "steps";

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
     * 步骤
     *
     * 参考定义: "#/definitions/StepSchema"
     *
     *
     *
     *
     *
     * @see Step
     */

    @Schema(title = "步骤")
    Step[] steps() ;

    /**
     * API 或 数据映射
     *
     * 参考定义: {"type":"string","description":"API 或 数据映射"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "API 或 数据映射")
    String source() default "	";

    /**
     * 指定当前步骤
     *
     * 参考定义: {"type":["number","string"],"description":"指定当前步骤"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "指定当前步骤")
    String value() default "	";

    /**
     * 变量映射
     *
     * 参考定义: {"type":"string","description":"变量映射"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "变量映射")
    String name() default "	";

    /**
     * status
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/StepStatus"},{"type":"object","additionalProperties":{"$ref":"#/definitions/StepStatus"}}]}
     *
     * [{"$ref":"#/definitions/StepStatus"},{"type":"object","additionalProperties":{"$ref":"#/definitions/StepStatus"}}]
     *
     *
     *
     * @see
     */

    @Schema(title = "status")
    String status() default "	";

    /**
     * 展示模式
     *
     * 参考定义: {"type":"string","enum":["horizontal","vertical"],"description":"展示模式"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "展示模式")
    Mode mode() ;

    /**
     * 标签放置位置
     *
     * 参考定义: {"type":"string","enum":["horizontal","vertical"],"description":"标签放置位置"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "标签放置位置")
    LabelPlacement labelPlacement() ;

    /**
     * 点状步骤条
     *
     * 参考定义: {"type":"boolean","description":"点状步骤条"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "点状步骤条")
    boolean progressDot() default false;

}
