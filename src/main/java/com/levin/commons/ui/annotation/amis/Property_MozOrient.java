package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MozOrient
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MozOrient")
public @interface Property_MozOrient {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block"},{"type":"string","const":"horizontal"},{"type":"string","const":"inline"},{"type":"string","const":"vertical"}]
   */

    String[] consts = { "block", "horizontal", "inline", "vertical" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
