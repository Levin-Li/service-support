package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ShapeOutside
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ShapeOutside")
public @interface Property_ShapeOutside {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Box"},{"type":"string","const":"margin-box"},{"type":"string","const":"none"},{"type":"string"}]
   */

    String[] consts = { "margin-box", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
