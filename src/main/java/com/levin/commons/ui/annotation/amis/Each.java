package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Each
 *
 * Each 循环功能渲染器。 文档：https://baidu.gitee.io/amis/docs/components/each
 *
 * @author auto gen by service-support at 2022-2-7 23:06:29
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Each 循环功能渲染器。 文档：https://baidu.gitee.io/amis/docs/components/each")
public @interface Each {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "";

    /**
     * 指定为each展示类型
     *
     * 参考定义: {"type":"string","const":"each","description":"指定为each展示类型"}
     *
     * @see 
     */
    @Schema(description = "指定为each展示类型")
    String type() default "each";

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
     * 关联字段名
     *
     * 参考定义: {"type":"string","description":"关联字段名"}
     *
     * @see 
     */
    @Schema(description = "关联字段名")
    String name() default "";

    /**
     * 关联字段名 支持数据映射
     *
     * 参考定义: {"type":"string","description":"关联字段名 支持数据映射"}
     *
     * @see 
     */
    @Schema(description = "关联字段名 支持数据映射")
    String source() default "";

    /**
     * items
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "items")
    String items() default "";

    /**
     * placeholder
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "placeholder")
    String placeholder() default "";

}
