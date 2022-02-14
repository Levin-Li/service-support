package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Tabs
 *
 * 选项卡控件。 文档：https://baidu.gitee.io/amis/docs/components/tabs
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "选项卡控件。 文档：https://baidu.gitee.io/amis/docs/components/tabs")
public @interface Tabs {
///////////////////////////////////////////

	//展示形式
	enum TabsMode{
		line,
		card,
		radio,
		vertical,
		tiled,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

	//配置子表单项默认的展示方式。
	enum SubFormMode{
		normal,
		inline,
		horizontal,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * type
     *
     * 参考定义: {"type":"string","const":"tabs"}
     *
     * @see 
     */
    @Schema(description = "type")
    String type() default "tabs";

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
     * 选项卡成员。当配置了 source 时，选项卡成员，将会根据目标数据进行重复。
     *
     * 参考定义: "#/definitions/TabSchema"
     *
     * @see 
     */
    @Schema(description = "选项卡成员。当配置了 source 时，选项卡成员，将会根据目标数据进行重复。")
    Tab[] tabs() ;

    /**
     * 关联已有数据，选项卡直接根据目标数据重复。
     *
     * 参考定义: {"type":"string","description":"关联已有数据，选项卡直接根据目标数据重复。"}
     *
     * @see 
     */
    @Schema(description = "关联已有数据，选项卡直接根据目标数据重复。")
    String source() default "	";

    /**
     * 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "类名")
    String tabsClassName() default "	";

    /**
     * 展示形式
     *
     * 参考定义: {"type":"string","enum":["","line","card","radio","vertical","tiled"],"description":"展示形式"}
     *
     * @see 
     */
    @Schema(description = "展示形式")
    TabsMode tabsMode() ;

    /**
     * 内容类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "内容类名")
    String contentClassName() default "	";

    /**
     * 链接外层类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "链接外层类名")
    String linksClassName() default "	";

    /**
     * 卡片是否只有在点开的时候加载？
     *
     * 参考定义: {"type":"boolean","description":"卡片是否只有在点开的时候加载？"}
     *
     * @see 
     */
    @Schema(description = "卡片是否只有在点开的时候加载？")
    boolean mountOnEnter() default false;

    /**
     * 卡片隐藏的时候是否销毁卡片内容
     *
     * 参考定义: {"type":"boolean","description":"卡片隐藏的时候是否销毁卡片内容"}
     *
     * @see 
     */
    @Schema(description = "卡片隐藏的时候是否销毁卡片内容")
    boolean unmountOnExit() default false;

    /**
     * 可以在右侧配置点其他功能按钮。
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * @see 
     */
    @Schema(description = "可以在右侧配置点其他功能按钮。")
    String toolbar() default "	";

    /**
     * 配置子表单项默认的展示方式。
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置子表单项默认的展示方式。"}
     *
     * @see 
     */
    @Schema(description = "配置子表单项默认的展示方式。")
    SubFormMode subFormMode() ;

    /**
     * 如果是水平排版，这个属性可以细化水平排版的左右宽度占比。
     *
     * 参考定义: "#/definitions/FormSchemaHorizontal"
     *
     * @see 
     */
    @Schema(description = "如果是水平排版，这个属性可以细化水平排版的左右宽度占比。")
    FormHorizontal subFormHorizontal() ;

    /**
     * 是否支持溢出滚动
     *
     * 参考定义: {"type":"boolean","description":"是否支持溢出滚动"}
     *
     * @see 
     */
    @Schema(description = "是否支持溢出滚动")
    boolean scrollable() default false;

}
