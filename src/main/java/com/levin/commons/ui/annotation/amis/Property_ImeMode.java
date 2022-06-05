package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ImeMode
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ImeMode")
public @interface Property_ImeMode {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"active"},{"type":"string","const":"auto"},{"type":"string","const":"disabled"},{"type":"string","const":"inactive"},{"type":"string","const":"normal"}]
   */

    String[] consts = { "active", "auto", "disabled", "inactive", "normal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
