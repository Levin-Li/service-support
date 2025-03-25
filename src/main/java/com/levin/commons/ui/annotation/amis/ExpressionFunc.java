package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ExpressionFunc
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "ExpressionFunc")
public @interface ExpressionFunc {
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
     * 参考定义: {"type":"string","const":"func"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "type")
    String type() default "func";

    /**
     * func
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "func")
    String func() default "	";

    /**
     * args
     *
     * 参考定义: "#/definitions/ExpressionComplex"
     *
     * 
     *
     * [{"$ref":"#/definitions/ExpressionValue"},{"$ref":"#/definitions/ExpressionFunc"},{"$ref":"#/definitions/ExpressionField"},{"$ref":"#/definitions/ExpressionFormula"}]
     *
     * @see ExpressionComplex
     */
    
    @Schema(title = "args")
    String[] args() default "	";

}
