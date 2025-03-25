package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ShortCutDateRange
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "ShortCutDateRange")
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
    
    @Schema(title = "label")
    String label() default "	";

    /**
     * startDate
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/moment.Moment"},{"type":"string"}]}
     *
     * [{"$ref":"#/definitions/moment.Moment"},{"type":"string"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "startDate")
    String startDate() default "	";

    /**
     * endDate
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/moment.Moment"},{"type":"string"}]}
     *
     * [{"$ref":"#/definitions/moment.Moment"},{"type":"string"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "endDate")
    String endDate() default "	";

}
