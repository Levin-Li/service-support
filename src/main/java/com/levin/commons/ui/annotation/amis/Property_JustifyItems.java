package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_JustifyItems
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_JustifyItems")
public @interface Property_JustifyItems {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"baseline"},{"type":"string","const":"left"},{"type":"string","const":"legacy"},{"type":"string","const":"normal"},{"type":"string","const":"right"},{"type":"string","const":"stretch"},{"type":"string"}]
   */

    String[] consts = { "baseline", "left", "legacy", "normal", "right", "stretch" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
