package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Type
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Type")
public @interface Type {
///////////////////////////////////////////

	//null
	enum ValueType{
		value,
		field,
		func,
		formula,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

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
     * defaultOp
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
    @Schema(description = "defaultOp")
    String defaultOp() default "	";

    /**
     * operators
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
    @Schema(description = "operators")
    String[] operators() default "	";

    /**
     * placeholder
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "placeholder")
    String placeholder() default "	";

    /**
     * valueTypes
     *
     * 参考定义: {"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "valueTypes")
    ValueType[] valueTypes() ;

}
