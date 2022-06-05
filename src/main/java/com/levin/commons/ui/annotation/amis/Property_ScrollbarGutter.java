package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ScrollbarGutter
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ScrollbarGutter")
public @interface Property_ScrollbarGutter {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"stable"},{"type":"string"}]
   */

    String[] consts = { "auto", "stable" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
