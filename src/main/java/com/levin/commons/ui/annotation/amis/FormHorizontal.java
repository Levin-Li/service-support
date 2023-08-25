package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * FormHorizontal
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "FormHorizontal")
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
   *
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
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "left")
    double left() default 0;

    /**
     * right
     *
     * 参考定义: {"type":"number"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "right")
    double right() default 0;

    /**
     * leftFixed
     *
     * 参考定义: {"anyOf":[{"type":"boolean"},{"type":"number"},{"type":"string","const":"xs"},{"type":"string","const":"sm"},{"type":"string","const":"md"},{"type":"string","const":"lg"}]}
     *
     * [{"type":"boolean"},{"type":"number"},{"type":"string","const":"xs"},{"type":"string","const":"sm"},{"type":"string","const":"md"},{"type":"string","const":"lg"}]
     *
     *
     *
     * @see
     */

    @Schema(title = "leftFixed")
    String leftFixed() default "	";

    /**
     * justify
     *
     * 参考定义: {"type":"boolean"}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "justify")
    boolean justify() default false;

    /**
     * labelAlign
     *
     * 参考定义: {"type":"string","enum":["left","right"]}
     *
     *
     *
     *
     *
     * @see
     */

    @Schema(title = "labelAlign")
    LabelAlign labelAlign() ;

}
