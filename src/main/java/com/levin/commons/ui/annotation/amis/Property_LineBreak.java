package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_LineBreak
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_LineBreak")
public @interface Property_LineBreak {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"anywhere"},{"type":"string","const":"auto"},{"type":"string","const":"loose"},{"type":"string","const":"normal"},{"type":"string","const":"strict"}]
   */

    String[] consts = { "anywhere", "auto", "loose", "normal", "strict" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
