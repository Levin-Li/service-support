package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Form
 *
 * Form 表单渲染器。nn说明：https://aisuda.bce.baidu.com/amis/zh-CN/components/form/index
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Form 表单渲染器。nn说明：https://aisuda.bce.baidu.com/amis/zh-CN/components/form/index")
public @interface Form {
///////////////////////////////////////////

	//配置表单项默认的展示方式。
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
     * 展示态时的className
     *
     * 参考定义: {}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "展示态时的className")
    String _static() default "	";

    /**
     * staticOn
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "staticOn")
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
     * staticClassName
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "staticClassName")
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
     * 指定为表单渲染器。
     *
     * 参考定义: {"type":"string","const":"form","description":"指定为表单渲染器。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为表单渲染器。")
    String type() default "form";

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
     * 表单标题
     *
     * 参考定义: {"type":"string","description":"表单标题"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表单标题")
    String title() default "	";

    /**
     * 按钮集合，会固定在底部显示。
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * 
     *
     * 
     *
     * @see Action
     */
    
    @Schema(title = "按钮集合，会固定在底部显示。")
    String[] actions() default "	";

    /**
     * 表单项集合
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(title = "表单项集合")
    String body() default "	";

    /**
     * tabs
     *
     * 参考定义: {}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "tabs")
    String tabs() default "	";

    /**
     * fieldSet
     *
     * 参考定义: {}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "fieldSet")
    String fieldSet() default "	";

    /**
     * data
     *
     * 参考定义: {}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "data")
    String data() default "	";

    /**
     * 是否开启调试，开启后会在顶部实时显示表单项数据。
     *
     * 参考定义: {"type":"boolean","description":"是否开启调试，开启后会在顶部实时显示表单项数据。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否开启调试，开启后会在顶部实时显示表单项数据。")
    boolean debug() default false;

    /**
     * Debug面板配置
     *
     * 参考定义: {"type":"object","properties":{"levelExpand":{"type":"number","description":"默认展开的级别"},"enableClipboard":{"type":"boolean","description":"是否可复制"},"iconStyle":{"type":"string","enum":["square","circle","triangle"],"description":"图标风格"},"quotesOnKeys":{"type":"boolean","description":"是否显示键的引号"},"sortKeys":{"type":"boolean","description":"是否为键排序"},"ellipsisThreshold":{"anyOf":[{"type":"number"},{"type":"boolean","const":false}],"description":"设置字符串的最大展示长度，超出长度阈值的字符串将被截断，点击value可切换字符串展示方式，默认为120"}},"additionalProperties":false,"description":"Debug面板配置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "Debug面板配置")
    String debugConfig() default "	";

    /**
     * 用来初始化表单数据
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/BaseApiObject"}],"description":"用来初始化表单数据"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/BaseApiObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "用来初始化表单数据")
    String initApi() default "	";

    /**
     * Form 用来获取初始数据的 api,与initApi不同的是，会一直轮询请求该接口，直到返回 finished 属性为 true 才 结束。
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/BaseApiObject"}],"description":"Form 用来获取初始数据的 api,与initApi不同的是，会一直轮询请求该接口，直到返回 finished 属性为 true 才 结束。"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/BaseApiObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "Form 用来获取初始数据的 api,与initApi不同的是，会一直轮询请求该接口，直到返回 finished 属性为 true 才 结束。")
    String initAsyncApi() default "	";

    /**
     * 设置了initAsyncApi后，默认会从返回数据的data.finished来判断是否完成，也可以设置成其他的xxx，就会从data.xxx中获取
     *
     * 参考定义: {"type":"string","description":"设置了initAsyncApi后，默认会从返回数据的data.finished来判断是否完成，也可以设置成其他的xxx，就会从data.xxx中获取"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置了initAsyncApi后，默认会从返回数据的data.finished来判断是否完成，也可以设置成其他的xxx，就会从data.xxx中获取")
    String initFinishedField() default "	";

    /**
     * 设置了initAsyncApi以后，默认拉取的时间间隔
     *
     * 参考定义: {"type":"number","description":"设置了initAsyncApi以后，默认拉取的时间间隔"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置了initAsyncApi以后，默认拉取的时间间隔")
    double initCheckInterval() default 0;

    /**
     * 是否初始加载
     *
     * 参考定义: {"type":"boolean","description":"是否初始加载"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否初始加载")
    boolean initFetch() default false;

    /**
     * 建议改成 api 的 sendOn 属性。
     *
     * 参考定义: {"type":"string","description":"建议改成 api 的 sendOn 属性。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "建议改成 api 的 sendOn 属性。")
    String initFetchOn() default "	";

    /**
     * 设置后将轮询调用 initApi
     *
     * 参考定义: {"type":"number","description":"设置后将轮询调用 initApi"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置后将轮询调用 initApi")
    double interval() default 0;

    /**
     * 是否静默拉取
     *
     * 参考定义: {"type":"boolean","description":"是否静默拉取"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否静默拉取")
    boolean silentPolling() default false;

    /**
     * 配置停止轮询的条件
     *
     * 参考定义: {"type":"string","description":"配置停止轮询的条件"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "配置停止轮询的条件")
    String stopAutoRefreshWhen() default "	";

    /**
     * 是否开启本地缓存
     *
     * 参考定义: {"type":"string","description":"是否开启本地缓存"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否开启本地缓存")
    String persistData() default "	";

    /**
     * 开启本地缓存后限制保存哪些 key
     *
     * 参考定义: {"type":"array","items":{"type":"string"},"description":"开启本地缓存后限制保存哪些 key"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "开启本地缓存后限制保存哪些 key")
    String[] persistDataKeys() default "	";

    /**
     * 提交成功后清空本地缓存
     *
     * 参考定义: {"type":"boolean","description":"提交成功后清空本地缓存"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "提交成功后清空本地缓存")
    boolean clearPersistDataAfterSubmit() default false;

    /**
     * Form 用来保存数据的 api。\n\n详情：https://aisuda.bce.baidu.com/amis/zh-CN/components/form/index#%E8%A1%A8%E5%8D%95%E6%8F%90%E4%BA%A4
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/BaseApiObject"}],"description":"Form 用来保存数据的 api。\n\n详情：https://aisuda.bce.baidu.com/amis/zh-CN/components/form/index#%E8%A1%A8%E5%8D%95%E6%8F%90%E4%BA%A4"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/BaseApiObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "Form 用来保存数据的 api。\n\n详情：https://aisuda.bce.baidu.com/amis/zh-CN/components/form/index#%E8%A1%A8%E5%8D%95%E6%8F%90%E4%BA%A4")
    String api() default "	";

    /**
     * Form 也可以配置 feedback。
     *
     * 参考定义: {"description":"Form 也可以配置 feedback。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "Form 也可以配置 feedback。")
    String feedback() default "	";

    /**
     * 设置此属性后，表单提交发送保存接口后，还会继续轮询请求该接口，直到返回 finished 属性为 true 才 结束。
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/BaseApiObject"}],"description":"设置此属性后，表单提交发送保存接口后，还会继续轮询请求该接口，直到返回 finished 属性为 true 才 结束。"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/BaseApiObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置此属性后，表单提交发送保存接口后，还会继续轮询请求该接口，直到返回 finished 属性为 true 才 结束。")
    String asyncApi() default "	";

    /**
     * 轮询请求的时间间隔，默认为 3秒。设置 asyncApi 才有效
     *
     * 参考定义: {"type":"number","description":"轮询请求的时间间隔，默认为 3秒。设置 asyncApi 才有效"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "轮询请求的时间间隔，默认为 3秒。设置 asyncApi 才有效")
    double checkInterval() default 0;

    /**
     * 如果决定结束的字段名不是 `finished` 请设置此属性，比如 `is_success`
     *
     * 参考定义: {"type":"string","description":"如果决定结束的字段名不是 `finished` 请设置此属性，比如 `is_success`"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "如果决定结束的字段名不是 `finished` 请设置此属性，比如 `is_success`")
    String finishedField() default "	";

    /**
     * 提交完后重置表单
     *
     * 参考定义: {"type":"boolean","description":"提交完后重置表单"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "提交完后重置表单")
    boolean resetAfterSubmit() default false;

    /**
     * 提交后清空表单
     *
     * 参考定义: {"type":"boolean","description":"提交后清空表单"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "提交后清空表单")
    boolean clearAfterSubmit() default false;

    /**
     * 配置表单项默认的展示方式。
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置表单项默认的展示方式。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "配置表单项默认的展示方式。")
    Mode mode() ;

    /**
     * 表单项显示为几列
     *
     * 参考定义: {"type":"number","description":"表单项显示为几列"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表单项显示为几列")
    double columnCount() default 0;

    /**
     * 如果是水平排版，这个属性可以细化水平排版的左右宽度占比。
     *
     * 参考定义: "#/definitions/FormHorizontal"
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
     * 是否自动将第一个表单元素聚焦。
     *
     * 参考定义: {"type":"boolean","description":"是否自动将第一个表单元素聚焦。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否自动将第一个表单元素聚焦。")
    boolean autoFocus() default false;

    /**
     * 消息文案配置，记住这个优先级是最低的，如果你的接口返回了 msg，接口返回的优先。
     *
     * 参考定义: {"type":"object","properties":{"validateFailed":{"type":"string","description":"表单验证失败时的提示"}},"additionalProperties":false,"description":"消息文案配置，记住这个优先级是最低的，如果你的接口返回了 msg，接口返回的优先。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "消息文案配置，记住这个优先级是最低的，如果你的接口返回了 msg，接口返回的优先。")
    String messages() default "	";

    /**
     * name
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "name")
    String name() default "	";

    /**
     * 配置容器 panel className
     *
     * 参考定义: "#/definitions/ClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "配置容器 panel className")
    String panelClassName() default "	";

    /**
     * 设置主键 id, 当设置后，检测表单是否完成时（asyncApi），只会携带此数据。
     *
     * 参考定义: {"type":"string","description":"设置主键 id, 当设置后，检测表单是否完成时（asyncApi），只会携带此数据。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置主键 id, 当设置后，检测表单是否完成时（asyncApi），只会携带此数据。")
    String primaryField() default "	";

    /**
     * redirect
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "redirect")
    String redirect() default "	";

    /**
     * reload
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "reload")
    String reload() default "	";

    /**
     * 修改的时候是否直接提交表单。
     *
     * 参考定义: {"type":"boolean","description":"修改的时候是否直接提交表单。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "修改的时候是否直接提交表单。")
    boolean submitOnChange() default false;

    /**
     * 表单初始先提交一次，联动的时候有用
     *
     * 参考定义: {"type":"boolean","description":"表单初始先提交一次，联动的时候有用"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表单初始先提交一次，联动的时候有用")
    boolean submitOnInit() default false;

    /**
     * 默认的提交按钮名称，如果设置成空，则可以把默认按钮去掉。
     *
     * 参考定义: {"type":"string","description":"默认的提交按钮名称，如果设置成空，则可以把默认按钮去掉。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认的提交按钮名称，如果设置成空，则可以把默认按钮去掉。")
    String submitText() default "	";

    /**
     * 默认表单提交自己会通过发送 api 保存数据，但是也可以设定另外一个 form 的 name 值，或者另外一个 `CRUD` 模型的 name 值。 如果 target 目标是一个 `Form` ，则目标 `Form` 会重新触发 `initApi` 和 `schemaApi`，api 可以拿到当前 form 数据。如果目标是一个 `CRUD` 模型，则目标模型会重新触发搜索，参数为当前 Form 数据。
     *
     * 参考定义: {"type":"string","description":"默认表单提交自己会通过发送 api 保存数据，但是也可以设定另外一个 form 的 name 值，或者另外一个 `CRUD` 模型的 name 值。 如果 target 目标是一个 `Form` ，则目标 `Form` 会重新触发 `initApi` 和 `schemaApi`，api 可以拿到当前 form 数据。如果目标是一个 `CRUD` 模型，则目标模型会重新触发搜索，参数为当前 Form 数据。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认表单提交自己会通过发送 api 保存数据，但是也可以设定另外一个 form 的 name 值，或者另外一个 `CRUD` 模型的 name 值。 如果 target 目标是一个 `Form` ，则目标 `Form` 会重新触发 `initApi` 和 `schemaApi`，api 可以拿到当前 form 数据。如果目标是一个 `CRUD` 模型，则目标模型会重新触发搜索，参数为当前 Form 数据。")
    String target() default "	";

    /**
     * 是否用 panel 包裹起来
     *
     * 参考定义: {"type":"boolean","description":"是否用 panel 包裹起来"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否用 panel 包裹起来")
    boolean wrapWithPanel() default false;

    /**
     * 是否固定底下的按钮在底部。
     *
     * 参考定义: {"type":"boolean","description":"是否固定底下的按钮在底部。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否固定底下的按钮在底部。")
    boolean affixFooter() default false;

    /**
     * 页面离开提示，为了防止页面不小心跳转而导致表单没有保存。
     *
     * 参考定义: {"type":"boolean","description":"页面离开提示，为了防止页面不小心跳转而导致表单没有保存。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "页面离开提示，为了防止页面不小心跳转而导致表单没有保存。")
    boolean promptPageLeave() default false;

    /**
     * 具体的提示信息，选填。
     *
     * 参考定义: {"type":"string","description":"具体的提示信息，选填。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "具体的提示信息，选填。")
    String promptPageLeaveMessage() default "	";

    /**
     * 组合校验规则，选填
     *
     * 参考定义: {"type":"array","items":{"type":"object","properties":{"rule":{"type":"string"},"message":{"type":"string"},"name":{"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}]}},"required":["rule","message"],"additionalProperties":false},"description":"组合校验规则，选填"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "组合校验规则，选填")
    String[] rules() default "	";

    /**
     * 禁用回车提交
     *
     * 参考定义: {"type":"boolean","description":"禁用回车提交"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "禁用回车提交")
    boolean preventEnterSubmit() default false;

    /**
     * 表单label的对齐方式
     *
     * 参考定义: "#/definitions/LabelAlign"
     *
     * 
     *
     * 
     *
     * @see LabelAlign
     */
    
    @Schema(title = "表单label的对齐方式")
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

}
