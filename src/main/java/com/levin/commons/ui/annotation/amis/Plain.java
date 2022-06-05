package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Plain
 *
 * Plain 纯文本渲染器 文档：https://baidu.gitee.io/amis/docs/components/plain
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Plain 纯文本渲染器 文档：https://baidu.gitee.io/amis/docs/components/plain")
public @interface Plain {
///////////////////////////////////////////

	//指定为模板渲染器。\n\n文档：https://baidu.gitee.io/amis/docs/concepts/template
	enum Type{
		plain,
		text,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////

  /**
   * Any Of
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
     * 参考定义: {"type":"string","enum":["plain","text"],"description":"指定为模板渲染器。\n\n文档：https://baidu.gitee.io/amis/docs/concepts/template"}
     *
     * @see 
     */
    @Schema(description = "指定为模板渲染器。\n\n文档：https://baidu.gitee.io/amis/docs/concepts/template")
    Type type() ;

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "容器 css 类名")
    String className() default "	";

    /**
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用"}
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
     * @see 
     */
    @Schema(description = "是否禁用表达式")
    String disabledOn() default "	";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
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
     * @see 
     */
    @Schema(description = "是否隐藏表达式")
    String hiddenOn() default "	";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
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
     * @see 
     */
    @Schema(description = "是否显示表达式")
    String visibleOn() default "	";

    /**
     * 组件唯一 id，主要用于日志采集
     *
     * 参考定义: {"type":"string","description":"组件唯一 id，主要用于日志采集"}
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
     * @see 
     */
    @Schema(description = "事件动作配置")
    String onEvent() default "	";

    /**
     * tpl
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(description = "tpl")
    Tpl tpl() ;

    /**
     * text
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(description = "text")
    Tpl text() ;

    /**
     * 是否内联显示？
     *
     * 参考定义: {"type":"boolean","description":"是否内联显示？"}
     *
     * @see 
     */
    @Schema(description = "是否内联显示？")
    boolean inline() default false;

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * @see 
     */
    @Schema(description = "占位符")
    String placeholder() default "	";

}
