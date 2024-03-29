package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ListenerAction
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:01
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "ListenerAction")
public @interface ListenerAction {
///////////////////////////////////////////

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
    
    @Schema(description = "actionType")
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
    
    @Schema(description = "description")
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
    
    @Schema(description = "componentId")
    String componentId() default "	";

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
    
    @Schema(description = "args")
    String args() default "	";

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
    
    @Schema(description = "outputVar")
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
    
    @Schema(description = "preventDefault")
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
    
    @Schema(description = "stopPropagation")
    boolean stopPropagation() default false;

    /**
     * expression
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(description = "expression")
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
    
    @Schema(description = "execOn")
    String execOn() default "	";

}
