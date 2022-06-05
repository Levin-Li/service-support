package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * DataType_ContentList
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:13
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "DataType_ContentList")
public @interface DataType_ContentList {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/DataType.Quote"},{"type":"string","const":"contents"},{"type":"string"}]
   */

    String[] consts = { "contents" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
