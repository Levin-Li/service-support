package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * HBoxColumnObject
 *
 *
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "HBoxColumnObject")
public @interface HBoxColumnObject {
///////////////////////////////////////////

	//垂直对齐方式
	enum Valign{
		top,
		middle,
		bottom,
		between,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//配置子表单项默认的展示方式。
	enum Mode{
		normal,
		inline,
		horizontal,
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
     * 列上 CSS 类名
     *
     * 参考定义: {"type":"string","description":"列上 CSS 类名"}
     *
     * @see
     */
    @Schema(title = "列上 CSS 类名")
    String columnClassName() default "	";

    /**
     * 垂直对齐方式
     *
     * 参考定义: {"type":"string","enum":["top","middle","bottom","between"],"description":"垂直对齐方式"}
     *
     * @see
     */
    @Schema(title = "垂直对齐方式")
    Valign valign() ;

    /**
     * 宽度
     *
     * 参考定义: {"type":["number","string"],"description":"宽度"}
     *
     * @see
     */
    @Schema(title = "宽度")
    String width() default "	";

    /**
     * 高度
     *
     * 参考定义: {"type":["number","string"],"description":"高度"}
     *
     * @see
     */
    @Schema(title = "高度")
    String height() default "	";

    /**
     * 其他样式
     *
     * 参考定义: {"type":"object","description":"其他样式"}
     *
     * @see
     */
    @Schema(title = "其他样式")
    String style() default "	";

    /**
     * 配置子表单项默认的展示方式。
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置子表单项默认的展示方式。"}
     *
     * @see
     */
    @Schema(title = "配置子表单项默认的展示方式。")
    Mode mode() ;

    /**
     * 如果是水平排版，这个属性可以细化水平排版的左右宽度占比。
     *
     * 参考定义: "#/definitions/FormSchemaHorizontal"
     *
     * @see
     */
    @Schema(title = "如果是水平排版，这个属性可以细化水平排版的左右宽度占比。")
    FormHorizontal horizontal() ;

    /**
     * 内容区
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see
     */
    @Schema(title = "内容区")
    String body() default "	";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
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
     * @see
     */
    @Schema(title = "是否显示表达式")
    String visibleOn() default "	";

}
