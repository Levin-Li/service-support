package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_BgSize
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_BgSize")
public @interface DataType_BgSize {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"string"},{"type":"number"},{"type":"string","const":"auto"},{"type":"string","const":"contain"},{"type":"string","const":"cover"}]
   */

    String[] consts = { "auto", "contain", "cover" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
