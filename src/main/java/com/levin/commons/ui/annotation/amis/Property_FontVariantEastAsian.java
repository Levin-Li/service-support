package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_FontVariantEastAsian
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FontVariantEastAsian")
public @interface Property_FontVariantEastAsian {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EastAsianVariantValues"},{"type":"string","const":"full-width"},{"type":"string","const":"normal"},{"type":"string","const":"proportional-width"},{"type":"string","const":"ruby"},{"type":"string"}]
   */

    String[] consts = { "full-width", "normal", "proportional-width", "ruby" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
