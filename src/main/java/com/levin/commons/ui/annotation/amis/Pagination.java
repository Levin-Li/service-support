package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Pagination
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Pagination")
public @interface Pagination {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * 
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * type
     *
     * 参考定义: {"type":"string","const":"pagination"}
     *
     * @see 
     */
    @Schema(description = "type")
    String type() default "pagination";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "容器 css 类名")
    String className() default "	";

    /**
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用","default":false}
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
     * @see 
     */
    @Schema(description = "是否禁用表达式")
    String disabledOn() default "	";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
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
     * @see 
     */
    @Schema(description = "是否隐藏表达式")
    String hiddenOn() default "	";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
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
     * @see 
     */
    @Schema(description = "是否显示表达式")
    String visibleOn() default "	";

    /**
     * 组件唯一 id，主要用于日志采集
     *
     * 参考定义: {"type":"string","description":"组件唯一 id，主要用于日志采集"}
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
     * @see 
     */
    @Schema(description = "事件动作配置")
    String onEvent() default "	";

    /**
     * 通过控制layout属性的顺序，调整分页结构 total,perPage,pager,go
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"array","items":{"type":"string"}}],"description":"通过控制layout属性的顺序，调整分页结构 total,perPage,pager,go","default":"pager"}
     *
     * @see 
     */
    @Schema(description = "通过控制layout属性的顺序，调整分页结构 total,perPage,pager,go")
    String layout() default "	";

    /**
     * 最多显示多少个分页按钮。
     *
     * 参考定义: {"type":"number","description":"最多显示多少个分页按钮。","default":5}
     *
     * @see 
     */
    @Schema(description = "最多显示多少个分页按钮。")
    double maxButtons() default 0;

    /**
     * 模式，默认normal，如果只想简单显示可以配置成 `simple`。
     *
     * 参考定义: "#/definitions/MODE_TYPE"
     *
     * @see 
     */
    @Schema(description = "模式，默认normal，如果只想简单显示可以配置成 `simple`。")
    String mode() default "	";

    /**
     * 当前页数
     *
     * 参考定义: {"type":"number","description":"当前页数"}
     *
     * @see 
     */
    @Schema(description = "当前页数")
    double activePage() default 0;

    /**
     * 总条数
     *
     * 参考定义: {"type":"number","description":"总条数"}
     *
     * @see 
     */
    @Schema(description = "总条数")
    double total() default 0;

    /**
     * 最后一页，总页数（如果传入了total，会重新计算lastPage）
     *
     * 参考定义: {"type":"number","description":"最后一页，总页数（如果传入了total，会重新计算lastPage）"}
     *
     * @see 
     */
    @Schema(description = "最后一页，总页数（如果传入了total，会重新计算lastPage）")
    double lastPage() default 0;

    /**
     * 每页显示条数
     *
     * 参考定义: {"type":"number","description":"每页显示条数","default":10}
     *
     * @see 
     */
    @Schema(description = "每页显示条数")
    double perPage() default 0;

    /**
     * 是否展示分页切换，也同时受layout控制
     *
     * 参考定义: {"type":"boolean","description":"是否展示分页切换，也同时受layout控制","default":false}
     *
     * @see 
     */
    @Schema(description = "是否展示分页切换，也同时受layout控制")
    boolean showPerPage() default false;

    /**
     * 指定每页可以显示多少条
     *
     * 参考定义: {"type":"array","items":{"type":"number"},"description":"指定每页可以显示多少条","default":[10,20,50,100]}
     *
     * @see 
     */
    @Schema(description = "指定每页可以显示多少条")
    double[] perPageAvailable() default 0;

    /**
     * 是否显示快速跳转输入框
     *
     * 参考定义: {"type":"boolean","description":"是否显示快速跳转输入框","default":false}
     *
     * @see 
     */
    @Schema(description = "是否显示快速跳转输入框")
    boolean showPageInput() default false;

    /**
     * hasNext
     *
     * 参考定义: {"type":"boolean"}
     *
     * @see 
     */
    @Schema(description = "hasNext")
    boolean hasNext() default false;

}
