package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * VariableItem
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "VariableItem")
public @interface VariableItem {
///////////////////////////////////////////

	//null
	enum SelectMode{
		tree,
		tabs,
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
     * value
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "value")
    String value() default "	";

    /**
     * path
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "path")
    String path() default "	";

    /**
     * children
     *
     * 参考定义: "#/definitions/VariableItem"
     *
     * 
     *
     * 
     *
     * @see VariableItem
     */
    
    @Schema(title = "children")
    String[] children() default "	";

    /**
     * type
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "type")
    String type() default "	";

    /**
     * tag
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "tag")
    String tag() default "	";

    /**
     * selectMode
     *
     * 参考定义: {"type":"string","enum":["tree","tabs"]}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "selectMode")
    SelectMode selectMode() ;

    /**
     * isMember
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "isMember")
    boolean isMember() default false;

}
