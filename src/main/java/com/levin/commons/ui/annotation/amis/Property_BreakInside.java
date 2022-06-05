package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_BreakInside
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_BreakInside")
public @interface Property_BreakInside {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"avoid"},{"type":"string","const":"avoid-column"},{"type":"string","const":"avoid-page"},{"type":"string","const":"avoid-region"}]
   */

    String[] consts = { "auto", "avoid", "avoid-column", "avoid-page", "avoid-region" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
