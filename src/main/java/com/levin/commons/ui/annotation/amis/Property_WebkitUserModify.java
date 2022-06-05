package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_WebkitUserModify
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_WebkitUserModify")
public @interface Property_WebkitUserModify {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"read-only"},{"type":"string","const":"read-write"},{"type":"string","const":"read-write-plaintext-only"}]
   */

    String[] consts = { "read-only", "read-write", "read-write-plaintext-only" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
