package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Cards
 *
 * Cards 卡片集合渲染器。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/card
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Cards 卡片集合渲染器。 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/card")
public @interface Cards {
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
     * 指定为 cards 类型
     *
     * 参考定义: {"type":"string","const":"cards","description":"指定为 cards 类型"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为 cards 类型")
    String type() default "cards";

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
     * card
     *
     * 参考定义: {"anyOf":[{"type":"object","properties":{"type":{"type":"string","const":"card","description":"指定为 card 类型"},"header":{"type":"object","properties":{"className":{"$ref":"#/definitions/SchemaClassName"},"title":{"$ref":"#/definitions/SchemaTpl","description":"标题"},"titleClassName":{"$ref":"#/definitions/SchemaClassName"},"subTitle":{"$ref":"#/definitions/SchemaCollection","description":"副标题"},"subTitleClassName":{"$ref":"#/definitions/SchemaClassName"},"subTitlePlaceholder":{"type":"string"},"description":{"$ref":"#/definitions/SchemaTpl","description":"描述"},"descriptionPlaceholder":{"type":"string","description":"描述占位内容"},"descriptionClassName":{"$ref":"#/definitions/SchemaClassName","description":"描述占位类名"},"desc":{"$ref":"#/definitions/SchemaTpl"},"descPlaceholder":{"$ref":"#/definitions/SchemaTpl"},"descClassName":{"$ref":"#/definitions/SchemaClassName"},"avatar":{"$ref":"#/definitions/SchemaUrlPath","description":"图片地址"},"avatarText":{"$ref":"#/definitions/SchemaTpl"},"avatarTextBackground":{"type":"array","items":{"type":"object","properties":{"length":{"type":"number"}},"required":["length"],"additionalProperties":{"type":"string"}}},"avatarTextClassName":{"$ref":"#/definitions/SchemaClassName"},"avatarClassName":{"$ref":"#/definitions/SchemaClassName","description":"图片包括层类名"},"imageClassName":{"$ref":"#/definitions/SchemaClassName","description":"图片类名。"},"highlight":{"$ref":"#/definitions/SchemaExpression","description":"是否点亮"},"highlightClassName":{"$ref":"#/definitions/SchemaClassName"},"href":{"$ref":"#/definitions/SchemaTpl","description":"链接地址"},"blank":{"type":"boolean","description":"是否新窗口打开"}},"additionalProperties":false,"description":"头部配置"},"body":{"type":"array","items":{"$ref":"#/definitions/CardBodyField"},"description":"内容区域"},"media":{"type":"object","properties":{"className":{"$ref":"#/definitions/SchemaClassName"},"type":{"type":"string","enum":["image","video"],"description":"多媒体类型"},"url":{"$ref":"#/definitions/SchemaUrlPath","description":"多媒体链接地址"},"position":{"type":"string","enum":["top","left","right","bottom"],"description":"多媒体区域位置"},"autoPlay":{"type":"boolean","description":"类型为video时是否自动播放"},"isLive":{"type":"boolean","description":"类型为video时是否是直播"},"poster":{"$ref":"#/definitions/SchemaUrlPath","description":"类型为video时视频封面"}},"additionalProperties":false,"description":"多媒体区域"},"actions":{"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"底部按钮集合。"},"toolbar":{"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"工具栏按钮"},"secondary":{"$ref":"#/definitions/SchemaTpl","description":"次要说明"},"useCardLabel":{"type":"boolean","description":"卡片内容区的表单项label是否使用Card内部的样式，默认为true"},"testid":{"type":"string"},"$$id":{"type":"string","description":"组件唯一 id，主要用于页面设计器中定位 json 节点"},"className":{"$ref":"#/definitions/SchemaClassName","description":"容器 css 类名"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"组件唯一 id，主要用于日志采集"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}},"debounce":{"$ref":"#/definitions/debounceConfig"},"track":{"$ref":"#/definitions/trackConfig"}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"static":{"type":"boolean","description":"是否静态展示"},"staticOn":{"$ref":"#/definitions/SchemaExpression","description":"是否静态展示表达式"},"staticPlaceholder":{"type":"string","description":"静态展示空值占位"},"staticClassName":{"$ref":"#/definitions/SchemaClassName","description":"静态展示表单项类名"},"staticLabelClassName":{"$ref":"#/definitions/SchemaClassName","description":"静态展示表单项Label类名"},"staticInputClassName":{"$ref":"#/definitions/SchemaClassName","description":"静态展示表单项Value类名"},"staticSchema":{},"style":{"type":"object","description":"组件样式"},"editorSetting":{"type":"object","properties":{"behavior":{"type":"string","description":"组件行为、用途，如 create、update、remove"},"displayName":{"type":"string","description":"组件名称，通常是业务名称方便定位"},"mock":{"description":"编辑器假数据，方便展示"}},"description":"编辑器配置，运行时可以忽略"},"useMobileUI":{"type":"boolean","description":"可以组件级别用来关闭移动端样式"},"testIdBuilder":{"$ref":"#/definitions/TestIdBuilder"}},"additionalProperties":false},{"$ref":"#/definitions/Card2Schema"}]}
     *
     * [{"type":"object","properties":{"type":{"type":"string","const":"card","description":"指定为 card 类型"},"header":{"type":"object","properties":{"className":{"$ref":"#/definitions/SchemaClassName"},"title":{"$ref":"#/definitions/SchemaTpl","description":"标题"},"titleClassName":{"$ref":"#/definitions/SchemaClassName"},"subTitle":{"$ref":"#/definitions/SchemaCollection","description":"副标题"},"subTitleClassName":{"$ref":"#/definitions/SchemaClassName"},"subTitlePlaceholder":{"type":"string"},"description":{"$ref":"#/definitions/SchemaTpl","description":"描述"},"descriptionPlaceholder":{"type":"string","description":"描述占位内容"},"descriptionClassName":{"$ref":"#/definitions/SchemaClassName","description":"描述占位类名"},"desc":{"$ref":"#/definitions/SchemaTpl"},"descPlaceholder":{"$ref":"#/definitions/SchemaTpl"},"descClassName":{"$ref":"#/definitions/SchemaClassName"},"avatar":{"$ref":"#/definitions/SchemaUrlPath","description":"图片地址"},"avatarText":{"$ref":"#/definitions/SchemaTpl"},"avatarTextBackground":{"type":"array","items":{"type":"object","properties":{"length":{"type":"number"}},"required":["length"],"additionalProperties":{"type":"string"}}},"avatarTextClassName":{"$ref":"#/definitions/SchemaClassName"},"avatarClassName":{"$ref":"#/definitions/SchemaClassName","description":"图片包括层类名"},"imageClassName":{"$ref":"#/definitions/SchemaClassName","description":"图片类名。"},"highlight":{"$ref":"#/definitions/SchemaExpression","description":"是否点亮"},"highlightClassName":{"$ref":"#/definitions/SchemaClassName"},"href":{"$ref":"#/definitions/SchemaTpl","description":"链接地址"},"blank":{"type":"boolean","description":"是否新窗口打开"}},"additionalProperties":false,"description":"头部配置"},"body":{"type":"array","items":{"$ref":"#/definitions/CardBodyField"},"description":"内容区域"},"media":{"type":"object","properties":{"className":{"$ref":"#/definitions/SchemaClassName"},"type":{"type":"string","enum":["image","video"],"description":"多媒体类型"},"url":{"$ref":"#/definitions/SchemaUrlPath","description":"多媒体链接地址"},"position":{"type":"string","enum":["top","left","right","bottom"],"description":"多媒体区域位置"},"autoPlay":{"type":"boolean","description":"类型为video时是否自动播放"},"isLive":{"type":"boolean","description":"类型为video时是否是直播"},"poster":{"$ref":"#/definitions/SchemaUrlPath","description":"类型为video时视频封面"}},"additionalProperties":false,"description":"多媒体区域"},"actions":{"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"底部按钮集合。"},"toolbar":{"type":"array","items":{"$ref":"#/definitions/ActionSchema"},"description":"工具栏按钮"},"secondary":{"$ref":"#/definitions/SchemaTpl","description":"次要说明"},"useCardLabel":{"type":"boolean","description":"卡片内容区的表单项label是否使用Card内部的样式，默认为true"},"testid":{"type":"string"},"$$id":{"type":"string","description":"组件唯一 id，主要用于页面设计器中定位 json 节点"},"className":{"$ref":"#/definitions/SchemaClassName","description":"容器 css 类名"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"组件唯一 id，主要用于日志采集"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}},"debounce":{"$ref":"#/definitions/debounceConfig"},"track":{"$ref":"#/definitions/trackConfig"}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"static":{"type":"boolean","description":"是否静态展示"},"staticOn":{"$ref":"#/definitions/SchemaExpression","description":"是否静态展示表达式"},"staticPlaceholder":{"type":"string","description":"静态展示空值占位"},"staticClassName":{"$ref":"#/definitions/SchemaClassName","description":"静态展示表单项类名"},"staticLabelClassName":{"$ref":"#/definitions/SchemaClassName","description":"静态展示表单项Label类名"},"staticInputClassName":{"$ref":"#/definitions/SchemaClassName","description":"静态展示表单项Value类名"},"staticSchema":{},"style":{"type":"object","description":"组件样式"},"editorSetting":{"type":"object","properties":{"behavior":{"type":"string","description":"组件行为、用途，如 create、update、remove"},"displayName":{"type":"string","description":"组件名称，通常是业务名称方便定位"},"mock":{"description":"编辑器假数据，方便展示"}},"description":"编辑器配置，运行时可以忽略"},"useMobileUI":{"type":"boolean","description":"可以组件级别用来关闭移动端样式"},"testIdBuilder":{"$ref":"#/definitions/TestIdBuilder"}},"additionalProperties":false},{"$ref":"#/definitions/Card2Schema"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "card")
    String card() default "	";

    /**
     * 头部 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "头部 CSS 类名")
    String headerClassName() default "	";

    /**
     * 底部 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "底部 CSS 类名")
    String footerClassName() default "	";

    /**
     * 卡片 CSS 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "卡片 CSS 类名")
    String itemClassName() default "	";

    /**
     * 无数据提示
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * 
     *
     * 
     *
     * @see Tpl
     */
    
    @Schema(title = "无数据提示")
    Tpl placeholder() ;

    /**
     * 是否显示底部
     *
     * 参考定义: {"type":"boolean","description":"是否显示底部"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示底部")
    boolean showFooter() default false;

    /**
     * 是否显示头部
     *
     * 参考定义: {"type":"boolean","description":"是否显示头部"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否显示头部")
    boolean showHeader() default false;

    /**
     * 数据源: 绑定当前环境变量
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * 
     *
     * 
     *
     * @see TokenizeableString
     */
    
    @Schema(title = "数据源: 绑定当前环境变量")
    String source() default "	";

    /**
     * 标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * 
     *
     * 
     *
     * @see Tpl
     */
    
    @Schema(title = "标题")
    Tpl title() ;

    /**
     * 是否隐藏勾选框
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏勾选框"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否隐藏勾选框")
    boolean hideCheckToggler() default false;

    /**
     * 是否固顶
     *
     * 参考定义: {"type":"boolean","description":"是否固顶"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否固顶")
    boolean affixHeader() default false;

    /**
     * 是否固底
     *
     * 参考定义: {"type":"boolean","description":"是否固底"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否固底")
    boolean affixFooter() default false;

    /**
     * 顶部区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(title = "顶部区域")
    String header() default "	";

    /**
     * 底部区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(title = "底部区域")
    String footer() default "	";

    /**
     * 配置某项是否可以点选
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "配置某项是否可以点选")
    String itemCheckableOn() default "	";

    /**
     * 配置某项是否可拖拽排序，前提是要开启拖拽功能
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "配置某项是否可拖拽排序，前提是要开启拖拽功能")
    String itemDraggableOn() default "	";

    /**
     * 点击卡片的时候是否勾选卡片。
     *
     * 参考定义: {"type":"boolean","description":"点击卡片的时候是否勾选卡片。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "点击卡片的时候是否勾选卡片。")
    boolean checkOnItemClick() default false;

    /**
     * 是否为瀑布流布局？
     *
     * 参考定义: {"type":"boolean","description":"是否为瀑布流布局？"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否为瀑布流布局？")
    boolean masonryLayout() default false;

    /**
     * 可以用来作为值的字段
     *
     * 参考定义: {"type":"string","description":"可以用来作为值的字段"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "可以用来作为值的字段")
    String valueField() default "	";

}
