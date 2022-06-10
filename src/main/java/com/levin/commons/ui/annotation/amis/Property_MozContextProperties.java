package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_MozContextProperties
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MozContextProperties")
public @interface Property_MozContextProperties {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"fill"},{"type":"string","const":"fill-opacity"},{"type":"string","const":"none"},{"type":"string","const":"stroke"},{"type":"string","const":"stroke-opacity"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "fill", "fill-opacity", "none", "stroke", "stroke-opacity" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
