package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * HBox
 *
 * Hbox 水平布局渲染器。 文档：https://baidu.gitee.io/amis/docs/components/hbox
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Hbox 水平布局渲染器。 文档：https://baidu.gitee.io/amis/docs/components/hbox")
public @interface HBox {
///////////////////////////////////////////

	//配置子表单项默认的展示方式。
	enum SubFormMode{
		normal,
		inline,
		horizontal,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//水平间距
	enum Gap{
		xs,
		sm,
		base,
		none,
		md,
		lg,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

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

	//水平对齐方式
	enum Align{
		left,
		right,
		between,
		center,
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
     * 指定为each展示类型
     *
     * 参考定义: {"type":"string","const":"hbox","description":"指定为each展示类型"}
     *
     * @see
     */
    @Schema(title = "指定为each展示类型")
    String type() default "hbox";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see
     */
    @Schema(title = "容器 css 类名")
    String className() default "	";

    /**
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用"}
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
     * @see
     */
    @Schema(title = "是否禁用表达式")
    String disabledOn() default "	";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
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
     * @see
     */
    @Schema(title = "是否隐藏表达式")
    String hiddenOn() default "	";

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

    /**
     * columns
     *
     * 参考定义: "#/definitions/HBoxColumn"
     *
     * @see
     */
    @Schema(title = "columns")
    HBoxColumnObject[] columns() ;

    /**
     * 配置子表单项默认的展示方式。
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置子表单项默认的展示方式。"}
     *
     * @see
     */
    @Schema(title = "配置子表单项默认的展示方式。")
    SubFormMode subFormMode() ;

    /**
     * 如果是水平排版，这个属性可以细化水平排版的左右宽度占比。
     *
     * 参考定义: "#/definitions/FormSchemaHorizontal"
     *
     * @see
     */
    @Schema(title = "如果是水平排版，这个属性可以细化水平排版的左右宽度占比。")
    FormHorizontal subFormHorizontal() ;

    /**
     * 水平间距
     *
     * 参考定义: {"type":"string","enum":["xs","sm","base","none","md","lg"],"description":"水平间距"}
     *
     * @see
     */
    @Schema(title = "水平间距")
    Gap gap() ;

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
     * 水平对齐方式
     *
     * 参考定义: {"type":"string","enum":["left","right","between","center"],"description":"水平对齐方式"}
     *
     * @see
     */
    @Schema(title = "水平对齐方式")
    Align align() ;

}
