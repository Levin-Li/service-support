package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_Appearance
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Appearance")
public @interface Property_Appearance {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.CompatAuto"},{"type":"string","const":"auto"},{"type":"string","const":"menulist-button"},{"type":"string","const":"none"},{"type":"string","const":"textfield"}]
   */

    String[] consts = { "auto", "menulist-button", "none", "textfield" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
