package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_OverflowBlock
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_OverflowBlock")
public @interface Property_OverflowBlock {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"clip"},{"type":"string","const":"hidden"},{"type":"string","const":"scroll"},{"type":"string","const":"visible"}]
   */

    String[] consts = { "auto", "clip", "hidden", "scroll", "visible" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
