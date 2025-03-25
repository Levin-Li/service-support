package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * CRUDCards
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "CRUDCards")
public @interface CRUDCards {
///////////////////////////////////////////

	//默认排序方向
	enum OrderDir{
		asc,
		desc,
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
     * 也可以直接从环境变量中读取，但是不太推荐。
     *
     * 参考定义: "#/definitions/SchemaTokenizeableString"
     *
     * 
     *
     * 
     *
     * @see TokenizeableString
     */
    
    @Schema(title = "也可以直接从环境变量中读取，但是不太推荐。")
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
     * 指定内容区的展示模式。
     *
     * 参考定义: {"type":"string","description":"指定内容区的展示模式。","const":"cards"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定内容区的展示模式。")
    String mode() default "cards";

    /**
     * 指定为 CRUD 渲染器。
     *
     * 参考定义: {"type":"string","const":"crud","description":"指定为 CRUD 渲染器。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为 CRUD 渲染器。")
    String type() default "crud";

    /**
     * 初始化数据 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "初始化数据 API")
    String api() default "	";

    /**
     * 懒加载 API，当行数据中用 defer: true 标记了，则其孩子节点将会用这个 API 来拉取数据。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "懒加载 API，当行数据中用 defer: true 标记了，则其孩子节点将会用这个 API 来拉取数据。")
    String deferApi() default "	";

    /**
     * 批量操作
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * 
     *
     * 
     *
     * @see Action
     */
    
    @Schema(title = "批量操作")
    String[] bulkActions() default "	";

    /**
     * 单条操作
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * 
     *
     * 
     *
     * @see Action
     */
    
    @Schema(title = "单条操作")
    String[] itemActions() default "	";

    /**
     * 每页个数，默认为 10，如果不是请设置。
     *
     * 参考定义: {"type":"number","description":"每页个数，默认为 10，如果不是请设置。","default":10}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "每页个数，默认为 10，如果不是请设置。")
    double perPage() default 0;

    /**
     * 默认排序字段
     *
     * 参考定义: {"type":"string","description":"默认排序字段"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认排序字段")
    String orderBy() default "	";

    /**
     * 默认排序方向
     *
     * 参考定义: {"type":"string","enum":["asc","desc"],"description":"默认排序方向"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认排序方向")
    OrderDir orderDir() ;

    /**
     * 可以默认给定初始参数如： {perPage: 24}
     *
     * 参考定义: "#/definitions/PlainObject"
     *
     * 
     *
     * 
     *
     * @see PlainObject
     */
    
    @Schema(title = "可以默认给定初始参数如： {perPage: 24}")
    String defaultParams() default "	";

    /**
     * 是否可通过拖拽排序
     *
     * 参考定义: {"type":"boolean","description":"是否可通过拖拽排序"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否可通过拖拽排序")
    boolean draggable() default false;

    /**
     * 是否可通过拖拽排序，通过表达式来配置
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "是否可通过拖拽排序，通过表达式来配置")
    String draggableOn() default "	";

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
     * 过滤器表单
     *
     * 参考定义: {"description":"过滤器表单"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "过滤器表单")
    String filter() default "	";

    /**
     * 初始是否拉取
     *
     * 参考定义: {"type":"boolean","description":"初始是否拉取"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "初始是否拉取")
    boolean initFetch() default false;

    /**
     * 初始是否拉取，用表达式来配置。
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "初始是否拉取，用表达式来配置。")
    String initFetchOn() default "	";

    /**
     * 配置内部 DOM 的 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * 
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"type":"string"}]}}]
     *
     * @see ClassName
     */
    
    @Schema(title = "配置内部 DOM 的 className")
    String innerClassName() default "	";

    /**
     * 设置自动刷新时间
     *
     * 参考定义: {"type":"number","description":"设置自动刷新时间"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置自动刷新时间")
    double interval() default 0;

    /**
     * 设置用来确定位置的字段名，设置后新的顺序将被赋值到该字段中。
     *
     * 参考定义: {"type":"string","description":"设置用来确定位置的字段名，设置后新的顺序将被赋值到该字段中。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置用来确定位置的字段名，设置后新的顺序将被赋值到该字段中。")
    String orderField() default "	";

    /**
     * 设置分页页码字段名。
     *
     * 参考定义: {"type":"string","description":"设置分页页码字段名。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置分页页码字段名。")
    String pageField() default "	";

    /**
     * 设置分页一页显示的多少条数据的字段名。
     *
     * 参考定义: {"type":"string","description":"设置分页一页显示的多少条数据的字段名。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置分页一页显示的多少条数据的字段名。")
    String perPageField() default "	";

    /**
     * 设置分页方向的字段名。单位简单分页时清楚时向前还是向后翻页。
     *
     * 参考定义: {"type":"string","description":"设置分页方向的字段名。单位简单分页时清楚时向前还是向后翻页。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "设置分页方向的字段名。单位简单分页时清楚时向前还是向后翻页。")
    String pageDirectionField() default "	";

    /**
     * 快速编辑后用来批量保存的 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "快速编辑后用来批量保存的 API")
    String quickSaveApi() default "	";

    /**
     * 快速编辑配置成及时保存时使用的 API
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "快速编辑配置成及时保存时使用的 API")
    String quickSaveItemApi() default "	";

    /**
     * 保存排序的 api
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "保存排序的 api")
    String saveOrderApi() default "	";

    /**
     * 是否将过滤条件的参数同步到地址栏,默认为true
     *
     * 参考定义: {"type":"boolean","description":"是否将过滤条件的参数同步到地址栏,默认为true","default":true}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否将过滤条件的参数同步到地址栏,默认为true")
    boolean syncLocation() default false;

    /**
     * 顶部工具栏
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"allOf":[{"$ref":"#/definitions/SchemaObject","patternProperties":{"^(align)$":{}}},{"type":"object","additionalProperties":true,"properties":{"align":{"type":"string","enum":["left","right"],"description":"对齐方式"}}}]},{"type":"object","additionalProperties":false,"properties":{"align":{"type":"string","enum":["left","right"],"description":"对齐方式"},"testid":{"type":"string"},"$$id":{"type":"string","description":"组件唯一 id，主要用于页面设计器中定位 json 节点"},"className":{"$ref":"#/definitions/SchemaClassName","description":"容器 css 类名"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"组件唯一 id，主要用于日志采集"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}},"debounce":{"$ref":"#/definitions/debounceConfig"},"track":{"$ref":"#/definitions/trackConfig"}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"static":{"type":"boolean","description":"是否静态展示"},"staticOn":{"$ref":"#/definitions/SchemaExpression","description":"是否静态展示表达式"},"staticPlaceholder":{"type":"string","description":"静态展示空值占位"},"staticClassName":{"$ref":"#/definitions/SchemaClassName","description":"静态展示表单项类名"},"staticLabelClassName":{"$ref":"#/definitions/SchemaClassName","description":"静态展示表单项Label类名"},"staticInputClassName":{"$ref":"#/definitions/SchemaClassName","description":"静态展示表单项Value类名"},"staticSchema":{},"style":{"type":"object","description":"组件样式"},"editorSetting":{"type":"object","properties":{"behavior":{"type":"string","description":"组件行为、用途，如 create、update、remove"},"displayName":{"type":"string","description":"组件名称，通常是业务名称方便定位"},"mock":{"description":"编辑器假数据，方便展示"}},"description":"编辑器配置，运行时可以忽略"},"useMobileUI":{"type":"boolean","description":"可以组件级别用来关闭移动端样式"},"testIdBuilder":{"$ref":"#/definitions/TestIdBuilder"},"type":{"$ref":"#/definitions/CRUDBultinToolbarType"}},"required":["type"]},{"$ref":"#/definitions/CRUDBultinToolbarType"}]},"description":"顶部工具栏"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "顶部工具栏")
    String[] headerToolbar() default "	";

    /**
     * 底部工具栏
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"allOf":[{"$ref":"#/definitions/SchemaObject","patternProperties":{"^(align)$":{}}},{"type":"object","additionalProperties":true,"properties":{"align":{"type":"string","enum":["left","right"],"description":"对齐方式"}}}]},{"type":"object","additionalProperties":false,"properties":{"align":{"type":"string","enum":["left","right"],"description":"对齐方式"},"testid":{"type":"string"},"$$id":{"type":"string","description":"组件唯一 id，主要用于页面设计器中定位 json 节点"},"className":{"$ref":"#/definitions/SchemaClassName","description":"容器 css 类名"},"$ref":{"type":"string","description":"配合 definitions 一起使用，可以实现无限循环的渲染器。"},"disabled":{"type":"boolean","description":"是否禁用"},"disabledOn":{"$ref":"#/definitions/SchemaExpression","description":"是否禁用表达式"},"hidden":{"type":"boolean","description":"是否隐藏"},"hiddenOn":{"$ref":"#/definitions/SchemaExpression","description":"是否隐藏表达式"},"visible":{"type":"boolean","description":"是否显示"},"visibleOn":{"$ref":"#/definitions/SchemaExpression","description":"是否显示表达式"},"id":{"type":"string","description":"组件唯一 id，主要用于日志采集"},"onEvent":{"type":"object","additionalProperties":{"type":"object","properties":{"weight":{"type":"number"},"actions":{"type":"array","items":{"$ref":"#/definitions/ListenerAction"}},"debounce":{"$ref":"#/definitions/debounceConfig"},"track":{"$ref":"#/definitions/trackConfig"}},"required":["actions"],"additionalProperties":false},"description":"事件动作配置"},"static":{"type":"boolean","description":"是否静态展示"},"staticOn":{"$ref":"#/definitions/SchemaExpression","description":"是否静态展示表达式"},"staticPlaceholder":{"type":"string","description":"静态展示空值占位"},"staticClassName":{"$ref":"#/definitions/SchemaClassName","description":"静态展示表单项类名"},"staticLabelClassName":{"$ref":"#/definitions/SchemaClassName","description":"静态展示表单项Label类名"},"staticInputClassName":{"$ref":"#/definitions/SchemaClassName","description":"静态展示表单项Value类名"},"staticSchema":{},"style":{"type":"object","description":"组件样式"},"editorSetting":{"type":"object","properties":{"behavior":{"type":"string","description":"组件行为、用途，如 create、update、remove"},"displayName":{"type":"string","description":"组件名称，通常是业务名称方便定位"},"mock":{"description":"编辑器假数据，方便展示"}},"description":"编辑器配置，运行时可以忽略"},"useMobileUI":{"type":"boolean","description":"可以组件级别用来关闭移动端样式"},"testIdBuilder":{"$ref":"#/definitions/TestIdBuilder"},"type":{"$ref":"#/definitions/CRUDBultinToolbarType"}},"required":["type"]},{"$ref":"#/definitions/CRUDBultinToolbarType"}]},"description":"底部工具栏"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "底部工具栏")
    String[] footerToolbar() default "	";

    /**
     * 每页显示多少个空间成员的配置如： [10, 20, 50, 100]。
     *
     * 参考定义: {"type":"array","items":{"type":"number"},"description":"每页显示多少个空间成员的配置如： [10, 20, 50, 100]。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "每页显示多少个空间成员的配置如： [10, 20, 50, 100]。")
    double[] perPageAvailable() default 0;

    /**
     * messages
     *
     * 参考定义: "#/definitions/SchemaMessage"
     *
     * 
     *
     * 
     *
     * @see Message
     */
    
    @Schema(title = "messages")
    Message messages() ;

    /**
     * 是否隐藏快速编辑的按钮。
     *
     * 参考定义: {"type":"boolean","description":"是否隐藏快速编辑的按钮。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否隐藏快速编辑的按钮。")
    boolean hideQuickSaveBtn() default false;

    /**
     * 是否自动跳顶部，当切分页的时候。
     *
     * 参考定义: {"type":"boolean","description":"是否自动跳顶部，当切分页的时候。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否自动跳顶部，当切分页的时候。")
    boolean autoJumpToTopOnPagerChange() default false;

    /**
     * 静默拉取
     *
     * 参考定义: {"type":"boolean","description":"静默拉取"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "静默拉取")
    boolean silentPolling() default false;

    /**
     * stopAutoRefreshWhen
     *
     * 参考定义: "#/definitions/SchemaExpression"
     *
     * 
     *
     * 
     *
     * @see Expression
     */
    
    @Schema(title = "stopAutoRefreshWhen")
    String stopAutoRefreshWhen() default "	";

    /**
     * stopAutoRefreshWhenModalIsOpen
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "stopAutoRefreshWhenModalIsOpen")
    boolean stopAutoRefreshWhenModalIsOpen() default false;

    /**
     * filterTogglable
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"object","properties":{"label":{"type":"string"},"activeLabel":{"type":"string"},"icon":{"type":"string"},"activeIcon":{"type":"string"}},"additionalProperties":false}]}
     *
     * [{"type":"boolean"},{"type":"object","properties":{"label":{"type":"string"},"activeLabel":{"type":"string"},"icon":{"type":"string"},"activeIcon":{"type":"string"}},"additionalProperties":false}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "filterTogglable")
    String filterTogglable() default "	";

    /**
     * filterDefaultVisible
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "filterDefaultVisible")
    boolean filterDefaultVisible() default false;

    /**
     * 是否将接口返回的内容自动同步到地址栏，前提是开启了同步地址栏。
     *
     * 参考定义: {"type":"boolean","description":"是否将接口返回的内容自动同步到地址栏，前提是开启了同步地址栏。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否将接口返回的内容自动同步到地址栏，前提是开启了同步地址栏。")
    boolean syncResponse2Query() default false;

    /**
     * 分页的时候是否保留用户选择。
     *
     * 参考定义: {"type":"boolean","description":"分页的时候是否保留用户选择。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "分页的时候是否保留用户选择。")
    boolean keepItemSelectionOnPageChange() default false;

    /**
     * 当配置 keepItemSelectionOnPageChange 时有用，用来配置已勾选项的文案。
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * 
     *
     * 
     *
     * @see Tpl
     */
    
    @Schema(title = "当配置 keepItemSelectionOnPageChange 时有用，用来配置已勾选项的文案。")
    Tpl labelTpl() ;

    /**
     * 是否为前端单次加载模式，可以用来实现前端分页。
     *
     * 参考定义: {"type":"boolean","description":"是否为前端单次加载模式，可以用来实现前端分页。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否为前端单次加载模式，可以用来实现前端分页。")
    boolean loadDataOnce() default false;

    /**
     * 在开启loadDataOnce时，当修改过滤条件时是否重新请求api\n\n如果没有配置，当查询条件表单触发的会重新请求 api，当是列过滤或者是 search-box 触发的则不重新请求 api 如果配置为 true，则不管是什么触发都会重新请求 api 如果配置为 false 则不管是什么触发都不会重新请求 api
     *
     * 参考定义: {"type":"boolean","description":"在开启loadDataOnce时，当修改过滤条件时是否重新请求api\n\n如果没有配置，当查询条件表单触发的会重新请求 api，当是列过滤或者是 search-box 触发的则不重新请求 api 如果配置为 true，则不管是什么触发都会重新请求 api 如果配置为 false 则不管是什么触发都不会重新请求 api"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "在开启loadDataOnce时，当修改过滤条件时是否重新请求api\n\n如果没有配置，当查询条件表单触发的会重新请求 api，当是列过滤或者是 search-box 触发的则不重新请求 api 如果配置为 true，则不管是什么触发都会重新请求 api 如果配置为 false 则不管是什么触发都不会重新请求 api")
    boolean loadDataOnceFetchOnFilter() default false;

    /**
     * 自定义搜索匹配函数，当开启loadDataOnce时，会基于该函数计算的匹配结果进行过滤，主要用于处理列字段类型较为复杂或者字段值格式和后端返回不一致的场景
     *
     * 参考定义: {"anyOf":[{"type":"string"},{}],"description":"自定义搜索匹配函数，当开启loadDataOnce时，会基于该函数计算的匹配结果进行过滤，主要用于处理列字段类型较为复杂或者字段值格式和后端返回不一致的场景"}
     *
     * [{"type":"string"},{}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "自定义搜索匹配函数，当开启loadDataOnce时，会基于该函数计算的匹配结果进行过滤，主要用于处理列字段类型较为复杂或者字段值格式和后端返回不一致的场景")
    String matchFunc() default "	";

    /**
     * 如果时内嵌模式，可以通过这个来配置默认的展开选项。
     *
     * 参考定义: {"type":"object","properties":{"expand":{"type":"string","enum":["first","all","none"],"description":"默认是展开第一个、所有、还是都不展开。"},"expandAll":{"type":"boolean","description":"是否显示全部切换按钮"},"accordion":{"type":"boolean","description":"是否为手风琴模式"}},"additionalProperties":false,"description":"如果时内嵌模式，可以通过这个来配置默认的展开选项。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "如果时内嵌模式，可以通过这个来配置默认的展开选项。")
    String expandConfig() default "	";

    /**
     * 默认只有当分页数大于 1 是才显示，如果总是想显示请配置。
     *
     * 参考定义: {"type":"boolean","description":"默认只有当分页数大于 1 是才显示，如果总是想显示请配置。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认只有当分页数大于 1 是才显示，如果总是想显示请配置。")
    boolean alwaysShowPagination() default false;

    /**
     * 开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/AutoGenerateFilterObject"},{"type":"boolean"}],"description":"开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单"}
     *
     * [{"$ref":"#/definitions/AutoGenerateFilterObject"},{"type":"boolean"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "开启查询区域，会根据列元素的searchable属性值，自动生成查询条件表单")
    String autoGenerateFilter() default "	";

    /**
     * 内容区域占满屏幕剩余空间
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"object","properties":{"height":{"type":"number"},"maxHeight":{"type":"number"}},"required":["height","maxHeight"],"additionalProperties":false}],"description":"内容区域占满屏幕剩余空间"}
     *
     * [{"type":"boolean"},{"type":"object","properties":{"height":{"type":"number"},"maxHeight":{"type":"number"}},"required":["height","maxHeight"],"additionalProperties":false}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "内容区域占满屏幕剩余空间")
    String autoFillHeight() default "	";

    /**
     * 是否开启Query信息转换，开启后将会对url中的Query进行转换，默认开启，默认仅转化布尔值
     *
     * 参考定义: {"anyOf":[{"type":"object","properties":{"enable":{"type":"boolean"},"types":{"type":"array","items":{"type":"string","enum":["boolean","number"]}}},"required":["enable"],"additionalProperties":false},{"type":"boolean"}],"description":"是否开启Query信息转换，开启后将会对url中的Query进行转换，默认开启，默认仅转化布尔值"}
     *
     * [{"type":"object","properties":{"enable":{"type":"boolean"},"types":{"type":"array","items":{"type":"string","enum":["boolean","number"]}}},"required":["enable"],"additionalProperties":false},{"type":"boolean"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否开启Query信息转换，开启后将会对url中的Query进行转换，默认开启，默认仅转化布尔值")
    String parsePrimitiveQuery() default "	";

}
