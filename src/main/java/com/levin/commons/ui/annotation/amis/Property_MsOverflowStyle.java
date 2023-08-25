package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_MsOverflowStyle
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_MsOverflowStyle")
public @interface Property_MsOverflowStyle {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-ms-autohiding-scrollbar"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string","const":"scrollbar"}]
   *
   *
   */
    String[] consts = { "-ms-autohiding-scrollbar", "auto", "none", "scrollbar" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
