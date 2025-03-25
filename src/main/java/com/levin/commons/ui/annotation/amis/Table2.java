package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Table2
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Table2")
public @interface Table2 {
///////////////////////////////////////////

	//null
	enum TableLayout{
		fixed,
		auto,
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
     * 指定为表格类型
     *
     * 参考定义: {"type":"string","const":"table2","description":"指定为表格类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为表格类型")
    String type() default "table2";

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
     * 表格标题
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/SchemaObject"},{"type":"array","items":{"$ref":"#/definitions/SchemaObject"}}],"description":"表格标题"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaObject"},{"type":"array","items":{"$ref":"#/definitions/SchemaObject"}}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表格标题")
    String title() default "	";

    /**
     * 表格数据源
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * 
     *
     * 
     *
     * @see TokenizeableString
     */
    
    @Schema(title = "表格数据源")
    String source() default "	";

    /**
     * 表格可自定义列
     *
     * 参考定义: {"anyOf":[{"type":"string","const":"auto"},{"type":"boolean"},{"$ref":"#/definitions/SchemaObject"}],"description":"表格可自定义列"}
     *
     * [{"type":"string","const":"auto"},{"type":"boolean"},{"$ref":"#/definitions/SchemaObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表格可自定义列")
    String columnsTogglable() default "	";

    /**
     * 表格列配置
     *
     * 参考定义: "#/definitions/ColumnSchema"
     *
     * 
     *
     * 
     *
     * @see Column
     */
    
    @Schema(title = "表格列配置")
    Column[] columns() ;

    /**
     * 表格可选择配置
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/RowSelectionSchema"},{"type":"boolean"}],"description":"表格可选择配置"}
     *
     * [{"$ref":"#/definitions/RowSelectionSchema"},{"type":"boolean"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表格可选择配置")
    String rowSelection() default "	";

    /**
     * 表格行可展开配置
     *
     * 参考定义: "#/definitions/ExpandableSchema"
     *
     * 
     *
     * 
     *
     * @see Expandable
     */
    
    @Schema(title = "表格行可展开配置")
    Expandable expandable() ;

    /**
     * 粘性头部
     *
     * 参考定义: {"type":"boolean","description":"粘性头部"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "粘性头部")
    boolean sticky() default false;

    /**
     * 加载中
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"string"},{"$ref":"#/definitions/SchemaObject"}],"description":"加载中"}
     *
     * [{"type":"boolean"},{"type":"string"},{"$ref":"#/definitions/SchemaObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "加载中")
    String loading() default "	";

    /**
     * 行角标内容
     *
     * 参考定义: "#/definitions/BadgeObject"
     *
     * 
     *
     * 
     *
     * @see BadgeObject
     */
    
    @Schema(title = "行角标内容")
    BadgeObject itemBadge() ;

    /**
     * 是否展示行角标
     *
     * 参考定义: {"type":"boolean","description":"是否展示行角标"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否展示行角标")
    boolean showBadge() default false;

    /**
     * 指定挂载dom
     *
     * 参考定义: {"description":"指定挂载dom"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定挂载dom")
    String popOverContainer() default "	";

    /**
     * 多选、嵌套展开记录的ID字段名 默认id
     *
     * 参考定义: {"type":"string","description":"多选、嵌套展开记录的ID字段名 默认id"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "多选、嵌套展开记录的ID字段名 默认id")
    String keyField() default "	";

    /**
     * 数据源嵌套自定义字段名
     *
     * 参考定义: {"type":"string","description":"数据源嵌套自定义字段名"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "数据源嵌套自定义字段名")
    String childrenColumnName() default "	";

    /**
     * 自定义行样式
     *
     * 参考定义: {"type":"string","description":"自定义行样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "自定义行样式")
    String rowClassNameExpr() default "	";

    /**
     * 是否固定内容行高度
     *
     * 参考定义: {"type":"string","description":"是否固定内容行高度"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否固定内容行高度")
    String lineHeight() default "	";

    /**
     * 是否展示边框
     *
     * 参考定义: {"type":"boolean","description":"是否展示边框"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否展示边框")
    boolean bordered() default false;

    /**
     * 是否展示表头
     *
     * 参考定义: {"type":"boolean","description":"是否展示表头"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否展示表头")
    boolean showHeader() default false;

    /**
     * 指定表尾
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/SchemaObject"},{"type":"array","items":{"$ref":"#/definitions/SchemaObject"}}],"description":"指定表尾"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaObject"},{"type":"array","items":{"$ref":"#/definitions/SchemaObject"}}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定表尾")
    String footer() default "	";

    /**
     * 快速编辑后用来批量保存的 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "快速编辑后用来批量保存的 API")
    String quickSaveApi() default "	";

    /**
     * 快速编辑配置成及时保存时使用的 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "快速编辑配置成及时保存时使用的 API")
    String quickSaveItemApi() default "	";

    /**
     * 接口报错信息配置
     *
     * 参考定义: "#/definitions/SchemaMessage"
     *
     * 
     *
     * 
     *
     * @see Message
     */
    
