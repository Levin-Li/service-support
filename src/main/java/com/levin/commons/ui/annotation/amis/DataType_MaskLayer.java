package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * DataType_MaskLayer
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
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
   *
   *
   */
    String[] consts = { "no-clip", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
