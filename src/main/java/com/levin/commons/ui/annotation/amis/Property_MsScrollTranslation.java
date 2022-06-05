package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MsScrollTranslation
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MsScrollTranslation")
public @interface Property_MsScrollTranslation {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string","const":"vertical-to-horizontal"}]
   */

    String[] consts = { "none", "vertical-to-horizontal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
