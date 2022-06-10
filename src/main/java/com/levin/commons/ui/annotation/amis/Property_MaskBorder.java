package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_MaskBorder
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MaskBorder")
public @interface Property_MaskBorder {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"alpha"},{"type":"string","const":"luminance"},{"type":"string","const":"none"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"},{"type":"string","const":"stretch"},{"type":"string"},{"type":"number"}]
   *
   *
   */
    String[] consts = { "alpha", "luminance", "none", "repeat", "round", "space", "stretch" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
