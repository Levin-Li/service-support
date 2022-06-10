package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_ObjectFit
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_ObjectFit")
public @interface Property_ObjectFit {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"contain"},{"type":"string","const":"cover"},{"type":"string","const":"fill"},{"type":"string","const":"none"},{"type":"string","const":"scale-down"}]
   *
   *
   */
    String[] consts = { "contain", "cover", "fill", "none", "scale-down" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
