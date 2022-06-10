package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_ColorScheme
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ColorScheme")
public @interface Property_ColorScheme {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"dark"},{"type":"string","const":"light"},{"type":"string","const":"normal"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "dark", "light", "normal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
