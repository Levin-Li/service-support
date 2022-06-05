package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MozWindowDragging
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MozWindowDragging")
public @interface Property_MozWindowDragging {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"drag"},{"type":"string","const":"no-drag"}]
   */

    String[] consts = { "drag", "no-drag" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
