package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_PageBreakAfter
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_PageBreakAfter")
public @interface Property_PageBreakAfter {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"always"},{"type":"string","const":"auto"},{"type":"string","const":"avoid"},{"type":"string","const":"left"},{"type":"string","const":"recto"},{"type":"string","const":"right"},{"type":"string","const":"verso"}]
   */

    String[] consts = { "always", "auto", "avoid", "left", "recto", "right", "verso" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
