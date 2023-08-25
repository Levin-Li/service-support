package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_TextDecorationStyle
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_TextDecorationStyle")
public @interface Property_TextDecorationStyle {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"dashed"},{"type":"string","const":"dotted"},{"type":"string","const":"double"},{"type":"string","const":"solid"},{"type":"string","const":"wavy"}]
   *
   *
   */
    String[] consts = { "dashed", "dotted", "double", "solid", "wavy" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
