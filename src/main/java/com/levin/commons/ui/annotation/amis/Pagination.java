package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Pagination
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Pagination")
public @interface Pagination {
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
     * 参考定义: {"type":"boolean","description":"是否禁用","default":false}
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
     * 参考定义: {"type":"string","const":"pagination"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "type")
    String type() default "pagination";

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
     * 通过控制layout属性的顺序，调整分页结构 total,perPage,pager,go
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"通过控制layout属性的顺序，调整分页结构 total,perPage,pager,go","default":"pager"}
     *
     * [{"type":"string"},{"type":"array","items":{"type":"string"}}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "通过控制layout属性的顺序，调整分页结构 total,perPage,pager,go")
    String layout() default "	";

    /**
     * 最多显示多少个分页按钮。
     *
     * 参考定义: {"type":"number","description":"最多显示多少个分页按钮。","default":5}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "最多显示多少个分页按钮。")
    double maxButtons() default 0;

    /**
     * 模式，默认normal，如果只想简单显示可以配置成 `simple`。
     *
     * 参考定义: "#/definitions/MODE_TYPE"
     *
     * 
     *
     * 
     *
     * @see MODE_TYPE
     */
    
    @Schema(title = "模式，默认normal，如果只想简单显示可以配置成 `simple`。")
    String mode() default "	";

    /**
     * 当前页数
     *
     * 参考定义: {"type":"number","description":"当前页数"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "当前页数")
    double activePage() default 0;

    /**
     * 总条数
     *
     * 参考定义: {"type":"number","description":"总条数"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "总条数")
    double total() default 0;

    /**
     * 每页显示条数
     *
     * 参考定义: {"type":"number","description":"每页显示条数","default":10}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "每页显示条数")
    double perPage() default 0;

    /**
     * 是否展示分页切换，也同时受layout控制
     *
     * 参考定义: {"type":"boolean","description":"是否展示分页切换，也同时受layout控制","default":false}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否展示分页切换，也同时受layout控制")
    boolean showPerPage() default false;

    /**
     * 指定每页可以显示多少条
     *
     * 参考定义: {"type":"array","items":{"type":"number"},"description":"指定每页可以显示多少条","default":[10,20,50,100]}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定每页可以显示多少条")
    double[] perPageAvailable() default 0;

    /**
     * 是否显示快速跳转输入框
     *
     * 参考定义: {"type":"boolean","description":"是否显示快速跳转输入框","default":false}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示快速跳转输入框")
    boolean showPageInput() default false;

    /**
     * hasNext
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "hasNext")
    boolean hasNext() default false;

    /**
     * 弹层挂载节点
     *
     * 参考定义: {"type":"string","description":"弹层挂载节点","default":false}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "弹层挂载节点")
    String popOverContainerSelector() default "	";

}
