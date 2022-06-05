package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_PlaceContent
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_PlaceContent")
public @interface Property_PlaceContent {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.ContentDistribution"},{"$ref":"#/definitions/DataType.ContentPosition"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string"}]
   */

    String[] consts = { "baseline", "normal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
