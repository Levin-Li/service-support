package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_BreakAfter
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_BreakAfter")
public @interface Property_BreakAfter {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"always"},{"type":"string","const":"auto"},{"type":"string","const":"avoid"},{"type":"string","const":"avoid-column"},{"type":"string","const":"avoid-page"},{"type":"string","const":"avoid-region"},{"type":"string","const":"column"},{"type":"string","const":"left"},{"type":"string","const":"page"},{"type":"string","const":"recto"},{"type":"string","const":"region"},{"type":"string","const":"right"},{"type":"string","const":"verso"}]
   *
   *
   */
    String[] consts = { "all", "always", "auto", "avoid", "avoid-column", "avoid-page", "avoid-region", "column", "left", "page", "recto", "region", "right", "verso" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
