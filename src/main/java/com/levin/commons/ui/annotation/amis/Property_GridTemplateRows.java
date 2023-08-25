package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * Property_GridTemplateRows
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "Property_GridTemplateRows")
public @interface Property_GridTemplateRows {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.TrackBreadth%3C(string%7Cnumber)%3E"},{"type":"string","const":"none"},{"type":"string","const":"subgrid"},{"type":"string"}]
   *
   *
   */
    String[] consts = { "none", "subgrid" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
