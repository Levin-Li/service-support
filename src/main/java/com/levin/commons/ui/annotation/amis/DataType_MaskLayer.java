package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_MaskLayer
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_MaskLayer")
public @interface DataType_MaskLayer {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.RepeatStyle"},{"$ref":"#/definitions/DataType.GeometryBox"},{"$ref":"#/definitions/DataType.CompositingOperator"},{"$ref":"#/definitions/DataType.MaskingMode"},{"type":"string","const":"no-clip"},{"type":"string","const":"none"},{"type":"string"}]
   */

    String[] consts = { "no-clip", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
