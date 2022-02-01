package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * FieldGroup
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "FieldGroup")
public @interface FieldGroup {

    /**
     * label
     *
     * 参考定义: {"type":"string"}
     *
     * @see 
     */
    @Schema(description = "label")
    String label() default "";

    /**
     * children
     *
     * 参考定义: {"type":"array","items":{"$ref":"#/definitions/FieldSimple"}}
     *
     * @see 
     */
    @Schema(description = "children")
    String[] children() default {};


}
