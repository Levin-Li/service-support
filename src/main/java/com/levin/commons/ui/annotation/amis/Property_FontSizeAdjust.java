package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_FontSizeAdjust
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FontSizeAdjust")
public @interface Property_FontSizeAdjust {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"from-font"},{"type":"string","const":"none"},{"type":"string"},{"type":"number"}]
   */

    String[] consts = { "from-font", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
