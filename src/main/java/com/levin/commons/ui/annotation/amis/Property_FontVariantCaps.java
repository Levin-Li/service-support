package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_FontVariantCaps
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FontVariantCaps")
public @interface Property_FontVariantCaps {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all-petite-caps"},{"type":"string","const":"all-small-caps"},{"type":"string","const":"normal"},{"type":"string","const":"petite-caps"},{"type":"string","const":"small-caps"},{"type":"string","const":"titling-caps"},{"type":"string","const":"unicase"}]
   *
   *
   */
    String[] consts = { "all-petite-caps", "all-small-caps", "normal", "petite-caps", "small-caps", "titling-caps", "unicase" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
