package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * ListBodyFieldObject
 *
 * 不指定类型默认就是文本
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "不指定类型默认就是文本")
public @interface ListBodyFieldObject {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * 列标题
     *
     * 参考定义: {"type":"string","description":"列标题"}
     *
     * @see
     */
    @Schema(title = "列标题")
    String label() default "	";

    /**
     * label 类名
     *
     * 参考定义: "#/definitions/SchemaClassName"
     *
     * @see
     */
    @Schema(title = "label 类名")
    String labelClassName() default "	";

    /**
     * 绑定字段名
     *
     * 参考定义: {"type":"string","description":"绑定字段名"}
     *
     * @see
     */
    @Schema(title = "绑定字段名")
    String name() default "	";

    /**
     * 配置查看详情功能
     *
     * 参考定义: "#/definitions/SchemaPopOver"
     *
     * @see
     */
    @Schema(title = "配置查看详情功能")
    boolean popOver() default false;

    /**
     * 配置快速编辑功能
     *
     * 参考定义: "#/definitions/SchemaQuickEdit"
     *
     * @see
     */
    @Schema(title = "配置快速编辑功能")
    boolean quickEdit() default false;

    /**
     * 配置点击复制功能
     *
     * 参考定义: "#/definitions/SchemaCopyable"
     *
     * @see
     */
    @Schema(title = "配置点击复制功能")
    boolean copyable() default false;

}
