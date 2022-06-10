package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * SearchBox
 *
 * 搜索框渲染器
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "搜索框渲染器")
public @interface SearchBox {
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
     * 指定为搜索框。\n\n文档：https://baidu.gitee.io/amis/docs/components/search-box
     *
     * 参考定义: {"type":"string","const":"search-box","description":"指定为搜索框。\n\n文档：https://baidu.gitee.io/amis/docs/components/search-box"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "指定为搜索框。\n\n文档：https://baidu.gitee.io/amis/docs/components/search-box")
    String type() default "search-box";

    /**
     * 外层 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "外层 css 类名")
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
     * 关键字名字。
     *
     * 参考定义: {"type":"string","description":"关键字名字。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "关键字名字。")
    String name() default "	";

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "占位符")
    String placeholder() default "	";

    /**
     * 是否为 Mini 样式。
     *
     * 参考定义: {"type":"boolean","description":"是否为 Mini 样式。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否为 Mini 样式。")
    boolean mini() default false;

    /**
     * 是否为加强样式
     *
     * 参考定义: {"type":"boolean","description":"是否为加强样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否为加强样式")
    boolean enhance() default false;

    /**
     * 是否可清除
     *
     * 参考定义: {"type":"boolean","description":"是否可清除"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否可清除")
    boolean clearable() default false;

    /**
     * 是否立马搜索。
     *
     * 参考定义: {"type":"boolean","description":"是否立马搜索。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否立马搜索。")
    boolean searchImediately() default false;

}
