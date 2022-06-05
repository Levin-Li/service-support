package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MinHeight
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MinHeight")
public @interface Property_MinHeight {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-fit-content"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fit-content"},{"type":"string","const":"-webkit-max-content"},{"type":"string","const":"-webkit-min-content"},{"type":"string","const":"auto"},{"type":"string","const":"fit-content"},{"type":"string","const":"intrinsic"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"}]
   */

    String[] consts = { "-moz-fit-content", "-moz-max-content", "-moz-min-content", "-webkit-fit-content", "-webkit-max-content", "-webkit-min-content", "auto", "fit-content", "intrinsic", "max-content", "min-content" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
