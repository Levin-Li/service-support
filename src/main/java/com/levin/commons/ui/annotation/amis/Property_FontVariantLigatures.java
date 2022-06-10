package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_FontVariantLigatures
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FontVariantLigatures")
public @interface Property_FontVariantLigatures {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"common-ligatures"},{"type":"string","const":"contextual"},{"type":"string","const":"discretionary-ligatures"},{"type":"string","const":"historical-ligatures"},{"type":"string","const":"no-common-ligatures"},{"type":"string","const":"no-contextual"},{"type":"string","const":"no-discretionary-ligatures"},{"type":"string","const":"no-historical-ligatures"},{"type":"string","const":"none"},{"type":"string","const":"normal"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "common-ligatures", "contextual", "discretionary-ligatures", "historical-ligatures", "no-common-ligatures", "no-contextual", "no-discretionary-ligatures", "no-historical-ligatures", "none", "normal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
