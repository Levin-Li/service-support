package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * FuncArg
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "FuncArg")
public @interface FuncArg {
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
     * type
     *
     * 参考定义: "#/definitions/FieldTypes"
     *
     * 
     *
     * 
     *
     * @see FieldTypes
     */
    
    @Schema(description = "type")
    String type() default "	";

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
    
    @Schema(description = "label")
    String label() default "	";

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

    /**
     * operators
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"type":"string"},{"type":"object","properties":{"lable":{"type":"string"},"value":{"type":"string"},"values":{"type":"array","items":{}}},"required":["lable","value"],"additionalProperties":false}]}}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "operators")
    String[] operators() default "	";

    /**
     * funcs
     *
     * 参考定义: {"type":"array","items":{"type":"string"}}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "funcs")
    String[] funcs() default "	";

    /**
     * defaultValue
     *
     * 参考定义: {}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "defaultValue")
    String defaultValue() default "	";

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
     * isOptional
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "isOptional")
    boolean isOptional() default false;

}
