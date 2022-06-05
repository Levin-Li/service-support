package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_OverscrollBehaviorInline
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_OverscrollBehaviorInline")
public @interface Property_OverscrollBehaviorInline {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"contain"},{"type":"string","const":"none"}]
   */

    String[] consts = { "auto", "contain", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
