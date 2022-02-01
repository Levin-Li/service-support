package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Type
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Type")
public @interface Type {

    /**
     * defaultOp
     *
     * 参考定义: "#/definitions/OperatorType"
     *
     * @see 
     */
    @Schema(description = "defaultOp")
    OperatorType defaultOp() ;

    /**
     * operators
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/OperatorType"}}
     *
     * @see 
     */
    @Schema(description = "operators")
    String[] operators() default {};

    /**
     * placeholder
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "placeholder")
    String placeholder() default "";

    /**
     * valueTypes
     *
     * 参考定义: {"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}}
     *
     * @see 
     */
    @Schema(description = "valueTypes")
    String[] valueTypes() default {};


}
