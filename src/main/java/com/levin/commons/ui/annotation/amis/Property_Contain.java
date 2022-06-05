package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_Contain
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Contain")
public @interface Property_Contain {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"content"},{"type":"string","const":"layout"},{"type":"string","const":"none"},{"type":"string","const":"paint"},{"type":"string","const":"size"},{"type":"string","const":"strict"},{"type":"string","const":"style"},{"type":"string"}]
   */

    String[] consts = { "content", "layout", "none", "paint", "size", "strict", "style" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
