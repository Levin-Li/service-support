package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * ShortCuts
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "ShortCuts")
public @interface ShortCuts {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"object","properties":{"label":{"type":"string"},"value":{"type":"string"}},"required":["label","value"],"additionalProperties":false},{"$ref":"#/definitions/ShortCutDate"},{"$ref":"#/definitions/ShortCutDateRange"}]
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
