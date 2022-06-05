package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_FontSize
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FontSize")
public @interface Property_FontSize {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.AbsoluteSize"},{"type":"string"},{"type":"number"},{"type":"string","const":"larger"},{"type":"string","const":"smaller"}]
   */

    String[] consts = { "larger", "smaller" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
