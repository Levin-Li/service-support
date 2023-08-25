package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Config
 *
 *
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Config")
public @interface Config {
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
     * valueTypes
     *
     * 参考定义: {"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}}
     *
     * @see
     */
    @Schema(title = "valueTypes")
    ValueType[] valueTypes() ;

    /**
     * fields
     *
     * 参考定义: "#/definitions/Fields"
     *
     * @see
     */
    @Schema(title = "fields")
    String[] fields() default "	";

    /**
     * funcs
     *
     * 参考定义: "#/definitions/Funcs"
     *
     * @see
     */
    @Schema(title = "funcs")
    String[] funcs() default "	";

    /**
     * maxLevel
     *
     * 参考定义: {"type":"number"}
     *
     * @see
     */
    @Schema(title = "maxLevel")
    double maxLevel() default 0;

    /**
     * types
     *
     * 参考定义: {"type":"object","additionalProperties":{"$ref":"#/definitions/Type"}}
     *
     * @see
     */
    @Schema(title = "types")
    String types() default "	";

}
