package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Option
 *
 *
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Option")
public @interface Option {
///////////////////////////////////////////

////////////////////////////////////////////

    /**
     * 用来显示的文字
     *
     * 参考定义: {"type":"string","description":"用来显示的文字"}
     *
     * @see
     */
    @Schema(title = "用来显示的文字")
    String label() default "	";

    /**
     * 可以用来给 Option 标记个范围，让数据展示更清晰。\n\n这个只有在数值展示的时候显示。
     *
     * 参考定义: {"type":"string","description":"可以用来给 Option 标记个范围，让数据展示更清晰。\n\n这个只有在数值展示的时候显示。"}
     *
     * @see
     */
    @Schema(title = "可以用来给 Option 标记个范围，让数据展示更清晰。\n\n这个只有在数值展示的时候显示。")
    String scopeLabel() default "	";

    /**
     * 请保证数值唯一，多个选项值一致会认为是同一个选项。
     *
     * 参考定义: {"description":"请保证数值唯一，多个选项值一致会认为是同一个选项。"}
     *
     * @see
     */
    @Schema(title = "请保证数值唯一，多个选项值一致会认为是同一个选项。")
    String value() default "	";

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
     * 支持嵌套
     *
     * 参考定义: "#/definitions/Options"
     *
     * @see
     */
    @Schema(title = "支持嵌套")
    String[] children() default "	";

    /**
     * 是否可见
     *
     * 参考定义: {"type":"boolean","description":"是否可见"}
     *
     * @see
     */
    @Schema(title = "是否可见")
    boolean visible() default false;

    /**
     * 最好不要用！因为有 visible 就够了。
     *
     * 参考定义: {"type":"boolean","description":"最好不要用！因为有 visible 就够了。"}
     *
     * @see
     */
    @Schema(title = "最好不要用！因为有 visible 就够了。")
    boolean hidden() default false;

    /**
     * 描述，部分控件支持
     *
     * 参考定义: {"type":"string","description":"描述，部分控件支持"}
     *
     * @see
     */
    @Schema(title = "描述，部分控件支持")
    String description() default "	";

    /**
     * 标记后数据延时加载
     *
     * 参考定义: {"type":"boolean","description":"标记后数据延时加载"}
     *
     * @see
     */
    @Schema(title = "标记后数据延时加载")
    boolean defer() default false;

    /**
     * 如果设置了，优先级更高，不设置走 source 接口加载。
     *
     * 参考定义: "#/definitions/SchemaApi"
     *
     * @see
     */
    @Schema(title = "如果设置了，优先级更高，不设置走 source 接口加载。")
    String deferApi() default "	";

    /**
     * 标记正在加载。只有 defer 为 true 时有意义。内部字段不可以外部设置
     *
     * 参考定义: {"type":"boolean","description":"标记正在加载。只有 defer 为 true 时有意义。内部字段不可以外部设置"}
     *
     * @see
     */
    @Schema(title = "标记正在加载。只有 defer 为 true 时有意义。内部字段不可以外部设置")
    boolean loading() default false;

    /**
     * 只有设置了 defer 才有意义，内部字段不可以外部设置
     *
     * 参考定义: {"type":"boolean","description":"只有设置了 defer 才有意义，内部字段不可以外部设置"}
     *
     * @see
     */
    @Schema(title = "只有设置了 defer 才有意义，内部字段不可以外部设置")
    boolean loaded() default false;

}
