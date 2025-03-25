package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Tabs
 *
 * 选项卡控件。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/tabs
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "选项卡控件。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/tabs")
public @interface Tabs {
///////////////////////////////////////////

	//配置子表单项默认的展示方式。
	enum SubFormMode{
		normal,
		inline,
		horizontal,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//编辑器模式，侧边的位置
	enum SidePosition{
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
     * type
     *
     * 参考定义: {"type":"string","const":"tabs"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "type")
    String type() default "tabs";

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
     * 选项卡成员。当配置了 source 时，选项卡成员，将会根据目标数据进行重复。
     *
     * 参考定义: "#/definitions/TabSchema"
     *
     * 
     *
     * 
     *
     * @see Tab
     */
    
    @Schema(title = "选项卡成员。当配置了 source 时，选项卡成员，将会根据目标数据进行重复。")
    Tab[] tabs() ;

    /**
     * 关联已有数据，选项卡直接根据目标数据重复。
     *
     * 参考定义: {"type":"string","description":"关联已有数据，选项卡直接根据目标数据重复。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "关联已有数据，选项卡直接根据目标数据重复。")
    String source() default "	";

    /**
     * 展示形式
     *
     * 参考定义: "#/definitions/TabsMode"
     *
     * 
     *
     * 
     *
     * @see TabsMode
     */
    
    @Schema(title = "展示形式")
    String tabsMode() default "	";

    /**
     * 内容类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "内容类名")
    String contentClassName() default "	";

    /**
     * 链接外层类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "链接外层类名")
    String linksClassName() default "	";

    /**
     * 卡片是否只有在点开的时候加载？
     *
     * 参考定义: {"type":"boolean","description":"卡片是否只有在点开的时候加载？"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "卡片是否只有在点开的时候加载？")
    boolean mountOnEnter() default false;

    /**
     * 卡片隐藏的时候是否销毁卡片内容
     *
     * 参考定义: {"type":"boolean","description":"卡片隐藏的时候是否销毁卡片内容"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "卡片隐藏的时候是否销毁卡片内容")
    boolean unmountOnExit() default false;

    /**
     * 可以在右侧配置点其他功能按钮。
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * 
     *
     * 
     *
     * @see Action
     */
    
    @Schema(title = "可以在右侧配置点其他功能按钮。")
    String toolbar() default "	";

    /**
     * 配置子表单项默认的展示方式。
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置子表单项默认的展示方式。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "配置子表单项默认的展示方式。")
    SubFormMode subFormMode() ;

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
    FormHorizontal subFormHorizontal() ;

    /**
     * 是否支持新增
     *
     * 参考定义: {"type":"boolean","description":"是否支持新增"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否支持新增")
    boolean addable() default false;

    /**
     * 是否支持删除
     *
     * 参考定义: {"type":"boolean","description":"是否支持删除"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否支持删除")
    boolean closable() default false;

    /**
     * 是否支持拖拽
     *
     * 参考定义: {"type":"boolean","description":"是否支持拖拽"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否支持拖拽")
    boolean draggable() default false;

    /**
     * 是否显示提示
     *
     * 参考定义: {"type":"boolean","description":"是否显示提示"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示提示")
    boolean showTip() default false;

    /**
     * tooltip 提示的类名
     *
     * 参考定义: {"type":"string","description":"tooltip 提示的类名"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "tooltip 提示的类名")
    String showTipClassName() default "	";

    /**
     * 是否可编辑标签名
     *
     * 参考定义: {"type":"boolean","description":"是否可编辑标签名"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可编辑标签名")
    boolean editable() default false;

    /**
     * 是否导航支持内容溢出滚动。属性废弃，为了兼容暂且保留
     *
     * 参考定义: {"type":"boolean","description":"是否导航支持内容溢出滚动。属性废弃，为了兼容暂且保留"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否导航支持内容溢出滚动。属性废弃，为了兼容暂且保留")
    boolean scrollable() default false;

    /**
     * 编辑器模式，侧边的位置
     *
     * 参考定义: {"type":"string","enum":["left","right"],"description":"编辑器模式，侧边的位置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "编辑器模式，侧边的位置")
    SidePosition sidePosition() ;

    /**
     * 自定义增加按钮文案
     *
     * 参考定义: {"type":"string","description":"自定义增加按钮文案"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "自定义增加按钮文案")
    String addBtnText() default "	";

    /**
     * 初始化激活的选项卡，hash值或索引值，支持使用表达式
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/SchemaExpression"},{"type":"number"}],"description":"初始化激活的选项卡，hash值或索引值，支持使用表达式"}
     *
     * [{"$ref":"#/definitions/SchemaExpression"},{"type":"number"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "初始化激活的选项卡，hash值或索引值，支持使用表达式")
    String defaultKey() default "	";

    /**
     * 激活的选项卡，hash值或索引值，支持使用表达式
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/SchemaExpression"},{"type":"number"}],"description":"激活的选项卡，hash值或索引值，支持使用表达式"}
     *
     * [{"$ref":"#/definitions/SchemaExpression"},{"type":"number"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "激活的选项卡，hash值或索引值，支持使用表达式")
    String activeKey() default "	";

    /**
     * 超过多少个时折叠按钮
     *
     * 参考定义: {"type":"number","description":"超过多少个时折叠按钮"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "超过多少个时折叠按钮")
    double collapseOnExceed() default 0;

    /**
     * 折叠按钮文字
     *
     * 参考定义: {"type":"string","description":"折叠按钮文字"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "折叠按钮文字")
    String collapseBtnLabel() default "	";

    /**
     * 是否滑动切换只在移动端生效
     *
     * 参考定义: {"type":"boolean","description":"是否滑动切换只在移动端生效"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否滑动切换只在移动端生效")
    boolean swipeable() default false;

}
