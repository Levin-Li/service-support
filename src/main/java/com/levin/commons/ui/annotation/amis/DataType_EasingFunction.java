package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_EasingFunction
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_EasingFunction")
public @interface DataType_EasingFunction {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.CubicBezierTimingFunction"},{"$ref":"#/definitions/DataType.StepTimingFunction"},{"type":"string","const":"linear"}]
   */

    String[] consts = { "linear" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
