package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextRendering
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextRendering")
public @interface Property_TextRendering {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"geometricPrecision"},{"type":"string","const":"optimizeLegibility"},{"type":"string","const":"optimizeSpeed"}]
   */

    String[] consts = { "auto", "geometricPrecision", "optimizeLegibility", "optimizeSpeed" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
