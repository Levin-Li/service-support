package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_RubyAlign
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_RubyAlign")
public @interface Property_RubyAlign {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"center"},{"type":"string","const":"space-around"},{"type":"string","const":"space-between"},{"type":"string","const":"start"}]
   */

    String[] consts = { "center", "space-around", "space-between", "start" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
