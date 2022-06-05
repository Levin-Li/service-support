package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * moment_Moment
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "moment_Moment")
public @interface moment_Moment {
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
     * constructor
     *
     * 参考定义: {"type":"object","properties":{"prototype":{},"length":{"type":"number"},"arguments":{},"caller":{"$ref":"#/definitions/interface-2073358172-9821-11285-2073358172-0-212510"}},"required":["prototype","length","arguments","caller"],"additionalProperties":false}
     *
     * @see 
     */
    @Schema(description = "constructor")
    String constructor() default "	";

}
