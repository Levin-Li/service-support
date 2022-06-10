package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_MozUserFocus
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MozUserFocus")
public @interface Property_MozUserFocus {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"ignore"},{"type":"string","const":"none"},{"type":"string","const":"normal"},{"type":"string","const":"select-after"},{"type":"string","const":"select-all"},{"type":"string","const":"select-before"},{"type":"string","const":"select-menu"},{"type":"string","const":"select-same"}]
   *
   *
   */
    String[] consts = { "ignore", "none", "normal", "select-after", "select-all", "select-before", "select-menu", "select-same" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
