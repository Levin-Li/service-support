package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * TooltipWrapper
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "TooltipWrapper")
public @interface TooltipWrapper {
///////////////////////////////////////////

	//文字提示浮层出现位置，默认为top
	enum Placement{
		top,
		right,
		bottom,
		left,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//主题样式， 默认为light
	enum TooltipTheme{
		light,
		dark,
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
     * 内容区CSS类名
     *
     * 参考定义: {"type":"string","description":"内容区CSS类名"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "内容区CSS类名")
    String className() default "	";

    /**
     * 是否禁用提示
     *
     * 参考定义: {"type":"boolean","description":"是否禁用提示"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否禁用提示")
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
     * 内容区自定义样式
     *
     * 参考定义: {"type":"object","description":"内容区自定义样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "内容区自定义样式")
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
     * 文字提示容器
     *
     * 参考定义: {"type":"string","const":"tooltip-wrapper","description":"文字提示容器"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "文字提示容器")
    String type() default "tooltip-wrapper";

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
     * 文字提示标题
     *
     * 参考定义: {"type":"string","description":"文字提示标题"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "文字提示标题")
    String title() default "	";

    /**
     * 文字提示内容，兼容 tooltip，但建议通过 content 来实现提示内容
     *
     * 参考定义: {"type":"string","description":"文字提示内容，兼容 tooltip，但建议通过 content 来实现提示内容"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "文字提示内容，兼容 tooltip，但建议通过 content 来实现提示内容")
    String content() default "	";

    /**
     * tooltip
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "tooltip")
    String tooltip() default "	";

    /**
     * 文字提示浮层出现位置，默认为top
     *
     * 参考定义: {"type":"string","enum":["top","right","bottom","left"],"description":"文字提示浮层出现位置，默认为top"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "文字提示浮层出现位置，默认为top")
    Placement placement() ;

    /**
     * 浮层位置相对偏移量
     *
     * 参考定义: {"type":"array","items":{"type":"number"},"minItems":2,"maxItems":2,"description":"浮层位置相对偏移量"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "浮层位置相对偏移量")
    double[] offset() default 0;

    /**
     * 是否展示浮层指向箭头
     *
     * 参考定义: {"type":"boolean","description":"是否展示浮层指向箭头"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否展示浮层指向箭头")
    boolean showArrow() default false;

    /**
     * 浮层触发方式，默认为hover
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/Trigger"},{"type":"array","items":{"$ref":"#/definitions/Trigger"}}],"description":"浮层触发方式，默认为hover"}
     *
     * [{"$ref":"#/definitions/Trigger"},{"type":"array","items":{"$ref":"#/definitions/Trigger"}}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "浮层触发方式，默认为hover")
    String trigger() default "	";

    /**
     * 浮层延迟显示时间, 单位 ms
     *
     * 参考定义: {"type":"number","description":"浮层延迟显示时间, 单位 ms"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "浮层延迟显示时间, 单位 ms")
    double mouseEnterDelay() default 0;

    /**
     * 浮层延迟隐藏时间, 单位 ms
     *
     * 参考定义: {"type":"number","description":"浮层延迟隐藏时间, 单位 ms"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "浮层延迟隐藏时间, 单位 ms")
    double mouseLeaveDelay() default 0;

    /**
     * 是否点击非内容区域关闭提示，默认为true
     *
     * 参考定义: {"type":"boolean","description":"是否点击非内容区域关闭提示，默认为true"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否点击非内容区域关闭提示，默认为true")
    boolean rootClose() default false;

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
     * 内容区包裹标签
     *
     * 参考定义: {"type":"string","description":"内容区包裹标签"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "内容区包裹标签")
    String wrapperComponent() default "	";

    /**
     * 内容区是否内联显示，默认为false
     *
     * 参考定义: {"type":"boolean","description":"内容区是否内联显示，默认为false"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "内容区是否内联显示，默认为false")
    boolean inline() default false;

    /**
     * 主题样式， 默认为light
     *
     * 参考定义: {"type":"string","enum":["light","dark"],"description":"主题样式， 默认为light"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "主题样式， 默认为light")
    TooltipTheme tooltipTheme() ;

    /**
     * 是否可以移入浮层中, 默认true
     *
     * 参考定义: {"type":"boolean","description":"是否可以移入浮层中, 默认true"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可以移入浮层中, 默认true")
    boolean enterable() default false;

    /**
     * 自定义提示浮层样式
     *
     * 参考定义: {"type":"object","description":"自定义提示浮层样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "自定义提示浮层样式")
    String tooltipStyle() default "	";

    /**
     * 文字提示浮层CSS类名
     *
     * 参考定义: {"type":"string","description":"文字提示浮层CSS类名"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "文字提示浮层CSS类名")
    String tooltipClassName() default "	";

}
