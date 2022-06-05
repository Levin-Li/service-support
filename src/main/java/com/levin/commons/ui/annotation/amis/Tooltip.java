package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Tooltip
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:11
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Tooltip")
public @interface Tooltip {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"string"},{"type":"object","properties":{"title":{"type":"string","description":"标题"},"content":{"type":"string","description":"内容"}},"required":["content"],"additionalProperties":false}]
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
