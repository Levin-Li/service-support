package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * CRUDCards
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "CRUDCards")
public @interface CRUDCards {

    /**
     * Construct a type with the properties of T except for those in type K.
     *
     * 参考定义: {"type":"object","properties":{"header":{"type":"object","properties":{"className":{"$ref":"#/definitions/SchemaClassName"},"title":{"$ref":"#/definitions/SchemaTpl","description":"标题"},"titleClassName":{"$ref":"#/definitions/SchemaClassName"},"subTitle":{"$ref":"#/definitions/SchemaTpl","description":"副标题"},"subTitleClassName":{"$ref":"#/definitions/SchemaClassName"},"subTitlePlaceholder":{"type":"string"},"description":{"$ref":"#/definitions/SchemaTpl","description":"描述"},"descriptionPlaceholder":{"type":"string","description":"描述占位内容"},"descriptionClassName":{"$ref":"#/definitions/SchemaClassName","description":"描述占位类名"},"desc":{"$ref":"#/definitions/SchemaTpl"},"descPlaceholder":{"$ref":"#/definitions/SchemaTpl"},"descClassName":{"$ref":"#/definitions/SchemaClassName"},"avatar":{"$ref":"#/definitions/SchemaUrlPath","description":"图片地址"},"avatarText":{"$ref":"#/definitions/SchemaTpl"},"avatarTextBackground":{"type":"array","items":{"type":"object","properties":{"length":{"type":"number","description":"Returns the length of a String object."}},"required":["length"],"additionalProperties":{"type":"string"},"description":"Allows manipulation and formatting of text strings and determination and location of substrings within strings."}},"avatarTextClassName":{"$ref":"#/definitions/SchemaClassName"},"avatarClassName":{"$ref":"#/definitions/SchemaClassName","description":"图片包括层类名"},"imageClassName":{"$ref":"#/definitions/SchemaClassName","description":"图片类名。"},"highlight":{"$ref":"#/definitions/SchemaExpression","description":"是否点亮"},"highlightClassName":{"$ref":"#/definitions/SchemaClassName"},"href":{"$ref":"#/definitions/SchemaTpl","description":"链接地址"},"blank":{"type":"boolean","description":"是否新窗口打开"}},"additionalProperties":false,"description":"头部配置"},"body":{"type":"array","items":{"$ref":"#/definitions/CardBodyField"},"description":"内容区域"},"media":{"type":"object","properties":{"className":{"$ref":"#/definitions/SchemaClassName"},"type":{"type":"string","enum":["image","video"],"description":"多媒体类型"},"url":{"$ref":"#/definitions/SchemaUrlPath","description":"多媒体链接地址"},"position":{"type":"string","enum":["top","left","right","bottom"],"description":"多媒体区域位置"},"autoPlay":{"type":"boolean","description":"类型为video时是否自动播放"},"isLive":{"type":"boolean","description":"类型为video时是否是直播"},"poster":{"$ref":"#/definitions/SchemaUrlPath","description":"类型为video时视频封面"}},"additionalProperties":false,"description":"多媒体区域"},"actions":{"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"底部按钮集合。"},"toolbar":{"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"工具栏按钮"},"secondary":{"$ref":"#/definitions/SchemaTpl","description":"次要说明"},"className":{"$ref":"#/definitions/SchemaClassName","description":"容器 css 类名"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"}},"additionalProperties":false,"description":"Construct a type with the properties of T except for those in type K."}
     *
     * @see 
     */
    @Schema(description = "Construct a type with the properties of T except for those in type K.")
    String card() default "";

    /**
     * 头部 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "头部 CSS 类名")
    String headerClassName() default "";

    /**
     * 底部 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "底部 CSS 类名")
    String footerClassName() default "";

    /**
     * 卡片 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "卡片 CSS 类名")
    String itemClassName() default "";

    /**
     * 无数据提示
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "无数据提示")
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
     * 也可以直接从环境变量中读取，但是不太推荐。
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * @see String
     */
    @Schema(description = "也可以直接从环境变量中读取，但是不太推荐。")
    String source() default "";

    /**
     * 标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "标题")
    String title() default "";

    /**
     * 是否隐藏勾选框
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏勾选框"}
     *
     * @see 
     */
    @Schema(description = "是否隐藏勾选框")
    boolean hideCheckToggler() default false;

    /**
     * 是否固顶
     *
     * 参考定义: {"type":"boolean","description":"是否固顶"}
     *
     * @see 
     */
    @Schema(description = "是否固顶")
    boolean affixHeader() default false;

