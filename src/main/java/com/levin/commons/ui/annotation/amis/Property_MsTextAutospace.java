package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MsTextAutospace
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MsTextAutospace")
public @interface Property_MsTextAutospace {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"ideograph-alpha"},{"type":"string","const":"ideograph-numeric"},{"type":"string","const":"ideograph-parenthesis"},{"type":"string","const":"ideograph-space"},{"type":"string","const":"none"}]
   */

    String[] consts = { "ideograph-alpha", "ideograph-numeric", "ideograph-parenthesis", "ideograph-space", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
