package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ConditionGroupValue
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "ConditionGroupValue")
public @interface ConditionGroupValue {
///////////////////////////////////////////

	//null
	enum Conjunction{
		and,
		or,
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
     * id
     *
     * 参考定义: {"type":"string"}
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
     * conjunction
     *
     * 参考定义: {"type":"string","enum":["and","or"]}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "conjunction")
    Conjunction conjunction() ;

    /**
     * not
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "not")
    boolean not() default false;

    /**
     * children
     *
     * 参考定义: {"type":"array","items":{"anyOf":[{"$ref":"#/definitions/ConditionRule"},{"$ref":"#/definitions/ConditionGroupValue"}]}}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "children")
    String[] children() default "	";

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
