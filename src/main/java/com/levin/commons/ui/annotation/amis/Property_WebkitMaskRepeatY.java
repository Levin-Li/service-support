package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_WebkitMaskRepeatY
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_WebkitMaskRepeatY")
public @interface Property_WebkitMaskRepeatY {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"no-repeat"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"}]
   *
   *
   */
    String[] consts = { "no-repeat", "repeat", "round", "space" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
