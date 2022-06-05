package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MozForceBrokenImageIcon
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MozForceBrokenImageIcon")
public @interface Property_MozForceBrokenImageIcon {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"number","const":0},{"type":"string"},{"type":"number","const":1}]
   */

    String[] consts = { "0", "1" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
