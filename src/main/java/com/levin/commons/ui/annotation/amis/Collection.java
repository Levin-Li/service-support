package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Collection
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:11
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Collection")
public @interface Collection {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"},{"type":"array","items":{"anyOf":[{"$ref":"#/definitions/SchemaObject"},{"$ref":"#/definitions/SchemaTpl"}]}}]
   */


//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
