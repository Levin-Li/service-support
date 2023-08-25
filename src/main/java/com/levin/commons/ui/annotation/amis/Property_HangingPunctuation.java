package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_HangingPunctuation
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_HangingPunctuation")
public @interface Property_HangingPunctuation {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"allow-end"},{"type":"string","const":"first"},{"type":"string","const":"force-end"},{"type":"string","const":"last"},{"type":"string","const":"none"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "allow-end", "first", "force-end", "last", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
