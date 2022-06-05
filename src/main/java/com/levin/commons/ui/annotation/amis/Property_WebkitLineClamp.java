package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_WebkitLineClamp
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_WebkitLineClamp")
public @interface Property_WebkitLineClamp {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"number"},{"type":"string"}]
   */

    String[] consts = { "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
