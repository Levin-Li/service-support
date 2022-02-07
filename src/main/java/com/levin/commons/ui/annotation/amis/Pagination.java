package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Pagination
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-7 23:06:29
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Pagination")
public @interface Pagination {
///////////////////////////////////////////

	//模式，默认显示多个分页数字，如果只想简单显示可以配置成 `simple`。
	enum Mode{
		simple,
		normal,
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
     * type
     *
     * 参考定义: {"type":"string","const":"pagination"}
     *
     * @see 
     */
    @Schema(description = "type")
    String type() default "pagination";

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
     * 是否显示快速跳转输入框
     *
     * 参考定义: {"type":"boolean","description":"是否显示快速跳转输入框"}
     *
     * @see 
     */
    @Schema(description = "是否显示快速跳转输入框")
    boolean showPageInput() default false;

    /**
     * 模式，默认显示多个分页数字，如果只想简单显示可以配置成 `simple`。
     *
     * 参考定义: {"type":"string","enum":["simple","normal"],"description":"模式，默认显示多个分页数字，如果只想简单显示可以配置成 `simple`。"}
     *
     * @see 
     */
    @Schema(description = "模式，默认显示多个分页数字，如果只想简单显示可以配置成 `simple`。")
    Mode mode() ;

    /**
     * 最多显示多少个分页按钮。
     *
     * 参考定义: {"type":"number","description":"最多显示多少个分页按钮。","default":5}
     *
     * @see 
     */
    @Schema(description = "最多显示多少个分页按钮。")
    double maxButtons() default 0;

}
