package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * DataType_Color
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "DataType_Color")
public @interface DataType_Color {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.NamedColor"},{"$ref":"#/definitions/DataType.DeprecatedSystemColor"},{"type":"string","const":"currentcolor"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "currentcolor" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
