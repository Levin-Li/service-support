package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MinBlockSize
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MinBlockSize")
public @interface Property_MinBlockSize {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fill-available"},{"type":"string","const":"auto"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"}]
   */

    String[] consts = { "-moz-max-content", "-moz-min-content", "-webkit-fill-available", "auto", "fit-content", "max-content", "min-content" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
