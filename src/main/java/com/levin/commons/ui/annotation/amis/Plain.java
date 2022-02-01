package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Plain
 *
 * \"Plain 纯文本渲染器 文档：https://baidu.gitee.io/amis/docs/components/plain\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"Plain 纯文本渲染器 文档：https://baidu.gitee.io/amis/docs/components/plain\"")
public @interface Plain {

    /**
     * 指定为模板渲染器。\n\n文档：https://baidu.gitee.io/amis/docs/concepts/template
     *
     * 参考定义: {"type":"string","enum":["plain","text"],"description":"指定为模板渲染器。\n\n文档：https://baidu.gitee.io/amis/docs/concepts/template"}
     *
     * @see 
     */
    @Schema(description = "指定为模板渲染器。\n\n文档：https://baidu.gitee.io/amis/docs/concepts/template")
    String type() default "";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
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
     * @see String
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
     * @see String
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
     * @see String
     */
    @Schema(description = "是否显示表达式")
    String visibleOn() default "";

    /**
     * tpl
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "tpl")
    String tpl() default "";

    /**
     * text
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "text")
    String text() default "";

    /**
     * 是否内联显示？
     *
     * 参考定义: {"type":"boolean","description":"是否内联显示？"}
     *
     * @see 
     */
    @Schema(description = "是否内联显示？")
    boolean inline() default false;

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * @see 
     */
    @Schema(description = "占位符")
    String placeholder() default "";


}
