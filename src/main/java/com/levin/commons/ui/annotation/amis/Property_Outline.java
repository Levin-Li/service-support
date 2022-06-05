package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_Outline
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Outline")
public @interface Property_Outline {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"type":"string","const":"auto"},{"type":"string","const":"invert"},{"type":"string"}]
   */

    String[] consts = { "auto", "invert" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
