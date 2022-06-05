package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ImageRendering
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ImageRendering")
public @interface Property_ImageRendering {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-crisp-edges"},{"type":"string","const":"-webkit-optimize-contrast"},{"type":"string","const":"auto"},{"type":"string","const":"crisp-edges"},{"type":"string","const":"pixelated"}]
   */

    String[] consts = { "-moz-crisp-edges", "-webkit-optimize-contrast", "auto", "crisp-edges", "pixelated" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
