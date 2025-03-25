package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * CRUD2List
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "CRUD2List")
public @interface CRUD2List {
///////////////////////////////////////////

	//大小
	enum Size{
		sm,
		base,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//数据展示模式 无限加载 or 分页
	enum LoadType{
		more,
		pagination,
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
     * 标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * 
     *
     * 
     *
     * @see Tpl
     */
    
    @Schema(title = "标题")
    Tpl title() ;

    /**
     * 底部区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(title = "底部区域")
    String footer() default "	";

    /**
     * 底部区域类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "底部区域类名")
    String footerClassName() default "	";

    /**
     * 顶部区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(title = "顶部区域")
    String header() default "	";

    /**
     * 顶部区域类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "顶部区域类名")
    String headerClassName() default "	";

    /**
     * 单条数据展示内容配置
     *
     * 参考定义: "#/definitions/ListItemSchema"
     *
     * 
     *
     * 
     *
     * @see ListItem
     */
    
    @Schema(title = "单条数据展示内容配置")
    ListItem listItem() ;

    /**
     * 也可以直接从环境变量中读取，但是不太推荐。
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * 
     *
     * 
     *
     * @see TokenizeableString
     */
    
    @Schema(title = "也可以直接从环境变量中读取，但是不太推荐。")
    String source() default "	";

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
     * 无数据提示
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * 
     *
     * 
     *
     * @see Tpl
     */
    
    @Schema(title = "无数据提示")
    Tpl placeholder() ;

    /**
     * 是否隐藏勾选框
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏勾选框"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否隐藏勾选框")
    boolean hideCheckToggler() default false;

    /**
     * 是否固顶
     *
     * 参考定义: {"type":"boolean","description":"是否固顶"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否固顶")
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
     * 配置某项是否可以点选
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "配置某项是否可以点选")
    String itemCheckableOn() default "	";

    /**
     * 配置某项是否可拖拽排序，前提是要开启拖拽功能
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "配置某项是否可拖拽排序，前提是要开启拖拽功能")
    String itemDraggableOn() default "	";

    /**
     * 点击列表单行时，是否选择
     *
     * 参考定义: {"type":"boolean","description":"点击列表单行时，是否选择"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "点击列表单行时，是否选择")
    boolean checkOnItemClick() default false;

    /**
     * 可以用来作为值的字段
     *
     * 参考定义: {"type":"string","description":"可以用来作为值的字段"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "可以用来作为值的字段")
    String valueField() default "	";

    /**
     * 大小
     *
     * 参考定义: {"type":"string","enum":["sm","base"],"description":"大小"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "大小")
    Size size() ;

    /**
     * 点击列表项的行为
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * 
     *
     * 
     *
     * @see Action
     */
    
