package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_BorderImageRepeat
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_BorderImageRepeat")
public @interface Property_BorderImageRepeat {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"},{"type":"string","const":"stretch"},{"type":"string"}]
   */

    String[] consts = { "repeat", "round", "space", "stretch" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
