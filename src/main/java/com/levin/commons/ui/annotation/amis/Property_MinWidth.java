package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_MinWidth
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MinWidth")
public @interface Property_MinWidth {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-fit-content"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fill-available"},{"type":"string","const":"-webkit-fit-content"},{"type":"string","const":"-webkit-max-content"},{"type":"string","const":"-webkit-min-content"},{"type":"string","const":"auto"},{"type":"string","const":"fit-content"},{"type":"string","const":"intrinsic"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"},{"type":"string","const":"min-intrinsic"}]
   *
   *
   */
    String[] consts = { "-moz-fit-content", "-moz-max-content", "-moz-min-content", "-webkit-fill-available", "-webkit-fit-content", "-webkit-max-content", "-webkit-min-content", "auto", "fit-content", "intrinsic", "max-content", "min-content", "min-intrinsic" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
