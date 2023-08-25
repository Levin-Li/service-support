package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Func
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Func")
public @interface Func {
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
     * type
     *
     * 参考定义: {"type":"string"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "type")
    String type() default "	";

    /**
     * returnType
     *
     * 参考定义: "#/definitions/FieldTypes"
     *
     *
     *
     *
     *
     * @see FieldTypes
     */

    @Schema(title = "returnType")
    String returnType() default "	";

    /**
     * args
     *
     * 参考定义: "#/definitions/FuncArg"
     *
     *
     *
     *
     *
     * @see FuncArg
     */

    @Schema(title = "args")
    FuncArg[] args() ;

    /**
     * label
     *
     * 参考定义: {"type":"string"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "label")
    String label() default "	";

}
