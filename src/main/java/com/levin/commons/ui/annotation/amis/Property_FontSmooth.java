package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_FontSmooth
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FontSmooth")
public @interface Property_FontSmooth {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.AbsoluteSize"},{"type":"string"},{"type":"number"},{"type":"string","const":"always"},{"type":"string","const":"auto"},{"type":"string","const":"never"}]
   */

    String[] consts = { "always", "auto", "never" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
