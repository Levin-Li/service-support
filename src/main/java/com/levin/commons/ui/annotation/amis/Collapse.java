package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Collapse
 *
 * Collapse 折叠渲染器，格式说明。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/collapse
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Collapse 折叠渲染器，格式说明。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/collapse")
public @interface Collapse {
///////////////////////////////////////////

	//标题展示位置
	enum HeaderPosition{
		top,
		bottom,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//控件大小
	enum Size{
		xs,
		sm,
		md,
		lg,
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
     * 指定为折叠器类型
     *
     * 参考定义: {"type":"string","const":"collapse","description":"指定为折叠器类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为折叠器类型")
    String type() default "collapse";

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
     * 标识
     *
     * 参考定义: {"type":"string","description":"标识"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "标识")
    String key() default "	";

    /**
     * 标题展示位置
     *
     * 参考定义: {"type":"string","enum":["top","bottom"],"description":"标题展示位置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "标题展示位置")
    HeaderPosition headerPosition() ;

    /**
     * 标题
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/SchemaCollection"}],"description":"标题"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaCollection"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "标题")
    String header() default "	";

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
    
    @Schema(title = "内容区域")
    String body() default "	";

    /**
     * 配置 Body 容器 className
     *
     * 参考定义: {"type":"string","description":"配置 Body 容器 className"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "配置 Body 容器 className")
    String bodyClassName() default "	";

    /**
     * 是否可折叠
     *
     * 参考定义: {"type":"boolean","description":"是否可折叠"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可折叠")
    boolean collapsable() default false;

    /**
     * 默认是否折叠
     *
     * 参考定义: {"type":"boolean","description":"默认是否折叠"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认是否折叠")
    boolean collapsed() default false;

    /**
     * 图标是否展示
     *
     * 参考定义: {"type":"boolean","description":"图标是否展示"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "图标是否展示")
    boolean showArrow() default false;

    /**
     * 自定义切换图标
     *
     * 参考定义: "#/definitions/SchemaObject"
     *
     * 
     *
     * 
     *
     * @see Object
     */
    
    @Schema(title = "自定义切换图标")
    String expandIcon() default "	";

    /**
     * 标题 CSS 类名
     *
     * 参考定义: {"type":"string","description":"标题 CSS 类名"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "标题 CSS 类名")
    String headingClassName() default "	";

    /**
     * 收起的标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * 
     *
     * 
     *
     * @see Tpl
     */
    
    @Schema(title = "收起的标题")
    Tpl collapseHeader() ;

    /**
     * 控件大小
     *
     * 参考定义: {"type":"string","enum":["xs","sm","md","lg","base"],"description":"控件大小"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "控件大小")
    Size size() ;

    /**
     * 点开时才加载内容
     *
     * 参考定义: {"type":"boolean","description":"点开时才加载内容"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "点开时才加载内容")
    boolean mountOnEnter() default false;

    /**
     * 卡片隐藏就销毁内容。
     *
     * 参考定义: {"type":"boolean","description":"卡片隐藏就销毁内容。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "卡片隐藏就销毁内容。")
    boolean unmountOnExit() default false;

    /**
     * 标题内容分割线
     *
     * 参考定义: {"type":"boolean","description":"标题内容分割线"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "标题内容分割线")
    boolean divideLine() default false;

}
