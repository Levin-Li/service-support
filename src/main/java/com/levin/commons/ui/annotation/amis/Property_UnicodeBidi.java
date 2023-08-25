package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_UnicodeBidi
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_UnicodeBidi")
public @interface Property_UnicodeBidi {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-isolate"},{"type":"string","const":"-moz-isolate-override"},{"type":"string","const":"-moz-plaintext"},{"type":"string","const":"-webkit-isolate"},{"type":"string","const":"-webkit-isolate-override"},{"type":"string","const":"-webkit-plaintext"},{"type":"string","const":"bidi-override"},{"type":"string","const":"embed"},{"type":"string","const":"isolate"},{"type":"string","const":"isolate-override"},{"type":"string","const":"normal"},{"type":"string","const":"plaintext"}]
   *
   *
   */
    String[] consts = { "-moz-isolate", "-moz-isolate-override", "-moz-plaintext", "-webkit-isolate", "-webkit-isolate-override", "-webkit-plaintext", "bidi-override", "embed", "isolate", "isolate-override", "normal", "plaintext" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
