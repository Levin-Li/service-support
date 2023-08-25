package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Alert
 *
 * Alert 提示渲染器。 文档：https://baidu.gitee.io/amis/docs/components/alert
 *
 * @author auto gen by service-support at 2022-2-10 12:04:41
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Alert 提示渲染器。 文档：https://baidu.gitee.io/amis/docs/components/alert")
public @interface Alert {
///////////////////////////////////////////

	//提示类型
	enum Level{
		info,
		warning,
		success,
		error,
		danger,
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
     * 指定为提示框类型
     *
     * 参考定义: {"type":"string","const":"alert","description":"指定为提示框类型"}
     *
     * @see 
     */
    @Schema(title = "指定为提示框类型")
    String type() default "alert";

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
     * 提示框标题
     *
     * 参考定义: {"type":"string","description":"提示框标题"}
     *
     * @see 
     */
    @Schema(title = "提示框标题")
    String title() default "	";

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
     * 提示类型
     *
     * 参考定义: {"type":"string","enum":["info","warning","success","error","danger"],"description":"提示类型"}
     *
     * @see 
     */
    @Schema(title = "提示类型")
    Level level() ;

    /**
     * 是否显示关闭按钮
     *
     * 参考定义: {"type":"boolean","description":"是否显示关闭按钮"}
     *
     * @see 
     */
    @Schema(title = "是否显示关闭按钮")
    boolean showCloseButton() default false;

    /**
     * 关闭按钮CSS类名
     *
     * 参考定义: {"type":"string","description":"关闭按钮CSS类名"}
     *
     * @see 
     */
    @Schema(title = "关闭按钮CSS类名")
    String closeButtonClassName() default "	";

    /**
     * 是否显示ICON
     *
     * 参考定义: {"type":"boolean","description":"是否显示ICON"}
     *
     * @see 
     */
    @Schema(title = "是否显示ICON")
    boolean showIcon() default false;

    /**
     * 左侧图标
     *
     * 参考定义: "#/definitions/SchemaIcon"
     *
     * @see 
     */
    @Schema(title = "左侧图标")
    Icon icon() ;

    /**
     * 图标CSS类名
     *
     * 参考定义: {"type":"string","description":"图标CSS类名"}
     *
     * @see 
     */
    @Schema(title = "图标CSS类名")
    String iconClassName() default "	";

}
