package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * TableControl
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "TableControl")
public @interface TableControl {
///////////////////////////////////////////

	//table layout
	enum TableLayout{
		fixed,
		auto,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//表单项大小
	enum Size{
		xs,
		sm,
		md,
		lg,
		full,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//配置当前表单项展示模式
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
     * 是否固底
     *
     * 参考定义: {"type":"boolean","description":"是否固底"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否固底")
    boolean affixFooter() default false;

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
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
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
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "顶部外层 CSS 类名")
    String headerClassName() default "	";

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "占位符")
    String placeholder() default "	";

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
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
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
     * 底部工具栏CSS样式类
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "底部工具栏CSS样式类")
    String toolbarClassName() default "	";

    /**
     * 合并单元格配置，配置数字表示从左到右的多少列自动合并单元格。
     *
     * 参考定义: {"anyOf":[{"type":"number"},{"$ref":"#/definitions/SchemaExpression"}],"description":"合并单元格配置，配置数字表示从左到右的多少列自动合并单元格。"}
     *
     * [{"type":"number"},{"$ref":"#/definitions/SchemaExpression"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "合并单元格配置，配置数字表示从左到右的多少列自动合并单元格。")
    String combineNum() default "	";

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
     * 参考定义: "#/definitions/BadgeObject"
     *
     * 
     *
     * 
     *
     * @see BadgeObject
     */
    
    @Schema(title = "行角标")
    BadgeObject itemBadge() ;

    /**
     * 开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/AutoGenerateFilterObject"},{"type":"boolean"}],"description":"开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单"}
     *
     * [{"$ref":"#/definitions/AutoGenerateFilterObject"},{"type":"boolean"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单")
    String autoGenerateFilter() default "	";

    /**
     * 是否可以访问父级数据，正常 combo 已经关联到数组成员，是不能访问父级数据的。
     *
     * 参考定义: {"type":"boolean","description":"是否可以访问父级数据，正常 combo 已经关联到数组成员，是不能访问父级数据的。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可以访问父级数据，正常 combo 已经关联到数组成员，是不能访问父级数据的。")
    boolean canAccessSuperData() default false;

    /**
     * 表格自动计算高度
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"object","properties":{"height":{"type":"number"},"maxHeight":{"type":"number"}},"required":["height","maxHeight"],"additionalProperties":false}],"description":"表格自动计算高度"}
     *
     * [{"type":"boolean"},{"type":"object","properties":{"height":{"type":"number"},"maxHeight":{"type":"number"}},"required":["height","maxHeight"],"additionalProperties":false}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表格自动计算高度")
    String autoFillHeight() default "	";

    /**
     * table layout
     *
     * 参考定义: {"type":"string","enum":["fixed","auto"],"description":"table layout"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "table layout")
    TableLayout tableLayout() ;

    /**
     * 懒加载 API，当行数据中用 defer: true 标记了，则其孩子节点将会用这个 API 来拉取数据。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "懒加载 API，当行数据中用 defer: true 标记了，则其孩子节点将会用这个 API 来拉取数据。")
    String deferApi() default "	";

    /**
     * testid
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "testid")
    String testid() default "	";

    /**
     * 组件唯一 id，主要用于页面设计器中定位 json 节点
     *
     * 参考定义: {"type":"string","description":"组件唯一 id，主要用于页面设计器中定位 json 节点"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "组件唯一 id，主要用于页面设计器中定位 json 节点")
    String $$id() default "	";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
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
     * 参考定义: {"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}},"debounce":{"$ref":"#/definitions/debounceConfig"},"track":{"$ref":"#/definitions/trackConfig"}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"}
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
     * 是否静态展示
     *
     * 参考定义: {}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否静态展示")
    String _static() default "	";

    /**
     * 是否静态展示表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "是否静态展示表达式")
    String staticOn() default "	";

    /**
     * 静态展示空值占位
     *
     * 参考定义: {"type":"string","description":"静态展示空值占位"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "静态展示空值占位")
    String staticPlaceholder() default "	";

    /**
     * 静态展示表单项类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "静态展示表单项类名")
    String staticClassName() default "	";

    /**
     * 静态展示表单项Label类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "静态展示表单项Label类名")
    String staticLabelClassName() default "	";

    /**
     * 静态展示表单项Value类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "静态展示表单项Value类名")
    String staticInputClassName() default "	";

    /**
     * 组件样式
     *
     * 参考定义: {"type":"object","description":"组件样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "组件样式")
    String style() default "	";

    /**
     * 编辑器配置，运行时可以忽略
     *
     * 参考定义: {"type":"object","properties":{"behavior":{"type":"string","description":"组件行为、用途，如 create、update、remove"},"displayName":{"type":"string","description":"组件名称，通常是业务名称方便定位"},"mock":{"description":"编辑器假数据，方便展示"}},"description":"编辑器配置，运行时可以忽略"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "编辑器配置，运行时可以忽略")
    String editorSetting() default "	";

    /**
     * 可以组件级别用来关闭移动端样式
     *
     * 参考定义: {"type":"boolean","description":"可以组件级别用来关闭移动端样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "可以组件级别用来关闭移动端样式")
    boolean useMobileUI() default false;

    /**
     * testIdBuilder
     *
     * 参考定义: "#/definitions/TestIdBuilder"
     *
     * 
     *
     * 
     *
     * @see TestIdBuilder
     */
    
