package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_CaretColor
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_CaretColor")
public @interface Property_CaretColor {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"auto"}]
   *
   *
   */
    String[] consts = { "auto" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
