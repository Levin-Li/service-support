package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_Offset
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_Offset")
public @interface Property_Offset {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.GeometryBox"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "auto", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
