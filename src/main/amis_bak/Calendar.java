package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Calendar
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-10 12:04:41
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Calendar")
public @interface Calendar {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * 指定为日历选择控件
     *
     * 参考定义: {"type":"string","const":"calendar","description":"指定为日历选择控件"}
     *
     * @see 
     */
    @Schema(description = "指定为日历选择控件")
    String type() default "calendar";

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
     * 日程
     *
     * 参考定义: {"anyOf":[{"type":"array","items":{"type":"object","properties":{"startTime":{"type":"string"},"endTime":{"type":"string"},"content":{},"className":{"type":"string"}},"required":["startTime","endTime","content"],"additionalProperties":false}},{"type":"string"}],"description":"日程"}
     *
     * @see 
     */
    @Schema(description = "日程")
    String[] schedules() default "	";

    /**
     * 日程显示颜色自定义
     *
     * 参考定义: {"type":"array","items":{"type":"string"},"description":"日程显示颜色自定义"}
     *
     * @see 
     */
    @Schema(description = "日程显示颜色自定义")
    String[] scheduleClassNames() default "	";

    /**
     * 日程点击展示
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * @see 
     */
    @Schema(description = "日程点击展示")
    String scheduleAction() default "	";

}
