package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_WhiteSpaceTrim
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_WhiteSpaceTrim")
public @interface Property_WhiteSpaceTrim {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"discard-after"},{"type":"string","const":"discard-before"},{"type":"string","const":"discard-inner"},{"type":"string","const":"none"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "discard-after", "discard-before", "discard-inner", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
