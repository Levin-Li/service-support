package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Table
 *
 * \"Table 表格渲染器。 文档：https://baidu.gitee.io/amis/docs/components/table\"
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "\"Table 表格渲染器。 文档：https://baidu.gitee.io/amis/docs/components/table\"")
public @interface Table {

    /**
     * 指定为表格渲染器。
     *
     * 参考定义: {"type":"string","enum":["table","static-table"],"description":"指定为表格渲染器。"}
     *
     * @see 
     */
    @Schema(description = "指定为表格渲染器。")
    String type() default "";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
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
     * @see String
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
     * @see String
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
     * @see String
     */
    @Schema(description = "是否显示表达式")
    String visibleOn() default "";

    /**
     * 是否固定表头
     *
     * 参考定义: {"type":"boolean","description":"是否固定表头"}
     *
     * @see 
     */
    @Schema(description = "是否固定表头")
    boolean affixHeader() default false;

    /**
     * 表格的列信息
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/TableColumn"},"description":"表格的列信息"}
     *
     * @see 
     */
    @Schema(description = "表格的列信息")
    String[] columns() default {};

    /**
     * 展示列显示开关，自动即：列数量大于或等于5个时自动开启
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"string","const":"auto"}],"description":"展示列显示开关，自动即：列数量大于或等于5个时自动开启"}
     *
     * @see 
     */
    @Schema(description = "展示列显示开关，自动即：列数量大于或等于5个时自动开启")
    boolean columnsTogglable() default false;

    /**
     * 是否开启底部展示功能，适合移动端展示
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"object","properties":{"expand":{"type":"string","enum":["first","all","none"]},"accordion":{"type":"boolean","description":"是否为手风琴模式"}},"additionalProperties":false}],"description":"是否开启底部展示功能，适合移动端展示"}
     *
     * @see 
     */
    @Schema(description = "是否开启底部展示功能，适合移动端展示")
    boolean footable() default false;

    /**
     * 底部外层 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "底部外层 CSS 类名")
    String footerClassName() default "";

    /**
     * 顶部外层 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "顶部外层 CSS 类名")
    String headerClassName() default "";

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * @see 
     */
    @Schema(description = "占位符")
    String placeholder() default "";

    /**
     * 是否显示底部
     *
     * 参考定义: {"type":"boolean","description":"是否显示底部"}
     *
     * @see 
     */
    @Schema(description = "是否显示底部")
    boolean showFooter() default false;

    /**
     * 是否显示头部
     *
     * 参考定义: {"type":"boolean","description":"是否显示头部"}
     *
     * @see 
     */
    @Schema(description = "是否显示头部")
    boolean showHeader() default false;

    /**
     * 数据源：绑定当前环境变量
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * @see String
     */
    @Schema(description = "数据源：绑定当前环境变量")
    String source() default "";

    /**
     * 表格 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "表格 CSS 类名")
    String tableClassName() default "";

    /**
     * 标题
     *
     * 参考定义: {"type":"string","description":"标题"}
     *
     * @see 
     */
    @Schema(description = "标题")
    String title() default "";

    /**
     * 工具栏 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "工具栏 CSS 类名")
    String toolbarClassName() default "";

    /**
     * 合并单元格配置，配置数字表示从左到右的多少列自动合并单元格。
     *
     * 参考定义: {"type":"number","description":"合并单元格配置，配置数字表示从左到右的多少列自动合并单元格。"}
     *
     * @see 
     */
    @Schema(description = "合并单元格配置，配置数字表示从左到右的多少列自动合并单元格。")
    double combineNum() default 0;

    /**
     * 合并单元格配置，配置从第几列开始合并。
     *
     * 参考定义: {"type":"number","description":"合并单元格配置，配置从第几列开始合并。"}
     *
     * @see 
     */
    @Schema(description = "合并单元格配置，配置从第几列开始合并。")
    double combineFromIndex() default 0;

    /**
     * 顶部总结行
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/SchemaObject"},"description":"顶部总结行"}
     *
     * @see 
     */
    @Schema(description = "顶部总结行")
    String[] prefixRow() default {};

    /**
     * 底部总结行
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/SchemaObject"},"description":"底部总结行"}
     *
     * @see 
     */
    @Schema(description = "底部总结行")
    String[] affixRow() default {};

    /**
     * 是否可调整列宽
     *
     * 参考定义: {"type":"boolean","description":"是否可调整列宽"}
     *
     * @see 
     */
    @Schema(description = "是否可调整列宽")
    boolean resizable() default false;

    /**
     * 行样式表表达式
     *
     * 参考定义: {"type":"string","description":"行样式表表达式"}
     *
     * @see 
     */
    @Schema(description = "行样式表表达式")
    String rowClassNameExpr() default "";

    /**
     * 行角标
     *
     * 参考定义: "#/definitions/BadgeSchema"
     *
     * @see String
     */
    @Schema(description = "行角标")
    String itemBadge() default "";

    /**
     * 开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单
     *
     * 参考定义: {"type":"boolean","description":"开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单"}
     *
     * @see 
     */
    @Schema(description = "开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单")
    boolean autoGenerateFilter() default false;


}
