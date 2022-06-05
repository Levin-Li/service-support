package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Expandable
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Expandable")
public @interface Expandable {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * 
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * 对应数据源的key值
     *
     * 参考定义: {"type":"string","description":"对应数据源的key值"}
     *
     * @see 
     */
    @Schema(description = "对应数据源的key值")
    String keyField() default "	";

    /**
     * 行是否可展开表达式
     *
     * 参考定义: {"type":"string","description":"行是否可展开表达式"}
     *
     * @see 
     */
    @Schema(description = "行是否可展开表达式")
    String expandableOn() default "	";

    /**
     * 展开行自定义样式表达式
     *
     * 参考定义: {"type":"string","description":"展开行自定义样式表达式"}
     *
     * @see 
     */
    @Schema(description = "展开行自定义样式表达式")
    String expandedRowClassNameExpr() default "	";

    /**
     * 已展开的key值
     *
     * 参考定义: {"type":"array","items":{"type":["string","number"]},"description":"已展开的key值"}
     *
     * @see 
     */
    @Schema(description = "已展开的key值")
    String[] expandedRowKeys() default "	";

    /**
     * 已展开的key值表达式
     *
     * 参考定义: {"type":"string","description":"已展开的key值表达式"}
     *
     * @see 
     */
    @Schema(description = "已展开的key值表达式")
    String expandedRowKeysExpr() default "	";

}
