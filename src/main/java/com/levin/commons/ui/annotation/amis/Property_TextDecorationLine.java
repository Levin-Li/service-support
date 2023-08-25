package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_TextDecorationLine
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_TextDecorationLine")
public @interface Property_TextDecorationLine {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"blink"},{"type":"string","const":"grammar-error"},{"type":"string","const":"line-through"},{"type":"string","const":"none"},{"type":"string","const":"overline"},{"type":"string","const":"spelling-error"},{"type":"string","const":"underline"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "blink", "grammar-error", "line-through", "none", "overline", "spelling-error", "underline" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
