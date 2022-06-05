package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_GridLine
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_GridLine")
public @interface DataType_GridLine {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"string","const":"auto"},{"type":"string"},{"type":"number"}]
   */

    String[] consts = { "auto" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
