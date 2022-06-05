package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_FlexFlow
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FlexFlow")
public @interface Property_FlexFlow {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"column"},{"type":"string","const":"column-reverse"},{"type":"string","const":"nowrap"},{"type":"string","const":"row"},{"type":"string","const":"row-reverse"},{"type":"string","const":"wrap"},{"type":"string","const":"wrap-reverse"},{"type":"string"}]
   */

    String[] consts = { "column", "column-reverse", "nowrap", "row", "row-reverse", "wrap", "wrap-reverse" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
