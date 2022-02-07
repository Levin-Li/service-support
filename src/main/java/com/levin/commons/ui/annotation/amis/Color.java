package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Color
 *
 * Color 显示渲染器，格式说明。 文档：https://baidu.gitee.io/amis/docs/components/color
 *
 * @author auto gen by service-support at 2022-2-7 23:06:29
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Color 显示渲染器，格式说明。 文档：https://baidu.gitee.io/amis/docs/components/color")
public @interface Color {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "";

    /**
     * 指定为颜色显示控件
     *
     * 参考定义: {"type":"string","const":"color","description":"指定为颜色显示控件"}
     *
     * @see 
     */
    @Schema(description = "指定为颜色显示控件")
    String type() default "color";

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
     * 默认颜色
     *
     * 参考定义: {"type":"string","description":"默认颜色"}
     *
     * @see 
     */
    @Schema(description = "默认颜色")
    String defaultColor() default "";

    /**
     * 是否用文字显示值。
     *
     * 参考定义: {"type":"boolean","description":"是否用文字显示值。"}
     *
     * @see 
     */
    @Schema(description = "是否用文字显示值。")
    boolean showValue() default false;

}
