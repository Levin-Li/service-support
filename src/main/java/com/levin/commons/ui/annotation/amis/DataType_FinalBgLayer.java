package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_FinalBgLayer
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_FinalBgLayer")
public @interface DataType_FinalBgLayer {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.Color"},{"$ref":"#/definitions/DataType.BgPosition%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.RepeatStyle"},{"$ref":"#/definitions/DataType.Attachment"},{"$ref":"#/definitions/DataType.Box"},{"type":"string","const":"none"},{"type":"string"}]
   */

    String[] consts = { "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
