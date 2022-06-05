package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * FormHorizontal
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "FormHorizontal")
public @interface FormHorizontal {
///////////////////////////////////////////

	//null
	enum LabelAlign{
		left,
		right,
		;

		@Override
		public String toString() { return super.toString().replace('_', '-'); }
	}

////////////////////////////////////////////

  /**
   * Any Of
   * 
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * left
     *
     * 参考定义: {"type":"number"}
     *
     * @see 
     */
    @Schema(description = "left")
    double left() default 0;

    /**
     * right
     *
     * 参考定义: {"type":"number"}
     *
     * @see 
     */
    @Schema(description = "right")
    double right() default 0;

    /**
     * leftFixed
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"number"},{"type":"string","const":"xs"},{"type":"string","const":"sm"},{"type":"string","const":"md"},{"type":"string","const":"lg"}]}
     *
     * @see 
     */
    @Schema(description = "leftFixed")
    boolean leftFixed() default false;

    /**
     * justify
     *
     * 参考定义: {"type":"boolean"}
     *
     * @see 
     */
    @Schema(description = "justify")
    boolean justify() default false;

    /**
     * labelAlign
     *
     * 参考定义: {"type":"string","enum":["left","right"]}
     *
     * @see 
     */
    @Schema(description = "labelAlign")
    LabelAlign labelAlign() ;

}
