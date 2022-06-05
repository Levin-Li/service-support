package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * ColorMapType
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "ColorMapType")
public @interface ColorMapType {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"array","items":{"type":"string"}},{"type":"array","items":{"type":"object","properties":{"value":{"type":"number"},"color":{"type":"string"}},"required":["value","color"],"additionalProperties":false}},{"type":"string"}]
   */


//////////////////////////////////////////////
   /**
    *
    */
   String[] value() default "	";

}
