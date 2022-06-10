package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_MsScrollSnapType
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MsScrollSnapType")
public @interface Property_MsScrollSnapType {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"mandatory"},{"type":"string","const":"none"},{"type":"string","const":"proximity"}]
   *
   *
   */
    String[] consts = { "mandatory", "none", "proximity" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
