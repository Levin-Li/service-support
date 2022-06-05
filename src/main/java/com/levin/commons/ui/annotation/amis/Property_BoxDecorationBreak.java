package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_BoxDecorationBreak
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_BoxDecorationBreak")
public @interface Property_BoxDecorationBreak {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"clone"},{"type":"string","const":"slice"}]
   */

    String[] consts = { "clone", "slice" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
