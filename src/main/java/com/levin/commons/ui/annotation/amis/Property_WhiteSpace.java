package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_WhiteSpace
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_WhiteSpace")
public @interface Property_WhiteSpace {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-pre-wrap"},{"type":"string","const":"break-spaces"},{"type":"string","const":"normal"},{"type":"string","const":"nowrap"},{"type":"string","const":"pre"},{"type":"string","const":"pre-line"},{"type":"string","const":"pre-wrap"}]
   *
   *
   */
    String[] consts = { "-moz-pre-wrap", "break-spaces", "normal", "nowrap", "pre", "pre-line", "pre-wrap" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
