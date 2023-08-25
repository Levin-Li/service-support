package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Card2
 *
 * Card2 新卡片渲染器。 文档：https://baidu.gitee.io/amis/docs/components/card2
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Card2 新卡片渲染器。 文档：https://baidu.gitee.io/amis/docs/components/card2")
public @interface Card2 {
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
     * 指定为 card2 类型
     *
     * 参考定义: {"type":"string","const":"card2","description":"指定为 card2 类型"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "指定为 card2 类型")
    String type() default "card2";

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
     * 内容
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     *
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */

    @Schema(title = "内容")
    String body() default "	";

    /**
     * body 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "body 类名")
    String bodyClassName() default "	";

    /**
     * 自定义样式
     *
     * 参考定义: {"type":"object","description":"自定义样式"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "自定义样式")
    String style() default "	";

    /**
     * 隐藏选框
     *
     * 参考定义: {"type":"boolean","description":"隐藏选框"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "隐藏选框")
    boolean hideCheckToggler() default false;

    /**
     * 不配置href且cards容器下生效，点击整个卡片触发选中
     *
     * 参考定义: {"type":"boolean","description":"不配置href且cards容器下生效，点击整个卡片触发选中"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "不配置href且cards容器下生效，点击整个卡片触发选中")
    boolean checkOnItemClick() default false;

    /**
     * 渲染标签
     *
     * 参考定义: {"type":"string","description":"渲染标签"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "渲染标签")
    String wrapperComponent() default "	";

}
