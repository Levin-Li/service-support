package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_HangingPunctuation
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_HangingPunctuation")
public @interface Property_HangingPunctuation {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"allow-end"},{"type":"string","const":"first"},{"type":"string","const":"force-end"},{"type":"string","const":"last"},{"type":"string","const":"none"},{"type":"string"}]
   */

    String[] consts = { "allow-end", "first", "force-end", "last", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
