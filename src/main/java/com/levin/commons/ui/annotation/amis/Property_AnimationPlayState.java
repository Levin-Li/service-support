package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_AnimationPlayState
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_AnimationPlayState")
public @interface Property_AnimationPlayState {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"paused"},{"type":"string","const":"running"},{"type":"string"}]
   */

    String[] consts = { "paused", "running" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
