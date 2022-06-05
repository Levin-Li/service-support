package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MozWindowShadow
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MozWindowShadow")
public @interface Property_MozWindowShadow {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"default"},{"type":"string","const":"menu"},{"type":"string","const":"none"},{"type":"string","const":"sheet"},{"type":"string","const":"tooltip"}]
   */

    String[] consts = { "default", "menu", "none", "sheet", "tooltip" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
