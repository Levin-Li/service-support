package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Panel
 *
 * Panel渲染器。 文档：https://baidu.gitee.io/amis/docs/components/panel
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Panel渲染器。 文档：https://baidu.gitee.io/amis/docs/components/panel")
public @interface Panel {
///////////////////////////////////////////

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
     * 指定为Panel渲染器。
     *
     * 参考定义: {"type":"string","const":"panel","description":"指定为Panel渲染器。"}
     *
     * @see
     */
    @Schema(title = "指定为Panel渲染器。")
    String type() default "panel";

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
     * 按钮集合
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * @see
     */
    @Schema(title = "按钮集合")
    String[] actions() default "	";

    /**
     * 按钮集合外层类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see
     */
    @Schema(title = "按钮集合外层类名")
    String actionsClassName() default "	";

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
     * 配置 Body 容器 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see
     */
    @Schema(title = "配置 Body 容器 className")
    String bodyClassName() default "	";

    /**
     * 底部内容区域
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see
     */
    @Schema(title = "底部内容区域")
    String footer() default "	";

    /**
     * 配置 footer 容器 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see
     */
    @Schema(title = "配置 footer 容器 className")
    String footerClassName() default "	";

    /**
     * footer 和 actions 外层 div 类名。
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see
     */
    @Schema(title = "footer 和 actions 外层 div 类名。")
    String footerWrapClassName() default "	";

    /**
     * 头部内容, 和 title 二选一。
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see
     */
    @Schema(title = "头部内容, 和 title 二选一。")
    String header() default "	";

    /**
     * 配置 header 容器 className
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see
     */
    @Schema(title = "配置 header 容器 className")
    String headerClassName() default "	";

    /**
     * Panel 标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see
     */
    @Schema(title = "Panel 标题")
    Tpl title() ;

    /**
     * 固定底部, 想要把按钮固定在底部的时候配置。
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"string","const":"always"}],"description":"固定底部, 想要把按钮固定在底部的时候配置。"}
     *
     * @see
     */
    @Schema(title = "固定底部, 想要把按钮固定在底部的时候配置。")
    boolean affixFooter() default false;

    /**
     * 配置子表单项默认的展示方式。
     *
     * 参考定义: {"type":"string","enum":["normal","inline","horizontal"],"description":"配置子表单项默认的展示方式。"}
     *
     * @see
     */
    @Schema(title = "配置子表单项默认的展示方式。")
    SubFormMode subFormMode() ;

    /**
     * 如果是水平排版，这个属性可以细化水平排版的左右宽度占比。
     *
     * 参考定义: "#/definitions/FormSchemaHorizontal"
     *
     * @see
     */
    @Schema(title = "如果是水平排版，这个属性可以细化水平排版的左右宽度占比。")
    FormHorizontal subFormHorizontal() ;

}
