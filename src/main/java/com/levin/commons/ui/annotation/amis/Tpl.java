package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Tpl
 *
 * tpl 渲染器
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:01
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "tpl 渲染器")
public @interface Tpl {
///////////////////////////////////////////

	//指定为模板渲染器。\n\n文档：https://baidu.gitee.io/amis/docs/concepts/template
	enum Type{
		tpl,
		html,
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
     * 指定为模板渲染器。\n\n文档：https://baidu.gitee.io/amis/docs/concepts/template
     *
     * 参考定义: {"type":"string","enum":["tpl","html"],"description":"指定为模板渲染器。\n\n文档：https://baidu.gitee.io/amis/docs/concepts/template"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "指定为模板渲染器。\n\n文档：https://baidu.gitee.io/amis/docs/concepts/template")
    Type type() ;

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
     * tpl
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     *
     *
     *
     *
     * @see Tpl
     */

    @Schema(title = "tpl")
    String tpl() default "	";

    /**
     * html
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     *
     *
     *
     *
     * @see Tpl
     */

    @Schema(title = "html")
    String html() default "	";

    /**
     * text
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     *
     *
     *
     *
     * @see Tpl
     */

    @Schema(title = "text")
    String text() default "	";

    /**
     * raw
     *
     * 参考定义: {"type":"string"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "raw")
    String raw() default "	";

    /**
     * 是否内联显示？
     *
     * 参考定义: {"type":"boolean","description":"是否内联显示？"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "是否内联显示？")
    boolean inline() default false;

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

    @Schema(title = "角标")
    Badge badge() ;

}
