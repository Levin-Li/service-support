package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Spinner
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Spinner")
public @interface Spinner {
///////////////////////////////////////////

	//spinner Icon 大小
	enum Size{
		sm,
		lg,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//spinner文案位置
	enum TipPlacement{
		top,
		right,
		bottom,
		left,
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
     * loadingConfig
     *
     * 参考定义: {"type":"object","properties":{"root":{"type":"string"},"show":{"type":"boolean"}},"additionalProperties":false}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "loadingConfig")
    String loadingConfig() default "	";

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
     * 自定义spinner的class
     *
     * 参考定义: {"type":"string","description":"自定义spinner的class"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "自定义spinner的class")
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
     * 组件类型
     *
     * 参考定义: {"type":"string","const":"spinner","description":"组件类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "组件类型")
    String type() default "spinner";

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
     * 控制Spinner显示与隐藏
     *
     * 参考定义: {"type":"boolean","description":"控制Spinner显示与隐藏"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "控制Spinner显示与隐藏")
    boolean show() default false;

    /**
     * spin图标位置包裹元素的自定义class
     *
     * 参考定义: {"type":"string","description":"spin图标位置包裹元素的自定义class"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "spin图标位置包裹元素的自定义class")
    String spinnerClassName() default "	";

    /**
     * 作为容器使用时最外层元素的class
     *
     * 参考定义: {"type":"string","description":"作为容器使用时最外层元素的class"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "作为容器使用时最外层元素的class")
    String spinnerWrapClassName() default "	";

    /**
     * mode
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "mode")
    String mode() default "	";

    /**
     * spinner Icon 大小
     *
     * 参考定义: {"type":"string","enum":["sm","lg",""],"description":"spinner Icon 大小"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "spinner Icon 大小")
    Size size() ;

    /**
     * 自定义icon
     *
     * 参考定义: {"type":"string","description":"自定义icon"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "自定义icon")
    String icon() default "	";

    /**
     * spinner文案
     *
     * 参考定义: {"type":"string","description":"spinner文案"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "spinner文案")
    String tip() default "	";

    /**
     * spinner文案位置
     *
     * 参考定义: {"type":"string","enum":["top","right","bottom","left"],"description":"spinner文案位置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "spinner文案位置")
    TipPlacement tipPlacement() ;

    /**
     * 延迟显示
     *
     * 参考定义: {"type":"number","description":"延迟显示"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "延迟显示")
    double delay() default 0;

    /**
     * 是否显示遮罩层
     *
     * 参考定义: {"type":"boolean","description":"是否显示遮罩层"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示遮罩层")
    boolean overlay() default false;

    /**
     * 作为容器使用时内容
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(title = "作为容器使用时内容")
    String body() default "	";

}
