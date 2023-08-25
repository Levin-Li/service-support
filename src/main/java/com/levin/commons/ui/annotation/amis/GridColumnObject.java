package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * GridColumnObject
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "GridColumnObject")
public @interface GridColumnObject {
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
     * 极小屏（<768px）时宽度占比
     *
     * 参考定义: {"anyOf":[{"type":"number"},{"type":"string","const":"auto"}],"description":"极小屏（<768px）时宽度占比"}
     *
     * [{"type":"number"},{"type":"string","const":"auto"}]
     *
     *
     *
     * @see
     */

    @Schema(title = "极小屏（<768px）时宽度占比")
    String xs() default "	";

    /**
     * 小屏时（>=768px）宽度占比
     *
     * 参考定义: {"anyOf":[{"type":"number"},{"type":"string","const":"auto"}],"description":"小屏时（>=768px）宽度占比"}
     *
     * [{"type":"number"},{"type":"string","const":"auto"}]
     *
     *
     *
     * @see
     */

    @Schema(title = "小屏时（>=768px）宽度占比")
    String sm() default "	";

    /**
     * 中屏时(>=992px)宽度占比
     *
     * 参考定义: {"anyOf":[{"type":"number"},{"type":"string","const":"auto"}],"description":"中屏时(>=992px)宽度占比"}
     *
     * [{"type":"number"},{"type":"string","const":"auto"}]
     *
     *
     *
     * @see
     */

    @Schema(title = "中屏时(>=992px)宽度占比")
    String md() default "	";

    /**
     * 大屏时(>=1200px)宽度占比
     *
     * 参考定义: {"anyOf":[{"type":"number"},{"type":"string","const":"auto"}],"description":"大屏时(>=1200px)宽度占比"}
     *
     * [{"type":"number"},{"type":"string","const":"auto"}]
     *
     *
     *
     * @see
     */

    @Schema(title = "大屏时(>=1200px)宽度占比")
    String lg() default "	";

    /**
     * 垂直对齐方式
     *
     * 参考定义: {"type":"string","enum":["top","middle","bottom","between"],"description":"垂直对齐方式"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "垂直对齐方式")
    Valign valign() ;

    /**
     * 配置子表单项默认的展示方式。
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置子表单项默认的展示方式。"}
     *
     *
     *
     *
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
     *
     *
     *
     *
     * @see FormHorizontal
     */

    @Schema(title = "如果是水平排版，这个属性可以细化水平排版的左右宽度占比。")
    FormHorizontal horizontal() ;

    /**
     * body
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     *
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */

    @Schema(title = "body")
    String body() default "	";

    /**
     * 列类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "列类名")
    String columnClassName() default "	";

}
