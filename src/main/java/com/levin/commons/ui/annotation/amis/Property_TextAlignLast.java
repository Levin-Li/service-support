package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextAlignLast
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextAlignLast")
public @interface Property_TextAlignLast {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"justify"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"start"}]
   */

    String[] consts = { "auto", "center", "end", "justify", "left", "right", "start" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
