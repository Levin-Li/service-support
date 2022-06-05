package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * ShortCutDateRange
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "ShortCutDateRange")
public @interface ShortCutDateRange {
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
     * label
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "label")
    String label() default "	";

    /**
     * startDate
     *
     * 参考定义: "#/definitions/moment.Moment"
     *
     * @see 
     */
    @Schema(description = "startDate")
    String startDate() ;

    /**
     * endDate
     *
     * 参考定义: "#/definitions/moment.Moment"
     *
     * @see 
     */
    @Schema(description = "endDate")
    String endDate() ;

}
