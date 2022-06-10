package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_Azimuth
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Azimuth")
public @interface Property_Azimuth {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"behind"},{"type":"string","const":"center"},{"type":"string","const":"center-left"},{"type":"string","const":"center-right"},{"type":"string","const":"far-left"},{"type":"string","const":"far-right"},{"type":"string","const":"left"},{"type":"string","const":"left-side"},{"type":"string","const":"leftwards"},{"type":"string","const":"right"},{"type":"string","const":"right-side"},{"type":"string","const":"rightwards"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "behind", "center", "center-left", "center-right", "far-left", "far-right", "left", "left-side", "leftwards", "right", "right-side", "rightwards" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
