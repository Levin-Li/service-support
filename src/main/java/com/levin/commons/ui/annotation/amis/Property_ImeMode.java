package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_ImeMode
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_ImeMode")
public @interface Property_ImeMode {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"active"},{"type":"string","const":"auto"},{"type":"string","const":"disabled"},{"type":"string","const":"inactive"},{"type":"string","const":"normal"}]
   *
   *
   */
    String[] consts = { "active", "auto", "disabled", "inactive", "normal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
