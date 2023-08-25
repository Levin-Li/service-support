package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Func
 *
 *
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
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
    @Schema(title = "type")
    String type() default "	";

    /**
     * returnType
     *
     * 参考定义: "#/definitions/FieldTypes"
     *
     * @see
     */
    @Schema(title = "returnType")
    String returnType() default "	";

    /**
     * args
     *
     * 参考定义: "#/definitions/FuncArg"
     *
     * @see
     */
    @Schema(title = "args")
    FuncArg[] args() ;

    /**
     * label
     *
     * 参考定义: {"type":"string"}
     *
     * @see
     */
    @Schema(title = "label")
    String label() default "	";

}
