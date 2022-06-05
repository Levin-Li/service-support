package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_Color
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_Color")
public @interface DataType_Color {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.NamedColor"},{"$ref":"#/definitions/DataType.DeprecatedSystemColor"},{"type":"string","const":"currentcolor"},{"type":"string"}]
   */

    String[] consts = { "currentcolor" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
