package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MsOverflowStyle
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MsOverflowStyle")
public @interface Property_MsOverflowStyle {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-ms-autohiding-scrollbar"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string","const":"scrollbar"}]
   */

    String[] consts = { "-ms-autohiding-scrollbar", "auto", "none", "scrollbar" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
