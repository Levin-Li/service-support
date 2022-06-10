package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_FlexFlow
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_FlexFlow")
public @interface Property_FlexFlow {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"column"},{"type":"string","const":"column-reverse"},{"type":"string","const":"nowrap"},{"type":"string","const":"row"},{"type":"string","const":"row-reverse"},{"type":"string","const":"wrap"},{"type":"string","const":"wrap-reverse"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "column", "column-reverse", "nowrap", "row", "row-reverse", "wrap", "wrap-reverse" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
