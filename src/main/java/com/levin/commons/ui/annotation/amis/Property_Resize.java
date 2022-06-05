package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_Resize
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Resize")
public @interface Property_Resize {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block"},{"type":"string","const":"both"},{"type":"string","const":"horizontal"},{"type":"string","const":"inline"},{"type":"string","const":"none"},{"type":"string","const":"vertical"}]
   */

    String[] consts = { "block", "both", "horizontal", "inline", "none", "vertical" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
