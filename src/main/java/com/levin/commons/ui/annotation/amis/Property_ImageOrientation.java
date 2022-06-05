package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ImageOrientation
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ImageOrientation")
public @interface Property_ImageOrientation {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"flip"},{"type":"string","const":"from-image"},{"type":"string"}]
   */

    String[] consts = { "flip", "from-image" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
