package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * DataType_SingleTransition
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_SingleTransition")
public @interface DataType_SingleTransition {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.EasingFunction"},{"type":"string"},{"type":"string","const":"all"},{"type":"string","const":"none"}]
   *
   *
   */
    String[] consts = { "all", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
