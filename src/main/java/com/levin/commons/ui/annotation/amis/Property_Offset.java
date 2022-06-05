package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_Offset
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Offset")
public @interface Property_Offset {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.GeometryBox"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string"}]
   */

    String[] consts = { "auto", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