    @Schema(title = "点击列表项的行为")
    String itemAction() default "	";

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
     * 指定内容区的展示模式。
     *
     * 参考定义: {"type":"string","description":"指定内容区的展示模式。","const":"list"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定内容区的展示模式。")
    String mode() default "list";

    /**
     * loadingConfig
     *
     * 参考定义: {"type":"object","properties":{"root":{"type":"string"},"show":{"type":"boolean"}},"additionalProperties":false}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "loadingConfig")
    String loadingConfig() default "	";

    /**
     * 指定为 CRUD2 渲染器。
     *
     * 参考定义: {"type":"string","const":"crud2","description":"指定为 CRUD2 渲染器。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为 CRUD2 渲染器。")
    String type() default "crud2";

    /**
     * 初始化数据 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "初始化数据 API")
    String api() default "	";

    /**
     * 静默拉取
     *
     * 参考定义: {"type":"boolean","description":"静默拉取"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "静默拉取")
    boolean silentPolling() default false;

    /**
     * 设置自动刷新时间
     *
     * 参考定义: {"type":"number","description":"设置自动刷新时间"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置自动刷新时间")
    double interval() default 0;

    /**
     * stopAutoRefreshWhen
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "stopAutoRefreshWhen")
    String stopAutoRefreshWhen() default "	";

    /**
     * 数据展示模式 无限加载 or 分页
     *
     * 参考定义: {"type":"string","enum":["more","pagination"],"description":"数据展示模式 无限加载 or 分页"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "数据展示模式 无限加载 or 分页")
    LoadType loadType() ;

    /**
     * 无限加载时，根据此项设置其每页加载数量，可以不限制
     *
     * 参考定义: {"type":"number","description":"无限加载时，根据此项设置其每页加载数量，可以不限制"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "无限加载时，根据此项设置其每页加载数量，可以不限制")
    double perPage() default 0;

    /**
     * 是否为前端单次加载模式，可以用来实现前端分页。
     *
     * 参考定义: {"type":"boolean","description":"是否为前端单次加载模式，可以用来实现前端分页。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否为前端单次加载模式，可以用来实现前端分页。")
    boolean loadDataOnce() default false;

    /**
     * 是否可以选择数据，外部事件动作
     *
     * 参考定义: {"type":"boolean","description":"是否可以选择数据，外部事件动作"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可以选择数据，外部事件动作")
    boolean selectable() default false;

    /**
     * 是否可以多选数据，仅当selectable为 true 时生效
     *
     * 参考定义: {"type":"boolean","description":"是否可以多选数据，仅当selectable为 true 时生效"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可以多选数据，仅当selectable为 true 时生效")
    boolean multiple() default false;

    /**
     * 是否展示已选数据区域，仅当selectable为 true 时生效
     *
     * 参考定义: {"type":"boolean","description":"是否展示已选数据区域，仅当selectable为 true 时生效"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否展示已选数据区域，仅当selectable为 true 时生效")
    boolean showSelection() default false;

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
     * 保存排序的 api
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "保存排序的 api")
    String saveOrderApi() default "	";

    /**
     * 是否将过滤条件的参数同步到地址栏,默认为true
     *
     * 参考定义: {"type":"boolean","description":"是否将过滤条件的参数同步到地址栏,默认为true","default":true}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否将过滤条件的参数同步到地址栏,默认为true")
    boolean syncLocation() default false;

    /**
     * 设置分页页码字段名。
     *
     * 参考定义: {"type":"string","description":"设置分页页码字段名。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置分页页码字段名。")
    String pageField() default "	";

    /**
     * 设置分页一页显示的多少条数据的字段名。
     *
     * 参考定义: {"type":"string","description":"设置分页一页显示的多少条数据的字段名。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置分页一页显示的多少条数据的字段名。")
    String perPageField() default "	";

    /**
     * name
     *
     * 参考定义: "#/definitions/SchemaName"
     *
     * 
     *
     * 
     *
     * @see Name
     */
    
    @Schema(title = "name")
    String name() default "	";

    /**
     * 是否隐藏快速编辑的按钮。
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏快速编辑的按钮。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否隐藏快速编辑的按钮。")
    boolean hideQuickSaveBtn() default false;

    /**
     * 是否自动跳顶部，当切分页的时候。
     *
     * 参考定义: {"type":"boolean","description":"是否自动跳顶部，当切分页的时候。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否自动跳顶部，当切分页的时候。")
    boolean autoJumpToTopOnPagerChange() default false;

    /**
     * 顶部区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(title = "顶部区域")
    String headerToolbar() default "	";

    /**
     * 顶部区域CSS类名
     *
     * 参考定义: {"type":"string","description":"顶部区域CSS类名"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "顶部区域CSS类名")
    String headerToolbarClassName() default "	";

    /**
     * 底部区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(title = "底部区域")
    String footerToolbar() default "	";

    /**
     * 底部区域CSS类名
     *
     * 参考定义: {"type":"string","description":"底部区域CSS类名"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "底部区域CSS类名")
    String footerToolbarClassName() default "	";

    /**
     * 是否将接口返回的内容自动同步到地址栏，前提是开启了同步地址栏。
     *
     * 参考定义: {"type":"boolean","description":"是否将接口返回的内容自动同步到地址栏，前提是开启了同步地址栏。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否将接口返回的内容自动同步到地址栏，前提是开启了同步地址栏。")
    boolean syncResponse2Query() default false;

    /**
     * 翻页时是否保留用户已选的数据
     *
     * 参考定义: {"type":"boolean","description":"翻页时是否保留用户已选的数据"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "翻页时是否保留用户已选的数据")
    boolean keepItemSelectionOnPageChange() default false;

    /**
     * 内容区域占满屏幕剩余空间
     *
     * 参考定义: {"type":"boolean","description":"内容区域占满屏幕剩余空间"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "内容区域占满屏幕剩余空间")
    boolean autoFillHeight() default false;

    /**
     * 行标识符，默认为id
     *
     * 参考定义: {"type":"string","description":"行标识符，默认为id"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "行标识符，默认为id")
    String primaryField() default "	";

    /**
     * 是否开启Query信息转换，开启后将会对url中的Query进行转换，默认开启，默认仅转化布尔值
     *
     * 参考定义: {"anyOf":[{"type":"object","properties":{"enable":{"type":"boolean"},"types":{"type":"array","items":{"type":"string","enum":["boolean","number"]}}},"required":["enable"],"additionalProperties":false},{"type":"boolean"}],"description":"是否开启Query信息转换，开启后将会对url中的Query进行转换，默认开启，默认仅转化布尔值"}
     *
     * [{"type":"object","properties":{"enable":{"type":"boolean"},"types":{"type":"array","items":{"type":"string","enum":["boolean","number"]}}},"required":["enable"],"additionalProperties":false},{"type":"boolean"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否开启Query信息转换，开启后将会对url中的Query进行转换，默认开启，默认仅转化布尔值")
    String parsePrimitiveQuery() default "	";

}