    @Schema(title = "testIdBuilder")
    TestIdBuilder testIdBuilder() ;

    /**
     * 表单项大小
     *
     * 参考定义: {"type":"string","enum":["xs","sm","md","lg","full"],"description":"表单项大小"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表单项大小")
    Size size() ;

    /**
     * 描述标题
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"boolean","const":false}],"description":"描述标题"}
     *
     * [{"type":"string"},{"type":"boolean","const":false}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "描述标题")
    String label() default "	";

    /**
     * 描述标题
     *
     * 参考定义: "#/definitions/LabelAlign"
     *
     * 
     *
     * 
     *
     * @see LabelAlign
     */
    
    @Schema(title = "描述标题")
    String labelAlign() default "	";

    /**
     * label自定义宽度，默认单位为px
     *
     * 参考定义: {"type":["number","string"],"description":"label自定义宽度，默认单位为px"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "label自定义宽度，默认单位为px")
    String labelWidth() default "	";

    /**
     * 配置 label className
     *
     * 参考定义: {"type":"string","description":"配置 label className"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "配置 label className")
    String labelClassName() default "	";

    /**
     * 字段名，表单提交时的 key，支持多层级，用.连接，如： a.b.c
     *
     * 参考定义: {"type":"string","description":"字段名，表单提交时的 key，支持多层级，用.连接，如： a.b.c"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "字段名，表单提交时的 key，支持多层级，用.连接，如： a.b.c")
    String name() default "	";

    /**
     * 额外的字段名，当为范围组件时可以用来将另外一个值打平出来
     *
     * 参考定义: {"type":"string","description":"额外的字段名，当为范围组件时可以用来将另外一个值打平出来"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "额外的字段名，当为范围组件时可以用来将另外一个值打平出来")
    String extraName() default "	";

    /**
     * 显示一个小图标, 鼠标放上去的时候显示提示内容
     *
     * 参考定义: {"description":"显示一个小图标, 鼠标放上去的时候显示提示内容"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "显示一个小图标, 鼠标放上去的时候显示提示内容")
    String remark() default "	";

    /**
     * 显示一个小图标, 鼠标放上去的时候显示提示内容, 这个小图标跟 label 在一起
     *
     * 参考定义: {"description":"显示一个小图标, 鼠标放上去的时候显示提示内容, 这个小图标跟 label 在一起"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "显示一个小图标, 鼠标放上去的时候显示提示内容, 这个小图标跟 label 在一起")
    String labelRemark() default "	";

    /**
     * 输入提示，聚焦的时候显示
     *
     * 参考定义: {"type":"string","description":"输入提示，聚焦的时候显示"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "输入提示，聚焦的时候显示")
    String hint() default "	";

    /**
     * 当修改完的时候是否提交表单。
     *
     * 参考定义: {"type":"boolean","description":"当修改完的时候是否提交表单。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "当修改完的时候是否提交表单。")
    boolean submitOnChange() default false;

    /**
     * 是否只读
     *
     * 参考定义: {"type":"boolean","description":"是否只读"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否只读")
    boolean readOnly() default false;

    /**
     * 只读条件
     *
     * 参考定义: {"type":"string","description":"只读条件"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "只读条件")
    String readOnlyOn() default "	";

    /**
     * 不设置时，当表单提交过后表单项每次修改都会触发重新验证， 如果设置了，则由此配置项来决定要不要每次修改都触发验证。
     *
     * 参考定义: {"type":"boolean","description":"不设置时，当表单提交过后表单项每次修改都会触发重新验证， 如果设置了，则由此配置项来决定要不要每次修改都触发验证。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "不设置时，当表单提交过后表单项每次修改都会触发重新验证， 如果设置了，则由此配置项来决定要不要每次修改都触发验证。")
    boolean validateOnChange() default false;

    /**
     * 描述内容，支持 Html 片段。
     *
     * 参考定义: {"type":"string","description":"描述内容，支持 Html 片段。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "描述内容，支持 Html 片段。")
    String description() default "	";

    /**
     * desc
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "desc")
    String desc() default "	";

    /**
     * 配置描述上的 className
     *
     * 参考定义: "#/definitions/ClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "配置描述上的 className")
    String descriptionClassName() default "	";

    /**
     * 配置当前表单项展示模式
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置当前表单项展示模式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "配置当前表单项展示模式")
    Mode mode() ;

    /**
     * 当配置为水平布局的时候，用来配置具体的左右分配。
     *
     * 参考定义: "#/definitions/FormHorizontal"
     *
     * 
     *
     * 
     *
     * @see FormHorizontal
     */
    
