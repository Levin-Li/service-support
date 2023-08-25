package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Table
 *
 * Table 表格渲染器。 文档：https://baidu.gitee.io/amis/docs/components/table
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Table 表格渲染器。 文档：https://baidu.gitee.io/amis/docs/components/table")
public @interface Table {
///////////////////////////////////////////

	//指定为表格渲染器。
	enum Type{
		table,
		static_table,
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
     * 指定为表格渲染器。
     *
     * 参考定义: {"type":"string","enum":["table","static-table"],"description":"指定为表格渲染器。"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "指定为表格渲染器。")
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
     * 是否固定表头
     *
     * 参考定义: {"type":"boolean","description":"是否固定表头"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "是否固定表头")
    boolean affixHeader() default false;

    /**
     * 表格的列信息
     *
     * 参考定义: "#/definitions/TableColumn"
     *
     *
     *
     * [{"$ref":"#/definitions/TableColumnWithType"},{"$ref":"#/definitions/TableColumnObject"}]
     *
     * @see TableColumn
     */

    @Schema(title = "表格的列信息")
    String[] columns() default "	";

    /**
     * 展示列显示开关，自动即：列数量大于或等于5个时自动开启
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"string","const":"auto"}],"description":"展示列显示开关，自动即：列数量大于或等于5个时自动开启"}
     *
     * [{"type":"boolean"},{"type":"string","const":"auto"}]
     *
     *
     *
     * @see
     */

    @Schema(title = "展示列显示开关，自动即：列数量大于或等于5个时自动开启")
    String columnsTogglable() default "	";

    /**
     * 是否开启底部展示功能，适合移动端展示
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"object","properties":{"expand":{"type":"string","enum":["first","all","none"]},"accordion":{"type":"boolean","description":"是否为手风琴模式"}},"additionalProperties":false}],"description":"是否开启底部展示功能，适合移动端展示"}
     *
     * [{"type":"boolean"},{"type":"object","properties":{"expand":{"type":"string","enum":["first","all","none"]},"accordion":{"type":"boolean","description":"是否为手风琴模式"}},"additionalProperties":false}]
     *
     *
     *
     * @see
     */

    @Schema(title = "是否开启底部展示功能，适合移动端展示")
    String footable() default "	";

    /**
     * 底部外层 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "底部外层 CSS 类名")
    String footerClassName() default "	";

    /**
     * 顶部外层 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "顶部外层 CSS 类名")
    String headerClassName() default "	";

    /**
     * 占位符
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/SchemaTpl"}],"description":"占位符"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaTpl"}]
     *
     *
     *
     * @see
     */

    @Schema(title = "占位符")
    String placeholder() default "	";

    /**
     * 无数据展示 icon
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/SchemaIcon"}],"description":"无数据展示 icon"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaIcon"}]
     *
     *
     *
     * @see
     */

    @Schema(title = "无数据展示 icon")
    String emptyIcon() default "	";

    /**
     * 是否显示底部
     *
     * 参考定义: {"type":"boolean","description":"是否显示底部"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "是否显示底部")
    boolean showFooter() default false;

    /**
     * 是否显示头部
     *
     * 参考定义: {"type":"boolean","description":"是否显示头部"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "是否显示头部")
    boolean showHeader() default false;

    /**
     * 数据源：绑定当前环境变量
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     *
     *
     *
     *
     * @see TokenizeableString
     */

    @Schema(title = "数据源：绑定当前环境变量")
    String source() default "	";

    /**
     * 表格 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "表格 CSS 类名")
    String tableClassName() default "	";

    /**
     * 标题
     *
     * 参考定义: {"type":"string","description":"标题"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "标题")
    String title() default "	";

    /**
     * 工具栏 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "工具栏 CSS 类名")
    String toolbarClassName() default "	";

    /**
     * 合并单元格配置，配置数字表示从左到右的多少列自动合并单元格。
     *
     * 参考定义: {"type":"number","description":"合并单元格配置，配置数字表示从左到右的多少列自动合并单元格。"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "合并单元格配置，配置数字表示从左到右的多少列自动合并单元格。")
    double combineNum() default 0;

    /**
     * 合并单元格配置，配置从第几列开始合并。
     *
     * 参考定义: {"type":"number","description":"合并单元格配置，配置从第几列开始合并。"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "合并单元格配置，配置从第几列开始合并。")
    double combineFromIndex() default 0;

    /**
     * 顶部总结行
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     *
     *
     *
     *
     * @see Object
     */

    @Schema(title = "顶部总结行")
    String[] prefixRow() default "	";

    /**
     * 底部总结行
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     *
     *
     *
     *
     * @see Object
     */

    @Schema(title = "底部总结行")
    String[] affixRow() default "	";

    /**
     * 是否可调整列宽
     *
     * 参考定义: {"type":"boolean","description":"是否可调整列宽"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "是否可调整列宽")
    boolean resizable() default false;

    /**
     * 行样式表表达式
     *
     * 参考定义: {"type":"string","description":"行样式表表达式"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "行样式表表达式")
    String rowClassNameExpr() default "	";

    /**
     * 行角标
     *
     * 参考定义: "#/definitions/BadgeSchema"
     *
     *
     *
     *
     *
     * @see Badge
     */

    @Schema(title = "行角标")
    Badge itemBadge() ;

    /**
     * 开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单
     *
     * 参考定义: {"type":"boolean","description":"开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单")
    boolean autoGenerateFilter() default false;

}
