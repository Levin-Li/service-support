package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * TableColumnObject
 *
 * 表格列，不指定类型时默认为文本类型。
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "表格列，不指定类型时默认为文本类型。")
public @interface TableColumnObject {
///////////////////////////////////////////

	//配置是否固定当前列
	enum Fixed{
		left,
		right,
		none,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//列对齐方式
	enum Align{
		left,
		right,
		center,
		justify,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//结合表格的 footable 一起使用。 填写 *、xs、sm、md、lg指定 footable 的触发条件，可以填写多个用空格隔开
	enum Breakpoint{
		xs,
		sm,
		md,
		lg,
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
     * 列标题
     *
     * 参考定义: {"type":"string","description":"列标题"}
     *
     * @see 
     */
    @Schema(description = "列标题")
    String label() default "	";

    /**
     * 配置是否固定当前列
     *
     * 参考定义: {"type":"string","enum":["left","right","none"],"description":"配置是否固定当前列"}
     *
     * @see 
     */
    @Schema(description = "配置是否固定当前列")
    Fixed fixed() ;

    /**
     * 绑定字段名
     *
     * 参考定义: {"type":"string","description":"绑定字段名"}
     *
     * @see 
     */
    @Schema(description = "绑定字段名")
    String name() default "	";

    /**
     * 配置查看详情功能
     *
     * 参考定义: "#/definitions/SchemaPopOver"
     *
     * @see 
     */
    @Schema(description = "配置查看详情功能")
    boolean popOver() default false;

    /**
     * 配置快速编辑功能
     *
     * 参考定义: "#/definitions/SchemaQuickEdit"
     *
     * @see 
     */
    @Schema(description = "配置快速编辑功能")
    boolean quickEdit() default false;

    /**
     * 作为表单项时，可以单独配置编辑时的快速编辑面板。
     *
     * 参考定义: "#/definitions/SchemaQuickEdit"
     *
     * @see 
     */
    @Schema(description = "作为表单项时，可以单独配置编辑时的快速编辑面板。")
    boolean quickEditOnUpdate() default false;

    /**
     * 配置点击复制功能
     *
     * 参考定义: "#/definitions/SchemaCopyable"
     *
     * @see 
     */
    @Schema(description = "配置点击复制功能")
    boolean copyable() default false;

    /**
     * 配置是否可以排序
     *
     * 参考定义: {"type":"boolean","description":"配置是否可以排序"}
     *
     * @see 
     */
    @Schema(description = "配置是否可以排序")
    boolean sortable() default false;

    /**
     * 是否可快速搜索
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"$ref":"#/definitions/SchemaObject"}],"description":"是否可快速搜索"}
     *
     * @see 
     */
    @Schema(description = "是否可快速搜索")
    boolean searchable() default false;

    /**
     * 配置是否默认展示
     *
     * 参考定义: {"type":"boolean","description":"配置是否默认展示"}
     *
     * @see 
     */
    @Schema(description = "配置是否默认展示")
    boolean toggled() default false;

    /**
     * 列宽度
     *
     * 参考定义: {"type":["number","string"],"description":"列宽度"}
     *
     * @see 
     */
    @Schema(description = "列宽度")
    String width() default "	";

    /**
     * 列对齐方式
     *
     * 参考定义: {"type":"string","enum":["left","right","center","justify"],"description":"列对齐方式"}
     *
     * @see 
     */
    @Schema(description = "列对齐方式")
    Align align() ;

    /**
     * 列样式表
     *
     * 参考定义: {"type":"string","description":"列样式表"}
     *
     * @see 
     */
    @Schema(description = "列样式表")
    String className() default "	";

    /**
     * 单元格样式表达式
     *
     * 参考定义: {"type":"string","description":"单元格样式表达式"}
     *
     * @see 
     */
    @Schema(description = "单元格样式表达式")
    String classNameExpr() default "	";

    /**
     * 列头样式表
     *
     * 参考定义: {"type":"string","description":"列头样式表"}
     *
     * @see 
     */
    @Schema(description = "列头样式表")
    String labelClassName() default "	";

    /**
     * todo
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"object","properties":{"source":{"type":"string"},"options":{"type":"array","items":{}}},"additionalProperties":false}],"description":"todo"}
     *
     * @see 
     */
    @Schema(description = "todo")
    boolean filterable() default false;

    /**
     * 结合表格的 footable 一起使用。 填写 *、xs、sm、md、lg指定 footable 的触发条件，可以填写多个用空格隔开
     *
     * 参考定义: {"type":"string","enum":["*","xs","sm","md","lg"],"description":"结合表格的 footable 一起使用。 填写 *、xs、sm、md、lg指定 footable 的触发条件，可以填写多个用空格隔开"}
     *
     * @see 
     */
    @Schema(description = "结合表格的 footable 一起使用。 填写 *、xs、sm、md、lg指定 footable 的触发条件，可以填写多个用空格隔开")
    Breakpoint breakpoint() ;

    /**
     * 提示信息
     *
     * 参考定义: "#/definitions/SchemaRemark"
     *
     * @see 
     */
    @Schema(description = "提示信息")
    Remark remark() ;

    /**
     * 默认值, 只有在 inputTable 里面才有用
     *
     * 参考定义: {"description":"默认值, 只有在 inputTable 里面才有用"}
     *
     * @see 
     */
    @Schema(description = "默认值, 只有在 inputTable 里面才有用")
    String value() default "	";

    /**
     * 是否唯一, 只有在 inputTable 里面才有用
     *
     * 参考定义: {"type":"boolean","description":"是否唯一, 只有在 inputTable 里面才有用"}
     *
     * @see 
     */
    @Schema(description = "是否唯一, 只有在 inputTable 里面才有用")
    boolean unique() default false;

}
