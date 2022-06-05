package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextTransform
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextTransform")
public @interface Property_TextTransform {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"capitalize"},{"type":"string","const":"full-size-kana"},{"type":"string","const":"full-width"},{"type":"string","const":"lowercase"},{"type":"string","const":"none"},{"type":"string","const":"uppercase"}]
   */

    String[] consts = { "capitalize", "full-size-kana", "full-width", "lowercase", "none", "uppercase" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
