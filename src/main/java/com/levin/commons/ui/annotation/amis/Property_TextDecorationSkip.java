package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextDecorationSkip
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextDecorationSkip")
public @interface Property_TextDecorationSkip {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"box-decoration"},{"type":"string","const":"edges"},{"type":"string","const":"leading-spaces"},{"type":"string","const":"none"},{"type":"string","const":"objects"},{"type":"string","const":"spaces"},{"type":"string","const":"trailing-spaces"},{"type":"string"}]
   */

    String[] consts = { "box-decoration", "edges", "leading-spaces", "none", "objects", "spaces", "trailing-spaces" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
