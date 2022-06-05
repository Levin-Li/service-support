package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_FontVariantAlternates
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FontVariantAlternates")
public @interface Property_FontVariantAlternates {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"historical-forms"},{"type":"string","const":"normal"},{"type":"string"}]
   */

    String[] consts = { "historical-forms", "normal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
