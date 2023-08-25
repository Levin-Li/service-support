package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * PortletTab
 *
 * 栏目容器渲染器。 文档：https://baidu.gitee.io/amis/docs/components/portlet
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "栏目容器渲染器。 文档：https://baidu.gitee.io/amis/docs/components/portlet")
public @interface PortletTab {
///////////////////////////////////////////

	//null
	enum IconPosition{
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
     * Tab 标题
     *
     * 参考定义: {"type":"string","description":"Tab 标题"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "Tab 标题")
    String title() default "	";

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
    String tab() default "	";

    /**
     * 可以在右侧配置点其他功能按钮，随着tab切换而切换
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     *
     *
     *
     *
     * @see Action
     */

    @Schema(title = "可以在右侧配置点其他功能按钮，随着tab切换而切换")
    String[] toolbar() default "	";

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
     * 按钮图标
     *
     * 参考定义: "#/definitions/SchemaIcon"
     *
     *
     *
     *
     *
     * @see Icon
     */

    @Schema(title = "按钮图标")
    Icon icon() ;

    /**
     * iconPosition
     *
     * 参考定义: {"type":"string","enum":["left","right"]}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "iconPosition")
    IconPosition iconPosition() ;

    /**
     * 设置以后内容每次都会重新渲染
     *
     * 参考定义: {"type":"boolean","description":"设置以后内容每次都会重新渲染"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "设置以后内容每次都会重新渲染")
    boolean reload() default false;

    /**
     * 点开时才加载卡片内容
     *
     * 参考定义: {"type":"boolean","description":"点开时才加载卡片内容"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "点开时才加载卡片内容")
    boolean mountOnEnter() default false;

    /**
     * 卡片隐藏就销毁卡片节点。
     *
     * 参考定义: {"type":"boolean","description":"卡片隐藏就销毁卡片节点。"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "卡片隐藏就销毁卡片节点。")
    boolean unmountOnExit() default false;

}
