package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_BgPosition
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_BgPosition")
public @interface DataType_BgPosition {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"string"},{"type":"number"},{"type":"string","const":"bottom"},{"type":"string","const":"center"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"top"}]
   */

    String[] consts = { "bottom", "center", "left", "right", "top" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
