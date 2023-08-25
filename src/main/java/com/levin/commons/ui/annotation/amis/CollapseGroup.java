package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * CollapseGroup
 *
 * CollapseGroup 折叠渲染器，格式说明。 文档：https://baidu.gitee.io/amis/docs/components/collapse
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "CollapseGroup 折叠渲染器，格式说明。 文档：https://baidu.gitee.io/amis/docs/components/collapse")
public @interface CollapseGroup {
///////////////////////////////////////////

	//设置图标位置
	enum ExpandIconPosition{
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
     * 指定为折叠器类型
     *
     * 参考定义: {"type":"string","const":"collapse-group","description":"指定为折叠器类型"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "指定为折叠器类型")
    String type() default "collapse-group";

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
     * 激活面板
     *
     * 参考定义: {"anyOf":[{"type":"array","items":{"type":["string","number"]}},{"type":"string"},{"type":"number"}],"description":"激活面板"}
     *
     * [{"type":"array","items":{"type":["string","number"]}},{"type":"string"},{"type":"number"}]
     *
     *
     *
     * @see
     */

    @Schema(title = "激活面板")
    String activeKey() default "	";

    /**
     * 手风琴模式
     *
     * 参考定义: {"type":"boolean","description":"手风琴模式"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "手风琴模式")
    boolean accordion() default false;

    /**
     * 自定义切换图标
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     *
     *
     *
     *
     * @see Object
     */

    @Schema(title = "自定义切换图标")
    String expandIcon() default "	";

    /**
     * 设置图标位置
     *
     * 参考定义: {"type":"string","enum":["left","right"],"description":"设置图标位置"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "设置图标位置")
    ExpandIconPosition expandIconPosition() ;

    /**
     * 内容区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     *
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */

    @Schema(title = "内容区域")
    String body() default "	";

}
