package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_PointerEvents
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_PointerEvents")
public @interface Property_PointerEvents {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"auto"},{"type":"string","const":"fill"},{"type":"string","const":"inherit"},{"type":"string","const":"none"},{"type":"string","const":"painted"},{"type":"string","const":"stroke"},{"type":"string","const":"visible"},{"type":"string","const":"visibleFill"},{"type":"string","const":"visiblePainted"},{"type":"string","const":"visibleStroke"}]
   */

    String[] consts = { "all", "auto", "fill", "inherit", "none", "painted", "stroke", "visible", "visibleFill", "visiblePainted", "visibleStroke" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
