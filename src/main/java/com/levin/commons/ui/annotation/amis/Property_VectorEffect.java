package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_VectorEffect
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_VectorEffect")
public @interface Property_VectorEffect {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"non-scaling-stroke"},{"type":"string","const":"none"}]
   */

    String[] consts = { "non-scaling-stroke", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
