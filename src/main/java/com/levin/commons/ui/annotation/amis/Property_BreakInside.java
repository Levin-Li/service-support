package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_BreakInside
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_BreakInside")
public @interface Property_BreakInside {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"avoid"},{"type":"string","const":"avoid-column"},{"type":"string","const":"avoid-page"},{"type":"string","const":"avoid-region"}]
   *
   *
   */
    String[] consts = { "auto", "avoid", "avoid-column", "avoid-page", "avoid-region" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
