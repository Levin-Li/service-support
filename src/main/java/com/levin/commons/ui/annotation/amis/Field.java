package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Field
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Field")
public @interface Field {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/FieldSimple"},{"$ref":"#/definitions/FieldGroup"},{"type":"object","properties":{"type":{"type":"string","const":"group"},"label":{"type":"string"},"name":{"type":"string"},"children":{"type":"array","items":{"$ref":"#/definitions/FieldSimple"}}},"required":["type","label","name","children"],"additionalProperties":false}]
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
