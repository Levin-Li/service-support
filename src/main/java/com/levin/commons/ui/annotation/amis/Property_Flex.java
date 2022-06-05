package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_Flex
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Flex")
public @interface Property_Flex {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"},{"type":"string","const":"content"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"},{"type":"string","const":"none"}]
   */

    String[] consts = { "auto", "content", "fit-content", "max-content", "min-content", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
