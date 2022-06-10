package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * FieldSimple
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "FieldSimple")
public @interface FieldSimple {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"type":"object","properties":{"type":{"type":"string","const":"text"},"label":{"type":"string"},"valueTypes":{"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}},"operators":{"type":"array","items":{"anyOf":[{"type":"string"},{"type":"object","properties":{"lable":{"type":"string"},"value":{"type":"string"},"values":{"type":"array","items":{}}},"required":["lable","value"],"additionalProperties":false}]}},"funcs":{"type":"array","items":{"type":"string"}},"defaultValue":{},"placeholder":{"type":"string"},"name":{"type":"string"},"minLength":{"type":"number"},"maxLength":{"type":"number"}},"required":["label","name","type"],"additionalProperties":false},{"type":"object","properties":{"type":{"type":"string","const":"number"},"label":{"type":"string"},"valueTypes":{"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}},"operators":{"type":"array","items":{"anyOf":[{"type":"string"},{"type":"object","properties":{"lable":{"type":"string"},"value":{"type":"string"},"values":{"type":"array","items":{}}},"required":["lable","value"],"additionalProperties":false}]}},"funcs":{"type":"array","items":{"type":"string"}},"defaultValue":{},"placeholder":{"type":"string"},"name":{"type":"string"},"maximum":{"type":"number"},"minimum":{"type":"number"},"step":{"type":"number"},"precision":{"type":"number"}},"required":["label","name","type"],"additionalProperties":false},{"type":"object","properties":{"type":{"type":"string","const":"date"},"label":{"type":"string"},"valueTypes":{"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}},"operators":{"type":"array","items":{"anyOf":[{"type":"string"},{"type":"object","properties":{"lable":{"type":"string"},"value":{"type":"string"},"values":{"type":"array","items":{}}},"required":["lable","value"],"additionalProperties":false}]}},"funcs":{"type":"array","items":{"type":"string"}},"defaultValue":{},"placeholder":{"type":"string"},"name":{"type":"string"},"format":{"type":"string"},"inputFormat":{"type":"string"},"minDate":{},"maxDate":{}},"required":["label","name","type"],"additionalProperties":false},{"type":"object","properties":{"type":{"type":"string","const":"time"},"label":{"type":"string"},"valueTypes":{"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}},"operators":{"type":"array","items":{"anyOf":[{"type":"string"},{"type":"object","properties":{"lable":{"type":"string"},"value":{"type":"string"},"values":{"type":"array","items":{}}},"required":["lable","value"],"additionalProperties":false}]}},"funcs":{"type":"array","items":{"type":"string"}},"defaultValue":{},"placeholder":{"type":"string"},"name":{"type":"string"},"minTime":{},"maxTime":{},"format":{"type":"string"},"inputFormat":{"type":"string"}},"required":["label","name","type"],"additionalProperties":false},{"type":"object","properties":{"type":{"type":"string","const":"datetime"},"label":{"type":"string"},"valueTypes":{"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}},"operators":{"type":"array","items":{"anyOf":[{"type":"string"},{"type":"object","properties":{"lable":{"type":"string"},"value":{"type":"string"},"values":{"type":"array","items":{}}},"required":["lable","value"],"additionalProperties":false}]}},"funcs":{"type":"array","items":{"type":"string"}},"defaultValue":{},"placeholder":{"type":"string"},"name":{"type":"string"},"format":{"type":"string"},"inputFormat":{"type":"string"},"timeFormat":{"type":"string"}},"required":["label","name","type"],"additionalProperties":false},{"type":"object","properties":{"type":{"type":"string","const":"select"},"label":{"type":"string"},"valueTypes":{"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}},"operators":{"type":"array","items":{"anyOf":[{"type":"string"},{"type":"object","properties":{"lable":{"type":"string"},"value":{"type":"string"},"values":{"type":"array","items":{}}},"required":["lable","value"],"additionalProperties":false}]}},"funcs":{"type":"array","items":{"type":"string"}},"defaultValue":{},"placeholder":{"type":"string"},"name":{"type":"string"},"multiple":{"type":"boolean"},"options":{"type":"array","items":{}},"source":{"$ref":"#/definitions/SchemaApi"},"searchable":{"type":"boolean"},"autoComplete":{"$ref":"#/definitions/SchemaApi","description":"自动完成 API，当输入部分文字的时候，会将这些文字通过 ${term} 可以取到，发送给接口。 接口可以返回匹配到的选项，帮助用户输入。"}},"required":["label","name","type"],"additionalProperties":false},{"type":"object","properties":{"type":{"type":"string","const":"boolean"},"label":{"type":"string"},"valueTypes":{"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}},"operators":{"type":"array","items":{"anyOf":[{"type":"string"},{"type":"object","properties":{"lable":{"type":"string"},"value":{"type":"string"},"values":{"type":"array","items":{}}},"required":["lable","value"],"additionalProperties":false}]}},"funcs":{"type":"array","items":{"type":"string"}},"defaultValue":{},"placeholder":{"type":"string"},"name":{"type":"string"}},"required":["label","name","type"],"additionalProperties":false},{"type":"object","properties":{"type":{"type":"string","const":"custom"},"label":{"type":"string"},"valueTypes":{"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}},"operators":{"type":"array","items":{"anyOf":[{"type":"string"},{"type":"object","properties":{"lable":{"type":"string"},"value":{"type":"string"},"values":{"type":"array","items":{}}},"required":["lable","value"],"additionalProperties":false}]}},"funcs":{"type":"array","items":{"type":"string"}},"defaultValue":{},"placeholder":{"type":"string"},"name":{"type":"string"},"value":{"$ref":"#/definitions/BaseSchema"}},"required":["label","name","type","value"],"additionalProperties":false}]
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
