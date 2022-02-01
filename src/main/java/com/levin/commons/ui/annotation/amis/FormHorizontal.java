package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * FormHorizontal
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "FormHorizontal")
public @interface FormHorizontal {

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


}
