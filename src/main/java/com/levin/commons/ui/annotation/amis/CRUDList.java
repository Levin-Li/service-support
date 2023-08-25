package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * CRUDList
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "CRUDList")
public @interface CRUDList {
///////////////////////////////////////////

	//大小
	enum Size{
		sm,
		base,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//默认排序方向
	enum OrderDir{
		asc,
		desc,
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
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
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
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
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
     * 点击卡片的时候是否勾选卡片。
     *
     * 参考定义: {"type":"boolean","description":"点击卡片的时候是否勾选卡片。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "点击卡片的时候是否勾选卡片。")
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
     * 指定为 CRUD 渲染器。
     *
     * 参考定义: {"type":"string","const":"crud","description":"指定为 CRUD 渲染器。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为 CRUD 渲染器。")
    String type() default "crud";

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
     * 批量操作
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * 
     *
     * 
     *
     * @see Action
     */
    
    @Schema(title = "批量操作")
    String[] bulkActions() default "	";

    /**
     * 单条操作
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * 
     *
     * 
     *
     * @see Action
     */
    
    @Schema(title = "单条操作")
    String[] itemActions() default "	";

    /**
     * 每页个数，默认为 10，如果不是请设置。
     *
     * 参考定义: {"type":"number","description":"每页个数，默认为 10，如果不是请设置。","default":10}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "每页个数，默认为 10，如果不是请设置。")
    double perPage() default 0;

    /**
     * 默认排序字段
     *
     * 参考定义: {"type":"string","description":"默认排序字段"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认排序字段")
    String orderBy() default "	";

    /**
     * 默认排序方向
     *
     * 参考定义: {"type":"string","enum":["asc","desc"],"description":"默认排序方向"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认排序方向")
    OrderDir orderDir() ;

    /**
     * 可以默认给定初始参数如： {perPage: 24}
     *
     * 参考定义: "#/definitions/PlainObject"
     *
     * 
     *
     * 
     *
     * @see PlainObject
     */
    
    @Schema(title = "可以默认给定初始参数如： {perPage: 24}")
    String defaultParams() default "	";

    /**
     * 是否可通过拖拽排序
     *
     * 参考定义: {"type":"boolean","description":"是否可通过拖拽排序"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可通过拖拽排序")
    boolean draggable() default false;

    /**
     * 是否可通过拖拽排序，通过表达式来配置
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "是否可通过拖拽排序，通过表达式来配置")
    String draggableOn() default "	";

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
     * 过滤器表单
     *
     * 参考定义: {"description":"过滤器表单"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "过滤器表单")
    String filter() default "	";

    /**
     * 初始是否拉取
     *
     * 参考定义: {"type":"boolean","description":"初始是否拉取"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "初始是否拉取")
    boolean initFetch() default false;

    /**
     * 初始是否拉取，用表达式来配置。
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "初始是否拉取，用表达式来配置。")
    String initFetchOn() default "	";

    /**
     * 配置内部 DOM 的 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "配置内部 DOM 的 className")
    String innerClassName() default "	";

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
     * 设置用来确定位置的字段名，设置后新的顺序将被赋值到该字段中。
     *
     * 参考定义: {"type":"string","description":"设置用来确定位置的字段名，设置后新的顺序将被赋值到该字段中。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置用来确定位置的字段名，设置后新的顺序将被赋值到该字段中。")
    String orderField() default "	";

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
     * 顶部工具栏
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"allOf":[{"$ref":"#/definitions/SchemaObject","patternProperties":{"^(align)$":{}}},{"type":"object","additionalProperties":true,"properties":{"align":{"type":"string","enum":["left","right"],"description":"对齐方式"}}}]},{"type":"object","additionalProperties":false,"properties":{"align":{"type":"string","enum":["left","right"],"description":"对齐方式"},"className":{"$ref":"#/definitions/SchemaClassName","description":"容器 css 类名"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"组件唯一 id，主要用于日志采集"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"type":{"$ref":"#/definitions/CRUDBultinToolbarType"}},"required":["type"]},{"$ref":"#/definitions/CRUDBultinToolbarType"}]},"description":"顶部工具栏"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "顶部工具栏")
    String[] headerToolbar() default "	";

    /**
     * 底部工具栏
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"allOf":[{"$ref":"#/definitions/SchemaObject","patternProperties":{"^(align)$":{}}},{"type":"object","additionalProperties":true,"properties":{"align":{"type":"string","enum":["left","right"],"description":"对齐方式"}}}]},{"type":"object","additionalProperties":false,"properties":{"align":{"type":"string","enum":["left","right"],"description":"对齐方式"},"className":{"$ref":"#/definitions/SchemaClassName","description":"容器 css 类名"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"组件唯一 id，主要用于日志采集"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"type":{"$ref":"#/definitions/CRUDBultinToolbarType"}},"required":["type"]},{"$ref":"#/definitions/CRUDBultinToolbarType"}]},"description":"底部工具栏"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "底部工具栏")
    String[] footerToolbar() default "	";

    /**
     * 每页显示多少个空间成员的配置如： [10, 20, 50, 100]。
     *
     * 参考定义: {"type":"array","items":{"type":"number"},"description":"每页显示多少个空间成员的配置如： [10, 20, 50, 100]。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "每页显示多少个空间成员的配置如： [10, 20, 50, 100]。")
    double[] perPageAvailable() default 0;

    /**
     * messages
     *
     * 参考定义: "#/definitions/SchemaMessage"
     *
     * 
     *
     * 
     *
     * @see Message
     */
    
    @Schema(title = "messages")
    Message messages() ;

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
     * stopAutoRefreshWhenModalIsOpen
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "stopAutoRefreshWhenModalIsOpen")
    boolean stopAutoRefreshWhenModalIsOpen() default false;

