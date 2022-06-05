package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextEmphasisStyle
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextEmphasisStyle")
public @interface Property_TextEmphasisStyle {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"circle"},{"type":"string","const":"dot"},{"type":"string","const":"double-circle"},{"type":"string","const":"filled"},{"type":"string","const":"none"},{"type":"string","const":"open"},{"type":"string","const":"sesame"},{"type":"string","const":"triangle"},{"type":"string"}]
   */

    String[] consts = { "circle", "dot", "double-circle", "filled", "none", "open", "sesame", "triangle" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
