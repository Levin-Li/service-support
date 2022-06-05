package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Function
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Function")
public @interface Function {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"string"},{"type":"object","properties":{"prototype":{},"length":{"type":"number"},"arguments":{},"caller":{"$ref":"#/definitions/interface-2073358172-9821-11285-2073358172-0-212510"}},"required":["prototype","length","arguments","caller"],"additionalProperties":false}]
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
