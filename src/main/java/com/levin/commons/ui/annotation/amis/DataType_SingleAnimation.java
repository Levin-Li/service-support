package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * DataType_SingleAnimation
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "DataType_SingleAnimation")
public @interface DataType_SingleAnimation {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.EasingFunction"},{"$ref":"#/definitions/DataType.SingleAnimationDirection"},{"$ref":"#/definitions/DataType.SingleAnimationFillMode"},{"$ref":"#/definitions/DataType.SingleAnimationTimeline"},{"type":"string"},{"type":"string","const":"infinite"},{"type":"string","const":"none"},{"type":"string","const":"paused"},{"type":"string","const":"running"},{"type":"number"}]
   *
   *
   */
    String[] consts = { "infinite", "none", "paused", "running" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
