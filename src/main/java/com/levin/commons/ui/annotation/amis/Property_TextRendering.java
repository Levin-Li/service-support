package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_TextRendering
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_TextRendering")
public @interface Property_TextRendering {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"geometricPrecision"},{"type":"string","const":"optimizeLegibility"},{"type":"string","const":"optimizeSpeed"}]
   *
   *
   */
    String[] consts = { "auto", "geometricPrecision", "optimizeLegibility", "optimizeSpeed" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
