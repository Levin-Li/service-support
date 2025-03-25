package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Json
 *
 * JSON 数据展示控件。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/json
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "JSON 数据展示控件。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/json")
public @interface Json {
///////////////////////////////////////////

	//指定为Json展示类型
	enum Type{
		json,
		static_json,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//图标风格
	enum IconStyle{
		square,
		circle,
		triangle,
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
     * 指定为Json展示类型
     *
     * 参考定义: {"type":"string","enum":["json","static-json"],"description":"指定为Json展示类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为Json展示类型")
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
     * 要展示的 JSON 数据
     *
     * 参考定义: {"anyOf":[{"type":"object"},{"type":"array","items":{}}],"description":"要展示的 JSON 数据"}
     *
     * [{"type":"object"},{"type":"array","items":{}}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "要展示的 JSON 数据")
    String value() default "	";

    /**
     * 默认展开的级别
     *
     * 参考定义: {"type":"number","description":"默认展开的级别"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认展开的级别")
    double levelExpand() default 0;

    /**
     * 支持从数据链取值
     *
     * 参考定义: {"type":"string","description":"支持从数据链取值"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "支持从数据链取值")
    String source() default "	";

    /**
     * 是否可修改
     *
     * 参考定义: {"type":"boolean","description":"是否可修改"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可修改")
    boolean mutable() default false;

    /**
     * 是否显示数据类型
     *
     * 参考定义: {"type":"boolean","description":"是否显示数据类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示数据类型")
    boolean displayDataTypes() default false;

    /**
     * 是否可复制
     *
     * 参考定义: {"type":"boolean","description":"是否可复制"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可复制")
    boolean enableClipboard() default false;

    /**
     * 图标风格
     *
     * 参考定义: {"type":"string","enum":["square","circle","triangle"],"description":"图标风格"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "图标风格")
    IconStyle iconStyle() ;

    /**
     * 是否显示键的引号
     *
     * 参考定义: {"type":"boolean","description":"是否显示键的引号"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示键的引号")
    boolean quotesOnKeys() default false;

    /**
     * 是否为键排序
     *
     * 参考定义: {"type":"boolean","description":"是否为键排序"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否为键排序")
    boolean sortKeys() default false;

    /**
     * 设置字符串的最大展示长度，超出长度阈值的字符串将被截断，点击value可切换字符串展示方式，默认为false
     *
     * 参考定义: {"anyOf":[{"type":"number"},{"type":"boolean","const":false}],"description":"设置字符串的最大展示长度，超出长度阈值的字符串将被截断，点击value可切换字符串展示方式，默认为false"}
     *
     * [{"type":"number"},{"type":"boolean","const":false}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置字符串的最大展示长度，超出长度阈值的字符串将被截断，点击value可切换字符串展示方式，默认为false")
    String ellipsisThreshold() default "	";

}
