package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_OverflowWrap
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_OverflowWrap")
public @interface Property_OverflowWrap {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"anywhere"},{"type":"string","const":"break-word"},{"type":"string","const":"normal"}]
   */

    String[] consts = { "anywhere", "break-word", "normal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
