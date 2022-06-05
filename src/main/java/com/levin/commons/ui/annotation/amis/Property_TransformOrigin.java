package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TransformOrigin
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TransformOrigin")
public @interface Property_TransformOrigin {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"bottom"},{"type":"string","const":"center"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"top"}]
   */

    String[] consts = { "bottom", "center", "left", "right", "top" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
