package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * CollapseGroup
 *
 * \"CollapseGroup 折叠渲染器，格式说明。 文档：https://baidu.gitee.io/amis/docs/components/collapse\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"CollapseGroup 折叠渲染器，格式说明。 文档：https://baidu.gitee.io/amis/docs/components/collapse\"")
public @interface CollapseGroup {

    /**
     * 指定为折叠器类型
     *
     * 参考定义: {"type":"string","const":"collapse-group","description":"指定为折叠器类型"}
     *
     * @see 
     */
    @Schema(description = "指定为折叠器类型")
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
     * 激活面板
     *
     * 参考定义: {"anyOf":[{"type":"array","items":{"type":["string","number"]}},{"type":"string"},{"type":"number"}],"description":"激活面板"}
     *
     * @see 
     */
    @Schema(description = "激活面板")
    String[] activeKey() default {};

    /**
     * 手风琴模式
     *
     * 参考定义: {"type":"boolean","description":"手风琴模式"}
     *
     * @see 
     */
    @Schema(description = "手风琴模式")
    boolean accordion() default false;

    /**
     * 自定义切换图标
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * @see String
     */
    @Schema(description = "自定义切换图标")
    String expandIcon() default "";

    /**
     * 设置图标位置
     *
     * 参考定义: {"type":"string","enum":["left","right"],"description":"设置图标位置"}
     *
     * @see 
     */
    @Schema(description = "设置图标位置")
    String expandIconPosition() default "";

    /**
     * 内容区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see String
     */
    @Schema(description = "内容区域")
    String body() default "";


}
