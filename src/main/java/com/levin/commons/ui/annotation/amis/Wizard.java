package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Wizard
 *
 * 表单向导 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/wizard
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "表单向导 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/wizard")
public @interface Wizard {
///////////////////////////////////////////

	//展示模式
	enum Mode{
		vertical,
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
     * 指定为表单向导
     *
     * 参考定义: {"type":"string","const":"wizard","description":"指定为表单向导"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为表单向导")
    String type() default "wizard";

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
     * 配置按钮 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "配置按钮 className")
    String actionClassName() default "	";

    /**
     * 完成按钮的文字描述
     *
     * 参考定义: {"type":"string","description":"完成按钮的文字描述"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "完成按钮的文字描述")
    String actionFinishLabel() default "	";

    /**
     * 下一步按钮的文字描述
     *
     * 参考定义: {"type":"string","description":"下一步按钮的文字描述"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "下一步按钮的文字描述")
    String actionNextLabel() default "	";

    /**
     * 下一步并且保存按钮的文字描述
     *
     * 参考定义: {"type":"string","description":"下一步并且保存按钮的文字描述"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "下一步并且保存按钮的文字描述")
    String actionNextSaveLabel() default "	";

    /**
     * 上一步按钮的文字描述
     *
     * 参考定义: {"type":"string","description":"上一步按钮的文字描述"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "上一步按钮的文字描述")
    String actionPrevLabel() default "	";

    /**
     * Wizard 用来保存数据的 api。 [详情](https://baidu.github.io/amis/docs/api#wizard)
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "Wizard 用来保存数据的 api。 [详情](https://baidu.github.io/amis/docs/api#wizard)")
    String api() default "	";

    /**
     * 是否合并后再提交
     *
     * 参考定义: {"type":"boolean","description":"是否合并后再提交"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否合并后再提交")
    boolean bulkSubmit() default false;

    /**
     * Wizard 用来获取初始数据的 api。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "Wizard 用来获取初始数据的 api。")
    String initApi() default "	";

    /**
     * 展示模式
     *
     * 参考定义: {"type":"string","enum":["vertical","horizontal"],"description":"展示模式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "展示模式")
    Mode mode() ;

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
     * 是否为只读模式。
     *
     * 参考定义: {"type":"boolean","description":"是否为只读模式。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否为只读模式。")
    boolean readOnly() default false;

    /**
     * 保存完后，可以指定跳转地址，支持相对路径和组内绝对路径，同时可以通过 $xxx 使用变量
     *
     * 参考定义: {"type":"string","description":"保存完后，可以指定跳转地址，支持相对路径和组内绝对路径，同时可以通过 $xxx 使用变量"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "保存完后，可以指定跳转地址，支持相对路径和组内绝对路径，同时可以通过 $xxx 使用变量")
    String redirect() default "	";

    /**
     * reload
     *
     * 参考定义: "#/definitions/SchemaReload"
     *
     * 
     *
     * 
     *
     * @see Reload
     */
    
    @Schema(title = "reload")
    String reload() default "	";

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
     * 是否将底部按钮固定在底部。
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"string","const":"always"}],"description":"是否将底部按钮固定在底部。"}
     *
     * [{"type":"boolean"},{"type":"string","const":"always"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否将底部按钮固定在底部。")
    String affixFooter() default "	";

    /**
     * steps
     *
     * 参考定义: "#/definitions/WizardStepSchema"
     *
     * 
     *
     * 
     *
     * @see WizardStep
     */
    
    @Schema(title = "steps")
    WizardStep[] steps() ;

    /**
     * startStep
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "startStep")
    String startStep() default "	";

    /**
     * 步骤条区域css类
     *
     * 参考定义: {"type":"string","description":"步骤条区域css类"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "步骤条区域css类")
    String stepsClassName() default "	";

    /**
     * 表单区域css类
     *
     * 参考定义: {"type":"string","description":"表单区域css类"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "表单区域css类")
    String bodyClassName() default "	";

    /**
     * step + body区域css类
     *
     * 参考定义: {"type":"string","description":"step + body区域css类"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "step + body区域css类")
    String stepClassName() default "	";

    /**
     * 底部操作栏的css类
     *
     * 参考定义: {"type":"string","description":"底部操作栏的css类"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "底部操作栏的css类")
    String footerClassName() default "	";

    /**
     * 是否用panel包裹
     *
     * 参考定义: {"type":"boolean","description":"是否用panel包裹"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否用panel包裹")
    boolean wrapWithPanel() default false;

}
