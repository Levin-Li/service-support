package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_Resize
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Resize")
public @interface Property_Resize {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block"},{"type":"string","const":"both"},{"type":"string","const":"horizontal"},{"type":"string","const":"inline"},{"type":"string","const":"none"},{"type":"string","const":"vertical"}]
   *
   *
   */
    String[] consts = { "block", "both", "horizontal", "inline", "none", "vertical" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
