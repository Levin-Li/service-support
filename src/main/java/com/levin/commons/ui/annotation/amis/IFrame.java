package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * IFrame
 *
 * IFrame 渲染器 文档：https://baidu.gitee.io/amis/docs/components/iframe
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "IFrame 渲染器 文档：https://baidu.gitee.io/amis/docs/components/iframe")
public @interface IFrame {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * type
     *
     * 参考定义: {"type":"string","const":"iframe"}
     *
     * @see 
     */
    @Schema(description = "type")
    String type() default "iframe";

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
     * 页面地址
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     * @see 
     */
    @Schema(description = "页面地址")
    String src() default "	";

    /**
     * 事件相应，配置后当 iframe 通过 postMessage 发送事件时，可以触发 AMIS 内部的动作。
     *
     * 参考定义: {"type":"object","additionalProperties":{"$ref":"#/definitions/ActionSchema"},"description":"事件相应，配置后当 iframe 通过 postMessage 发送事件时，可以触发 AMIS 内部的动作。"}
     *
     * @see 
     */
    @Schema(description = "事件相应，配置后当 iframe 通过 postMessage 发送事件时，可以触发 AMIS 内部的动作。")
    String events() default "	";

    /**
     * width
     *
     * 参考定义: {"type":["number","string"]}
     *
     * @see 
     */
    @Schema(description = "width")
    String width() default "	";

    /**
     * height
     *
     * 参考定义: {"type":["number","string"]}
     *
     * @see 
     */
    @Schema(description = "height")
    String height() default "	";

}
