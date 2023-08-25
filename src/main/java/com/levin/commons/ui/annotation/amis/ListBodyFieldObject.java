package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ListBodyFieldObject
 *
 * 不指定类型默认就是文本
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
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
     * 列标题
     *
     * 参考定义: {"type":"string","description":"列标题"}
     *
     *
     *
     *
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
     *
     *
     * [{"type":"string"},{"type":"object","additionalProperties":{"anyOf":[{"type":"boolean"},{"not":{}},{"type":"null"},{"$ref":"#/definitions/SchemaExpression"}]}}]
     *
     * @see ClassName
     */

    @Schema(title = "label 类名")
    String labelClassName() default "	";

    /**
     * 绑定字段名
     *
     * 参考定义: {"type":"string","description":"绑定字段名"}
     *
     *
     *
     *
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
     *
     *
     * [{"type":"boolean"},{"$ref":"#/definitions/SchemaPopOverObject"}]
     *
     * @see PopOver
     */

    @Schema(title = "配置查看详情功能")
    String popOver() default "	";

    /**
     * 配置快速编辑功能
     *
     * 参考定义: "#/definitions/SchemaQuickEdit"
     *
     *
     *
     * [{"type":"boolean"},{"$ref":"#/definitions/SchemaQuickEditObject"}]
     *
     * @see QuickEdit
     */

    @Schema(title = "配置快速编辑功能")
    String quickEdit() default "	";

    /**
     * 配置点击复制功能
     *
     * 参考定义: "#/definitions/SchemaCopyable"
     *
     *
     *
     * [{"type":"boolean"},{"$ref":"#/definitions/SchemaCopyableObject"}]
     *
     * @see Copyable
     */

    @Schema(title = "配置点击复制功能")
    String copyable() default "	";

}
