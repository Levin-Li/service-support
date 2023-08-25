package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_TextUnderlinePosition
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_TextUnderlinePosition")
public @interface Property_TextUnderlinePosition {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"from-font"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"under"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "auto", "from-font", "left", "right", "under" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
