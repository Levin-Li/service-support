package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ConditionFieldFunc
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "ConditionFieldFunc")
public @interface ConditionFieldFunc {
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
     * type
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "type")
    String type() default "	";

    /**
     * returnType
     *
     * 参考定义: "#/definitions/FieldTypes"
     *
     * 
     *
     * 
     *
     * @see FieldTypes
     */
    
    @Schema(title = "returnType")
    String returnType() default "	";

    /**
     * args
     *
     * 参考定义: "#/definitions/ConditionBuilderFuncArg"
     *
     * 
     *
     * 
     *
     * @see ConditionBuilderFuncArg
     */
    
    @Schema(title = "args")
    ConditionBuilderFuncArg[] args() ;

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

}
