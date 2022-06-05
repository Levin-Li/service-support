package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ShapeImageThreshold
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ShapeImageThreshold")
public @interface Property_ShapeImageThreshold {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
