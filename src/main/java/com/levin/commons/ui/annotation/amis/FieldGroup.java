package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * FieldGroup
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-7 23:06:29
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "FieldGroup")
public @interface FieldGroup {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "";

    /**
     * label
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "label")
    String label() default "";

    /**
     * children
     *
     * 参考定义: "#/definitions/FieldSimple"
     *
     * @see 
     */
    @Schema(description = "children")
    String[] children() default "";

}