    /**
     * filterTogglable
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "filterTogglable")
    boolean filterTogglable() default false;

    /**
     * filterDefaultVisible
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "filterDefaultVisible")
    boolean filterDefaultVisible() default false;

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
     * 分页的时候是否保留用户选择。
     *
     * 参考定义: {"type":"boolean","description":"分页的时候是否保留用户选择。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "分页的时候是否保留用户选择。")
    boolean keepItemSelectionOnPageChange() default false;

    /**
     * 当配置 keepItemSelectionOnPageChange 时有用，用来配置已勾选项的文案。
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * 
     *
     * 
     *
     * @see Tpl
     */
    
    @Schema(title = "当配置 keepItemSelectionOnPageChange 时有用，用来配置已勾选项的文案。")
    Tpl labelTpl() ;

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
     * 在开启loadDataOnce时，filter时是否去重新请求api
     *
     * 参考定义: {"type":"boolean","description":"在开启loadDataOnce时，filter时是否去重新请求api"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "在开启loadDataOnce时，filter时是否去重新请求api")
    boolean loadDataOnceFetchOnFilter() default false;

    /**
     * 如果时内嵌模式，可以通过这个来配置默认的展开选项。
     *
     * 参考定义: {"type":"object","properties":{"expand":{"type":"string","enum":["first","all","none"],"description":"默认是展开第一个、所有、还是都不展开。"},"expandAll":{"type":"boolean","description":"是否显示全部切换按钮"},"accordion":{"type":"boolean","description":"是否为手风琴模式"}},"additionalProperties":false,"description":"如果时内嵌模式，可以通过这个来配置默认的展开选项。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "如果时内嵌模式，可以通过这个来配置默认的展开选项。")
    String expandConfig() default "	";

    /**
     * 默认只有当分页数大于 1 是才显示，如果总是想显示请配置。
     *
     * 参考定义: {"type":"boolean","description":"默认只有当分页数大于 1 是才显示，如果总是想显示请配置。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认只有当分页数大于 1 是才显示，如果总是想显示请配置。")
    boolean alwaysShowPagination() default false;

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

}
