package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_TextDecorationSkip
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_TextDecorationSkip")
public @interface Property_TextDecorationSkip {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"box-decoration"},{"type":"string","const":"edges"},{"type":"string","const":"leading-spaces"},{"type":"string","const":"none"},{"type":"string","const":"objects"},{"type":"string","const":"spaces"},{"type":"string","const":"trailing-spaces"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "box-decoration", "edges", "leading-spaces", "none", "objects", "spaces", "trailing-spaces" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