    /**
     * 顶部区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see String
     */
    @Schema(description = "顶部区域")
    String header() default "";

    /**
     * 底部区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see String
     */
    @Schema(description = "底部区域")
    String footer() default "";

    /**
     * 配置某项是否可以点选
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "配置某项是否可以点选")
    String itemCheckableOn() default "";

    /**
     * 配置某项是否可拖拽排序，前提是要开启拖拽功能
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "配置某项是否可拖拽排序，前提是要开启拖拽功能")
    String itemDraggableOn() default "";

    /**
     * 点击卡片的时候是否勾选卡片。
     *
     * 参考定义: {"type":"boolean","description":"点击卡片的时候是否勾选卡片。"}
     *
     * @see 
     */
    @Schema(description = "点击卡片的时候是否勾选卡片。")
    boolean checkOnItemClick() default false;

    /**
     * 是否为瀑布流布局？
     *
     * 参考定义: {"type":"boolean","description":"是否为瀑布流布局？"}
     *
     * @see 
     */
    @Schema(description = "是否为瀑布流布局？")
    boolean masonryLayout() default false;

    /**
     * 可以用来作为值的字段
     *
     * 参考定义: {"type":"string","description":"可以用来作为值的字段"}
     *
     * @see 
     */
    @Schema(description = "可以用来作为值的字段")
    String valueField() default "";

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
     * 指定内容区的展示模式。
     *
     * 参考定义: {"type":"string","description":"指定内容区的展示模式。","const":"cards"}
     *
     * @see 
     */
    @Schema(description = "指定内容区的展示模式。")
    String mode() default "";

    /**
     * 指定为 CRUD 渲染器。
     *
     * 参考定义: {"type":"string","const":"crud","description":"指定为 CRUD 渲染器。"}
     *
     * @see 
     */
    @Schema(description = "指定为 CRUD 渲染器。")
    String type() default "";

    /**
     * 初始化数据 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see String
     */
    @Schema(description = "初始化数据 API")
    String api() default "";

    /**
     * 批量操作
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"批量操作"}
     *
     * @see 
     */
    @Schema(description = "批量操作")
    String[] bulkActions() default {};

    /**
     * 单条操作
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"单条操作"}
     *
     * @see 
     */
    @Schema(description = "单条操作")
    String[] itemActions() default {};

    /**
     * 每页个数，默认为 10，如果不是请设置。
     *
     * 参考定义: {"type":"number","description":"每页个数，默认为 10，如果不是请设置。","default":10}
     *
     * @see 
     */
    @Schema(description = "每页个数，默认为 10，如果不是请设置。")
    double perPage() default 0;

    /**
     * 可以默认给定初始参数如： {\"\"perPage\"\": 24}
     *
     * 参考定义: "#/definitions/PlainObject"
     *
     * @see 
     */
    @Schema(description = "可以默认给定初始参数如： {\"\"perPage\"\": 24}")
    PlainObject defaultParams() ;

    /**
     * 是否可通过拖拽排序
     *
     * 参考定义: {"type":"boolean","description":"是否可通过拖拽排序"}
     *
     * @see 
     */
    @Schema(description = "是否可通过拖拽排序")
    boolean draggable() default false;

    /**
     * 是否可通过拖拽排序，通过表达式来配置
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "是否可通过拖拽排序，通过表达式来配置")
    String draggableOn() default "";

    /**
     * name
     *
     * 参考定义: "#/definitions/SchemaName"
     *
     * @see String
     */
    @Schema(description = "name")
    String name() default "";

    /**
     * 过滤器表单
     *
     * 参考定义: {"description":"过滤器表单"}
     *
     * @see 
     */
    @Schema(description = "过滤器表单")
    String filter() default "";

    /**
     * 初始是否拉取
     *
     * 参考定义: {"type":"boolean","description":"初始是否拉取"}
     *
     * @see 
     */
    @Schema(description = "初始是否拉取")
    boolean initFetch() default false;

    /**
     * 初始是否拉取，用表达式来配置。
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "初始是否拉取，用表达式来配置。")
    String initFetchOn() default "";

    /**
     * 配置内部 DOM 的 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see String
     */
    @Schema(description = "配置内部 DOM 的 className")
    String innerClassName() default "";

    /**
     * 设置自动刷新时间
     *
     * 参考定义: {"type":"number","description":"设置自动刷新时间"}
     *
     * @see 
     */
    @Schema(description = "设置自动刷新时间")
    double interval() default 0;

