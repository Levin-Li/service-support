package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_TextEmphasis
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_TextEmphasis")
public @interface Property_TextEmphasis {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"circle"},{"type":"string","const":"dot"},{"type":"string","const":"double-circle"},{"type":"string","const":"filled"},{"type":"string","const":"none"},{"type":"string","const":"open"},{"type":"string","const":"sesame"},{"type":"string","const":"triangle"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "circle", "dot", "double-circle", "filled", "none", "open", "sesame", "triangle" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
