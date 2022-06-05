package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextCombineUpright
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextCombineUpright")
public @interface Property_TextCombineUpright {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"none"},{"type":"string"}]
   */

    String[] consts = { "all", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
