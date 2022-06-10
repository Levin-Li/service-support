package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Card
 *
 * Card 卡片渲染器。 文档：https://baidu.gitee.io/amis/docs/components/card
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Card 卡片渲染器。 文档：https://baidu.gitee.io/amis/docs/components/card")
public @interface Card {
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
     * 指定为 card 类型
     *
     * 参考定义: {"type":"string","const":"card","description":"指定为 card 类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "指定为 card 类型")
    String type() default "card";

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
     * 头部配置
     *
     * 参考定义: {"type":"object","properties":{"className":{"$ref":"#/definitions/SchemaClassName"},"title":{"$ref":"#/definitions/SchemaTpl","description":"标题"},"titleClassName":{"$ref":"#/definitions/SchemaClassName"},"subTitle":{"$ref":"#/definitions/SchemaTpl","description":"副标题"},"subTitleClassName":{"$ref":"#/definitions/SchemaClassName"},"subTitlePlaceholder":{"type":"string"},"description":{"$ref":"#/definitions/SchemaTpl","description":"描述"},"descriptionPlaceholder":{"type":"string","description":"描述占位内容"},"descriptionClassName":{"$ref":"#/definitions/SchemaClassName","description":"描述占位类名"},"desc":{"$ref":"#/definitions/SchemaTpl"},"descPlaceholder":{"$ref":"#/definitions/SchemaTpl"},"descClassName":{"$ref":"#/definitions/SchemaClassName"},"avatar":{"$ref":"#/definitions/SchemaUrlPath","description":"图片地址"},"avatarText":{"$ref":"#/definitions/SchemaTpl"},"avatarTextBackground":{"type":"array","items":{"type":"object","properties":{"length":{"type":"number"}},"required":["length"],"additionalProperties":{"type":"string"}}},"avatarTextClassName":{"$ref":"#/definitions/SchemaClassName"},"avatarClassName":{"$ref":"#/definitions/SchemaClassName","description":"图片包括层类名"},"imageClassName":{"$ref":"#/definitions/SchemaClassName","description":"图片类名。"},"highlight":{"$ref":"#/definitions/SchemaExpression","description":"是否点亮"},"highlightClassName":{"$ref":"#/definitions/SchemaClassName"},"href":{"$ref":"#/definitions/SchemaTpl","description":"链接地址"},"blank":{"type":"boolean","description":"是否新窗口打开"}},"additionalProperties":false,"description":"头部配置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "头部配置")
    String header() default "	";

    /**
     * 内容区域
     *
     * 参考定义: "#/definitions/CardBodyField"
     *
     * 
     *
     * 
     *
     * @see CardBodyField
     */
    
    @Schema(description = "内容区域")
    String[] body() default "	";

    /**
     * 多媒体区域
     *
     * 参考定义: {"type":"object","properties":{"className":{"$ref":"#/definitions/SchemaClassName"},"type":{"type":"string","enum":["image","video"],"description":"多媒体类型"},"url":{"$ref":"#/definitions/SchemaUrlPath","description":"多媒体链接地址"},"position":{"type":"string","enum":["top","left","right","bottom"],"description":"多媒体区域位置"},"autoPlay":{"type":"boolean","description":"类型为video时是否自动播放"},"isLive":{"type":"boolean","description":"类型为video时是否是直播"},"poster":{"$ref":"#/definitions/SchemaUrlPath","description":"类型为video时视频封面"}},"additionalProperties":false,"description":"多媒体区域"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "多媒体区域")
    String media() default "	";

    /**
     * 底部按钮集合。
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * 
     *
     * 
     *
     * @see Action
     */
    
    @Schema(description = "底部按钮集合。")
    String[] actions() default "	";

    /**
     * 工具栏按钮
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * 
     *
     * 
     *
     * @see Action
     */
    
    @Schema(description = "工具栏按钮")
    String[] toolbar() default "	";

    /**
     * 次要说明
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * 
     *
     * 
     *
     * @see Tpl
     */
    
    @Schema(description = "次要说明")
    Tpl secondary() ;

}
