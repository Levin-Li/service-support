package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_SingleAnimation
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_SingleAnimation")
public @interface DataType_SingleAnimation {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.EasingFunction"},{"$ref":"#/definitions/DataType.SingleAnimationDirection"},{"$ref":"#/definitions/DataType.SingleAnimationFillMode"},{"type":"string"},{"type":"string","const":"infinite"},{"type":"string","const":"none"},{"type":"string","const":"paused"},{"type":"string","const":"running"},{"type":"number"}]
   */

    String[] consts = { "infinite", "none", "paused", "running" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
