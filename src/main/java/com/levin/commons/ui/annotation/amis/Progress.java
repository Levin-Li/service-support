package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Progress
 *
 * 进度展示控件。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/progress
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "进度展示控件。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/progress")
public @interface Progress {
///////////////////////////////////////////

	//进度条类型
	enum Mode{
		line,
		circle,
		dashboard,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//仪表盘进度条缺口位置
	enum GapPosition{
		top,
		bottom,
		left,
		right,
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
     * type
     *
     * 参考定义: {"type":"string","const":"progress"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "type")
    String type() default "progress";

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
     * 关联字段名
     *
     * 参考定义: {"type":"string","description":"关联字段名"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "关联字段名")
    String name() default "	";

    /**
     * 进度值
     *
     * 参考定义: {"type":"number","description":"进度值"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "进度值")
    double value() default 0;

    /**
     * 进度条类型
     *
     * 参考定义: {"type":"string","enum":["line","circle","dashboard"],"description":"进度条类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "进度条类型")
    Mode mode() ;

    /**
     * 进度条 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "进度条 CSS 类名")
    String progressClassName() default "	";

    /**
     * 配置不同的值段，用不同的样式提示用户
     *
     * 参考定义: "#/definitions/ColorMapType"
     *
     * 
     *
     * [{"type":"array","items":{"type":"string"}},{"type":"array","items":{"type":"object","properties":{"value":{"type":"number"},"color":{"type":"string"}},"required":["value","color"],"additionalProperties":false}},{"type":"string"}]
     *
     * @see ColorMapType
     */
    
    @Schema(title = "配置不同的值段，用不同的样式提示用户")
    String map() default "	";

    /**
     * 是否显示值
     *
     * 参考定义: {"type":"boolean","description":"是否显示值"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示值")
    boolean showLabel() default false;

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "占位符")
    String placeholder() default "	";

    /**
     * 是否显示背景间隔
     *
     * 参考定义: {"type":"boolean","description":"是否显示背景间隔"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示背景间隔")
    boolean stripe() default false;

    /**
     * 是否显示动画（只有在开启的时候才能看出来）
     *
     * 参考定义: {"type":"boolean","description":"是否显示动画（只有在开启的时候才能看出来）"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示动画（只有在开启的时候才能看出来）")
    boolean animate() default false;

    /**
     * 进度条线的宽度
     *
     * 参考定义: {"type":"number","description":"进度条线的宽度"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "进度条线的宽度")
    double strokeWidth() default 0;

    /**
     * 仪表盘进度条缺口角度，可取值 0 ~ 295
     *
     * 参考定义: {"type":"number","description":"仪表盘进度条缺口角度，可取值 0 ~ 295"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "仪表盘进度条缺口角度，可取值 0 ~ 295")
    double gapDegree() default 0;

    /**
     * 仪表盘进度条缺口位置
     *
     * 参考定义: {"type":"string","enum":["top","bottom","left","right"],"description":"仪表盘进度条缺口位置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "仪表盘进度条缺口位置")
    GapPosition gapPosition() ;

    /**
     * 内容的模板函数
     *
     * 参考定义: {"type":"string","description":"内容的模板函数"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "内容的模板函数")
    String valueTpl() default "	";

    /**
     * 阈值
     *
     * 参考定义: {"anyOf":[{"type":"object","properties":{"value":{"$ref":"#/definitions/SchemaTpl"},"color":{"type":"string"}},"required":["value"],"additionalProperties":false},{"type":"array","items":{"type":"object","properties":{"value":{"$ref":"#/definitions/SchemaTpl"},"color":{"type":"string"}},"required":["value"],"additionalProperties":false}}],"description":"阈值"}
     *
     * [{"type":"object","properties":{"value":{"$ref":"#/definitions/SchemaTpl"},"color":{"type":"string"}},"required":["value"],"additionalProperties":false},{"type":"array","items":{"type":"object","properties":{"value":{"$ref":"#/definitions/SchemaTpl"},"color":{"type":"string"}},"required":["value"],"additionalProperties":false}}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "阈值")
    String threshold() default "	";

    /**
     * 是否显示阈值数值
     *
     * 参考定义: {"type":"boolean","description":"是否显示阈值数值"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示阈值数值")
    boolean showThresholdText() default false;

}
