package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * Config
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-1 16:13:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Config")
public @interface Config {

    /**
     * valueTypes
     *
     * 参考定义: {"type":"array","items":{"type":"string","enum":["value","field","func","formula"]}}
     *
     * @see 
     */
    @Schema(description = "valueTypes")
    String[] valueTypes() default {};

    /**
     * fields
     *
     * 参考定义: "#/definitions/Fields"
     *
     * @see 
     */
    @Schema(description = "fields")
    String[] fields() default {};

    /**
     * funcs
     *
     * 参考定义: "#/definitions/Funcs"
     *
     * @see 
     */
    @Schema(description = "funcs")
    String[] funcs() default {};

    /**
     * maxLevel
     *
     * 参考定义: {"type":"number"}
     *
     * @see 
     */
    @Schema(description = "maxLevel")
    double maxLevel() default 0;

    /**
     * types
     *
     * 参考定义: {"type":"object","additionalProperties":{"$ref":"#/definitions/Type"}}
     *
     * @see 
     */
    @Schema(description = "types")
    String types() default "";


}
