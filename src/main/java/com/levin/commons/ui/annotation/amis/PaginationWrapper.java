package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * PaginationWrapper
 *
 * 分页容器功能性渲染器。详情请见：https://baidu.gitee.io/amis/docs/components/pagination-wrapper
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "分页容器功能性渲染器。详情请见：https://baidu.gitee.io/amis/docs/components/pagination-wrapper")
public @interface PaginationWrapper {
///////////////////////////////////////////

	//分页显示位置，如果配置为 none 则需要自己在内容区域配置 pagination 组件，否则不显示。
	enum Position{
		top,
		bottom,
		none,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * 指定为分页容器功能性渲染器
     *
     * 参考定义: {"type":"string","const":"pagination-wrapper","description":"指定为分页容器功能性渲染器"}
     *
     * @see 
     */
    @Schema(description = "指定为分页容器功能性渲染器")
    String type() default "pagination-wrapper";

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
     * 是否显示快速跳转输入框
     *
     * 参考定义: {"type":"boolean","description":"是否显示快速跳转输入框"}
     *
     * @see 
     */
    @Schema(description = "是否显示快速跳转输入框")
    boolean showPageInput() default false;

    /**
     * 最多显示多少个分页按钮。
     *
     * 参考定义: {"type":"number","description":"最多显示多少个分页按钮。","default":5}
     *
     * @see 
     */
    @Schema(description = "最多显示多少个分页按钮。")
    double maxButtons() default 0;

    /**
     * 输入字段名
     *
     * 参考定义: {"type":"string","description":"输入字段名"}
     *
     * @see 
     */
    @Schema(description = "输入字段名")
    String inputName() default "	";

    /**
     * 输出字段名
     *
     * 参考定义: {"type":"string","description":"输出字段名"}
     *
     * @see 
     */
    @Schema(description = "输出字段名")
    String outputName() default "	";

    /**
     * 每页显示多条数据。
     *
     * 参考定义: {"type":"number","description":"每页显示多条数据。","default":10}
     *
     * @see 
     */
    @Schema(description = "每页显示多条数据。")
    double perPage() default 0;

    /**
     * 分页显示位置，如果配置为 none 则需要自己在内容区域配置 pagination 组件，否则不显示。
     *
     * 参考定义: {"type":"string","enum":["top","bottom","none"],"description":"分页显示位置，如果配置为 none 则需要自己在内容区域配置 pagination 组件，否则不显示。"}
     *
     * @see 
     */
    @Schema(description = "分页显示位置，如果配置为 none 则需要自己在内容区域配置 pagination 组件，否则不显示。")
    Position position() ;

    /**
     * 内容区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "内容区域")
    String body() default "	";

}
