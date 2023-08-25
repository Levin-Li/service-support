package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Operation
 *
 * 操作栏渲染器。 文档：https://baidu.gitee.io/amis/docs/components/operation
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "操作栏渲染器。 文档：https://baidu.gitee.io/amis/docs/components/operation")
public @interface Operation {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * 指定为操作栏
     *
     * 参考定义: {"type":"string","const":"operation","description":"指定为操作栏"}
     *
     * @see
     */
    @Schema(title = "指定为操作栏")
    String type() default "operation";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see
     */
    @Schema(title = "容器 css 类名")
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
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * @see
     */
    @Schema(title = "占位符")
    String placeholder() default "	";

    /**
     * buttons
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * @see
     */
    @Schema(title = "buttons")
    String[] buttons() default "	";

}
