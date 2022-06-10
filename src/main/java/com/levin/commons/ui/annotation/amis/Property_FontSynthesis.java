package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_FontSynthesis
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FontSynthesis")
public @interface Property_FontSynthesis {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string","const":"small-caps"},{"type":"string","const":"style"},{"type":"string","const":"weight"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "none", "small-caps", "style", "weight" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
