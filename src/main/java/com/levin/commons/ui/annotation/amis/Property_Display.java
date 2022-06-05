package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_Display
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Display")
public @interface Property_Display {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.DisplayOutside"},{"$ref":"#/definitions/DataType.DisplayInside"},{"$ref":"#/definitions/DataType.DisplayInternal"},{"$ref":"#/definitions/DataType.DisplayLegacy"},{"type":"string","const":"contents"},{"type":"string","const":"list-item"},{"type":"string","const":"none"},{"type":"string"}]
   */

    String[] consts = { "contents", "list-item", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
