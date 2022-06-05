package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ScrollMarginBlockStart
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ScrollMarginBlockStart")
public @interface Property_ScrollMarginBlockStart {
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
