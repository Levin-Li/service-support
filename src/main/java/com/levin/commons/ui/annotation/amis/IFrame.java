package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * IFrame
 *
 * IFrame 渲染器 文档：https://baidu.gitee.io/amis/docs/components/iframe
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "IFrame 渲染器 文档：https://baidu.gitee.io/amis/docs/components/iframe")
public @interface IFrame {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   *
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * type
     *
     * 参考定义: {"type":"string","const":"iframe"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "type")
    String type() default "iframe";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "容器 css 类名")
    String className() default "	";

    /**
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用"}
     *
     *
     *
     *
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
     *
     *
     *
     *
     * @see Expression
     */

    @Schema(title = "是否禁用表达式")
    String disabledOn() default "	";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
     *
     *
     *
     *
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
     *
     *
     *
     *
     * @see Expression
     */

    @Schema(title = "是否隐藏表达式")
    String hiddenOn() default "	";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
     *
     *
     *
     *
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
     *
     *
     *
     *
     * @see Expression
     */

    @Schema(title = "是否显示表达式")
    String visibleOn() default "	";

    /**
     * 组件唯一 id，主要用于日志采集
     *
     * 参考定义: {"type":"string","description":"组件唯一 id，主要用于日志采集"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "组件唯一 id，主要用于日志采集")
    String id() default "	";

    /**
     * 事件动作配置
     *
     * 参考定义: {"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "事件动作配置")
    String onEvent() default "	";

    /**
     * 页面地址
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     *
     *
     *
     *
     * @see UrlPath
     */

    @Schema(title = "页面地址")
    String src() default "	";

    /**
     * 事件相应，配置后当 iframe 通过 postMessage 发送事件时，可以触发 AMIS 内部的动作。
     *
     * 参考定义: {"type":"object","additionalProperties":{"$ref":"#/definitions/ActionSchema"},"description":"事件相应，配置后当 iframe 通过 postMessage 发送事件时，可以触发 AMIS 内部的动作。"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "事件相应，配置后当 iframe 通过 postMessage 发送事件时，可以触发 AMIS 内部的动作。")
    String events() default "	";

    /**
     * width
     *
     * 参考定义: {"type":["number","string"]}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "width")
    String width() default "	";

    /**
     * height
     *
     * 参考定义: {"type":["number","string"]}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "height")
    String height() default "	";

}
