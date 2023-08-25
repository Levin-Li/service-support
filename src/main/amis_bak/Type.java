package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Type
 *
 *
 *
 * @author auto gen by service-support at 2022-2-10 12:04:43
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Type")
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
    *
    */
   String value() default "	";

    /**
     * defaultOp
     *
     * 参考定义: "#/definitions/OperatorType"
     *
     * @see
     */
    @Schema(title = "defaultOp")
    String defaultOp() default "	";

    /**
     * operators
     *
     * 参考定义: "#/definitions/OperatorType"
     *
     * @see
     */
    @Schema(title = "operators")
    String[] operators() default "	";

    /**
     * placeholder
     *
     * 参考定义: {"type":"string"}
     *
     * @see
     */
    @Schema(title = "placeholder")
    String placeholder() default "	";

    /**
     * valueTypes
     *
     * 参考定义: {"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}}
     *
     * @see
     */
    @Schema(title = "valueTypes")
    ValueType[] valueTypes() ;

}
