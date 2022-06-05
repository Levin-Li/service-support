package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_WhiteSpace
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_WhiteSpace")
public @interface Property_WhiteSpace {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-pre-wrap"},{"type":"string","const":"break-spaces"},{"type":"string","const":"normal"},{"type":"string","const":"nowrap"},{"type":"string","const":"pre"},{"type":"string","const":"pre-line"},{"type":"string","const":"pre-wrap"}]
   */

    String[] consts = { "-moz-pre-wrap", "break-spaces", "normal", "nowrap", "pre", "pre-line", "pre-wrap" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
