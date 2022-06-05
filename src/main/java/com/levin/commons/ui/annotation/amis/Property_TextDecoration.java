package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextDecoration
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextDecoration")
public @interface Property_TextDecoration {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"},{"type":"string","const":"blink"},{"type":"string","const":"dashed"},{"type":"string","const":"dotted"},{"type":"string","const":"double"},{"type":"string","const":"from-font"},{"type":"string","const":"grammar-error"},{"type":"string","const":"line-through"},{"type":"string","const":"none"},{"type":"string","const":"overline"},{"type":"string","const":"solid"},{"type":"string","const":"spelling-error"},{"type":"string","const":"underline"},{"type":"string","const":"wavy"}]
   */

    String[] consts = { "auto", "blink", "dashed", "dotted", "double", "from-font", "grammar-error", "line-through", "none", "overline", "solid", "spelling-error", "underline", "wavy" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
