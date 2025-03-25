package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ConditionBuilderConfig
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "ConditionBuilderConfig")
public @interface ConditionBuilderConfig {
///////////////////////////////////////////

	//null
	enum ValueType{
		value,
		field,
		func,
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
     * 参考定义: {"type":"array","items":{"type":"string","enum":["value","field","func"]}}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "valueTypes")
    ValueType[] valueTypes() ;

    /**
     * fields
     *
     * 参考定义: "#/definitions/ConditionBuilderFields"
     *
     * 
     *
     * 
     *
     * @see ConditionBuilderFields
     */
    
    @Schema(title = "fields")
    String[] fields() default "	";

    /**
     * funcs
     *
     * 参考定义: "#/definitions/ConditionBuilderFuncs"
     *
     * 
     *
     * 
     *
     * @see ConditionBuilderFuncs
     */
    
    @Schema(title = "funcs")
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
    
    @Schema(title = "maxLevel")
    double maxLevel() default 0;

    /**
     * types
     *
     * 参考定义: {"type":"object","additionalProperties":{"$ref":"#/definitions/ConditionBuilderType"}}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "types")
    String types() default "	";

}
