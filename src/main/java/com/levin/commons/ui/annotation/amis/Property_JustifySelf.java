package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_JustifySelf
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_JustifySelf")
public @interface Property_JustifySelf {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"auto"},{"type":"string","const":"baseline"},{"type":"string","const":"left"},{"type":"string","const":"normal"},{"type":"string","const":"right"},{"type":"string","const":"stretch"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "auto", "baseline", "left", "normal", "right", "stretch" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
