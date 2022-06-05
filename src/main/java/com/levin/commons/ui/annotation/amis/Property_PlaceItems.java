package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_PlaceItems
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_PlaceItems")
public @interface Property_PlaceItems {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string","const":"stretch"},{"type":"string"}]
   */

    String[] consts = { "baseline", "normal", "stretch" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
