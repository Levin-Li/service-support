package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_BoxOrient
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_BoxOrient")
public @interface Property_BoxOrient {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block-axis"},{"type":"string","const":"horizontal"},{"type":"string","const":"inherit"},{"type":"string","const":"inline-axis"},{"type":"string","const":"vertical"}]
   */

    String[] consts = { "block-axis", "horizontal", "inherit", "inline-axis", "vertical" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
