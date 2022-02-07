package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Timeline
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-7 23:06:29
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Timeline")
public @interface Timeline {
///////////////////////////////////////////

	//文字相对于时间轴展示方向
	enum Mode{
		left,
		right,
		alternate,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//展示方向
	enum Direction{
		horizontal,
		vertical,
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
     * 指定为 Timeline 时间轴渲染器
     *
     * 参考定义: {"type":"string","const":"timeline","description":"指定为 Timeline 时间轴渲染器"}
     *
     * @see 
     */
    @Schema(description = "指定为 Timeline 时间轴渲染器")
    String type() default "timeline";

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
     * 节点数据
     *
     * 参考定义: "#/definitions/TimelineItemSchema"
     *
     * @see 
     */
    @Schema(description = "节点数据")
    TimelineItem[] items() ;

    /**
     * API 或 数据映射
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/SchemaApi"},{"$ref":"#/definitions/SchemaTokenizeableString"}],"description":"API 或 数据映射"}
     *
     * @see 
     */
    @Schema(description = "API 或 数据映射")
    String source() default "";

    /**
     * 文字相对于时间轴展示方向
     *
     * 参考定义: {"type":"string","enum":["left","right","alternate"],"description":"文字相对于时间轴展示方向"}
     *
     * @see 
     */
    @Schema(description = "文字相对于时间轴展示方向")
    Mode mode() ;

    /**
     * 展示方向
     *
     * 参考定义: {"type":"string","enum":["horizontal","vertical"],"description":"展示方向"}
     *
     * @see 
     */
    @Schema(description = "展示方向")
    Direction direction() ;

    /**
     * 节点倒序
     *
     * 参考定义: {"type":"boolean","description":"节点倒序"}
     *
     * @see 
     */
    @Schema(description = "节点倒序")
    boolean reverse() default false;

}
