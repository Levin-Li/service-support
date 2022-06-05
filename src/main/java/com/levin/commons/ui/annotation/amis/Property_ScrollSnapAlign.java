package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ScrollSnapAlign
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ScrollSnapAlign")
public @interface Property_ScrollSnapAlign {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"none"},{"type":"string","const":"start"},{"type":"string"}]
   */

    String[] consts = { "center", "end", "none", "start" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
