package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_WritingMode
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_WritingMode")
public @interface Property_WritingMode {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"horizontal-tb"},{"type":"string","const":"sideways-lr"},{"type":"string","const":"sideways-rl"},{"type":"string","const":"vertical-lr"},{"type":"string","const":"vertical-rl"}]
   */

    String[] consts = { "horizontal-tb", "sideways-lr", "sideways-rl", "vertical-lr", "vertical-rl" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
