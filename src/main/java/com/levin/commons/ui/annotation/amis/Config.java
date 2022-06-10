package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Config
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Config")
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
     * fields
     *
     * 参考定义: "#/definitions/Fields"
     *
     * 
     *
     * 
     *
     * @see Fields
     */
    
    @Schema(description = "fields")
    String[] fields() default "	";

    /**
     * funcs
     *
     * 参考定义: "#/definitions/Funcs"
     *
     * 
     *
     * 
     *
     * @see Funcs
     */
    
    @Schema(description = "funcs")
    String[] funcs() default "	";

    /**
     * maxLevel
     *
     * 参考定义: {"type":"number"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "maxLevel")
    double maxLevel() default 0;

    /**
     * types
     *
     * 参考定义: {"type":"object","additionalProperties":{"$ref":"#/definitions/Type"}}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "types")
    String types() default "	";

}
