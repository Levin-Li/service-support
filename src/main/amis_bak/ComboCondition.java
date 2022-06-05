package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * ComboCondition
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "ComboCondition")
public @interface ComboCondition {
///////////////////////////////////////////

////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * test
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "test")
    String test() default "	";

    /**
     * items
     *
     * 参考定义: "#/definitions/ComboSubControl"
     *
     * @see 
     */
    @Schema(description = "items")
    String[] items() default "	";

    /**
     * label
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "label")
    String label() default "	";

    /**
     * scaffold
     *
     * 参考定义: {}
     *
     * @see 
     */
    @Schema(description = "scaffold")
    String scaffold() default "	";

    /**
     * mode
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "mode")
    String mode() default "	";

}
