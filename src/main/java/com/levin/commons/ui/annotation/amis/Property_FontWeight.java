package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_FontWeight
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FontWeight")
public @interface Property_FontWeight {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.FontWeightAbsolute"},{"type":"string","const":"bolder"},{"type":"string","const":"lighter"}]
   */

    String[] consts = { "bolder", "lighter" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
