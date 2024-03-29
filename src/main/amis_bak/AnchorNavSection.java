package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * AnchorNavSection
 *
 * AnchorNavSection 锚点区域渲染器 文档：https://baidu.gitee.io/amis/docs/components/anchor-nav
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "AnchorNavSection 锚点区域渲染器 文档：https://baidu.gitee.io/amis/docs/components/anchor-nav")
public @interface AnchorNavSection {
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
     * 导航文字说明
     *
     * 参考定义: {"type":"string","description":"导航文字说明"}
     *
     * @see 
     */
    @Schema(description = "导航文字说明")
    String title() default "	";

    /**
     * 锚点链接
     *
     * 参考定义: {"type":"string","description":"锚点链接"}
     *
     * @see 
     */
    @Schema(description = "锚点链接")
    String href() default "	";

    /**
     * 内容
     *
     * 参考定义: "#/definitions/SchemaCollection"
     *
     * @see 
     */
    @Schema(description = "内容")
    String body() default "	";

}
