package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MozStackSizing
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MozStackSizing")
public @interface Property_MozStackSizing {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"ignore"},{"type":"string","const":"stretch-to-fit"}]
   */

    String[] consts = { "ignore", "stretch-to-fit" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
