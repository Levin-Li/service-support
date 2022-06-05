package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ListStyle
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ListStyle")
public @interface Property_ListStyle {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"inside"},{"type":"string","const":"none"},{"type":"string","const":"outside"},{"type":"string"}]
   */

    String[] consts = { "inside", "none", "outside" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
