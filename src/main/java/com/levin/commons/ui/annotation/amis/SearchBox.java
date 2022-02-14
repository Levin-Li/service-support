package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * SearchBox
 *
 * 搜索框渲染器
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "搜索框渲染器")
public @interface SearchBox {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * 指定为搜索框。\n\n文档：https://baidu.gitee.io/amis/docs/components/search-box
     *
     * 参考定义: {"type":"string","const":"search-box","description":"指定为搜索框。\n\n文档：https://baidu.gitee.io/amis/docs/components/search-box"}
     *
     * @see 
     */
    @Schema(description = "指定为搜索框。\n\n文档：https://baidu.gitee.io/amis/docs/components/search-box")
    String type() default "search-box";

    /**
     * 外层 css 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see 
     */
    @Schema(description = "外层 css 类名")
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
     * 关键字名字。
     *
     * 参考定义: {"type":"string","description":"关键字名字。"}
     *
     * @see 
     */
    @Schema(description = "关键字名字。")
    String name() default "	";

    /**
     * 占位符
     *
     * 参考定义: {"type":"string","description":"占位符"}
     *
     * @see 
     */
    @Schema(description = "占位符")
    String placeholder() default "	";

    /**
     * 是否为 Mini 样式。
     *
     * 参考定义: {"type":"boolean","description":"是否为 Mini 样式。"}
     *
     * @see 
     */
    @Schema(description = "是否为 Mini 样式。")
    boolean mini() default false;

    /**
     * 是否立马搜索。
     *
     * 参考定义: {"type":"boolean","description":"是否立马搜索。"}
     *
     * @see 
     */
    @Schema(description = "是否立马搜索。")
    boolean searchImediately() default false;

}
