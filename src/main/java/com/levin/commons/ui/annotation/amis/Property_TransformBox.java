package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TransformBox
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TransformBox")
public @interface Property_TransformBox {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"border-box"},{"type":"string","const":"content-box"},{"type":"string","const":"fill-box"},{"type":"string","const":"stroke-box"},{"type":"string","const":"view-box"}]
   */

    String[] consts = { "border-box", "content-box", "fill-box", "stroke-box", "view-box" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
