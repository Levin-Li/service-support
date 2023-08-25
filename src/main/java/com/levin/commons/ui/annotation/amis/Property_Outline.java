package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_Outline
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_Outline")
public @interface Property_Outline {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"type":"string","const":"auto"},{"type":"string","const":"invert"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "auto", "invert" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
