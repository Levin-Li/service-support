package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Service
 *
 * Service 服务类控件。 文档：https://baidu.gitee.io/amis/docs/components/service
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Service 服务类控件。 文档：https://baidu.gitee.io/amis/docs/components/service")
public @interface Service {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * 指定为 Service 数据拉取控件。
     *
     * 参考定义: {"type":"string","const":"service","description":"指定为 Service 数据拉取控件。"}
     *
     * @see
     */
    @Schema(title = "指定为 Service 数据拉取控件。")
    String type() default "service";

    /**
     * 容器 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see
     */
    @Schema(title = "容器 css 类名")
    String className() default "	";

    /**
     * 是否禁用
     *
     * 参考定义: {"type":"boolean","description":"是否禁用"}
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
     * @see
     */
    @Schema(title = "是否禁用表达式")
    String disabledOn() default "	";

    /**
     * 是否隐藏
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏"}
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
     * @see
     */
    @Schema(title = "是否隐藏表达式")
    String hiddenOn() default "	";

    /**
     * 是否显示
     *
     * 参考定义: {"type":"boolean","description":"是否显示"}
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
     * @see
     */
    @Schema(title = "是否显示表达式")
    String visibleOn() default "	";

    /**
     * 页面初始化的时候，可以设置一个 API 让其取拉取，发送数据会携带当前 data 数据（包含地址栏参数），获取得数据会合并到 data 中，供组件内使用。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see
     */
    @Schema(title = "页面初始化的时候，可以设置一个 API 让其取拉取，发送数据会携带当前 data 数据（包含地址栏参数），获取得数据会合并到 data 中，供组件内使用。")
    String api() default "	";

    /**
     * WebScocket 地址，用于实时获取数据
     *
     * 参考定义: {"type":"string","description":"WebScocket 地址，用于实时获取数据"}
     *
     * @see
     */
    @Schema(title = "WebScocket 地址，用于实时获取数据")
    String ws() default "	";

    /**
     * 通过调用外部函数来获取数据
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"type":"object","properties":{"prototype":{},"length":{"type":"number"},"arguments":{},"caller":{"$ref":"#/definitions/interface-605559364-9821-11285-605559364-0-212357549528237"}},"required":["prototype","length","arguments","caller"],"additionalProperties":false,"description":"Creates a new function."}],"description":"通过调用外部函数来获取数据"}
     *
     * @see
     */
    @Schema(title = "通过调用外部函数来获取数据")
    String dataProvider() default "	";

    /**
     * 内容区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see
     */
    @Schema(title = "内容区域")
    String body() default "	";

    /**
     * fetchOn
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see
     */
    @Schema(title = "fetchOn")
    String fetchOn() default "	";

    /**
     * 是否默认就拉取？
     *
     * 参考定义: {"type":"boolean","description":"是否默认加载 schemaApi"}
     *
     * @see
     */
    @Schema(title = "是否默认就拉取？")
    boolean initFetch() default false;

    /**
     * 是否默认就拉取？通过表达式来决定.
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see
     */
    @Schema(title = "是否默认就拉取？通过表达式来决定.")
    String initFetchOn() default "	";

    /**
     * 用来获取远程 Schema 的 api
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see
     */
    @Schema(title = "用来获取远程 Schema 的 api")
    String schemaApi() default "	";

    /**
     * 是否轮询拉取
     *
     * 参考定义: {"type":"number","description":"是否轮询拉取"}
     *
     * @see
     */
    @Schema(title = "是否轮询拉取")
    double interval() default 0;

    /**
     * 是否静默拉取
     *
     * 参考定义: {"type":"boolean","description":"是否静默拉取"}
     *
     * @see
     */
    @Schema(title = "是否静默拉取")
    boolean silentPolling() default false;

    /**
     * 关闭轮询的条件。
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see
     */
    @Schema(title = "关闭轮询的条件。")
    String stopAutoRefreshWhen() default "	";

    /**
     * messages
     *
     * 参考定义: "#/definitions/SchemaMessage"
     *
     * @see
     */
    @Schema(title = "messages")
    Message messages() ;

    /**
     * name
     *
     * 参考定义: "#/definitions/SchemaName"
     *
     * @see
     */
    @Schema(title = "name")
    String name() default "	";

}
