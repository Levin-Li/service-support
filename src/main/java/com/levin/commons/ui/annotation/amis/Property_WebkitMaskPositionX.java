package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_WebkitMaskPositionX
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_WebkitMaskPositionX")
public @interface Property_WebkitMaskPositionX {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"center"},{"type":"string","const":"left"},{"type":"string","const":"right"}]
   */

    String[] consts = { "center", "left", "right" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
