package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Page
 *
 * amis Page 渲染器。详情请见：https://baidu.gitee.io/amis/docs/components/page
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:01
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "amis Page 渲染器。详情请见：https://baidu.gitee.io/amis/docs/components/page")
public @interface Page {
///////////////////////////////////////////

	//默认不设置自动感觉内容来决定要不要展示这些区域 如果配置了，以配置为主。
	enum Region{
		aside,
		body,
		toolbar,
		header,
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
     * 指定为 page 渲染器。
     *
     * 参考定义: {"type":"string","const":"page","description":"指定为 page 渲染器。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "指定为 page 渲染器。")
    String type() default "page";

    /**
     * 配置容器 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "配置容器 className")
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
    
    @Schema(description = "是否禁用")
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
    
    @Schema(description = "是否禁用表达式")
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
    
    @Schema(description = "是否隐藏")
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
    
    @Schema(description = "是否隐藏表达式")
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
    
    @Schema(description = "是否显示")
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
    
    @Schema(description = "是否显示表达式")
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
    
    @Schema(description = "组件唯一 id，主要用于日志采集")
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
    
    @Schema(description = "事件动作配置")
    String onEvent() default "	";

    /**
     * 页面标题
     *
     * 参考定义: {"type":"string","description":"页面标题"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "页面标题")
    String title() default "	";

    /**
     * 页面副标题
     *
     * 参考定义: {"type":"string","description":"页面副标题"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "页面副标题")
    String subTitle() default "	";

    /**
     * 页面描述, 标题旁边会出现个小图标，放上去会显示这个属性配置的内容。
     *
     * 参考定义: "#/definitions/SchemaRemark"
     *
     * 
     *
     * 
     *
     * @see Remark
     */
    
    @Schema(description = "页面描述, 标题旁边会出现个小图标，放上去会显示这个属性配置的内容。")
    Remark remark() ;

    /**
     * 内容区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(description = "内容区域")
    String body() default "	";

    /**
     * 内容区 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "内容区 css 类名")
    String bodyClassName() default "	";

    /**
     * 边栏区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(description = "边栏区域")
    String aside() default "	";

    /**
     * 边栏是否允许拖动
     *
     * 参考定义: {"type":"boolean","description":"边栏是否允许拖动"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "边栏是否允许拖动")
    boolean asideResizor() default false;

    /**
     * 边栏内容是否粘住，即不跟随滚动。
     *
     * 参考定义: {"type":"boolean","description":"边栏内容是否粘住，即不跟随滚动。","default":true}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "边栏内容是否粘住，即不跟随滚动。")
    boolean asideSticky() default false;

    /**
     * 边栏最小宽度
     *
     * 参考定义: {"type":"number","description":"边栏最小宽度"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "边栏最小宽度")
    double asideMinWidth() default 0;

    /**
     * 边栏最小宽度
     *
     * 参考定义: {"type":"number","description":"边栏最小宽度"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "边栏最小宽度")
    double asideMaxWidth() default 0;

    /**
     * 边栏区 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "边栏区 css 类名")
    String asideClassName() default "	";

    /**
     * 自定义页面级别样式表
     *
     * 参考定义: {"type":"object","additionalProperties":{"type":"object","additionalProperties":{"type":"string"},"description":"样式属性名及值"},"description":"自定义页面级别样式表"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "自定义页面级别样式表")
    String css() default "	";

    /**
     * 移动端下的样式表
     *
     * 参考定义: {"type":"object","additionalProperties":{"type":"object","additionalProperties":{"type":"string"},"description":"样式属性名及值"},"description":"移动端下的样式表"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "移动端下的样式表")
    String mobileCSS() default "	";

    /**
     * 页面级别的初始数据
     *
     * 参考定义: "#/definitions/SchemaDefaultData"
     *
     * 
     *
     * 
     *
     * @see DefaultData
     */
    
    @Schema(description = "页面级别的初始数据")
    String data() default "	";

    /**
     * 配置 header 容器 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "配置 header 容器 className")
    String headerClassName() default "	";

    /**
     * 页面初始化的时候，可以设置一个 API 让其取拉取，发送数据会携带当前 data 数据（包含地址栏参数），获取得数据会合并到 data 中，供组件内使用。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(description = "页面初始化的时候，可以设置一个 API 让其取拉取，发送数据会携带当前 data 数据（包含地址栏参数），获取得数据会合并到 data 中，供组件内使用。")
    String initApi() default "	";

    /**
     * 是否默认就拉取？
     *
     * 参考定义: {"type":"boolean","description":"是否默认就拉取？"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否默认就拉取？")
    boolean initFetch() default false;

    /**
     * 是否默认就拉取表达式
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(description = "是否默认就拉取表达式")
    String initFetchOn() default "	";

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
    
    @Schema(description = "messages")
    Message messages() ;

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
    
    @Schema(description = "name")
    String name() default "	";

    /**
     * 页面顶部区域，当存在 title 时在右上角显示。
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(description = "页面顶部区域，当存在 title 时在右上角显示。")
    String toolbar() default "	";

    /**
     * 配置 toolbar 容器 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "配置 toolbar 容器 className")
    String toolbarClassName() default "	";

    /**
     * definitions
     *
     * 参考定义: {}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "definitions")
    String definitions() default "	";

    /**
     * 配置轮询间隔，配置后 initApi 将轮询加载。
     *
     * 参考定义: {"type":"number","description":"配置轮询间隔，配置后 initApi 将轮询加载。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "配置轮询间隔，配置后 initApi 将轮询加载。")
    double interval() default 0;

    /**
     * 是否要静默加载，也就是说不显示进度
     *
     * 参考定义: {"type":"boolean","description":"是否要静默加载，也就是说不显示进度"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否要静默加载，也就是说不显示进度")
    boolean silentPolling() default false;

    /**
     * 配置停止轮询的条件。
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(description = "配置停止轮询的条件。")
    String stopAutoRefreshWhen() default "	";

    /**
     * 是否显示错误信息，默认是显示的。
     *
     * 参考定义: {"type":"boolean","description":"是否显示错误信息，默认是显示的。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "是否显示错误信息，默认是显示的。")
    boolean showErrorMsg() default false;

    /**
     * css 变量
     *
     * 参考定义: {"description":"css 变量"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "css 变量")
    String cssVars() default "	";

    /**
     * 默认不设置自动感觉内容来决定要不要展示这些区域 如果配置了，以配置为主。
     *
     * 参考定义: {"type":"array","items":{"type":"string","enum":["aside","body","toolbar","header"]},"description":"默认不设置自动感觉内容来决定要不要展示这些区域 如果配置了，以配置为主。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "默认不设置自动感觉内容来决定要不要展示这些区域 如果配置了，以配置为主。")
    Region[] regions() ;

    /**
     * 自定义样式
     *
     * 参考定义: {"type":"object","description":"自定义样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "自定义样式")
    String style() default "	";

    /**
     * 下拉刷新配置
     *
     * 参考定义: {"type":"object","properties":{"disabled":{"type":"boolean"},"pullingText":{"type":"string"},"loosingText":{"type":"string"}},"additionalProperties":false,"description":"下拉刷新配置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "下拉刷新配置")
    String pullRefresh() default "	";

}
