package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_VerticalAlign
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_VerticalAlign")
public @interface Property_VerticalAlign {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"baseline"},{"type":"string","const":"bottom"},{"type":"string","const":"middle"},{"type":"string","const":"sub"},{"type":"string","const":"super"},{"type":"string","const":"text-bottom"},{"type":"string","const":"text-top"},{"type":"string","const":"top"}]
   */

    String[] consts = { "baseline", "bottom", "middle", "sub", "super", "text-bottom", "text-top", "top" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
