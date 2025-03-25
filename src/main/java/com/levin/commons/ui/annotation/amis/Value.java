package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Value
 *
 * Range 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/form/range
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Range 文档：https://aisuda.bce.baidu.com/amis/zh-CN/components/form/range")
public @interface Value {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"string"},{"$ref":"#/definitions/MultipleValue"},{"type":"number"},{"type":"array","items":{"type":"number"},"minItems":2,"maxItems":2}]
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
