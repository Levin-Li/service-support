package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ListItem
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "ListItem")
public @interface ListItem {
///////////////////////////////////////////

	//操作位置，默认在右侧，可以设置成左侧。
	enum ActionsPosition{
		left,
		right,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

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
     * actions
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     *
     *
     *
     *
     * @see Action
     */

    @Schema(title = "actions")
    String[] actions() default "	";

    /**
     * 操作位置，默认在右侧，可以设置成左侧。
     *
     * 参考定义: {"type":"string","enum":["left","right"],"description":"操作位置，默认在右侧，可以设置成左侧。"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "操作位置，默认在右侧，可以设置成左侧。")
    ActionsPosition actionsPosition() ;

    /**
     * 图片地址
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     *
     *
     *
     *
     * @see UrlPath
     */

    @Schema(title = "图片地址")
    String avatar() default "	";

    /**
     * 内容区域
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"$ref":"#/definitions/ListBodyField"},{"$ref":"#/definitions/ListBodyFieldObject"}]},"description":"内容区域"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "内容区域")
    String[] body() default "	";

    /**
     * 描述
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     *
     *
     *
     *
     * @see Tpl
     */

    @Schema(title = "描述")
    Tpl desc() ;

    /**
     * tooltip 说明
     *
     * 参考定义: "#/definitions/SchemaRemark"
     *
     *
     *
     *
     *
     * @see Remark
     */

    @Schema(title = "tooltip 说明")
    Remark remark() ;

    /**
     * 标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     *
     *
     *
     *
     * @see Tpl
     */

    @Schema(title = "标题")
    Tpl title() ;

    /**
     * 副标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     *
     *
     *
     *
     * @see Tpl
     */

    @Schema(title = "副标题")
    Tpl subTitle() ;

}
