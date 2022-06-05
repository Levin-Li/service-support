package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_WordBreak
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_WordBreak")
public @interface Property_WordBreak {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"break-all"},{"type":"string","const":"break-word"},{"type":"string","const":"keep-all"},{"type":"string","const":"normal"}]
   */

    String[] consts = { "break-all", "break-word", "keep-all", "normal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
