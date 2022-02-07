package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * FuncArg
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-7 23:06:29
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
    *
    */
   String value() default "";

    /**
     * type
     *
     * 参考定义: "#/definitions/FieldTypes"
     *
     * @see 
     */
    @Schema(description = "type")
    String type() default "";

    /**
     * label
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "label")
    String label() default "";

    /**
     * valueTypes
     *
     * 参考定义: {"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}}
     *
     * @see 
     */
    @Schema(description = "valueTypes")
    ValueType[] valueTypes() ;

    /**
     * operators
     *
     * 参考定义: {"type":"array","items":{"type":"string"}}
     *
     * @see 
     */
    @Schema(description = "operators")
    String[] operators() default "";

    /**
     * funcs
     *
     * 参考定义: {"type":"array","items":{"type":"string"}}
     *
     * @see 
     */
    @Schema(description = "funcs")
    String[] funcs() default "";

    /**
     * defaultValue
     *
     * 参考定义: {}
     *
     * @see 
     */
    @Schema(description = "defaultValue")
    String defaultValue() default "";

    /**
     * placeholder
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "placeholder")
    String placeholder() default "";

    /**
     * isOptional
     *
     * 参考定义: {"type":"boolean"}
     *
     * @see 
     */
    @Schema(description = "isOptional")
    boolean isOptional() default false;

}
