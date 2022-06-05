package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_WebkitMaskRepeatX
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_WebkitMaskRepeatX")
public @interface Property_WebkitMaskRepeatX {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"no-repeat"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"}]
   */

    String[] consts = { "no-repeat", "repeat", "round", "space" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
