package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_ScrollTimelineAxis
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_ScrollTimelineAxis")
public @interface Property_ScrollTimelineAxis {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block"},{"type":"string","const":"inline"},{"type":"string","const":"x"},{"type":"string","const":"y"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "block", "inline", "x", "y" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
