package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_TextTransform
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextTransform")
public @interface Property_TextTransform {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"capitalize"},{"type":"string","const":"full-size-kana"},{"type":"string","const":"full-width"},{"type":"string","const":"lowercase"},{"type":"string","const":"none"},{"type":"string","const":"uppercase"}]
   *
   *
   */
    String[] consts = { "capitalize", "full-size-kana", "full-width", "lowercase", "none", "uppercase" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
