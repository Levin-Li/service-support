package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_ImageRendering
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_ImageRendering")
public @interface Property_ImageRendering {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-crisp-edges"},{"type":"string","const":"-webkit-optimize-contrast"},{"type":"string","const":"auto"},{"type":"string","const":"crisp-edges"},{"type":"string","const":"pixelated"}]
   *
   *
   */
    String[] consts = { "-moz-crisp-edges", "-webkit-optimize-contrast", "auto", "crisp-edges", "pixelated" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
