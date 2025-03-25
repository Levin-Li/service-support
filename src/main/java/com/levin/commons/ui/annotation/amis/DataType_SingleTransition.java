package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * DataType_SingleTransition
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "DataType_SingleTransition")
public @interface DataType_SingleTransition {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.EasingFunction"},{"type":"string"},{"type":"string","const":"all"},{"type":"string","const":"allow-discrete"},{"type":"string","const":"none"},{"type":"string","const":"normal"}]
   *
   *
   */
    String[] consts = { "all", "allow-discrete", "none", "normal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
