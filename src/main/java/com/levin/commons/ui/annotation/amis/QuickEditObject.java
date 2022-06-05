package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * QuickEditObject
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "QuickEditObject")
public @interface QuickEditObject {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"allOf":[{"$ref":"#/definitions/SchemaObject","patternProperties":{"^(saveImmediately|resetOnFailed|mode)$":{}}},{"type":"object","additionalProperties":true,"properties":{"saveImmediately":{"type":"boolean","description":"是否立即保存"},"resetOnFailed":{"type":"boolean","description":"接口保存失败后，是否重置组件编辑状态"},"mode":{"type":"string","const":"inline","description":"是否直接内嵌"}}}]},{"type":"object","properties":{"saveImmediately":{"type":"boolean","description":"是否立即保存"},"resetOnFailed":{"type":"boolean","description":"接口保存失败后，是否重置组件编辑状态"},"mode":{"type":"string","const":"inline","description":"是否直接内嵌"},"body":{"$ref":"#/definitions/SchemaCollection"}},"required":["body"],"additionalProperties":false}]
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
