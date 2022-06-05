package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextDecorationStyle
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextDecorationStyle")
public @interface Property_TextDecorationStyle {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"dashed"},{"type":"string","const":"dotted"},{"type":"string","const":"double"},{"type":"string","const":"solid"},{"type":"string","const":"wavy"}]
   */

    String[] consts = { "dashed", "dotted", "double", "solid", "wavy" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
