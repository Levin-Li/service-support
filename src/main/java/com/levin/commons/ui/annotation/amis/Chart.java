package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Chart
 *
 * Chart 图表渲染器。 文档：https://baidu.gitee.io/amis/docs/components/carousel
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Chart 图表渲染器。 文档：https://baidu.gitee.io/amis/docs/components/carousel")
public @interface Chart {
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
     * 指定为 chart 类型
     *
     * 参考定义: {"type":"string","const":"chart","description":"指定为 chart 类型"}
     *
     * @see 
     */
    @Schema(description = "指定为 chart 类型")
    String type() default "chart";

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
     * 参考定义: {"type":"boolean","description":"是否禁用"}
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
     * Chart 主题配置
     *
     * 参考定义: {"description":"Chart 主题配置"}
     *
     * @see 
     */
    @Schema(description = "Chart 主题配置")
    String chartTheme() default "	";

    /**
     * 图表配置接口
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see 
     */
    @Schema(description = "图表配置接口")
    String api() default "	";

    /**
     * 是否初始加载。
     *
     * 参考定义: {"type":"boolean","description":"是否初始加载。"}
     *
     * @see 
     */
    @Schema(description = "是否初始加载。")
    boolean initFetch() default false;

    /**
     * 是否初始加载用表达式来配置
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * @see 
     */
    @Schema(description = "是否初始加载用表达式来配置")
    String initFetchOn() default "	";

    /**
     * 配置echart的config，支持数据映射。如果用了数据映射，为了同步更新，请设置 trackExpression
     *
     * 参考定义: {"description":"配置echart的config，支持数据映射。如果用了数据映射，为了同步更新，请设置 trackExpression"}
     *
     * @see 
     */
    @Schema(description = "配置echart的config，支持数据映射。如果用了数据映射，为了同步更新，请设置 trackExpression")
    String config() default "	";

    /**
     * 跟踪表达式，如果这个表达式的运行结果发生变化了，则会更新 Echart，当 config 中用了数据映射时有用。
     *
     * 参考定义: {"type":"string","description":"跟踪表达式，如果这个表达式的运行结果发生变化了，则会更新 Echart，当 config 中用了数据映射时有用。"}
     *
     * @see 
     */
    @Schema(description = "跟踪表达式，如果这个表达式的运行结果发生变化了，则会更新 Echart，当 config 中用了数据映射时有用。")
    String trackExpression() default "	";

    /**
     * 宽度设置
     *
     * 参考定义: {"type":"number","description":"宽度设置"}
     *
     * @see 
     */
    @Schema(description = "宽度设置")
    double width() default 0;

    /**
     * 高度设置
     *
     * 参考定义: {"type":"number","description":"高度设置"}
     *
     * @see 
     */
    @Schema(description = "高度设置")
    double height() default 0;

    /**
     * 刷新时间
     *
     * 参考定义: {"type":"number","description":"刷新时间"}
     *
     * @see 
     */
    @Schema(description = "刷新时间")
    double interval() default 0;

    /**
     * name
     *
     * 参考定义: "#/definitions/SchemaName"
     *
     * @see 
     */
    @Schema(description = "name")
    String name() default "	";

    /**
     * style样式
     *
     * 参考定义: {"type":"object","description":"style样式"}
     *
     * @see 
     */
    @Schema(description = "style样式")
    String style() default "	";

    /**
     * dataFilter
     *
     * 参考定义: "#/definitions/SchemaFunction"
     *
     * @see 
     */
    @Schema(description = "dataFilter")
    String dataFilter() default "	";

    /**
     * source
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * @see 
     */
    @Schema(description = "source")
    String source() default "	";

    /**
     * 默认开启 Config 中的数据映射，如果想关闭，请开启此功能。
     *
     * 参考定义: {"type":"boolean","description":"默认开启 Config 中的数据映射，如果想关闭，请开启此功能。"}
     *
     * @see 
     */
    @Schema(description = "默认开启 Config 中的数据映射，如果想关闭，请开启此功能。")
    boolean disableDataMapping() default false;

    /**
     * 点击行为配置，可以用来满足下钻操作等。
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * @see 
     */
    @Schema(description = "点击行为配置，可以用来满足下钻操作等。")
    String clickAction() default "	";

    /**
     * 默认配置时追加的，如果更新配置想完全替换配置请配置为 true.
     *
     * 参考定义: {"type":"boolean","description":"默认配置时追加的，如果更新配置想完全替换配置请配置为 true."}
     *
     * @see 
     */
    @Schema(description = "默认配置时追加的，如果更新配置想完全替换配置请配置为 true.")
    boolean replaceChartOption() default false;

    /**
     * 不可见的时候隐藏
     *
     * 参考定义: {"type":"boolean","description":"不可见的时候隐藏"}
     *
     * @see 
     */
    @Schema(description = "不可见的时候隐藏")
    boolean unMountOnHidden() default false;

}
