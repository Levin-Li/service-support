package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_Clear
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_Clear")
public @interface Property_Clear {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"both"},{"type":"string","const":"inline-end"},{"type":"string","const":"inline-start"},{"type":"string","const":"left"},{"type":"string","const":"none"},{"type":"string","const":"right"}]
   *
   *
   */
    String[] consts = { "both", "inline-end", "inline-start", "left", "none", "right" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
