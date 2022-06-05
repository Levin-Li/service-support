package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MozContextProperties
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MozContextProperties")
public @interface Property_MozContextProperties {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"fill"},{"type":"string","const":"fill-opacity"},{"type":"string","const":"none"},{"type":"string","const":"stroke"},{"type":"string","const":"stroke-opacity"},{"type":"string"}]
   */

    String[] consts = { "fill", "fill-opacity", "none", "stroke", "stroke-opacity" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
