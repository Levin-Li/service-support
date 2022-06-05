package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MozUserInput
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MozUserInput")
public @interface Property_MozUserInput {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"disabled"},{"type":"string","const":"enabled"},{"type":"string","const":"none"}]
   */

    String[] consts = { "auto", "disabled", "enabled", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
