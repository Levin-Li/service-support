package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ConditionRule
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "ConditionRule")
public @interface ConditionRule {
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
     * id
     *
     * 参考定义: {}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "id")
    String id() default "	";

    /**
     * left
     *
     * 参考定义: "#/definitions/ExpressionComplex"
     *
     * 
     *
     * [{"$ref":"#/definitions/ExpressionValue"},{"$ref":"#/definitions/ExpressionFunc"},{"$ref":"#/definitions/ExpressionField"},{"$ref":"#/definitions/ExpressionFormula"}]
     *
     * @see ExpressionComplex
     */
    
    @Schema(title = "left")
    String left() default "	";

    /**
     * op
     *
     * 参考定义: "#/definitions/OperatorType"
     *
     * 
     *
     * [{"type":"string","const":"equal"},{"type":"string","const":"not_equal"},{"type":"string","const":"is_empty"},{"type":"string","const":"is_not_empty"},{"type":"string","const":"like"},{"type":"string","const":"not_like"},{"type":"string","const":"starts_with"},{"type":"string","const":"ends_with"},{"type":"string","const":"less"},{"type":"string","const":"less_or_equal"},{"type":"string","const":"greater"},{"type":"string","const":"greater_or_equal"},{"type":"string","const":"between"},{"type":"string","const":"not_between"},{"type":"string","const":"select_equals"},{"type":"string","const":"select_not_equals"},{"type":"string","const":"select_any_in"},{"type":"string","const":"select_not_any_in"},{"type":"object","properties":{"label":{"type":"string"},"value":{"type":"string"}},"required":["label","value"],"additionalProperties":false}]
     *
     * @see OperatorType
     */
    @Const({"equal", "not_equal", "is_empty", "is_not_empty", "like", "not_like", "starts_with", "ends_with", "less", "less_or_equal", "greater", "greater_or_equal", "between", "not_between", "select_equals", "select_not_equals", "select_any_in", "select_not_any_in"})
    @Schema(title = "op")
    String op() default "	";

    /**
     * right
     *
     * 参考定义: {"anyOf":[{"$ref":"#/definitions/ExpressionComplex"},{"type":"array","items":{"$ref":"#/definitions/ExpressionComplex"}}]}
     *
     * [{"$ref":"#/definitions/ExpressionComplex"},{"type":"array","items":{"$ref":"#/definitions/ExpressionComplex"}}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "right")
    String right() default "	";

    /**
     * if
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "if")
    String _if() default "	";

}
