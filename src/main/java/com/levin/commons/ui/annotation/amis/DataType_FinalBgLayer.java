package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * DataType_FinalBgLayer
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "DataType_FinalBgLayer")
public @interface DataType_FinalBgLayer {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.Color"},{"$ref":"#/definitions/DataType.BgPosition%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.RepeatStyle"},{"$ref":"#/definitions/DataType.Attachment"},{"$ref":"#/definitions/DataType.Box"},{"type":"string","const":"none"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
