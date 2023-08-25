package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * SparkLine
 *
 *
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "SparkLine")
public @interface SparkLine {
///////////////////////////////////////////

////////////////////////////////////////////

    /**
     * type
     *
     * 参考定义: {"type":"string","const":"sparkline"}
     *
     * @see
     */
    @Schema(title = "type")
    String type() default "sparkline";

    /**
     * css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see
     */
    @Schema(title = "css 类名")
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

    /**
     * 关联数据变量。
     *
     * 参考定义: {"type":"string","description":"关联数据变量。"}
     *
     * @see
     */
    @Schema(title = "关联数据变量。")
    String name() default "	";

    /**
     * 宽度
     *
     * 参考定义: {"type":"number","description":"宽度","default":100}
     *
     * @see
     */
    @Schema(title = "宽度")
    double width() default 0;

    /**
     * 高度
     *
     * 参考定义: {"type":"number","description":"高度","default":50}
     *
     * @see
     */
    @Schema(title = "高度")
    double height() default 0;

    /**
     * 点击行为
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * @see
     */
    @Schema(title = "点击行为")
    String clickAction() default "	";

    /**
     * 空数据时显示的内容
     *
     * 参考定义: {"type":"string","description":"空数据时显示的内容"}
     *
     * @see
     */
    @Schema(title = "空数据时显示的内容")
    String placeholder() default "	";

    /**
     * value
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"type":"number"},{"type":"object","properties":{"value":{"type":"number"},"label":{"type":"string"}},"required":["value"],"additionalProperties":false}]}}
     *
     * @see
     */
    @Schema(title = "value")
    String[] value() default "	";

}
