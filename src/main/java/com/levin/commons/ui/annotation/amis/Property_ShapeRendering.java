package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_ShapeRendering
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ShapeRendering")
public @interface Property_ShapeRendering {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"crispEdges"},{"type":"string","const":"geometricPrecision"},{"type":"string","const":"optimizeSpeed"}]
   */

    String[] consts = { "auto", "crispEdges", "geometricPrecision", "optimizeSpeed" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
