package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_GeometryBox
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_GeometryBox")
public @interface DataType_GeometryBox {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.Box"},{"type":"string","const":"fill-box"},{"type":"string","const":"margin-box"},{"type":"string","const":"stroke-box"},{"type":"string","const":"view-box"}]
   */

    String[] consts = { "fill-box", "margin-box", "stroke-box", "view-box" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
