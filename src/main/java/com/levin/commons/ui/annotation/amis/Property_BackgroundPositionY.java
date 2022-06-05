package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_BackgroundPositionY
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_BackgroundPositionY")
public @interface Property_BackgroundPositionY {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"bottom"},{"type":"string","const":"center"},{"type":"string","const":"top"},{"type":"string","const":"y-end"},{"type":"string","const":"y-start"}]
   */

    String[] consts = { "bottom", "center", "top", "y-end", "y-start" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
