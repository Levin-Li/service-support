package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MsBlockProgression
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MsBlockProgression")
public @interface Property_MsBlockProgression {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"bt"},{"type":"string","const":"lr"},{"type":"string","const":"rl"},{"type":"string","const":"tb"}]
   */

    String[] consts = { "bt", "lr", "rl", "tb" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
