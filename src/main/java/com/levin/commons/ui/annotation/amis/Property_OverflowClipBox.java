package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_OverflowClipBox
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_OverflowClipBox")
public @interface Property_OverflowClipBox {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"content-box"},{"type":"string","const":"padding-box"}]
   */

    String[] consts = { "content-box", "padding-box" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
