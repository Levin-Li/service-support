package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_BackgroundPosition
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_BackgroundPosition")
public @interface Property_BackgroundPosition {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.BgPosition%3C(string%7Cnumber)%3E"},{"type":"string"}]
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}