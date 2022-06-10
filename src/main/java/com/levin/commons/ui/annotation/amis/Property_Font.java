package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_Font
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_Font")
public @interface Property_Font {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"caption"},{"type":"string","const":"icon"},{"type":"string","const":"menu"},{"type":"string","const":"message-box"},{"type":"string","const":"small-caption"},{"type":"string","const":"status-bar"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "caption", "icon", "menu", "message-box", "small-caption", "status-bar" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
