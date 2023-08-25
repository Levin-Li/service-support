package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * NavItem
 *
 *
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "NavItem")
public @interface NavItem {
///////////////////////////////////////////

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
     * 文字说明
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/SchemaCollection"}],"description":"文字说明"}
     *
     * @see
     */
    @Schema(title = "文字说明")
    String label() default "	";

    /**
     * 图标类名，参考 fontawesome 4。
     *
     * 参考定义: "#/definitions/SchemaIcon"
     *
     * @see
     */
    @Schema(title = "图标类名，参考 fontawesome 4。")
    Icon icon() ;

    /**
     * to
     *
     * 参考定义: "#/definitions/SchemaUrlPath"
     *
     * @see
     */
    @Schema(title = "to")
    String to() default "	";

    /**
     * target
     *
     * 参考定义: {"type":"string"}
     *
     * @see
     */
    @Schema(title = "target")
    String target() default "	";

    /**
     * unfolded
     *
     * 参考定义: {"type":"boolean"}
     *
     * @see
     */
    @Schema(title = "unfolded")
    boolean unfolded() default false;

    /**
     * active
     *
     * 参考定义: {"type":"boolean"}
     *
     * @see
     */
    @Schema(title = "active")
    boolean active() default false;

    /**
     * defer
     *
     * 参考定义: {"type":"boolean"}
     *
     * @see
     */
    @Schema(title = "defer")
    boolean defer() default false;

    /**
     * deferApi
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see
     */
    @Schema(title = "deferApi")
    String deferApi() default "	";

    /**
     * children
     *
     * 参考定义: "#/definitions/NavItemSchema"
     *
     * @see
     */
    @Schema(title = "children")
    String[] children() default "	";

}
