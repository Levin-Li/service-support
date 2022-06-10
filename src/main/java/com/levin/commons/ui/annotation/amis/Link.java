package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Link
 *
 * Link 链接展示控件。 文档：https://baidu.gitee.io/amis/docs/components/link
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Link 链接展示控件。 文档：https://baidu.gitee.io/amis/docs/components/link")
public @interface Link {
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
     * 指定为 link 链接展示控件
     *
     * 参考定义: {"type":"string","const":"link","description":"指定为 link 链接展示控件"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "指定为 link 链接展示控件")
    String type() default "link";

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
    
    @Schema(description = "容器 css 类名")
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
     * 是否新窗口打开。
     *
     * 参考定义: {"type":"boolean","description":"是否新窗口打开。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否新窗口打开。")
    boolean blank() default false;

    /**
     * 链接地址
     *
     * 参考定义: {"type":"string","description":"链接地址"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "链接地址")
    String href() default "	";

    /**
     * 链接内容，如果不配置将显示链接地址。
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * 
     *
     * 
     *
     * @see Tpl
     */
    
    @Schema(description = "链接内容，如果不配置将显示链接地址。")
    Tpl body() ;

    /**
     * 角标
     *
     * 参考定义: "#/definitions/BadgeSchema"
     *
     * 
     *
     * 
     *
     * @see Badge
     */
    
    @Schema(description = "角标")
    Badge badge() ;

    /**
     * a标签原生target属性
     *
     * 参考定义: {"type":"string","description":"a标签原生target属性"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "a标签原生target属性")
    String htmlTarget() default "	";

    /**
     * 图标
     *
     * 参考定义: {"type":"string","description":"图标"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "图标")
    String icon() default "	";

    /**
     * 右侧图标
     *
     * 参考定义: {"type":"string","description":"右侧图标"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "右侧图标")
    String rightIcon() default "	";

}
