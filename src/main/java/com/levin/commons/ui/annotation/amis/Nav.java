package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Nav
 *
 * Nav 导航渲染器 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/nav
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Nav 导航渲染器 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/nav")
public @interface Nav {
///////////////////////////////////////////

	//true 为垂直排列，false 为水平排列类似如 tabs。
	enum Stacked{
		_true,
		_false,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//垂直模式 非折叠状态下 控制菜单打开方式
	enum Mode{
		__float,
		inline,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//主题配色 默认light
	enum ThemeColor{
		light,
		dark,
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
     * 指定为 Nav 导航渲染器
     *
     * 参考定义: {"type":"string","const":"nav","description":"指定为 Nav 导航渲染器"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "指定为 Nav 导航渲染器")
    String type() default "nav";

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
     * 链接地址集合
     *
     * 参考定义: "#/definitions/NavItemSchema"
     *
     * 
     *
     * 
     *
     * @see NavItem
     */
    
    @Schema(title = "链接地址集合")
    NavItem[] links() ;

    /**
     * indentSize
     *
     * 参考定义: {"type":"number","default":16}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "indentSize")
    double indentSize() default 0;

    /**
     * 可以通过 API 拉取。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "可以通过 API 拉取。")
    String source() default "	";

    /**
     * 懒加载 api，如果不配置复用 source 接口。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * 
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaApiObject"}]
     *
     * @see Api
     */
    
    @Schema(title = "懒加载 api，如果不配置复用 source 接口。")
    String deferApi() default "	";

    /**
     * true 为垂直排列，false 为水平排列类似如 tabs。
     *
     * 参考定义: {"type":"boolean","enum":[true,false],"description":"true 为垂直排列，false 为水平排列类似如 tabs。"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "true 为垂直排列，false 为水平排列类似如 tabs。")
    Stacked stacked() ;

    /**
     * 更多操作菜单列表
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * 
     *
     * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
     *
     * @see Collection
     */
    
    @Schema(title = "更多操作菜单列表")
    String itemActions() default "	";

    /**
     * 可拖拽
     *
     * 参考定义: {"type":"boolean","description":"可拖拽"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "可拖拽")
    boolean draggable() default false;

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
     * 角标
     *
     * 参考定义: "#/definitions/BadgeObject"
     *
     * 
     *
     * 
     *
     * @see BadgeObject
     */
    
    @Schema(title = "角标")
    BadgeObject itemBadge() ;

    /**
     * 角标
     *
     * 参考定义: "#/definitions/BadgeObject"
     *
     * 
     *
     * 
     *
     * @see BadgeObject
     */
    
    @Schema(title = "角标")
    BadgeObject badge() ;

    /**
     * 仅允许同层级拖拽
     *
     * 参考定义: {"type":"boolean","description":"仅允许同层级拖拽"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "仅允许同层级拖拽")
    boolean dragOnSameLevel() default false;

    /**
     * 横向导航时自动收纳配置
     *
     * 参考定义: "#/definitions/NavOverflow"
     *
     * 
     *
     * 
     *
     * @see NavOverflow
     */
    
    @Schema(title = "横向导航时自动收纳配置")
    NavOverflow overflow() ;

    /**
     * 最多展示多少层级
     *
     * 参考定义: {"type":"number","description":"最多展示多少层级"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "最多展示多少层级")
    double level() default 0;

    /**
     * 默认展开层级 小于等于该层数的节点默认全部打开
     *
     * 参考定义: {"type":"number","description":"默认展开层级 小于等于该层数的节点默认全部打开"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "默认展开层级 小于等于该层数的节点默认全部打开")
    double defaultOpenLevel() default 0;

    /**
     * 控制仅展示指定key菜单下的子菜单项
     *
     * 参考定义: {"type":"string","description":"控制仅展示指定key菜单下的子菜单项"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "控制仅展示指定key菜单下的子菜单项")
    String showKey() default "	";

    /**
     * 控制菜单缩起
     *
     * 参考定义: {"type":"boolean","description":"控制菜单缩起"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "控制菜单缩起")
    boolean collapsed() default false;

    /**
     * 垂直模式 非折叠状态下 控制菜单打开方式
     *
     * 参考定义: {"type":"string","enum":["float","inline"],"description":"垂直模式 非折叠状态下 控制菜单打开方式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "垂直模式 非折叠状态下 控制菜单打开方式")
    Mode mode() ;

    /**
     * 自定义展开图标
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/SchemaObject"}],"description":"自定义展开图标"}
     *
     * [{"type":"string"},{"$ref":"#/definitions/SchemaObject"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "自定义展开图标")
    String expandIcon() default "	";

    /**
     * 自定义展开图标位置 默认在前面 before after
     *
     * 参考定义: {"type":"string","description":"自定义展开图标位置 默认在前面 before after"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "自定义展开图标位置 默认在前面 before after")
    String expandPosition() default "	";

    /**
     * 主题配色 默认light
     *
     * 参考定义: {"type":"string","enum":["light","dark"],"description":"主题配色 默认light"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "主题配色 默认light")
    ThemeColor themeColor() ;

    /**
     * 手风琴展开 仅垂直inline模式支持
     *
     * 参考定义: {"type":"boolean","description":"手风琴展开 仅垂直inline模式支持"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "手风琴展开 仅垂直inline模式支持")
    boolean accordion() default false;

    /**
     * 子菜单项展开浮层样式
     *
     * 参考定义: {"type":"string","description":"子菜单项展开浮层样式"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "子菜单项展开浮层样式")
    String popupClassName() default "	";

    /**
     * 是否开启搜索
     *
     * 参考定义: {"type":"boolean","description":"是否开启搜索"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "是否开启搜索")
    boolean searchable() default false;

    /**
     * 搜索框相关配置
     *
     * 参考定义: {"type":"object","properties":{"className":{"type":"string","description":"搜索框外层CSS样式类"},"matchFunc":{"anyOf":[{"type":"string"},{}],"description":"搜索匹配函数"},"placeholder":{"type":"string","description":"占位符"},"mini":{"type":"boolean","description":"是否为 Mini 样式。"},"enhance":{"type":"boolean","description":"是否为加强样式"},"clearable":{"type":"boolean","description":"是否可清除"},"searchImediately":{"type":"boolean","description":"是否立马搜索。"},"valueField":{"type":"string","description":"指定唯一标识字段"}},"additionalProperties":false,"description":"搜索框相关配置"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "搜索框相关配置")
    String searchConfig() default "	";

}
