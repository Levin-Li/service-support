package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_Font
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Font")
public @interface Property_Font {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"caption"},{"type":"string","const":"icon"},{"type":"string","const":"menu"},{"type":"string","const":"message-box"},{"type":"string","const":"small-caption"},{"type":"string","const":"status-bar"},{"type":"string"}]
   */

    String[] consts = { "caption", "icon", "menu", "message-box", "small-caption", "status-bar" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
