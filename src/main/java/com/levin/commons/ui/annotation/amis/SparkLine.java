package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * SparkLine
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "SparkLine")
public @interface SparkLine {
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
     * type
     *
     * 参考定义: {"type":"string","const":"sparkline"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "type")
    String type() default "sparkline";

    /**
     * css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "css 类名")
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
    
    @Schema(description = "是否禁用")
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
    
    @Schema(description = "是否禁用表达式")
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
    
    @Schema(description = "是否隐藏")
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
    
    @Schema(description = "是否隐藏表达式")
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
    
    @Schema(description = "是否显示")
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
    
    @Schema(description = "是否显示表达式")
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
    
    @Schema(description = "组件唯一 id，主要用于日志采集")
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
    
    @Schema(description = "事件动作配置")
    String onEvent() default "	";

    /**
     * 关联数据变量。
     *
     * 参考定义: {"type":"string","description":"关联数据变量。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "关联数据变量。")
    String name() default "	";

    /**
     * 宽度
     *
     * 参考定义: {"type":"number","description":"宽度","default":100}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "宽度")
    double width() default 0;

    /**
     * 高度
     *
     * 参考定义: {"type":"number","description":"高度","default":50}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "高度")
    double height() default 0;

    /**
     * 点击行为
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * 
     *
     * 
     *
     * @see Action
     */
    
    @Schema(description = "点击行为")
    String clickAction() default "	";

    /**
     * 空数据时显示的内容
     *
     * 参考定义: {"type":"string","description":"空数据时显示的内容"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "空数据时显示的内容")
    String placeholder() default "	";

    /**
     * value
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"type":"number"},{"type":"object","properties":{"value":{"type":"number"},"label":{"type":"string"}},"required":["value"],"additionalProperties":false}]}}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "value")
    String[] value() default "	";

}
