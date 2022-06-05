package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_SingleTransition
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_SingleTransition")
public @interface DataType_SingleTransition {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.EasingFunction"},{"type":"string"},{"type":"string","const":"all"},{"type":"string","const":"none"}]
   */

    String[] consts = { "all", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
