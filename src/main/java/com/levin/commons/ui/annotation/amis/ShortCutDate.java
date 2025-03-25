package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ShortCutDate
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "ShortCutDate")
public @interface ShortCutDate {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * 
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * label
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "label")
    String label() default "	";

    /**
     * 支持表达式
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/moment.Moment"},{"type":"string"}],"description":"支持表达式"}
     *
     * [{"$ref":"#/definitions/moment.Moment"},{"type":"string"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "支持表达式")
    String date() default "	";

}
