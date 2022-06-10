package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * DataType_EasingFunction
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_EasingFunction")
public @interface DataType_EasingFunction {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.CubicBezierTimingFunction"},{"$ref":"#/definitions/DataType.StepTimingFunction"},{"type":"string","const":"linear"}]
   *
   *
   */
    String[] consts = { "linear" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
