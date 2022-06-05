package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ColorScheme
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ColorScheme")
public @interface Property_ColorScheme {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"dark"},{"type":"string","const":"light"},{"type":"string","const":"normal"},{"type":"string"}]
   */

    String[] consts = { "dark", "light", "normal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
