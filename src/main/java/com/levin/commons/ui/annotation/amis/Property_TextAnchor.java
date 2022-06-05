package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_TextAnchor
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextAnchor")
public @interface Property_TextAnchor {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"end"},{"type":"string","const":"middle"},{"type":"string","const":"start"}]
   */

    String[] consts = { "end", "middle", "start" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
