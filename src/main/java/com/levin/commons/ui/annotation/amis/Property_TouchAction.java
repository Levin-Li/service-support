package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_TouchAction
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TouchAction")
public @interface Property_TouchAction {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-ms-manipulation"},{"type":"string","const":"-ms-none"},{"type":"string","const":"-ms-pinch-zoom"},{"type":"string","const":"auto"},{"type":"string","const":"manipulation"},{"type":"string","const":"none"},{"type":"string","const":"pan-down"},{"type":"string","const":"pan-left"},{"type":"string","const":"pan-right"},{"type":"string","const":"pan-up"},{"type":"string","const":"pan-x"},{"type":"string","const":"pan-y"},{"type":"string","const":"pinch-zoom"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "-ms-manipulation", "-ms-none", "-ms-pinch-zoom", "auto", "manipulation", "none", "pan-down", "pan-left", "pan-right", "pan-up", "pan-x", "pan-y", "pinch-zoom" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