    @Schema(title = "接口报错信息配置")
    Message messages() ;

    /**
     * 重新加载的组件名称
     *
     * 参考定义: {"type":"string","description":"重新加载的组件名称"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "重新加载的组件名称")
    String reload() default "	";

    /**
     * 操作列配置
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * 
     *
     * 
     *
     * @see Action
     */
    
    @Schema(title = "操作列配置")
    String[] actions() default "	";

    /**
     * 批量操作最大限制数
     *
     * 参考定义: {"type":"number","description":"批量操作最大限制数"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "批量操作最大限制数")
    double maxKeepItemSelectionLength() default 0;

    /**
     * 翻页是否保存数据
     *
     * 参考定义: {"type":"boolean","description":"翻页是否保存数据"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "翻页是否保存数据")
    boolean keepItemSelectionOnPageChange() default false;

    /**
     * 是否可选择 作用同rowSelection 兼容原CRUD属性 默认多选
     *
     * 参考定义: {"type":"boolean","description":"是否可选择 作用同rowSelection 兼容原CRUD属性 默认多选"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可选择 作用同rowSelection 兼容原CRUD属性 默认多选")
    boolean selectable() default false;

    /**
     * 是否可多选 作用同rowSelection.type 兼容原CRUD属性 不设置认为是多选 仅设置selectable才起作用
     *
     * 参考定义: {"type":"boolean","description":"是否可多选 作用同rowSelection.type 兼容原CRUD属性 不设置认为是多选 仅设置selectable才起作用"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可多选 作用同rowSelection.type 兼容原CRUD属性 不设置认为是多选 仅设置selectable才起作用")
    boolean multiple() default false;

    /**
     * 设置ID字段名 作用同keyFiled 兼容原CURD属性
     *
     * 参考定义: {"type":"string","description":"设置ID字段名 作用同keyFiled 兼容原CURD属性"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置ID字段名 作用同keyFiled 兼容原CURD属性")
    String primaryField() default "	";

    /**
     * tableLayout
     *
     * 参考定义: {"type":"string","enum":["fixed","auto"]}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "tableLayout")
    TableLayout tableLayout() ;

    /**
     * 表格自动计算高度
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"$ref":"#/definitions/AutoFillHeightObject"}],"description":"表格自动计算高度"}
     *
     * [{"type":"boolean"},{"$ref":"#/definitions/AutoFillHeightObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表格自动计算高度")
    String autoFillHeight() default "	";

    /**
     * 表格是否可以获取父级数据域值，默认为false
     *
     * 参考定义: {"type":"boolean","description":"表格是否可以获取父级数据域值，默认为false"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表格是否可以获取父级数据域值，默认为false")
    boolean canAccessSuperData() default false;

    /**
     * 当一次性渲染太多列上有用，默认为 100，可以用来提升表格渲染性能
     *
     * 参考定义: {"type":"number","description":"当一次性渲染太多列上有用，默认为 100，可以用来提升表格渲染性能","default":100}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "当一次性渲染太多列上有用，默认为 100，可以用来提升表格渲染性能")
    double lazyRenderAfter() default 0;

}
