package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Steps
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Steps")
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

////////////////////////////////////////////

    /**
     * 指定为 Steps 步骤条渲染器
     *
     * 参考定义: {"type":"string","const":"steps","description":"指定为 Steps 步骤条渲染器"}
     *
     * @see 
     */
    @Schema(description = "指定为 Steps 步骤条渲染器")
    String type() default "steps";

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
     * 步骤
     *
     * 参考定义: "#/definitions/StepSchema"
     *
     * @see 
     */
    @Schema(description = "步骤")
    Step[] steps() ;

    /**
     * API 或 数据映射
     *
     * 参考定义: {"type":"string","description":"API 或 数据映射"}
     *
     * @see 
     */
    @Schema(description = "API 或 数据映射")
    String source() default "	";

    /**
     * 指定当前步骤
     *
     * 参考定义: {"type":["number","string"],"description":"指定当前步骤"}
     *
     * @see 
     */
    @Schema(description = "指定当前步骤")
    String value() default "	";

    /**
     * 变量映射
     *
     * 参考定义: {"type":"string","description":"变量映射"}
     *
     * @see 
     */
    @Schema(description = "变量映射")
    String name() default "	";

    /**
     * status
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/StepStatus"},{"type":"object","additionalProperties":{"$ref":"#/definitions/StepStatus"}}]}
     *
     * @see 
     */
    @Schema(description = "status")
    String status() default "	";

    /**
     * 展示模式
     *
     * 参考定义: {"type":"string","enum":["horizontal","vertical"],"description":"展示模式"}
     *
     * @see 
     */
    @Schema(description = "展示模式")
    Mode mode() ;

}
