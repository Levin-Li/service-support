package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * CustomField
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "CustomField")
public @interface CustomField {
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
     * type
     *
     * 参考定义: {"type":"string","const":"custom"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "type")
    String type() default "custom";

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
    
    @Schema(title = "operators")
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
    
    @Schema(title = "funcs")
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
    
    @Schema(title = "defaultValue")
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
    
    @Schema(title = "placeholder")
    String placeholder() default "	";

    /**
     * name
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "name")
    String name() default "	";

    /**
     * value
     *
     * 参考定义: {}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "value")
    String value() default "	";

}
