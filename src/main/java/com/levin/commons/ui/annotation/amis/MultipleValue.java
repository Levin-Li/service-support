package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * MultipleValue
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "MultipleValue")
public @interface MultipleValue {
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
     * min
     *
     * 参考定义: {"type":"number"}
     *
     * @see 
     */
    @Schema(description = "min")
    double min() default 0;

    /**
     * max
     *
     * 参考定义: {"type":"number"}
     *
     * @see 
     */
    @Schema(description = "max")
    double max() default 0;

}
