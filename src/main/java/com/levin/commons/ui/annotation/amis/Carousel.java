package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Carousel
 *
 * Carousel 轮播图渲染器。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/carousel
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Carousel 轮播图渲染器。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/carousel")
public @interface Carousel {
///////////////////////////////////////////

	//null
	enum ControlsTheme{
		light,
		dark,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//配置控件内容
	enum Control{
		dots,
		arrows,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//动画类型
	enum Animation{
		fade,
		slide,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//预览图模式
	enum ThumbMode{
		contain,
		cover,
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
     * 指定为轮播图类型
     *
     * 参考定义: {"type":"string","const":"carousel","description":"指定为轮播图类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为轮播图类型")
    String type() default "carousel";

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
     * 是否自动播放
     *
     * 参考定义: {"type":"boolean","description":"是否自动播放"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否自动播放")
    boolean auto() default false;

    /**
     * 轮播间隔时间
     *
     * 参考定义: {"type":["number","string"],"description":"轮播间隔时间"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "轮播间隔时间")
    String interval() default "	";

    /**
     * 动画时长
     *
     * 参考定义: {"type":"number","description":"动画时长"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "动画时长")
    double duration() default 0;

    /**
     * 设置宽度
     *
     * 参考定义: {"type":"number","description":"设置宽度"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置宽度")
    double width() default 0;

    /**
     * 设置高度
     *
     * 参考定义: {"type":"number","description":"设置高度"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置高度")
    double height() default 0;

    /**
     * controlsTheme
     *
     * 参考定义: {"type":"string","enum":["light","dark"]}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "controlsTheme")
    ControlsTheme controlsTheme() ;

    /**
     * 占位
     *
     * 参考定义: {"type":"string","description":"占位"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "占位")
    String placeholder() default "	";

    /**
     * 配置控件内容
     *
     * 参考定义: {"type":"array","items":{"type":"string","enum":["dots","arrows"]},"description":"配置控件内容"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "配置控件内容")
    Control[] controls() ;

    /**
     * 动画类型
     *
     * 参考定义: {"type":"string","enum":["fade","slide"],"description":"动画类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "动画类型")
    Animation animation() ;

    /**
     * item
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(title = "配置单条呈现模板")
    String item() default "	";

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
     * 预览图模式
     *
     * 参考定义: {"type":"string","enum":["contain","cover"],"description":"预览图模式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "预览图模式")
    ThumbMode thumbMode() ;

    /**
     * 配置固定值
     *
     * 参考定义: {"type":"array","items":{},"description":"配置固定值"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "配置固定值")
    String[] options() default "	";

    /**
     * 是否一直显示箭头
     *
     * 参考定义: {"type":"boolean","description":"是否一直显示箭头"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否一直显示箭头")
    boolean alwaysShowArrow() default false;

    /**
     * 多图模式配置项
     *
     * 参考定义: {"type":"object","properties":{"count":{"type":"number"}},"required":["count"],"additionalProperties":false,"description":"多图模式配置项"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "多图模式配置项")
    String multiple() default "	";

    /**
     * 自定义箭头图标
     *
     * 参考定义: {"type":"object","properties":{"prev":{"$ref":"#/definitions/SchemaCollection"},"next":{"$ref":"#/definitions/SchemaCollection"}},"additionalProperties":false,"description":"自定义箭头图标"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "自定义箭头图标")
    String icons() default "	";

}