    @Schema(title = "当配置为水平布局的时候，用来配置具体的左右分配。")
    FormHorizontal horizontal() ;

    /**
     * 表单 control 是否为 inline 模式。
     *
     * 参考定义: {"type":"boolean","description":"表单 control 是否为 inline 模式。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表单 control 是否为 inline 模式。")
    boolean inline() default false;

    /**
     * 配置 input className
     *
     * 参考定义: "#/definitions/ClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "配置 input className")
    String inputClassName() default "	";

    /**
     * 是否为必填
     *
     * 参考定义: {"type":"boolean","description":"是否为必填"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否为必填")
    boolean required() default false;

    /**
     * 验证失败的提示信息
     *
     * 参考定义: {"type":"object","properties":{"isAlpha":{"type":"string"},"isAlphanumeric":{"type":"string"},"isEmail":{"type":"string"},"isFloat":{"type":"string"},"isInt":{"type":"string"},"isJson":{"type":"string"},"isLength":{"type":"string"},"isNumeric":{"type":"string"},"isRequired":{"type":"string"},"isUrl":{"type":"string"},"matchRegexp":{"type":"string"},"matchRegexp2":{"type":"string"},"matchRegexp3":{"type":"string"},"matchRegexp4":{"type":"string"},"matchRegexp5":{"type":"string"},"maxLength":{"type":"string"},"maximum":{"type":"string"},"minLength":{"type":"string"},"minimum":{"type":"string"},"isDateTimeSame":{"type":"string"},"isDateTimeBefore":{"type":"string"},"isDateTimeAfter":{"type":"string"},"isDateTimeSameOrBefore":{"type":"string"},"isDateTimeSameOrAfter":{"type":"string"},"isDateTimeBetween":{"type":"string"},"isTimeSame":{"type":"string"},"isTimeBefore":{"type":"string"},"isTimeAfter":{"type":"string"},"isTimeSameOrBefore":{"type":"string"},"isTimeSameOrAfter":{"type":"string"},"isTimeBetween":{"type":"string"}},"description":"验证失败的提示信息"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "验证失败的提示信息")
    String validationErrors() default "	";

    /**
     * validations
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"object","properties":{"isAlpha":{"type":"boolean","description":"是否是字母"},"isAlphanumeric":{"type":"boolean","description":"是否为字母数字"},"isEmail":{"type":"boolean","description":"是否为邮箱地址"},"isFloat":{"type":"boolean","description":"是否为浮点型"},"isInt":{"type":"boolean","description":"是否为整型"},"isJson":{"type":"boolean","description":"是否为 json"},"isLength":{"type":"number","description":"长度等于指定值"},"isNumeric":{"type":"boolean","description":"是否为数字"},"isRequired":{"type":"boolean","description":"是否为必填"},"isUrl":{"type":"boolean","description":"是否为 URL 地址"},"matchRegexp":{"type":"string","description":"内容命中指定正则"},"matchRegexp1":{"type":"string","description":"内容命中指定正则"},"matchRegexp2":{"type":"string","description":"内容命中指定正则"},"matchRegexp3":{"type":"string","description":"内容命中指定正则"},"matchRegexp4":{"type":"string","description":"内容命中指定正则"},"matchRegexp5":{"type":"string","description":"内容命中指定正则"},"maxLength":{"type":"number","description":"最大长度为指定值"},"maximum":{"type":"number","description":"最大值为指定值"},"minLength":{"type":"number","description":"最小长度为指定值"},"minimum":{"type":"number","description":"最小值为指定值"},"isDateTimeSame":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"和目标日期相同，支持指定粒度，默认到毫秒"},"isDateTimeBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标日期，支持指定粒度，默认到毫秒"},"isDateTimeAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标日期，支持指定粒度，默认到毫秒"},"isDateTimeSameOrBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标日期或和目标日期相同，支持指定粒度，默认到毫秒"},"isDateTimeSameOrAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标日期或和目标日期相同，支持指定粒度，默认到毫秒"},"isDateTimeBetween":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"日期处于目标日期范围，支持指定粒度和区间的开闭形式，默认到毫秒, 左右开区间"},"isTimeSame":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"和目标时间相同，支持指定粒度，默认到毫秒"},"isTimeBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标时间，支持指定粒度，默认到毫秒"},"isTimeAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标时间，支持指定粒度，默认到毫秒"},"isTimeSameOrBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标时间或和目标时间相同，支持指定粒度，默认到毫秒"},"isTimeSameOrAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标时间或和目标时间相同，支持指定粒度，默认到毫秒"},"isTimeBetween":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"时间处于目标时间范围，支持指定粒度和区间的开闭形式，默认到毫秒, 左右开区间"}}}]}
     *
     * [{"type":"string"},{"type":"object","properties":{"isAlpha":{"type":"boolean","description":"是否是字母"},"isAlphanumeric":{"type":"boolean","description":"是否为字母数字"},"isEmail":{"type":"boolean","description":"是否为邮箱地址"},"isFloat":{"type":"boolean","description":"是否为浮点型"},"isInt":{"type":"boolean","description":"是否为整型"},"isJson":{"type":"boolean","description":"是否为 json"},"isLength":{"type":"number","description":"长度等于指定值"},"isNumeric":{"type":"boolean","description":"是否为数字"},"isRequired":{"type":"boolean","description":"是否为必填"},"isUrl":{"type":"boolean","description":"是否为 URL 地址"},"matchRegexp":{"type":"string","description":"内容命中指定正则"},"matchRegexp1":{"type":"string","description":"内容命中指定正则"},"matchRegexp2":{"type":"string","description":"内容命中指定正则"},"matchRegexp3":{"type":"string","description":"内容命中指定正则"},"matchRegexp4":{"type":"string","description":"内容命中指定正则"},"matchRegexp5":{"type":"string","description":"内容命中指定正则"},"maxLength":{"type":"number","description":"最大长度为指定值"},"maximum":{"type":"number","description":"最大值为指定值"},"minLength":{"type":"number","description":"最小长度为指定值"},"minimum":{"type":"number","description":"最小值为指定值"},"isDateTimeSame":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"和目标日期相同，支持指定粒度，默认到毫秒"},"isDateTimeBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标日期，支持指定粒度，默认到毫秒"},"isDateTimeAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标日期，支持指定粒度，默认到毫秒"},"isDateTimeSameOrBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标日期或和目标日期相同，支持指定粒度，默认到毫秒"},"isDateTimeSameOrAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标日期或和目标日期相同，支持指定粒度，默认到毫秒"},"isDateTimeBetween":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"日期处于目标日期范围，支持指定粒度和区间的开闭形式，默认到毫秒, 左右开区间"},"isTimeSame":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"和目标时间相同，支持指定粒度，默认到毫秒"},"isTimeBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标时间，支持指定粒度，默认到毫秒"},"isTimeAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标时间，支持指定粒度，默认到毫秒"},"isTimeSameOrBefore":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"早于目标时间或和目标时间相同，支持指定粒度，默认到毫秒"},"isTimeSameOrAfter":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"晚于目标时间或和目标时间相同，支持指定粒度，默认到毫秒"},"isTimeBetween":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"时间处于目标时间范围，支持指定粒度和区间的开闭形式，默认到毫秒, 左右开区间"}}}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "validations")
    String validations() default "	";

    /**
     * 默认值，切记只能是静态值，不支持取变量，跟数据关联是通过设置 name 属性来实现的。
     *
     * 参考定义: {"description":"默认值，切记只能是静态值，不支持取变量，跟数据关联是通过设置 name 属性来实现的。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认值，切记只能是静态值，不支持取变量，跟数据关联是通过设置 name 属性来实现的。")
    String value() default "	";

    /**
     * 表单项隐藏时，是否在当前 Form 中删除掉该表单项值。注意同名的未隐藏的表单项值也会删掉
     *
     * 参考定义: {"type":"boolean","description":"表单项隐藏时，是否在当前 Form 中删除掉该表单项值。注意同名的未隐藏的表单项值也会删掉"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表单项隐藏时，是否在当前 Form 中删除掉该表单项值。注意同名的未隐藏的表单项值也会删掉")
    boolean clearValueOnHidden() default false;

    /**
     * 远端校验表单项接口
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/BaseApiObject"}],"description":"远端校验表单项接口"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/BaseApiObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "远端校验表单项接口")
    String validateApi() default "	";

    /**
     * 自动填充，当选项被选择的时候，将选项中的其他值同步设置到表单内。
     *
     * 参考定义: {"anyOf":[{"type":"object","additionalProperties":{"type":"string"}},{"type":"object","properties":{"showSuggestion":{"type":"boolean","description":"是否为参照录入模式，参照录入会展示候选值供用户选择，而不是直接填充。"},"api":{"anyOf":[{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}],"description":"自动填充 api"},"silent":{"type":"boolean","description":"是否展示数据格式错误提示，默认为不展示","default":true},"fillMappinng":{"type":"object","description":"填充时的数据映射"},"trigger":{"type":"string","enum":["change","foucs"],"description":"触发条件，默认为 change"},"mode":{"type":"string","enum":["popOver","dialog","drawer"],"description":"弹窗方式，当为参照录入时用可以配置"},"position":{"type":"string","description":"当参照录入为抽屉时可以配置弹出位置"},"size":{"type":"string","description":"当为参照录入时可以配置弹出容器的大小"},"columns":{"type":"array","items":{},"description":"参照录入展示的项"},"filter":{"description":"参照录入时的过滤条件"}},"additionalProperties":false}],"description":"自动填充，当选项被选择的时候，将选项中的其他值同步设置到表单内。"}
     *
     * [{"type":"object","additionalProperties":{"type":"string"}},{"type":"object","properties":{"showSuggestion":{"type":"boolean","description":"是否为参照录入模式，参照录入会展示候选值供用户选择，而不是直接填充。"},"api":{"anyOf":[{"$ref":"#/definitions/BaseApiObject"},{"type":"string"}],"description":"自动填充 api"},"silent":{"type":"boolean","description":"是否展示数据格式错误提示，默认为不展示","default":true},"fillMappinng":{"type":"object","description":"填充时的数据映射"},"trigger":{"type":"string","enum":["change","foucs"],"description":"触发条件，默认为 change"},"mode":{"type":"string","enum":["popOver","dialog","drawer"],"description":"弹窗方式，当为参照录入时用可以配置"},"position":{"type":"string","description":"当参照录入为抽屉时可以配置弹出位置"},"size":{"type":"string","description":"当为参照录入时可以配置弹出容器的大小"},"columns":{"type":"array","items":{},"description":"参照录入展示的项"},"filter":{"description":"参照录入时的过滤条件"}},"additionalProperties":false}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "自动填充，当选项被选择的时候，将选项中的其他值同步设置到表单内。")
    String autoFill() default "	";

    /**
     * initAutoFill
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"string","const":"fillIfNotSet"}]}
     *
     * [{"type":"boolean"},{"type":"string","const":"fillIfNotSet"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "initAutoFill")
    String initAutoFill() default "	";

    /**
     * type
     *
     * 参考定义: {"type":"string","const":"input-table"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "type")
    String type() default "input-table";

    /**
     * 可新增
     *
     * 参考定义: {"type":"boolean","description":"可新增"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "可新增")
    boolean addable() default false;

    /**
     * 是否可以新增子项
     *
     * 参考定义: {"type":"boolean","description":"是否可以新增子项"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可以新增子项")
    boolean childrenAddable() default false;

    /**
     * 可复制新增
     *
     * 参考定义: {"type":"boolean","description":"可复制新增"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "可复制新增")
    boolean copyable() default false;

    /**
     * 复制按钮文字
     *
     * 参考定义: {"type":"string","description":"复制按钮文字"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "复制按钮文字")
    String copyBtnLabel() default "	";

    /**
     * 复制按钮图标
     *
     * 参考定义: {"type":"string","description":"复制按钮图标"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "复制按钮图标")
    String copyBtnIcon() default "	";

    /**
     * 是否显示复制按钮
     *
     * 参考定义: {"type":"boolean","description":"是否显示复制按钮"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示复制按钮")
    boolean copyAddBtn() default false;

    /**
     * 是否可以拖拽排序
     *
     * 参考定义: {"type":"boolean","description":"是否可以拖拽排序"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可以拖拽排序")
    boolean draggable() default false;

    /**
     * 新增 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "新增 API")
    String addApi() default "	";

    /**
     * 新增按钮文字
     *
     * 参考定义: {"type":"string","description":"新增按钮文字"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "新增按钮文字")
    String addBtnLabel() default "	";

    /**
     * 新增按钮图标
     *
     * 参考定义: {"type":"string","description":"新增按钮图标"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "新增按钮图标")
    String addBtnIcon() default "	";

    /**
     * 孩子新增按钮文字
     *
     * 参考定义: {"type":"string","description":"孩子新增按钮文字"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "孩子新增按钮文字")
    String subAddBtnLabel() default "	";

    /**
     * 孩子新增按钮图标
     *
     * 参考定义: {"type":"string","description":"孩子新增按钮图标"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "孩子新增按钮图标")
    String subAddBtnIcon() default "	";

    /**
     * 可否删除
     *
     * 参考定义: {"type":"boolean","description":"可否删除"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "可否删除")
    boolean removable() default false;

    /**
     * 删除的 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "删除的 API")
    String deleteApi() default "	";

    /**
     * 可否编辑
     *
     * 参考定义: {"type":"boolean","description":"可否编辑"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "可否编辑")
    boolean editable() default false;

    /**
     * 更新按钮名称
     *
     * 参考定义: {"type":"string","description":"更新按钮名称"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "更新按钮名称")
    String editBtnLabel() default "	";

    /**
     * 更新按钮图标
     *
     * 参考定义: {"type":"string","description":"更新按钮图标"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "更新按钮图标")
    String editBtnIcon() default "	";

    /**
     * 确认按钮文字
     *
     * 参考定义: {"type":"string","description":"确认按钮文字"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "确认按钮文字")
    String confirmBtnLabel() default "	";

    /**
     * 确认按钮图标
     *
     * 参考定义: {"type":"string","description":"确认按钮图标"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "确认按钮图标")
    String confirmBtnIcon() default "	";

    /**
     * 取消按钮文字
     *
     * 参考定义: {"type":"string","description":"取消按钮文字"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "取消按钮文字")
    String cancelBtnLabel() default "	";

    /**
     * 取消按钮图标
     *
     * 参考定义: {"type":"string","description":"取消按钮图标"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "取消按钮图标")
    String cancelBtnIcon() default "	";

    /**
     * 删除按钮文字
     *
     * 参考定义: {"type":"string","description":"删除按钮文字"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "删除按钮文字")
    String deleteBtnLabel() default "	";

    /**
     * 删除按钮图标
     *
     * 参考定义: {"type":"string","description":"删除按钮图标"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "删除按钮图标")
    String deleteBtnIcon() default "	";

    /**
     * 更新 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "更新 API")
    String updateApi() default "	";

    /**
     * 初始值，新增的时候
     *
     * 参考定义: {"description":"初始值，新增的时候"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "初始值，新增的时候")
    String scaffold() default "	";

    /**
     * 删除确认文字
     *
     * 参考定义: {"type":"string","description":"删除确认文字"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "删除确认文字")
    String deleteConfirmText() default "	";

    /**
     * 值字段
     *
     * 参考定义: {"type":"string","description":"值字段"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "值字段")
    String valueField() default "	";

    /**
     * 是否为确认的编辑模式。
     *
     * 参考定义: {"type":"boolean","description":"是否为确认的编辑模式。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否为确认的编辑模式。")
    boolean needConfirm() default false;

    /**
     * 是否显示序号
     *
     * 参考定义: {"type":"boolean","description":"是否显示序号"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示序号")
    boolean showIndex() default false;

    /**
     * 分页个数，默认不分页
     *
     * 参考定义: {"type":"number","description":"分页个数，默认不分页"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "分页个数，默认不分页")
    double perPage() default 0;

    /**
     * 限制最大个数
     *
     * 参考定义: {"anyOf":[{"type":"number"},{"$ref":"#/definitions/SchemaTokenizeableString"}],"description":"限制最大个数"}
     *
     * [{"type":"number"},{"$ref":"#/definitions/SchemaTokenizeableString"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "限制最大个数")
    String maxLength() default "	";

    /**
     * 限制最小个数
     *
     * 参考定义: {"anyOf":[{"type":"number"},{"$ref":"#/definitions/SchemaTokenizeableString"}],"description":"限制最小个数"}
     *
     * [{"type":"number"},{"$ref":"#/definitions/SchemaTokenizeableString"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "限制最小个数")
    String minLength() default "	";

    /**
     * 是否显示底部新增按钮
     *
     * 参考定义: {"type":"boolean","description":"是否显示底部新增按钮"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示底部新增按钮")
    boolean showFooterAddBtn() default false;

    /**
     * 是否显示表格操作栏新增按钮
     *
     * 参考定义: {"type":"boolean","description":"是否显示表格操作栏新增按钮"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示表格操作栏新增按钮")
    boolean showTableAddBtn() default false;

    /**
     * 底部新增按钮配置
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(title = "底部新增按钮配置")
    String footerAddBtn() default "	";

    /**
     * 是否开启 static 状态切换
     *
     * 参考定义: {"type":"boolean","description":"是否开启 static 状态切换"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否开启 static 状态切换")
    boolean enableStaticTransform() default false;

}
