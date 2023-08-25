package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_JustifyContent
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_JustifyContent")
public @interface Property_JustifyContent {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.ContentDistribution"},{"$ref":"#/definitions/DataType.ContentPosition"},{"type":"string","const":"left"},{"type":"string","const":"normal"},{"type":"string","const":"right"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "left", "normal", "right" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
