package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_UserSelect
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_UserSelect")
public @interface Property_UserSelect {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-none"},{"type":"string","const":"all"},{"type":"string","const":"auto"},{"type":"string","const":"contain"},{"type":"string","const":"element"},{"type":"string","const":"none"},{"type":"string","const":"text"}]
   *
   *
   */
    String[] consts = { "-moz-none", "all", "auto", "contain", "element", "none", "text" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
