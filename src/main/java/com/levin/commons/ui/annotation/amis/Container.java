package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Container
 *
 * \"Container 容器渲染器。 文档：https://baidu.gitee.io/amis/docs/components/container\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"Container 容器渲染器。 文档：https://baidu.gitee.io/amis/docs/components/container\"")
public @interface Container {

    /**
     * 指定为 container 类型
     *
     * 参考定义: {"type":"string","const":"container","description":"指定为 container 类型"}
     *
     * @see 
     */
    @Schema(description = "指定为 container 类型")
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
     * 内容
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see String
     */
    @Schema(description = "内容")
    String body() default "";

    /**
     * body 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "body 类名")
    String bodyClassName() default "";

    /**
     * 自定义样式
     *
     * 参考定义: {"type":"object","description":"自定义样式"}
     *
     * @see 
     */
    @Schema(description = "自定义样式")
    String style() default "";

    /**
     * 使用的标签
     *
     * 参考定义: {"type":"string","description":"使用的标签"}
     *
     * @see 
     */
    @Schema(description = "使用的标签")
    String wrapperComponent() default "";


}
