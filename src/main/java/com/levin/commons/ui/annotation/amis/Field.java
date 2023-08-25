package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Field
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Field")
public @interface Field {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/FieldSimple"},{"$ref":"#/definitions/FieldGroup"},{"type":"object","properties":{"type":{"type":"string","const":"group"},"label":{"type":"string"},"name":{"type":"string"},"children":{"type":"array","items":{"$ref":"#/definitions/FieldSimple"}}},"required":["type","label","name","children"],"additionalProperties":false}]
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
