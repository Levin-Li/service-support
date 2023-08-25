package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_TextDecoration
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_TextDecoration")
public @interface Property_TextDecoration {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"},{"type":"string","const":"blink"},{"type":"string","const":"dashed"},{"type":"string","const":"dotted"},{"type":"string","const":"double"},{"type":"string","const":"from-font"},{"type":"string","const":"grammar-error"},{"type":"string","const":"line-through"},{"type":"string","const":"none"},{"type":"string","const":"overline"},{"type":"string","const":"solid"},{"type":"string","const":"spelling-error"},{"type":"string","const":"underline"},{"type":"string","const":"wavy"}]
   *
   *
   */
    String[] consts = { "auto", "blink", "dashed", "dotted", "double", "from-font", "grammar-error", "line-through", "none", "overline", "solid", "spelling-error", "underline", "wavy" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
