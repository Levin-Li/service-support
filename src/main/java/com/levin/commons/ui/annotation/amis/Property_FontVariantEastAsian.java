package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_FontVariantEastAsian
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FontVariantEastAsian")
public @interface Property_FontVariantEastAsian {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EastAsianVariantValues"},{"type":"string","const":"full-width"},{"type":"string","const":"normal"},{"type":"string","const":"proportional-width"},{"type":"string","const":"ruby"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "full-width", "normal", "proportional-width", "ruby" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
