package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * PopOver
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "PopOver")
public @interface PopOver {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"boolean"},{"$ref":"#/definitions/SchemaPopOverObject"}]
   */


//////////////////////////////////////////////
   /**
    *
    */
   boolean value() default false;

}
