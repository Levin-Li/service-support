package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_AlignSelf
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_AlignSelf")
public @interface Property_AlignSelf {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"auto"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string","const":"stretch"},{"type":"string"}]
   */

    String[] consts = { "auto", "baseline", "normal", "stretch" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
