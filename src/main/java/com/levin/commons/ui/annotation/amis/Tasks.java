package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Tasks
 *
 * Tasks 渲染器，格式说明 文档：https://baidu.gitee.io/amis/docs/components/tasks
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Tasks 渲染器，格式说明 文档：https://baidu.gitee.io/amis/docs/components/tasks")
public @interface Tasks {
///////////////////////////////////////////

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
     * 指定为任务类型
     *
     * 参考定义: {"type":"string","const":"tasks","description":"指定为任务类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "指定为任务类型")
    String type() default "tasks";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "容器 css 类名")
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
     * btnClassName
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "btnClassName")
    String btnClassName() default "	";

    /**
     * 操作按钮文字
     *
     * 参考定义: {"type":"string","description":"操作按钮文字"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "操作按钮文字")
    String btnText() default "	";

    /**
     * 用来获取任务状态的 API，当没有进行时任务时不会发送。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(description = "用来获取任务状态的 API，当没有进行时任务时不会发送。")
    String checkApi() default "	";

    /**
     * 当有任务进行中，会每隔一段时间再次检测，而时间间隔就是通过此项配置，默认 3s。
     *
     * 参考定义: {"type":"number","description":"当有任务进行中，会每隔一段时间再次检测，而时间间隔就是通过此项配置，默认 3s。","default":3000}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "当有任务进行中，会每隔一段时间再次检测，而时间间隔就是通过此项配置，默认 3s。")
    double interval() default 0;

    /**
     * items
     *
     * 参考定义: {"type":"array","items":{"type":"object","properties":{"key":{"type":"string","description":"任务键值，请唯一区分"},"label":{"type":"string","description":"任务名称"},"remark":{"type":"string","description":"当前任务状态，支持 html"},"status":{"type":"number","enum":[0,1,2,3,4,5],"description":"任务状态： 0: 初始状态，不可操作。 1: 就绪，可操作状态。 2: 进行中，还没有结束。 3：有错误，不可重试。 4: 已正常结束。 5：有错误，且可以重试。"}},"additionalProperties":false}}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "items")
    String[] items() default "	";

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
     * 操作列说明
     *
     * 参考定义: {"type":"string","description":"操作列说明"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "操作列说明")
    String operationLabel() default "	";

    /**
     * 如果任务失败，且可以重试，提交的时候会使用此 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(description = "如果任务失败，且可以重试，提交的时候会使用此 API")
    String reSubmitApi() default "	";

    /**
     * 备注列说明
     *
     * 参考定义: {"type":"string","description":"备注列说明"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "备注列说明")
    String remarkLabel() default "	";

    /**
     * 配置容器重试按钮 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "配置容器重试按钮 className")
    String retryBtnClassName() default "	";

    /**
     * 重试操作按钮文字
     *
     * 参考定义: {"type":"string","description":"重试操作按钮文字"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "重试操作按钮文字")
    String retryBtnText() default "	";

    /**
     * 状态列说明
     *
     * 参考定义: {"type":"string","description":"状态列说明"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "状态列说明")
    String statusLabel() default "	";

    /**
     * 状态显示对应的类名配置。
     *
     * 参考定义: {"type":"array","items":{"type":"string"},"description":"状态显示对应的类名配置。","default":["label-warning","label-info","label-success","label-danger","label-default","label-danger"]}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "状态显示对应的类名配置。")
    String[] statusLabelMap() default "	";

    /**
     * 状态显示对应的文字显示配置。
     *
     * 参考定义: {"type":"array","items":{"type":"string"},"description":"状态显示对应的文字显示配置。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "状态显示对应的文字显示配置。")
    String[] statusTextMap() default "	";

    /**
     * 提交任务使用的 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(description = "提交任务使用的 API")
    String submitApi() default "	";

    /**
     * 配置 table className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(description = "配置 table className")
    String tableClassName() default "	";

    /**
     * 任务名称列说明
     *
     * 参考定义: {"type":"string","description":"任务名称列说明"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "任务名称列说明")
    String taskNameLabel() default "	";

    /**
     * initialStatusCode
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "initialStatusCode")
    double initialStatusCode() default 0;

    /**
     * readyStatusCode
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "readyStatusCode")
    double readyStatusCode() default 0;

    /**
     * loadingStatusCode
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "loadingStatusCode")
    double loadingStatusCode() default 0;

    /**
     * canRetryStatusCode
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "canRetryStatusCode")
    double canRetryStatusCode() default 0;

    /**
     * finishStatusCode
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "finishStatusCode")
    double finishStatusCode() default 0;

    /**
     * errorStatusCode
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "errorStatusCode")
    double errorStatusCode() default 0;

}
