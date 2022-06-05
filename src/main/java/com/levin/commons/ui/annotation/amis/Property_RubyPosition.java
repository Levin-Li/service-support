package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_RubyPosition
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_RubyPosition")
public @interface Property_RubyPosition {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"alternate"},{"type":"string","const":"inter-character"},{"type":"string","const":"over"},{"type":"string","const":"under"},{"type":"string"}]
   */

    String[] consts = { "alternate", "inter-character", "over", "under" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
