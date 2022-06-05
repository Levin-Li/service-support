package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ScrollSnapTypeY
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ScrollSnapTypeY")
public @interface Property_ScrollSnapTypeY {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"mandatory"},{"type":"string","const":"none"},{"type":"string","const":"proximity"}]
   */

    String[] consts = { "mandatory", "none", "proximity" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
