package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_LineWidth
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_LineWidth")
public @interface DataType_LineWidth {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"string"},{"type":"number"},{"type":"string","const":"medium"},{"type":"string","const":"thick"},{"type":"string","const":"thin"}]
   */

    String[] consts = { "medium", "thick", "thin" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
