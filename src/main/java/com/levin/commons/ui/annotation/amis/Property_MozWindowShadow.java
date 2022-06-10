package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_MozWindowShadow
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MozWindowShadow")
public @interface Property_MozWindowShadow {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"default"},{"type":"string","const":"menu"},{"type":"string","const":"none"},{"type":"string","const":"sheet"},{"type":"string","const":"tooltip"}]
   *
   *
   */
    String[] consts = { "default", "menu", "none", "sheet", "tooltip" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
