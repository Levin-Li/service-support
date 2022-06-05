package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_GridAutoFlow
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_GridAutoFlow")
public @interface Property_GridAutoFlow {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"column"},{"type":"string","const":"dense"},{"type":"string","const":"row"},{"type":"string"}]
   */

    String[] consts = { "column", "dense", "row" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
