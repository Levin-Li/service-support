package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_Paint
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_Paint")
public @interface DataType_Paint {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"child"},{"type":"string","const":"context-fill"},{"type":"string","const":"context-stroke"},{"type":"string","const":"none"},{"type":"string"}]
   */

    String[] consts = { "child", "context-fill", "context-stroke", "none" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
