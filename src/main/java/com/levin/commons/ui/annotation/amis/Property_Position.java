package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_Position
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Position")
public @interface Property_Position {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-webkit-sticky"},{"type":"string","const":"absolute"},{"type":"string","const":"fixed"},{"type":"string","const":"relative"},{"type":"string","const":"static"},{"type":"string","const":"sticky"}]
   */

    String[] consts = { "-webkit-sticky", "absolute", "fixed", "relative", "static", "sticky" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
