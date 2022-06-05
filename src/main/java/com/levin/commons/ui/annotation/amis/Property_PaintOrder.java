package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_PaintOrder
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_PaintOrder")
public @interface Property_PaintOrder {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"fill"},{"type":"string","const":"markers"},{"type":"string","const":"normal"},{"type":"string","const":"stroke"},{"type":"string"}]
   */

    String[] consts = { "fill", "markers", "normal", "stroke" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
