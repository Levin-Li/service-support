package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Json
 *
 * JSON 数据展示控件。 文档：https://baidu.gitee.io/amis/docs/components/json
 *
 * @author auto gen by service-support at 2022-2-7 23:06:29
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "JSON 数据展示控件。 文档：https://baidu.gitee.io/amis/docs/components/json")
public @interface Json {
///////////////////////////////////////////

	//指定为Json展示类型
	enum Type{
		json,
		static_json,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////
   /**
    *
    */
   String value() default "";

    /**
     * 指定为Json展示类型
     *
     * 参考定义: {"type":"string","enum":["json","static-json"],"description":"指定为Json展示类型"}
     *
     * @see 
     */
    @Schema(description = "指定为Json展示类型")
    Type type() ;

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "容器 css 类名")
    String className() default "";

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
    String disabledOn() default "";

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
    String hiddenOn() default "";

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
    String visibleOn() default "";

    /**
     * 默认展开的级别
     *
     * 参考定义: {"type":"number","description":"默认展开的级别"}
     *
     * @see 
     */
    @Schema(description = "默认展开的级别")
    double levelExpand() default 0;

    /**
     * 支持从数据链取值
     *
     * 参考定义: {"type":"string","description":"支持从数据链取值"}
     *
     * @see 
     */
    @Schema(description = "支持从数据链取值")
    String source() default "";

    /**
     * 是否可修改
     *
     * 参考定义: {"type":"boolean","description":"是否可修改"}
     *
     * @see 
     */
    @Schema(description = "是否可修改")
    boolean mutable() default false;

    /**
     * 是否显示数据类型
     *
     * 参考定义: {"type":"boolean","description":"是否显示数据类型"}
     *
     * @see 
     */
    @Schema(description = "是否显示数据类型")
    boolean displayDataTypes() default false;

}
