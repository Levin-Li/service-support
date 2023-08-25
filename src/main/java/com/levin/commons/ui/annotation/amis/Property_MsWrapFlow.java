package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_MsWrapFlow
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_MsWrapFlow")
public @interface Property_MsWrapFlow {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"both"},{"type":"string","const":"clear"},{"type":"string","const":"end"},{"type":"string","const":"maximum"},{"type":"string","const":"start"}]
   *
   *
   */
    String[] consts = { "auto", "both", "clear", "end", "maximum", "start" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
