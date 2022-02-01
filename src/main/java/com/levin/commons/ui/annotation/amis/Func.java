package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Func
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Func")
public @interface Func {

    /**
     * type
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "type")
    String type() default "";

    /**
     * returnType
     *
     * 参考定义: "#/definitions/FieldTypes"
     *
     * @see 
     */
    @Schema(description = "returnType")
    FieldTypes returnType() ;

    /**
     * args
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/FuncArg"}}
     *
     * @see 
     */
    @Schema(description = "args")
    String[] args() default {};

    /**
     * label
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "label")
    String label() default "";


}
