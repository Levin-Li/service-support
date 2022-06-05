package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_GridTemplateRows
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_GridTemplateRows")
public @interface Property_GridTemplateRows {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.TrackBreadth%3C(string%7Cnumber)%3E"},{"type":"string","const":"none"},{"type":"string","const":"subgrid"},{"type":"string"}]
   */

    String[] consts = { "none", "subgrid" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
