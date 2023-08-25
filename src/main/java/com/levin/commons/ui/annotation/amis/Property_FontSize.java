package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_FontSize
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_FontSize")
public @interface Property_FontSize {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.AbsoluteSize"},{"type":"string"},{"type":"number"},{"type":"string","const":"larger"},{"type":"string","const":"smaller"}]
   *
   *
   */
    String[] consts = { "larger", "smaller" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
