package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_RubyPosition
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_RubyPosition")
public @interface Property_RubyPosition {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"alternate"},{"type":"string","const":"inter-character"},{"type":"string","const":"over"},{"type":"string","const":"under"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "alternate", "inter-character", "over", "under" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