    /**
     * 设置用来确定位置的字段名，设置后新的顺序将被赋值到该字段中。
     *
     * 参考定义: {"type":"string","description":"设置用来确定位置的字段名，设置后新的顺序将被赋值到该字段中。"}
     *
     * @see 
     */
    @Schema(description = "设置用来确定位置的字段名，设置后新的顺序将被赋值到该字段中。")
    String orderField() default "";

    /**
     * 设置分页页码字段名。
     *
     * 参考定义: {"type":"string","description":"设置分页页码字段名。"}
     *
     * @see 
     */
    @Schema(description = "设置分页页码字段名。")
    String pageField() default "";

    /**
     * 设置分页一页显示的多少条数据的字段名。
     *
     * 参考定义: {"type":"string","description":"设置分页一页显示的多少条数据的字段名。"}
     *
     * @see 
     */
    @Schema(description = "设置分页一页显示的多少条数据的字段名。")
    String perPageField() default "";

    /**
     * 快速编辑后用来批量保存的 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see String
     */
    @Schema(description = "快速编辑后用来批量保存的 API")
    String quickSaveApi() default "";

    /**
     * 快速编辑配置成及时保存时使用的 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see String
     */
    @Schema(description = "快速编辑配置成及时保存时使用的 API")
    String quickSaveItemApi() default "";

    /**
     * 保存排序的 api
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see String
     */
    @Schema(description = "保存排序的 api")
    String saveOrderApi() default "";

    /**
     * 是否将过滤条件的参数同步到地址栏,默认为true
     *
     * 参考定义: {"type":"boolean","description":"是否将过滤条件的参数同步到地址栏,默认为true","default":true}
     *
     * @see 
     */
    @Schema(description = "是否将过滤条件的参数同步到地址栏,默认为true")
    boolean syncLocation() default false;

    /**
     * 顶部工具栏
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"allOf":[{"$ref":"#/definitions/SchemaObject","patternProperties":{"^(align)$":{}}},{"type":"object","additionalProperties":true,"properties":{"align":{"type":"string","enum":["left","right"],"description":"对齐方式"}}}]},{"type":"object","additionalProperties":false,"properties":{"align":{"type":"string","enum":["left","right"],"description":"对齐方式"},"className":{"$ref":"#/definitions/SchemaClassName","description":"容器 css 类名"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"type":{"$ref":"#/definitions/CRUDBultinToolbarType"}},"required":["type"]},{"$ref":"#/definitions/CRUDBultinToolbarType"}]},"description":"顶部工具栏"}
     *
     * @see 
     */
    @Schema(description = "顶部工具栏")
    String[] headerToolbar() default {};

    /**
     * 底部工具栏
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"allOf":[{"$ref":"#/definitions/SchemaObject","patternProperties":{"^(align)$":{}}},{"type":"object","additionalProperties":true,"properties":{"align":{"type":"string","enum":["left","right"],"description":"对齐方式"}}}]},{"type":"object","additionalProperties":false,"properties":{"align":{"type":"string","enum":["left","right"],"description":"对齐方式"},"className":{"$ref":"#/definitions/SchemaClassName","description":"容器 css 类名"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"type":{"$ref":"#/definitions/CRUDBultinToolbarType"}},"required":["type"]},{"$ref":"#/definitions/CRUDBultinToolbarType"}]},"description":"底部工具栏"}
     *
     * @see 
     */
    @Schema(description = "底部工具栏")
    String[] footerToolbar() default {};

    /**
     * 每页显示多少个空间成员的配置如： [10, 20, 50, 100]。
     *
     * 参考定义: {"type":"array","items":{"type":"number"},"description":"每页显示多少个空间成员的配置如： [10, 20, 50, 100]。"}
     *
     * @see 
     */
    @Schema(description = "每页显示多少个空间成员的配置如： [10, 20, 50, 100]。")
    String[] perPageAvailable() default {};

    /**
     * messages
     *
     * 参考定义: "#/definitions/SchemaMessage"
     *
     * @see 
     */
    @Schema(description = "messages")
    Message messages() ;

    /**
     * 是否隐藏快速编辑的按钮。
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏快速编辑的按钮。"}
     *
     * @see 
     */
    @Schema(description = "是否隐藏快速编辑的按钮。")
    boolean hideQuickSaveBtn() default false;

    /**
     * 是否自动跳顶部，当切分页的时候。
     *
     * 参考定义: {"type":"boolean","description":"是否自动跳顶部，当切分页的时候。"}
     *
     * @see 
     */
    @Schema(description = "是否自动跳顶部，当切分页的时候。")
    boolean autoJumpToTopOnPagerChange() default false;

