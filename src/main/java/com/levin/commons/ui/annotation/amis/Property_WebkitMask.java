package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_WebkitMask
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_WebkitMask")
public @interface Property_WebkitMask {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.RepeatStyle"},{"$ref":"#/definitions/DataType.Box"},{"type":"string","const":"border"},{"type":"string","const":"content"},{"type":"string","const":"none"},{"type":"string","const":"padding"},{"type":"string","const":"text"},{"type":"string"}]
   */

    String[] consts = { "border", "content", "none", "padding", "text" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
