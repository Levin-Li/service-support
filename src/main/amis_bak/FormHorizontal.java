package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * FormHorizontal
 *
 *
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "FormHorizontal")
public @interface FormHorizontal {
///////////////////////////////////////////

////////////////////////////////////////////
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
    @Schema(title = "left")
    double left() default 0;

    /**
     * right
     *
     * 参考定义: {"type":"number"}
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
     * @see
     */
    @Schema(title = "leftFixed")
    boolean leftFixed() default false;

}
