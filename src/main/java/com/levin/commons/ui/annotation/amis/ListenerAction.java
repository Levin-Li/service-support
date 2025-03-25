package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ListenerAction
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "ListenerAction")
public @interface ListenerAction {
///////////////////////////////////////////

	//null
	enum DataMergeMode{
		merge,
		override,
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
     * actionType
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "actionType")
    String actionType() default "	";

    /**
     * description
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "description")
    String description() default "	";

    /**
     * componentId
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "componentId")
    String componentId() default "	";

    /**
     * componentName
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "componentName")
    String componentName() default "	";

    /**
     * ignoreError
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "ignoreError")
    boolean ignoreError() default false;

    /**
     * args
     *
     * 参考定义: {"type":"object"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "args")
    String args() default "	";

    /**
     * data
     *
     * 参考定义: {"anyOf":[{"type":"object"},{"type":"null"}]}
     *
     * [{"type":"object"},{"type":"null"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "data")
    String data() default "	";

    /**
     * dataMergeMode
     *
     * 参考定义: {"type":"string","enum":["merge","override"]}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "dataMergeMode")
    DataMergeMode dataMergeMode() ;

    /**
     * outputVar
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "outputVar")
    String outputVar() default "	";

    /**
     * preventDefault
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "preventDefault")
    boolean preventDefault() default false;

    /**
     * stopPropagation
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "stopPropagation")
    boolean stopPropagation() default false;

    /**
     * expression
     *
     * 参考定义: {"anyOf":[{"type":"string"},{"$ref":"#/definitions/ConditionGroupValue"}]}
     *
     * [{"type":"string"},{"$ref":"#/definitions/ConditionGroupValue"}]
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "expression")
    String expression() default "	";

    /**
     * execOn
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "execOn")
    String execOn() default "	";

}
