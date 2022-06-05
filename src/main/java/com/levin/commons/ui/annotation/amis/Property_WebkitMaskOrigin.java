package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_WebkitMaskOrigin
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_WebkitMaskOrigin")
public @interface Property_WebkitMaskOrigin {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Box"},{"type":"string","const":"border"},{"type":"string","const":"content"},{"type":"string","const":"padding"},{"type":"string"}]
   */

    String[] consts = { "border", "content", "padding" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
