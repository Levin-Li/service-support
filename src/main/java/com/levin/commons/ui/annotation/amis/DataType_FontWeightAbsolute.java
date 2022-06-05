package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_FontWeightAbsolute
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_FontWeightAbsolute")
public @interface DataType_FontWeightAbsolute {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"string","const":"bold"},{"type":"string","const":"normal"},{"type":"number"},{"type":"string"}]
   */

    String[] consts = { "bold", "normal" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
