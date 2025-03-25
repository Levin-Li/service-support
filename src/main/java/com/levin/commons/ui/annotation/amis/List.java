package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * List
 *
 * List 列表展示控件。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/card
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "List 列表展示控件。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/card")
public @interface List {
///////////////////////////////////////////

	//指定为 List 列表展示控件。
	enum Type{
		list,
		static_list,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//大小
	enum Size{
		sm,
		base,
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
     * 指定为 List 列表展示控件。
     *
     * 参考定义: {"type":"string","enum":["list","static-list"],"description":"指定为 List 列表展示控件。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为 List 列表展示控件。")
    Type type() ;

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
     * 数据源: 绑定当前环境变量
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * 
     *
     * 
     *
     * @see TokenizeableString
     */
    
    @Schema(title = "数据源: 绑定当前环境变量")
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

}
