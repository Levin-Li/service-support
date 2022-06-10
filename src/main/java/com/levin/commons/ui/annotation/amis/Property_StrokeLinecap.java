package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_StrokeLinecap
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_StrokeLinecap")
public @interface Property_StrokeLinecap {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"butt"},{"type":"string","const":"round"},{"type":"string","const":"square"}]
   *
   *
   */
    String[] consts = { "butt", "round", "square" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
