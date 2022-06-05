package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_CaptionSide
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_CaptionSide")
public @interface Property_CaptionSide {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block-end"},{"type":"string","const":"block-start"},{"type":"string","const":"bottom"},{"type":"string","const":"inline-end"},{"type":"string","const":"inline-start"},{"type":"string","const":"top"}]
   */

    String[] consts = { "block-end", "block-start", "bottom", "inline-end", "inline-start", "top" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
