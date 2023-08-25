package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * DataType_Paint
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "DataType_Paint")
public @interface DataType_Paint {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"child"},{"type":"string","const":"context-fill"},{"type":"string","const":"context-stroke"},{"type":"string","const":"none"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "child", "context-fill", "context-stroke", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
