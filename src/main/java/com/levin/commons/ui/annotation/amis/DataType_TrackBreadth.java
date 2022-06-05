package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_TrackBreadth
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_TrackBreadth")
public @interface DataType_TrackBreadth {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"string"},{"type":"number"},{"type":"string","const":"auto"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"}]
   */

    String[] consts = { "auto", "max-content", "min-content" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