    /**
     * 静默拉取
     *
     * 参考定义: {"type":"boolean","description":"静默拉取"}
     *
     * @see 
     */
    @Schema(description = "静默拉取")
    boolean silentPolling() default false;

    /**
     * stopAutoRefreshWhen
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see String
     */
    @Schema(description = "stopAutoRefreshWhen")
    String stopAutoRefreshWhen() default "";

    /**
     * stopAutoRefreshWhenModalIsOpen
     *
     * 参考定义: {"type":"boolean"}
     *
     * @see 
     */
    @Schema(description = "stopAutoRefreshWhenModalIsOpen")
    boolean stopAutoRefreshWhenModalIsOpen() default false;

    /**
     * filterTogglable
     *
     * 参考定义: {"type":"boolean"}
     *
     * @see 
     */
    @Schema(description = "filterTogglable")
    boolean filterTogglable() default false;

    /**
     * filterDefaultVisible
     *
     * 参考定义: {"type":"boolean"}
     *
     * @see 
     */
    @Schema(description = "filterDefaultVisible")
    boolean filterDefaultVisible() default false;

    /**
     * 是否将接口返回的内容自动同步到地址栏，前提是开启了同步地址栏。
     *
     * 参考定义: {"type":"boolean","description":"是否将接口返回的内容自动同步到地址栏，前提是开启了同步地址栏。"}
     *
     * @see 
     */
    @Schema(description = "是否将接口返回的内容自动同步到地址栏，前提是开启了同步地址栏。")
    boolean syncResponse2Query() default false;

    /**
     * 分页的时候是否保留用户选择。
     *
     * 参考定义: {"type":"boolean","description":"分页的时候是否保留用户选择。"}
     *
     * @see 
     */
    @Schema(description = "分页的时候是否保留用户选择。")
    boolean keepItemSelectionOnPageChange() default false;

    /**
     * 当配置 keepItemSelectionOnPageChange 时有用，用来配置已勾选项的文案。
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see String
     */
    @Schema(description = "当配置 keepItemSelectionOnPageChange 时有用，用来配置已勾选项的文案。")
    String labelTpl() default "";

    /**
     * 是否为前端单次加载模式，可以用来实现前端分页。
     *
     * 参考定义: {"type":"boolean","description":"是否为前端单次加载模式，可以用来实现前端分页。"}
     *
     * @see 
     */
    @Schema(description = "是否为前端单次加载模式，可以用来实现前端分页。")
    boolean loadDataOnce() default false;

    /**
     * 在开启loadDataOnce时，filter时是否去重新请求api
     *
     * 参考定义: {"type":"boolean","description":"在开启loadDataOnce时，filter时是否去重新请求api"}
     *
     * @see 
     */
    @Schema(description = "在开启loadDataOnce时，filter时是否去重新请求api")
    boolean loadDataOnceFetchOnFilter() default false;

    /**
     * 如果时内嵌模式，可以通过这个来配置默认的展开选项。
     *
     * 参考定义: {"type":"object","properties":{"expand":{"type":"string","enum":["first","all","none"],"description":"默认是展开第一个、所有、还是都不展开。"},"expandAll":{"type":"boolean","description":"是否显示全部切换按钮"},"accordion":{"type":"boolean","description":"是否为手风琴模式"}},"additionalProperties":false,"description":"如果时内嵌模式，可以通过这个来配置默认的展开选项。"}
     *
     * @see 
     */
    @Schema(description = "如果时内嵌模式，可以通过这个来配置默认的展开选项。")
    String expandConfig() default "";

    /**
     * 默认只有当分页数大于 1 是才显示，如果总是想显示请配置。
     *
     * 参考定义: {"type":"boolean","description":"默认只有当分页数大于 1 是才显示，如果总是想显示请配置。"}
     *
     * @see 
     */
    @Schema(description = "默认只有当分页数大于 1 是才显示，如果总是想显示请配置。")
    boolean alwaysShowPagination() default false;

    /**
     * 开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单
     *
     * 参考定义: {"type":"boolean","description":"开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单"}
     *
     * @see 
     */
    @Schema(description = "开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单")
    boolean autoGenerateFilter() default false;

    /**
     * 内容区域占满屏幕剩余空间
     *
     * 参考定义: {"type":"boolean","description":"内容区域占满屏幕剩余空间"}
     *
     * @see 
     */
    @Schema(description = "内容区域占满屏幕剩余空间")
    boolean autoFillHeight() default false;


}
