package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * QuickEditObject
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "QuickEditObject")
public @interface QuickEditObject {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"allOf":[{"$ref":"#/definitions/SchemaObject","patternProperties":{"^(saveImmediately|resetOnFailed|reload|mode|icon)$":{}}},{"type":"object","additionalProperties":true,"properties":{"saveImmediately":{"type":"boolean","description":"是否立即保存"},"resetOnFailed":{"type":"boolean","description":"接口保存失败后，是否重置组件编辑状态"},"reload":{"type":"string","description":"配置刷新目标，默认就会刷新所属 crud 组件， 如果不需要，请配置为 \"none\""},"mode":{"type":"string","const":"inline","description":"是否直接内嵌"},"icon":{"type":"string","description":"配置按钮图标"}}}]},{"type":"object","properties":{"saveImmediately":{"type":"boolean","description":"是否立即保存"},"resetOnFailed":{"type":"boolean","description":"接口保存失败后，是否重置组件编辑状态"},"reload":{"type":"string","description":"配置刷新目标，默认就会刷新所属 crud 组件， 如果不需要，请配置为 \"none\""},"mode":{"type":"string","const":"inline","description":"是否直接内嵌"},"icon":{"type":"string","description":"配置按钮图标"},"body":{"$ref":"#/definitions/SchemaCollection"}},"required":["body"],"additionalProperties":false}]
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
