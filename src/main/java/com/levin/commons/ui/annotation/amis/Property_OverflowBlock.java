package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_OverflowBlock
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_OverflowBlock")
public @interface Property_OverflowBlock {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"clip"},{"type":"string","const":"hidden"},{"type":"string","const":"scroll"},{"type":"string","const":"visible"}]
   *
   *
   */
    String[] consts = { "auto", "clip", "hidden", "scroll", "visible" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
