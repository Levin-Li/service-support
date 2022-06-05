package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_MsContentZoomSnap
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MsContentZoomSnap")
public @interface Property_MsContentZoomSnap {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"mandatory"},{"type":"string","const":"none"},{"type":"string","const":"proximity"},{"type":"string"}]
   */

    String[] consts = { "mandatory", "none", "proximity" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
