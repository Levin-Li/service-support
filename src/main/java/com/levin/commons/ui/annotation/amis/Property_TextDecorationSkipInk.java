package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextDecorationSkipInk
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextDecorationSkipInk")
public @interface Property_TextDecorationSkipInk {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"auto"},{"type":"string","const":"none"}]
   */

    String[] consts = { "all", "auto", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
