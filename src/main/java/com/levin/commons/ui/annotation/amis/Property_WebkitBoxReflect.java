package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_WebkitBoxReflect
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_WebkitBoxReflect")
public @interface Property_WebkitBoxReflect {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"above"},{"type":"string","const":"below"},{"type":"string","const":"left"},{"type":"string","const":"right"}]
   *
   *
   */
    String[] consts = { "above", "below", "left", "right" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
