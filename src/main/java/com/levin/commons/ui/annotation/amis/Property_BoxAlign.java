package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_BoxAlign
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_BoxAlign")
public @interface Property_BoxAlign {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"baseline"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"start"},{"type":"string","const":"stretch"}]
   *
   *
   */
    String[] consts = { "baseline", "center", "end", "start", "stretch" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
