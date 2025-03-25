package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * ImageToolbarAction
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:50
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "ImageToolbarAction")
public @interface ImageToolbarAction {
///////////////////////////////////////////

	//null
	enum Key{
		ROTATE_RIGHT,
		ROTATE_LEFT,
		ZOOM_IN,
		ZOOM_OUT,
		SCALE_ORIGIN,
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
     * key
     *
     * 参考定义: {"type":"string","enum":["ROTATE_RIGHT","ROTATE_LEFT","ZOOM_IN","ZOOM_OUT","SCALE_ORIGIN"]}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "key")
    Key key() ;

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
     * icon
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "icon")
    String icon() default "	";

    /**
     * iconClassName
     *
     * 参考定义: {"type":"string"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "iconClassName")
    String iconClassName() default "	";

    /**
     * disabled
     *
     * 参考定义: {"type":"boolean"}
     *
     * 
     *
     * 
     *
     * @see 
     */
    
    @Schema(title = "disabled")
    boolean disabled() default false;

}
