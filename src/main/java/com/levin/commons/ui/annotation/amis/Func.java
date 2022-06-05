package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Func
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Func")
public @interface Func {
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
     * type
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "type")
    String type() default "	";

    /**
     * returnType
     *
     * 参考定义: "#/definitions/FieldTypes"
     *
     * @see 
     */
    @Schema(description = "returnType")
    String returnType() default "	";

    /**
     * args
     *
     * 参考定义: "#/definitions/FuncArg"
     *
     * @see 
     */
    @Schema(description = "args")
    FuncArg[] args() ;

    /**
     * label
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "label")
    String label() default "	";

}
