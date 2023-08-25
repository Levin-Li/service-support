package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_BoxOrient
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_BoxOrient")
public @interface Property_BoxOrient {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block-axis"},{"type":"string","const":"horizontal"},{"type":"string","const":"inherit"},{"type":"string","const":"inline-axis"},{"type":"string","const":"vertical"}]
   *
   *
   */
    String[] consts = { "block-axis", "horizontal", "inherit", "inline-axis", "vertical" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
