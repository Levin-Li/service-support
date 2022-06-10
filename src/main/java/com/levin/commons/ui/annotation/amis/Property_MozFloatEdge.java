package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_MozFloatEdge
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_MozFloatEdge")
public @interface Property_MozFloatEdge {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"border-box"},{"type":"string","const":"content-box"},{"type":"string","const":"margin-box"},{"type":"string","const":"padding-box"}]
   *
   *
   */
    String[] consts = { "border-box", "content-box", "margin-box", "padding-box" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
