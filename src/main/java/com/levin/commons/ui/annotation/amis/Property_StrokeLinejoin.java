package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_StrokeLinejoin
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_StrokeLinejoin")
public @interface Property_StrokeLinejoin {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"bevel"},{"type":"string","const":"miter"},{"type":"string","const":"round"}]
   */

    String[] consts = { "bevel", "miter", "round" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
