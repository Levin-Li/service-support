package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_OverflowY
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_OverflowY")
public @interface Property_OverflowY {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-hidden-unscrollable"},{"type":"string","const":"auto"},{"type":"string","const":"clip"},{"type":"string","const":"hidden"},{"type":"string","const":"scroll"},{"type":"string","const":"visible"}]
   */

    String[] consts = { "-moz-hidden-unscrollable", "auto", "clip", "hidden", "scroll", "visible" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
