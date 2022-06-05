package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextDecorationLine
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextDecorationLine")
public @interface Property_TextDecorationLine {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"blink"},{"type":"string","const":"grammar-error"},{"type":"string","const":"line-through"},{"type":"string","const":"none"},{"type":"string","const":"overline"},{"type":"string","const":"spelling-error"},{"type":"string","const":"underline"},{"type":"string"}]
   */

    String[] consts = { "blink", "grammar-error", "line-through", "none", "overline", "spelling-error", "underline" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
