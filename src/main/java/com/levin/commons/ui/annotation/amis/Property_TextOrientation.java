package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextOrientation
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextOrientation")
public @interface Property_TextOrientation {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"mixed"},{"type":"string","const":"sideways"},{"type":"string","const":"upright"}]
   */

    String[] consts = { "mixed", "sideways", "upright" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
