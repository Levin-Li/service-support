package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Chart
 *
 * Chart 图表渲染器。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/chart
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Chart 图表渲染器。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/chart")
public @interface Chart {
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
     * style样式
     *
     * 参考定义: {"type":"object","description":"style样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "style样式")
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
     * 指定为 chart 类型
     *
     * 参考定义: {"type":"string","const":"chart","description":"指定为 chart 类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为 chart 类型")
    String type() default "chart";

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
     * Chart 主题配置
     *
     * 参考定义: {"description":"Chart 主题配置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "Chart 主题配置")
    String chartTheme() default "	";

    /**
     * 图表配置接口
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "图表配置接口")
    String api() default "	";

    /**
     * 是否初始加载。
     *
     * 参考定义: {"type":"boolean","description":"是否初始加载。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否初始加载。")
    boolean initFetch() default false;

    /**
     * 是否初始加载用表达式来配置
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "是否初始加载用表达式来配置")
    String initFetchOn() default "	";

    /**
     * 配置echart的config，支持数据映射。如果用了数据映射，为了同步更新，请设置 trackExpression
     *
     * 参考定义: {"description":"配置echart的config，支持数据映射。如果用了数据映射，为了同步更新，请设置 trackExpression"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "配置echart的config，支持数据映射。如果用了数据映射，为了同步更新，请设置 trackExpression")
    String config() default "	";

    /**
     * 跟踪表达式，如果这个表达式的运行结果发生变化了，则会更新 Echart，当 config 中用了数据映射时有用。
     *
     * 参考定义: {"type":"string","description":"跟踪表达式，如果这个表达式的运行结果发生变化了，则会更新 Echart，当 config 中用了数据映射时有用。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "跟踪表达式，如果这个表达式的运行结果发生变化了，则会更新 Echart，当 config 中用了数据映射时有用。")
    String trackExpression() default "	";

    /**
     * 宽度设置
     *
     * 参考定义: {"type":["number","string"],"description":"宽度设置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "宽度设置")
    String width() default "	";

    /**
     * 高度设置
     *
     * 参考定义: {"type":["number","string"],"description":"高度设置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "高度设置")
    String height() default "	";

    /**
     * 刷新时间
     *
     * 参考定义: {"type":"number","description":"刷新时间"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "刷新时间")
    double interval() default 0;

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
     * dataFilter
     *
     * 参考定义: "#/definitions/SchemaFunction"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","properties":{"prototype":{},"length":{"type":"number"},"arguments":{},"caller":{"$ref":"#/definitions/interface-1922134811-9821-11285-1922134811-0-212510"}},"required":["prototype","length","arguments","caller"],"additionalProperties":false}]
     *
     * @see Function
     */
    
    @Schema(title = "dataFilter")
    String dataFilter() default "	";

    /**
     * source
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * 
     *
     * 
     *
     * @see TokenizeableString
     */
    
    @Schema(title = "source")
    String source() default "	";

    /**
     * 默认开启 Config 中的数据映射，如果想关闭，请开启此功能。
     *
     * 参考定义: {"type":"boolean","description":"默认开启 Config 中的数据映射，如果想关闭，请开启此功能。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认开启 Config 中的数据映射，如果想关闭，请开启此功能。")
    boolean disableDataMapping() default false;

    /**
     * 点击行为配置，可以用来满足下钻操作等。
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * 
     *
     * 
     *
     * @see Action
     */
    
    @Schema(title = "点击行为配置，可以用来满足下钻操作等。")
    String clickAction() default "	";

    /**
     * 默认配置时追加的，如果更新配置想完全替换配置请配置为 true.
     *
     * 参考定义: {"type":"boolean","description":"默认配置时追加的，如果更新配置想完全替换配置请配置为 true."}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认配置时追加的，如果更新配置想完全替换配置请配置为 true.")
    boolean replaceChartOption() default false;

    /**
     * 不可见的时候隐藏
     *
     * 参考定义: {"type":"boolean","description":"不可见的时候隐藏"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "不可见的时候隐藏")
    boolean unMountOnHidden() default false;

    /**
     * 获取 geo json 文件的地址
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "获取 geo json 文件的地址")
    String mapURL() default "	";

    /**
     * 地图名称
     *
     * 参考定义: {"type":"string","description":"地图名称"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "地图名称")
    String mapName() default "	";

    /**
     * 加载百度地图
     *
     * 参考定义: {"type":"boolean","description":"加载百度地图"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "加载百度地图")
    boolean loadBaiduMap() default false;

}
