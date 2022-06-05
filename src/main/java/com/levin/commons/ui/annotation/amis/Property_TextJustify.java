package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextJustify
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextJustify")
public @interface Property_TextJustify {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"inter-character"},{"type":"string","const":"inter-word"},{"type":"string","const":"none"}]
   */

    String[] consts = { "auto", "inter-character", "inter-word", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
