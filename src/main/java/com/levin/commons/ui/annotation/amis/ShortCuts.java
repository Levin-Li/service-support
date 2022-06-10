package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ShortCuts
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "ShortCuts")
public @interface ShortCuts {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"object","properties":{"label":{"type":"string"},"value":{"type":"string"}},"required":["label","value"],"additionalProperties":false},{"$ref":"#/definitions/ShortCutDate"},{"$ref":"#/definitions/ShortCutDateRange"}]
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}