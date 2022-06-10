package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_WebkitMaskClip
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_WebkitMaskClip")
public @interface Property_WebkitMaskClip {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Box"},{"type":"string","const":"border"},{"type":"string","const":"content"},{"type":"string","const":"padding"},{"type":"string","const":"text"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "border", "content", "padding", "text" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
