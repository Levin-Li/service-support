package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextAlign
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextAlign")
public @interface Property_TextAlign {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"justify"},{"type":"string","const":"left"},{"type":"string","const":"match-parent"},{"type":"string","const":"right"},{"type":"string","const":"start"}]
   */

    String[] consts = { "center", "end", "justify", "left", "match-parent", "right", "start" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
