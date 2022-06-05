package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MsHyphenateLimitLines
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MsHyphenateLimitLines")
public @interface Property_MsHyphenateLimitLines {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"no-limit"},{"type":"number"},{"type":"string"}]
   */

    String[] consts = { "no-limit" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
