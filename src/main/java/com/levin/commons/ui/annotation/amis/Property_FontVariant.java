package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_FontVariant
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FontVariant")
public @interface Property_FontVariant {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EastAsianVariantValues"},{"type":"string","const":"all-petite-caps"},{"type":"string","const":"all-small-caps"},{"type":"string","const":"common-ligatures"},{"type":"string","const":"contextual"},{"type":"string","const":"diagonal-fractions"},{"type":"string","const":"discretionary-ligatures"},{"type":"string","const":"full-width"},{"type":"string","const":"historical-forms"},{"type":"string","const":"historical-ligatures"},{"type":"string","const":"lining-nums"},{"type":"string","const":"no-common-ligatures"},{"type":"string","const":"no-contextual"},{"type":"string","const":"no-discretionary-ligatures"},{"type":"string","const":"no-historical-ligatures"},{"type":"string","const":"none"},{"type":"string","const":"normal"},{"type":"string","const":"oldstyle-nums"},{"type":"string","const":"ordinal"},{"type":"string","const":"petite-caps"},{"type":"string","const":"proportional-nums"},{"type":"string","const":"proportional-width"},{"type":"string","const":"ruby"},{"type":"string","const":"slashed-zero"},{"type":"string","const":"small-caps"},{"type":"string","const":"stacked-fractions"},{"type":"string","const":"tabular-nums"},{"type":"string","const":"titling-caps"},{"type":"string","const":"unicase"},{"type":"string"}]
   */

    String[] consts = { "all-petite-caps", "all-small-caps", "common-ligatures", "contextual", "diagonal-fractions", "discretionary-ligatures", "full-width", "historical-forms", "historical-ligatures", "lining-nums", "no-common-ligatures", "no-contextual", "no-discretionary-ligatures", "no-historical-ligatures", "none", "normal", "oldstyle-nums", "ordinal", "petite-caps", "proportional-nums", "proportional-width", "ruby", "slashed-zero", "small-caps", "stacked-fractions", "tabular-nums", "titling-caps", "unicase" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
