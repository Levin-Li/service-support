package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_FontVariantNumeric
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_FontVariantNumeric")
public @interface Property_FontVariantNumeric {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"diagonal-fractions"},{"type":"string","const":"lining-nums"},{"type":"string","const":"normal"},{"type":"string","const":"oldstyle-nums"},{"type":"string","const":"ordinal"},{"type":"string","const":"proportional-nums"},{"type":"string","const":"slashed-zero"},{"type":"string","const":"stacked-fractions"},{"type":"string","const":"tabular-nums"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "diagonal-fractions", "lining-nums", "normal", "oldstyle-nums", "ordinal", "proportional-nums", "slashed-zero", "stacked-fractions", "tabular-nums" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
