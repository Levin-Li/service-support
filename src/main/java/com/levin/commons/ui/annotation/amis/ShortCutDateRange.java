package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ShortCutDateRange
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
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
   *
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
     * 
     *
     * 
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
     * 
     *
     * 
     *
     * @see Moment_Moment
     */
    
    @Schema(description = "startDate")
    String startDate() ;

    /**
     * endDate
     *
     * 参考定义: "#/definitions/moment.Moment"
     *
     * 
     *
     * 
     *
     * @see Moment_Moment
     */
    
    @Schema(description = "endDate")
    String endDate() ;

}
