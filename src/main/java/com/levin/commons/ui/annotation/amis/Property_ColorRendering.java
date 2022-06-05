package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ColorRendering
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ColorRendering")
public @interface Property_ColorRendering {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"optimizeQuality"},{"type":"string","const":"optimizeSpeed"}]
   */

    String[] consts = { "auto", "optimizeQuality", "optimizeSpeed" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
