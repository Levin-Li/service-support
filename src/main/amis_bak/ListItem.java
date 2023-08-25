package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * ListItem
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "ListItem")
public @interface ListItem {
///////////////////////////////////////////

	//操作位置，默认在右侧，可以设置成左侧。
	enum ActionsPosition{
		left,
		right,
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
     * actions
     *
     * 参考定义: "#/definitions/ActionSchema"
     *
     * @see 
     */
    @Schema(title = "actions")
    String[] actions() default "	";

    /**
     * 操作位置，默认在右侧，可以设置成左侧。
     *
     * 参考定义: {"type":"string","enum":["left","right"],"description":"操作位置，默认在右侧，可以设置成左侧。"}
     *
     * @see 
     */
    @Schema(title = "操作位置，默认在右侧，可以设置成左侧。")
    ActionsPosition actionsPosition() ;

    /**
     * 图片地址
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     * @see 
     */
    @Schema(title = "图片地址")
    String avatar() default "	";

    /**
     * 内容区域
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"$ref":"#/definitions/ListBodyField"},{"$ref":"#/definitions/ListBodyFieldObject"}]},"description":"内容区域"}
     *
     * @see 
     */
    @Schema(title = "内容区域")
    String[] body() default "	";

    /**
     * 描述
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(title = "描述")
    Tpl desc() ;

    /**
     * tooltip 说明
     *
     * 参考定义: "#/definitions/SchemaRemark"
     *
     * @see 
     */
    @Schema(title = "tooltip 说明")
    Remark remark() ;

    /**
     * 标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(title = "标题")
    Tpl title() ;

    /**
     * 副标题
     *
     * 参考定义: "#/definitions/SchemaTpl"
     *
     * @see 
     */
    @Schema(title = "副标题")
    Tpl subTitle() ;

}
