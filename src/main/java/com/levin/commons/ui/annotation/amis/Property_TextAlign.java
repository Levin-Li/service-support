package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_TextAlign
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_TextAlign")
public @interface Property_TextAlign {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"justify"},{"type":"string","const":"left"},{"type":"string","const":"match-parent"},{"type":"string","const":"right"},{"type":"string","const":"start"}]
   *
   *
   */
    String[] consts = { "center", "end", "justify", "left", "match-parent", "right", "start" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
