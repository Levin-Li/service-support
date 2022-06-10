package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_MozUserModify
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MozUserModify")
public @interface Property_MozUserModify {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"read-only"},{"type":"string","const":"read-write"},{"type":"string","const":"write-only"}]
   *
   *
   */
    String[] consts = { "read-only", "read-write", "write-only" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
