package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_WhiteSpace
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
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
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-pre-wrap"},{"type":"string","const":"balance"},{"type":"string","const":"break-spaces"},{"type":"string","const":"collapse"},{"type":"string","const":"discard"},{"type":"string","const":"discard-after"},{"type":"string","const":"discard-before"},{"type":"string","const":"discard-inner"},{"type":"string","const":"none"},{"type":"string","const":"normal"},{"type":"string","const":"nowrap"},{"type":"string","const":"pre"},{"type":"string","const":"pre-line"},{"type":"string","const":"pre-wrap"},{"type":"string","const":"preserve"},{"type":"string","const":"preserve-breaks"},{"type":"string","const":"preserve-spaces"},{"type":"string","const":"pretty"},{"type":"string","const":"stable"},{"type":"string","const":"wrap"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "-moz-pre-wrap", "balance", "break-spaces", "collapse", "discard", "discard-after", "discard-before", "discard-inner", "none", "normal", "nowrap", "pre", "pre-line", "pre-wrap", "preserve", "preserve-breaks", "preserve-spaces", "pretty", "stable", "wrap" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
