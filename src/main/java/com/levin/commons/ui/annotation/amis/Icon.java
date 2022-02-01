package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Icon
 *
 * \"Icon 图表渲染器 文档：https://baidu.gitee.io/amis/docs/components/icon\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"Icon 图表渲染器 文档：https://baidu.gitee.io/amis/docs/components/icon\"")
public @interface Icon {

    /**
     * type
     *
     * 参考定义: {"type":"string","const":"icon"}
     *
     * @see 
     */
    @Schema(description = "type")
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
     * 按钮类型
     *
     * 参考定义: {"type":"string","description":"按钮类型"}
     *
     * @see 
     */
    @Schema(description = "按钮类型")
    String icon() default "";

    /**
     * vendor
     *
     * 参考定义: {"type":"string","enum":["iconfont","fa"]}
     *
     * @see 
     */
    @Schema(description = "vendor")
    String vendor() default "";

    /**
     * 角标
     *
     * 参考定义: "#/definitions/BadgeSchema"
     *
     * @see 
     */
    @Schema(description = "角标")
    Badge badge() ;


}
