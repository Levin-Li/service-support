package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_BlockOverflow
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_BlockOverflow")
public @interface Property_BlockOverflow {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"clip"},{"type":"string","const":"ellipsis"},{"type":"string"}]
   */

    String[] consts = { "clip", "ellipsis" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
