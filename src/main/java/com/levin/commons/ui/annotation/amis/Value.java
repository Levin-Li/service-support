package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Value
 *
 * Range 文档：https://baidu.gitee.io/amis/docs/components/form/range
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Range 文档：https://baidu.gitee.io/amis/docs/components/form/range")
public @interface Value {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"string"},{"$ref":"#/definitions/MultipleValue"},{"type":"number"},{"type":"array","items":{"type":"number"},"minItems":2,"maxItems":2}]
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
