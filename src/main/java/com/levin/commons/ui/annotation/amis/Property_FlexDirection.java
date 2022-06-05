package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_FlexDirection
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FlexDirection")
public @interface Property_FlexDirection {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"column"},{"type":"string","const":"column-reverse"},{"type":"string","const":"row"},{"type":"string","const":"row-reverse"}]
   */

    String[] consts = { "column", "column-reverse", "row", "row-reverse" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
