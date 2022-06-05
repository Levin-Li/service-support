package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_BackgroundPositionX
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_BackgroundPositionX")
public @interface Property_BackgroundPositionX {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"center"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"x-end"},{"type":"string","const":"x-start"}]
   */

    String[] consts = { "center", "left", "right", "x-end", "x-start" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
